package org.reform.gateway;

import org.reform.metadata.DatabaseSchema;
import org.reform.metadata.TableSchema;

import java.util.ArrayList;

public class DatabaseGateway {

    private ArrayList<TableSchema> ts;

    DatabaseGateway() {
        ts = DatabaseSchema.getTables();
    }

    private void createTables(){

    }
//
//    private void
}
