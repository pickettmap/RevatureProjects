package org.reform.dto;

import org.reform.QueryBuilder.DDL.CreateQuery;
import org.reform.QueryBuilder.GenericQueryFactory;
import org.reform.metadata.DatabaseSchema;
import org.reform.metadata.TableSchema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DatabaseMapper<T> {

    private ArrayList<TableSchema> ts;
    private DatabaseSchema ds;
    private GenericQueryFactory gqf = GenericQueryFactory.getInstance();

    public DatabaseMapper(){
        this.ds = new DatabaseSchema();
    }

    public static HashSet<String> createTableQueries(){
        HashSet queries = new HashSet();

        while(!DatabaseSchema.dataBasePersisted()) {
            for (TableSchema t : DatabaseSchema.getTables()) {
                if(t.isInTheDatabase()){
                    break;
                }
                if((!t.hasChildEntities() & !t.isInTheDatabase()) || t.allVisitedChildEntities()) {
                    queries.add(CreateQuery.createSqlTable(t));
                    t.setInTheDatabase(true);
                    DatabaseSchema.updateParents(t.getTableClass());
                }
            }

        }
        return queries;
    }

    //need to call drop table
    //need to call alter table
    //need to call truncate table

}
