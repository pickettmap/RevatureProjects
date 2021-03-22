package org.deform.metadata;

import org.deform.util.TypeMappingUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

public class TableSchema<T> {
    private Class c;
    private String tableName;
    private ArrayList<ColumnData> columnSchemas;
    private ArrayList<Class> referencedClasses;
    private ArrayList<ForeignKeySchema> foreignKeys;


    /**
     * Takes in an a class and maps the class's attributes to columns
     * If class has reference to another class, a foreign key is made for the referenced class.
     * @param c
     */
    public TableSchema(Class c) {
        this.c = c;
        this.tableName = c.getSimpleName().toLowerCase(Locale.ROOT);
        this.columnSchemas  = new ArrayList<>();
        this.referencedClasses = new ArrayList<>();
        this.foreignKeys = new ArrayList<>();

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


    public Boolean hasReferencedEntities() {
        return !referencedClasses.isEmpty();
    }

    public ArrayList<Class> getReferencedClasses() {return referencedClasses;}

    public void addForeignKey(ForeignKeySchema fk) {
        foreignKeys.add(fk);
    }

    /**
     * Looks through all of the fields in the class.
     * If the field is not a primitive or wrapper class (including String),
     * it is assumed that it holds a reference to another class and a foreign key is created.
     */
    private void detectTableReferences() {
        for(Field f :getFields()) {
            if(!TypeMappingUtil.isPrimitiveType(f.getType())) {
                Class c = f.getType();
                referencedClasses.add(c);
            }
        }
    }

    //TODO: Clean up to string
    @Override
    public String toString() {
        return tableName + " table: " +
                " Columns: " + columnSchemas +
                " Referenced Classes: " + referencedClasses +
                " Foreign Keys: " + foreignKeys;
    }
}