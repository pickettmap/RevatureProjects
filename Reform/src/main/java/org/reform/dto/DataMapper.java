package org.reform.dto;

import org.reform.QueryBuilder.GenericQuery;
import org.reform.QueryBuilder.GenericQueryFactory;
import org.reform.gateway.DataGateway;
import org.reform.metadata.QueryType;

public class DataMapper<T>{

    private T t;
    private DataGateway<T> dg;
    private GenericQueryFactory gqf = GenericQueryFactory.getInstance();
    private int id;
    GenericQuery query;

    public DataMapper(T t) {
        this.t = t;
        this.dg = new DataGateway<T>();
        this.id = dg.getIdByObjectValues(t);
    }

    public void save() {
        query = gqf.getGenericQuery(t, QueryType.INSERT);
        dg.initializeGenericQuery(t, query, id);
        dg.doSomething();
    }

    public T getById(int id) throws Exception {

        return dg.selectSingleObject(t, id);
    }

    public int getId() {
        return id;
    }

    public void update(){
        query = gqf.getGenericQuery(t, QueryType.UPDATE);
        dg.initializeGenericQuery(t, query, id);
        dg.doSomething();
    }

    public void delete(){
        dg.deleteObject(t, this.id);
    }

    public void delete(int id){
        dg.deleteObject(t, id);
    }

}
