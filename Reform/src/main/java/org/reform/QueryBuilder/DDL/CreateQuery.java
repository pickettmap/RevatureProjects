package org.reform.QueryBuilder.DDL;

import org.reform.connection.PropertyConfig;
import org.reform.metadata.ColumnData;
import org.reform.metadata.TableSchema;
import org.reform.util.SqlUtil;

public class CreateQuery {

    public static String createSqlTable(TableSchema t) {
        PropertyConfig config = new PropertyConfig();
        config.setProfile("postgres");
        StringBuilder sb = new StringBuilder();
        if(config.getProfile().equals("postgres")) {
            sb.append("create table if not exists " + "cool_stuff."+t.getTableName() + "(id serial primary key, ");
        } else {
            sb.append("create table if not exists " + t.getTableName() + "(id int auto_increment primary key, ");
        }
        t.getColumns().stream().forEach(col -> sb.append(" " + SqlUtil.sqlColumnFormatting((ColumnData) col)));
        sb.deleteCharAt(sb.toString().length() - 2);
        sb.append(")");
        return sb.toString();
    }
}
