package org.reform.exception;

public class DefaultConstructorException extends InstantiationException{
    public DefaultConstructorException() {
        super("No default constructor found, please provide one");
    }
}
