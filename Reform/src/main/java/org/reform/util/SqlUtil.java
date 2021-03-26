package org.reform.util;

import org.reform.metadata.ColumnData;
import org.reform.metadata.QueryType;
import org.reform.metadata.TableSchema;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public static String getBeginningStatement(String tableName, QueryType queryType) {
        StringBuilder sb = new StringBuilder();

        switch (queryType){
            case DROP:
                sb.append("drop table if exists" + tableName+ " ");
                break;
            case ALTER:
                sb.append("alter table " + tableName + " ");
                break;
            case CREATE:
                sb.append("create table if not exists " + tableName + "(id int auto_increment primary key, ");
                break;
            case DELETE:
                sb.append("delete from " + tableName + " where ");
                break;
            case INSERT:
                sb.append("insert into " + tableName + " (");
                break;
            case SELECT:
                sb.append("select * from " + tableName + " ");
                break;
            case UPDATE:
                sb.append("update " + tableName + " set ");
                break;
            case TRUNCATE:
                sb.append("truncate table " + tableName);
                break;
            default:
                return "No matching query type found";
        }
    return sb.toString();
    }
}
