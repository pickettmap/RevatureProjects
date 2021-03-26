package org.reform.dto;

import org.reform.QueryBuilder.GenericQuery;
import org.reform.QueryBuilder.GenericQueryFactory;
import org.reform.gateway.DataGateway;
import org.reform.metadata.QueryType;

import java.util.concurrent.*;

public class DataMapper<T>{

    private T t;
    private DataGateway<T> dg;
    private GenericQueryFactory gqf = GenericQueryFactory.getInstance();
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private int id;
    GenericQuery query;

    public DataMapper(){}

    public DataMapper(T t) {
        this.t = t;
        this.dg = new DataGateway<T>();
        this.id = dg.getIdByObjectValues(t);
    }

    public void setObject(T t){
        this.t = t;
        this.dg = new DataGateway<T>();
        this.id = dg.getIdByObjectValues(t);
    }

    public Class getMappedClass() {
        return this.t.getClass();
    }

    public void save() {
        query = gqf.getGenericQuery(t, QueryType.INSERT);
        dg.initializeGenericQuery(t, query);
        executorService.execute(() ->dg.execute());
    }

    public T getById(int id){
        try{
            Future<T> future = executorService.submit(() -> dg.selectSingleObject(t, id));
            return (T) future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        Future<Integer> future = executorService.submit(() -> dg.getIdByObjectValues(t));
        try {
            this.id = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void update(){
        query = gqf.getGenericQuery(t, QueryType.UPDATE);
        dg.initializeGenericQuery(t, query, this.id);
        executorService.execute(() ->dg.execute());
    }

    public void delete(){
        executorService.execute(() ->  dg.deleteObject(t, this.id));
    }

    public void delete(int id){
        executorService.execute(() ->  dg.deleteObject(t, id));
    }

    public void finishSession() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

}
