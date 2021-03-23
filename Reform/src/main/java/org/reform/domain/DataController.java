package org.reform.domain;

public class DataController {

    private Class c;
    private DataMapper dm;

    DataController(Object o) {
        this.c = o.getClass();
    }
}
