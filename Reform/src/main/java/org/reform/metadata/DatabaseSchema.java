package org.reform.metadata;

import org.reform.annotations.Entity;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Set;

public class DatabaseSchema {
    private Reflections reflections;
    private static Set<Class<?>> classes;
    private static ArrayList<TableSchema> tables;
    private ArrayList<Class> parentClasses = new ArrayList<>();

    public DatabaseSchema(){
        this.reflections = new Reflections();
        this.classes = reflections.getTypesAnnotatedWith(Entity.class);
        this.tables = new ArrayList<TableSchema>();
        populateDatabase();
        this.parentClasses = getParentClasses();
        getReferencedClasses();
    }

    public static ArrayList<TableSchema> getTables(){
        return tables;
    }

    public void printClasses(){
        System.out.println(classes);
    }

    public void printTables(){
        System.out.println(tables);
    }

    /**
     * Creates table schemas for every class marked with @Entity
     */
    private void populateDatabase(){
        for(Class c : classes) {
            TableSchema t = new TableSchema(c);
            tables.add(t);
        }
    }


    public ArrayList<Class> getParentClasses() {
        ArrayList<Class> parentClasses = new ArrayList<>();

        for (TableSchema t : tables) {
            if (t.hasChildEntities()) {
                parentClasses.add(t.getTableClass());
            }
        }
        System.out.println(parentClasses);
        return parentClasses;
    }

    //this should only look at through referencing classes
    private void insertForeignKey(Class c1, Class c2) {
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getTableClass().equals(c2)) {
                ForeignKeySchema fk = new ForeignKeySchema(c1);
                tables.get(i).addForeignKey(fk);
                break;
            }
        }
    }


    //TODO: This is disgusting, don't show this in public
    private void getReferencedClasses(){
        Set<Class> childClasses;

        for (Class c : parentClasses) {
            for(int i = 0; i < tables.size(); i++) {
                if(tables.get(i).getTableClass().equals(c)) {
                    //childClasses = tables.get(i).getReferencedClasses();
                    childClasses = tables.get(i).getReferencedClasses().keySet();
                    for(Class c2 : childClasses) {
                        insertForeignKey(c, c2);
                    }
                }
            }
        }
    }

}
