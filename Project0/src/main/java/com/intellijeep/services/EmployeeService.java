package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.model.Car;
import com.intellijeep.model.User;
import com.intellijeep.model.info.CarSaleInfo;
import com.intellijeep.model.info.CarSpecInfo;
import com.intellijeep.util.IntelliJeepArrayList;

public class EmployeeService {
    private CarDao carDao;

    public EmployeeService(CarDao carDao) {
        this.carDao = carDao;
    }

    public int addCarToLot(Car car) {
        return carDao.save(car);
    }

    
}
