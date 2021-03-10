package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.Car;
import com.intellijeep.model.CarStatus;

public class CarService {

    private CarDao carDao;

    public CarService() {
        this.carDao = (CarDao) DaoFactory.createDao(Car.class);
    }

    public Car getCarByID(int carID) {
        return carDao.getByID(carID);
    }

    //TODO: create car getByID
    public Boolean doesCarExist(Integer carID){
        if(getCarByID(carID) != null) {
            return true;
        }
        return false;
    }
}
