package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.Car;
import com.intellijeep.model.User;
import com.intellijeep.model.info.CarSaleInfo;
import com.intellijeep.model.info.CarSpecInfo;
import com.intellijeep.util.IntelliJeepArrayList;

//X As an employee, I can add a car to the lot.
//X As an employee, I can accept or reject an offer for a car.
//X As an employee, I can remove a car from the lot.
//X As an employee, I can view all payments.


public class EmployeeService {
    private CarDao carDao;

    public EmployeeService() {
        this.carDao = (CarDao) DaoFactory.createDao(Car.class);
    }

    public int addCarToLot(Car car) {
        return carDao.save(car);
    }

    
}
