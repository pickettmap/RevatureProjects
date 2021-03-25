package org.reform.util;

import org.reform.metadata.ColumnData;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SqlUtil {

    public static String sqlColumnFormatting(ColumnData c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c.getName().toLowerCase(Locale.ROOT) + " ");
        sb.append(convertDataTypeToSql(c.getType()));
        sb.append(", ");
        return sb.toString();
    }

    private static final Map<String, String> SQL_TYPE_MAP;
    static {
        SQL_TYPE_MAP = new HashMap<String, String>();
        SQL_TYPE_MAP.put("string", "text");
        SQL_TYPE_MAP.put("boolean", "bit");
        SQL_TYPE_MAP.put("int", "int");
        SQL_TYPE_MAP.put("integer","int"); //for class
        SQL_TYPE_MAP.put("long", "long");
        SQL_TYPE_MAP.put("float", "real");
        SQL_TYPE_MAP.put("double", "float");
    }

    public static String convertDataTypeToSql(Class c) {
        //TODO: see if you can work in foreign key
        if(!TypeMappingUtil.isPrimitiveType(c)) {
            return "text";
        }
        else{
            return SQL_TYPE_MAP.get(c.getSimpleName().toLowerCase(Locale.ROOT));
        }
    }
}
