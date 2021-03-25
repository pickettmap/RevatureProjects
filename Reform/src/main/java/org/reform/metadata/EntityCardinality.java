package org.reform.metadata;

import java.lang.reflect.Type;

public class EntityCardinality {
    private Class childClass;
    private CardinalityType childCardinality;

    public EntityCardinality(){}

    public EntityCardinality( Class childClass, CardinalityType childCardinality) {
        this.childClass = childClass;
        this.childCardinality = childCardinality;
    }

    public Class getChildClass() {
        return childClass;
    }

    public void setChildClass(Class childClass) {
        this.childClass = childClass;
    }

    public CardinalityType getChildCardinality() {
        return childCardinality;
    }

    public void setChildCardinality(CardinalityType childCardinality) {
        this.childCardinality = childCardinality;
    }
}
