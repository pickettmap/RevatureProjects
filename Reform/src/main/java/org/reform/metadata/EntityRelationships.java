package org.reform.metadata;

import java.lang.reflect.Type;

public class EntityRelationships {
    private Type parent;
    private CardinalityType cardinalityType;
    private Type child;

    public EntityRelationships(Type parent, CardinalityType cardinalityType, Type child) {
        this.parent = parent;
        this.cardinalityType = cardinalityType;
        this.child = child;
    }

    public Type getParent() {
        return parent;
    }

    public void setParent(Type parent) {
        this.parent = parent;
    }

    public CardinalityType getCardinalityType() {
        return cardinalityType;
    }

    public void setCardinalityType(CardinalityType cardinalityType) {
        this.cardinalityType = cardinalityType;
    }

    public Type getChild() {
        return child;
    }

    public void setChild(Type child) {
        this.child = child;
    }
}
