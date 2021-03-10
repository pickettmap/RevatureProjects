package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.Car;
import com.intellijeep.model.info.CarSaleInfo;
import com.intellijeep.model.info.CarSpecInfo;

public class CarService {

    private CarDao carDao;

    public CarService() {
        this.carDao = (CarDao) DaoFactory.createDao(Car.class);
    }

    public Car carCreation(CarSaleInfo carSaleInfo, CarSpecInfo carSpecInfo) {
        Car c = new Car.CarBuilder()
                .carSaleInfo(carSaleInfo)
                .carSpecInfo(carSpecInfo)
                .build();
        return c;
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
