package com.intellijeep.db;


import com.intellijeep.util.IntelliJeepCollection;

/**
 * A dao contract to determine functionality in the DealershipDao objects
 * @param <T> Class used for this dao object
 * @param <I> Primary key used by the class
 */
public interface GenericDao<T, I>{

    int save(T t);

    T getByID(I id);

    IntelliJeepCollection getAll();

    Boolean remove(I id);

    Boolean update(T t);

    int updateAll(IntelliJeepCollection collection);
}
