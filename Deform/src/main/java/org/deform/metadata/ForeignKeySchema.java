package org.deform.metadata;

public class ForeignKeySchema {
    private Class referencedClass;

    public ForeignKeySchema(Class referencedClass) {
        this.referencedClass = referencedClass;
    }

    public Class getForeignKeyClass() {
        return referencedClass;
    }

    //TODO: Clean up to string
    @Override
    public String toString() {
        return "ForeignKey{" +
                "referencedClass=" + referencedClass.getName() +
                '}';
    }
}