package org.deform.metadata;

import org.deform.annotations.Entity;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Set;

public class DatabaseSchema {
    private Reflections reflections;
    private static Set<Class<?>> classes;
    private static ArrayList<TableSchema> tables;
    private ArrayList<Class> referencingClasses = new ArrayList<>();

    public DatabaseSchema(){
        this.reflections = new Reflections();
        this.classes = reflections.getTypesAnnotatedWith(Entity.class);
        this.tables = new ArrayList<TableSchema>();
        populateDatabase();
        this.referencingClasses = getReferencingClasses();
        populateForeignKeys();
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


    public ArrayList<Class> getReferencingClasses() {
        ArrayList<Class> referencingClasses = new ArrayList<>();

        for (TableSchema t : tables) {
            if (t.hasReferencedEntities()) {
                referencingClasses.add(t.getTableClass());
            }
        }
        System.out.println(referencingClasses);
        return referencingClasses;
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
        ArrayList<Class> referencedClasses = new ArrayList<>();

        for (Class c : referencingClasses) {
            for(int i = 0; i < tables.size(); i++) {
                if(tables.get(i).getTableClass().equals(c)) {
                    referencedClasses = tables.get(i).getReferencedClasses();

                    for(Class c2 : referencedClasses) {
                        insertForeignKey(c, c2);
                    }
                }
            }
        }
    }

    public void populateForeignKeys() {
        getReferencedClasses();
    }

}
