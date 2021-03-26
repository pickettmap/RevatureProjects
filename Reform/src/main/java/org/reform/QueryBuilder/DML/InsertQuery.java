package org.reform.QueryBuilder.DML;

import org.reform.QueryBuilder.GenericQuery;
import org.reform.util.TypeMappingUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class InsertQuery<T> implements GenericQuery {

    private T t = null;
    final String insert = "insert into ";
    private int numFields;

    public InsertQuery (T t) {
        this.t = t;
        this.numFields = t.getClass().getDeclaredFields().length;
    }

    @Override
    public String genericStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(insert);
        sb.append(t.getClass().getSimpleName().toLowerCase(Locale.ROOT)).append(" (");
        Field[] fields = t.getClass().getDeclaredFields();
        for(int i = 0; i < numFields-1; i++) {
            sb.append(fields[i].getName()).append(", ");
        }
        sb.append(fields[numFields-1].getName());
        sb.append(") values(");
        for(int i = 0; i < numFields-1; i++) {
            sb.append("?, ");
        }
        sb.append("?)");

        return sb.toString();
    }

}
