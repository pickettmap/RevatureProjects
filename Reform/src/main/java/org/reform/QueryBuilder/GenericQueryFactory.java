package org.reform.QueryBuilder;

import org.reform.QueryBuilder.DML.DeleteQuery;
import org.reform.QueryBuilder.DML.InsertQuery;
import org.reform.QueryBuilder.DML.SelectQuery;
import org.reform.QueryBuilder.DML.UpdateQuery;
import org.reform.metadata.QueryType;

public class GenericQueryFactory<T> {

    public static GenericQueryFactory instance;

    private GenericQueryFactory(){}

    public static GenericQueryFactory getInstance() {
        if(instance == null) {
            instance = new GenericQueryFactory();
        }

        return instance;
    }

    public GenericQuery getGenericQuery(T t, QueryType qt) {
        switch(qt) {
            case UPDATE:
                return new UpdateQuery(t);
            case INSERT:
                return new InsertQuery(t);
            case SELECT:
                return new SelectQuery(t);
            case DELETE:
                return new DeleteQuery(t);
            default:
                return null;
        }
    }
}
