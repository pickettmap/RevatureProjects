package org.reform.util;

import java.lang.reflect.Field;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementUtil<T> {
    private T t;
    private Integer id = -1;

    public PreparedStatementUtil(T t) {
        this.t = t;
    }

    public PreparedStatementUtil(T t, Integer id) {
        this.t = t;
        this.id = id;
    }

    public PreparedStatement setPreparedStatement(PreparedStatement ps) throws IllegalAccessException, SQLException {
        int index = 1;

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (!TypeMappingUtil.isPrimitiveType(f.get(t).getClass())) {
                //TODO: try to add foreign key reference to pet id
                ps.setObject(index, f.get(t).getClass().getSimpleName());
            } else {
                ps.setObject(index, f.get(t));
            }
            index++;
        }
        ParameterMetaData pmd = ps.getParameterMetaData();
        if(pmd.getParameterCount() > fields.length) {
            ps.setInt(index,id);
        }
        return ps;
    }
}
