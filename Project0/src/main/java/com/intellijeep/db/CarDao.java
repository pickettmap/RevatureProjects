package com.intellijeep.db;

import com.intellijeep.model.Car;
import com.intellijeep.util.IntelliJeepCollection;

public class CarDao implements GenericDao<Car>{

    public static CarDao instance;

    private CarDao(){}

    static CarDao getInstance(){
        if(instance==null) {
            instance = new CarDao();
        }

        return instance;
    }


    @Override
    public int save(Car car) {
        return 0;
    }

    @Override
    public Car getByID(Integer id) {
        return null;
    }

    @Override
    public IntelliJeepCollection<Car> getAll() {
        return null;
    }

    @Override
    public Boolean remove(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Car car) {
        return null;
    }

    @Override
    public int updateAll(IntelliJeepCollection<Car> collection) {
        return 0;
    }
}
