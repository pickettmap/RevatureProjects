package org.reform.metadata;

import org.reform.util.TypeMappingUtil;

import java.lang.reflect.Field;
import java.util.*;

public class TableSchema<T> {
    private Class c;
    private String tableName;
    private HashSet<ColumnData> columnSchemas;
    private HashMap<Class, Boolean> childClasses;
    private HashSet<Class> parentClasses;
    private HashSet<EntityCardinality> childCardinalities;
    private Boolean inTheDatabase = false;


    /**
     * Takes in an a class and maps the class's attributes to columns
     * If class has reference to another class, a foreign key is made for the referenced class.
     * @param c
     */
    public TableSchema(Class c) {
        this.c = c;
        this.tableName = c.getSimpleName().toLowerCase(Locale.ROOT);
        this.columnSchemas  = new HashSet<ColumnData>();
        this.childClasses = new HashMap<Class,Boolean>();
        this.parentClasses = new HashSet<Class>();
        this.childCardinalities = new HashSet<EntityCardinality>();

        createColumns();
        detectChildReferences();
//        detectChildCardinality();
    }

    public Class getTableClass(){return c;}

    public String getTableName(){return tableName;}

    public Field[] getFields() {
        return c.getDeclaredFields();
    }

    /**
     * Looks through all of the attributes in a class regardless of access modifier
     * Creates columns based on the attribute's name and type
     */
    private void createColumns() {
         for(Field f : getFields()) {
             String fieldName = f.getName();
             Class<?> fieldType = f.getType();
             ColumnData c = new ColumnData(fieldName, fieldType);
             columnSchemas.add(c);
         }
    }

    public Set<ColumnData> getColumns() {
        return columnSchemas;
    }

    public ColumnData getColumn(Class c) {
        for(ColumnData c1 : columnSchemas) {
            if(c.equals(c1)) {
                return c1;
            }
        }
        return null;
    }


    public Boolean hasChildEntities() {
        return !childClasses.isEmpty();
    }

    //Returns false if a child table hasn't been created/visited
    //Returns true if all of the table's children have been created
    public Boolean allVisitedChildEntities() {
        for (Map.Entry mapElement : childClasses.entrySet()) {
            if(mapElement.getValue().equals(false)) {
                return false;
            }
        }
        return true;
    }

    public void setVisited(Class c) {
        childClasses.put(c, true);
    }

    public HashSet<Class> getParentClasses() {return  parentClasses;
    }

    public Set<Class> getChildClasses() {return (Set<Class>) childClasses.keySet();}

    public void addForeignKey(Class c) {
        parentClasses.add(c);
    }

    /**
     * Looks through all of the fields in the class.
     * If the field is not a primitive or wrapper class (including String),
     * it is assumed that it holds a reference to another class and a foreign key is created.
     */

    private void detectChildReferences() {
        for(Field f :getFields()) {
            f.setAccessible(true);
            Class c = f.getType();
            if(!TypeMappingUtil.isPrimitiveType(c)) {
                childClasses.put(c, false);
            }
        }
    }

    private void detectChildCardinality() {
        for(Field f : getFields()) {
            Class c = f.getType();
            EntityCardinality e = new EntityCardinality();

            if(!TypeMappingUtil.isPrimitiveType(c)) {
                e.setChildClass(c);
                if (!TypeMappingUtil.isCollection(c)) {
                    e.setChildCardinality(CardinalityType.ONE);
                } else {
                    e.setChildCardinality(CardinalityType.MANY);
                }
                childCardinalities.add(e);
            }
        }
    }

    //TODO: Clean up to string
    @Override
    public String toString() {
        return tableName + " table: " + "\n" +
                " Columns: " + columnSchemas + "\n" +
                " Parent classes: " + parentClasses + "\n" +
                " Child classes: " + childClasses;
    }

    public Boolean isInTheDatabase() {
        return inTheDatabase;
    }

    public void setInTheDatabase(Boolean inTheDatabase) {
        this.inTheDatabase = inTheDatabase;
    }
}