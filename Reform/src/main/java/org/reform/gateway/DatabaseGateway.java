package org.reform.gateway;

import org.reform.QueryBuilder.DDL.CreateQuery;
import org.reform.metadata.ColumnData;
import org.reform.metadata.DatabaseSchema;
import org.reform.metadata.TableSchema;
import org.reform.util.SqlUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DatabaseGateway {

    private ArrayList<TableSchema> ts;
    private DatabaseSchema ds;

    public DatabaseGateway(){
        this.ds = new DatabaseSchema();
    }
    //move this into the mapping layer
    public HashSet<String> createAllTables(){
        HashSet<String> queries = new HashSet();
        Set<Class> childClasses;

        while(!DatabaseSchema.dataBasePersisted()) {
            for (TableSchema t : DatabaseSchema.getTables()) {
                if(t.getInTheDatabase()){
                    break;
                }
                if((!t.hasChildEntities() & !t.getInTheDatabase()) || t.allVisitedChildEntities()) {
                    queries.add(CreateQuery.createSqlTable(t));
                    System.out.println(CreateQuery.createSqlTable(t));
                    t.setInTheDatabase(true);
                    DatabaseSchema.updateParents(t.getTableClass());
                }
            }

        }

        return queries;
    }

}
