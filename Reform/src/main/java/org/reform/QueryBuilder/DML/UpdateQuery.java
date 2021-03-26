package org.reform.QueryBuilder.DML;

import org.reform.QueryBuilder.GenericQuery;
import org.reform.metadata.QueryType;
import org.reform.util.SqlUtil;
import org.reform.util.TypeMappingUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class UpdateQuery<T> implements GenericQuery {

    private T t = null;
    private String tableName;
    private final String starter;
    private int numFields;

    public UpdateQuery(T t) {
        this.t = t;
        this.numFields = t.getClass().getDeclaredFields().length;
        this.tableName = t.getClass().getSimpleName().toLowerCase(Locale.ROOT);
        starter = SqlUtil.getBeginningStatement(tableName, QueryType.UPDATE);
    }

    @Override
    public String genericStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(starter);
        Field[] fields = t.getClass().getDeclaredFields();
        for(int i = 0; i < numFields-1; i++) {
            sb.append(fields[i].getName()).append(" = ?, ");
        }
        sb.append(fields[numFields-1].getName() + " = ? ");
        sb.append(" where id = ?");

        return sb.toString();
    }

}
