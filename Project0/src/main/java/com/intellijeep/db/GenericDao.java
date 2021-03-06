package com.intellijeep.db;


import com.intellijeep.util.IntelliJeepArrayList;

/**
 * A dao contract to determine functionality in the DealershipDao objects
 * @param <T> Class used for this dao object
 */
public interface GenericDao<T>{

    int save(T t);

    T getByID(Integer id);

    IntelliJeepArrayList<T> getAll();

    Boolean remove(Integer id);

    Boolean update(T t);

    int updateAll(IntelliJeepArrayList<T> collection);
}
