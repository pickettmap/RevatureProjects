package org.reform.gateway;

import org.reform.metadata.ColumnData;
import org.reform.metadata.DatabaseSchema;
import org.reform.metadata.TableSchema;
import org.reform.util.SqlUtil;

import java.util.ArrayList;

public class DatabaseGateway {

    private ArrayList<TableSchema> ts;

    private void createAllTables(){
        ts = DatabaseSchema.getTables();
        //while there are still tables to be created (aka unvisited child entities)
        while(DatabaseSchema.dataBasePersisted()) {
            for (TableSchema t : ts) {
                if (!t.hasChildEntities()) {
                    //create
                    //mark as visited in parent
                }
            }
        }
    }
//todo: unstatic
    public static String createTable(TableSchema t) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists " + t.getTableName() + "(id int auto_increment, ");

        t.getColumns().stream().forEach(col -> sb.append(" " + SqlUtil.sqlColumnFormatting((ColumnData) col)));
        sb.deleteCharAt(sb.toString().length() - 2);
        sb.append(")");
        return sb.toString();
    }
}
