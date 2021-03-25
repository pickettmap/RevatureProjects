package org.reform.QueryBuilder.DDL;

import org.reform.metadata.ColumnData;
import org.reform.metadata.TableSchema;
import org.reform.util.SqlUtil;

public class CreateQuery {

    public static String createSqlTable(TableSchema t) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists " + t.getTableName() + "(id int auto_increment, ");

        t.getColumns().stream().forEach(col -> sb.append(" " + SqlUtil.sqlColumnFormatting((ColumnData) col)));
        sb.deleteCharAt(sb.toString().length() - 2);
        sb.append(")");
        return sb.toString();
    }
}
