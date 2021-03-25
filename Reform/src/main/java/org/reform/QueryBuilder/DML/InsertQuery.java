package org.reform.QueryBuilder.DML;

import org.reform.util.TypeMappingUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class InsertQuery<T> {

    private PreparedStatement ps = null;
    private T t = null;
    final String insert = "insert into ";
    private int numFields;

    public InsertQuery(PreparedStatement ps, T t) {
        this.ps = ps;
        this.t = t;
        this.numFields = t.getClass().getDeclaredFields().length;
    }

    public InsertQuery (T t) {
        this.t = t;
        this.numFields = t.getClass().getDeclaredFields().length;
    }

    public String generalSqlStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append(insert);
        sb.append(t.getClass().getSimpleName().toLowerCase(Locale.ROOT)+ " (");
        Field[] fields = t.getClass().getDeclaredFields();
        for(int i = 0; i < numFields-1; i++) {
            sb.append(fields[i].getName()+", ");
        }
        sb.append(fields[numFields-1].getName());
        sb.append(") values(");
        for(int i = 0; i < numFields-1; i++) {
            sb.append("?, ");
        }
        sb.append("?)");

        return sb.toString();
    }

    public PreparedStatement makePreparedStatement(PreparedStatement ps) throws IllegalAccessException, SQLException {
        int index = 1;

        //TODO: check field types
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if(!TypeMappingUtil.isPrimitiveType(f.get(t).getClass())) {
                //TODO: try to add foreign key reference to pet id
                ps.setObject(index, f.get(t).getClass().getSimpleName());
            }
            else {
                ps.setObject(index, f.get(t));
            }
            index++;
        }
        return ps;
    }


}
