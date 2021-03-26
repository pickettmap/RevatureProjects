package org.reform.QueryBuilder.DML;

import org.reform.QueryBuilder.GenericQuery;

import java.util.Locale;

public class DeleteQuery<T> implements GenericQuery {

    private T t = null;
    private String tableName;

    public DeleteQuery(T t) {
        this.t = t;
        this.tableName = t.getClass().getSimpleName().toLowerCase(Locale.ROOT);
    }

    @Override
    public String genericStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from " + tableName + " where id = ?");
        return sb.toString();
    }
}
