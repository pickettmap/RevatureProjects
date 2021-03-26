package org.reform.QueryBuilder.DML;

import org.reform.QueryBuilder.GenericQuery;
import org.reform.metadata.QueryType;
import org.reform.util.SqlUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Locale;

public class SelectQuery<T> implements GenericQuery {

    private T t = null;
    private int numFields;
    private String tableName;
    String starter = "";

    public SelectQuery (T t) {
        this.t = t;
        this.numFields = t.getClass().getDeclaredFields().length;
        this.tableName = t.getClass().getSimpleName().toLowerCase(Locale.ROOT);
        this.starter  = SqlUtil.getBeginningStatement(tableName, QueryType.SELECT);
    }

    public SelectQuery(Class c) {
        this.numFields = c.getDeclaredFields().length;
        this.tableName = c.getSimpleName().toLowerCase(Locale.ROOT);
        this.starter = SqlUtil.getBeginningStatement(tableName, QueryType.SELECT);
    }

    public String selectAllFromTable(){
        return starter;
    }

    public String selectOneFromTable() {
        return starter + "where id=?";
    }

    @Override
    public String genericStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("select id from " + tableName + " where ");
        Field[] fields = t.getClass().getDeclaredFields();
        for(int i = 0; i < numFields-1; i++) {
            sb.append(fields[i].getName()).append(" = ? and ");
        }
        sb.append(fields[numFields-1].getName() + " = ? ");

        return sb.toString();
    }
}
