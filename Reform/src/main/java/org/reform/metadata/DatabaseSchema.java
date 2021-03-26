package org.reform.metadata;

import org.reform.annotation.Entity;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DatabaseSchema {
    private Reflections reflections;
    private static Set<Class<?>> classes;
    private static ArrayList<TableSchema> tables;
    private HashSet<Class> parentClasses;

    public DatabaseSchema(){
        this.reflections = new Reflections();
        this.classes = reflections.getTypesAnnotatedWith(Entity.class);
        this.tables = new ArrayList<TableSchema>();
        populateDatabase();
        this.parentClasses = addParentClasses();
        getReferencedClasses();
    }

    public static ArrayList<TableSchema> getTables(){
        return tables;
    }

    public void printClasses(){
        System.out.println(classes);
    }

    public void printTables(){
        StringBuilder sb = new StringBuilder();
        for(TableSchema t : tables) {
            sb.append(t.toString());
            sb.append("\n");
        }
        System.out.println(sb.toString());
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

    private static TableSchema findTableByClass(Class c) {
        for(TableSchema t : tables) {
            if(t.getTableClass().equals(c)) {
                return t;
            }
        }
        return null;
    }

    public static void updateParents(Class clazz){
        TableSchema child = findTableByClass(clazz);
        HashSet<Class> result = child.getParentClasses();

        for(Class c : result) {
            TableSchema parent = findTableByClass(c);
            parent.setVisited(clazz);
        }
    }



    public HashSet<Class> addParentClasses() {
        HashSet<Class> parentClasses = new HashSet<>();

        for (TableSchema t : tables) {
            if (t.hasChildEntities()) {
                parentClasses.add(t.getTableClass());
            }
        }
        return parentClasses;
    }


    private void insertForeignKey(Class c1, Class c2) {
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getTableClass().equals(c2)) {
                tables.get(i).addForeignKey(c1);
                break;
            }
        }
    }

    private void getReferencedClasses(){
        Set<Class> childClasses;

        for (Class c : parentClasses) {
            for(int i = 0; i < tables.size(); i++) {
                if(tables.get(i).getTableClass().equals(c)) {
                    childClasses = tables.get(i).getChildClasses();
                    for(Class c2 : childClasses) {
                        insertForeignKey(c, c2);
                    }
                }
            }
        }
    }

    public static boolean dataBasePersisted() {
        for(TableSchema t : tables) {
            if(!t.isInTheDatabase()) {
                return false;
            }
        }
        return true;
    }
}
