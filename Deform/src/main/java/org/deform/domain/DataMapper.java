package org.deform.domain;

import java.util.Optional;

public interface DataMapper<T> {

    Optional<T> find (int i);
    void insert(T t);
    void update(T t);
    void delete(T t);
}
