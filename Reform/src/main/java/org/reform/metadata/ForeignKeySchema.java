package org.reform.metadata;

public class ForeignKeySchema {
    private Class childClass;

    public ForeignKeySchema(Class childClass) {
        this.childClass = childClass;
    }

    public Class getForeignKeyClass() {
        return childClass;
    }

    //TODO: Clean up to string
    @Override
    public String toString() {
        return "ForeignKey{" +
                "childClass=" + childClass.getName() +
                '}';
    }
}