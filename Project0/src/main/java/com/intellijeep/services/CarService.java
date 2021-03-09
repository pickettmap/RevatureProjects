package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.model.Car;
import com.intellijeep.model.info.CarSaleInfo;
import com.intellijeep.model.info.CarSpecInfo;

public class CarService {
    public Car carCreation(CarSaleInfo carSaleInfo, CarSpecInfo carSpecInfo) {
        Car c = new Car.CarBuilder()
                .carSaleInfo(carSaleInfo)
                .carSpecInfo(carSpecInfo)
                .build();
        return c;
    }
}
