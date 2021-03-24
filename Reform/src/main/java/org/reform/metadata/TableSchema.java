package org.reform.metadata;

import org.reform.util.TypeMappingUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TableSchema<T> {
    private Class c;
    private static String tableName;
    private static ArrayList<ColumnData> columnSchemas;
    private static HashMap<Class, Boolean> childClasses;
    private static ArrayList<ForeignKeySchema> parentClasses;


    /**
     * Takes in an a class and maps the class's attributes to columns
     * If class has reference to another class, a foreign key is made for the referenced class.
     * @param c
     */
    public TableSchema(Class c) {
        this.c = c;
        this.tableName = c.getSimpleName().toLowerCase(Locale.ROOT);
        this.columnSchemas  = new ArrayList<>();
        this.childClasses = new HashMap<Class,Boolean>();
        this.parentClasses = new ArrayList<>();

        createColumns();
        detectTableReferences();
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

    public ArrayList<ColumnData> getColumns() {
        return columnSchemas;
    }

    public ColumnData getColumn(Class c) {
        for(ColumnData c1 : columnSchemas) {
            if(c.equals(1)) {
                return c1;
            }
        }
        return null;
    }


    public Boolean hasChildEntities() {
        return !childClasses.isEmpty();
    }

    public Map<Class, Boolean> getReferencedClasses() {return childClasses;}

    public void addForeignKey(ForeignKeySchema fk) {
        parentClasses.add(fk);
    }

    /**
     * Looks through all of the fields in the class.
     * If the field is not a primitive or wrapper class (including String),
     * it is assumed that it holds a reference to another class and a foreign key is created.
     */

    //TODO: table should store cardinality, not column
    private void detectTableReferences() {
        for(Field f :getFields()) {
            Class c = f.getType();
            if(!TypeMappingUtil.isPrimitiveType(c)) {
                childClasses.put(c, false);
            }
        }
    }



    //TODO: Clean up to string
    @Override
    public String toString() {
        return tableName + " table: " +
                " Columns: " + columnSchemas +
                " Referenced Classes: " + childClasses +
                " Foreign Keys: " + parentClasses;
    }
}