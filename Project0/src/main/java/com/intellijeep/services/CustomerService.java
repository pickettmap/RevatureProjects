package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.model.Car;
import com.intellijeep.model.CarStatus;
import com.intellijeep.model.User;

public class CustomerService {

    private CarDao carDao;

    public CustomerService(CarDao carDao) {
        this.carDao = carDao;
    }

    public void viewLotCars(){
        System.out.println(carDao.getByStatus(CarStatus.ON_LOT));
    }

    public void viewOwnedCars(User u) {
        //View customer car
    }

    public void makeOffer(int CarID){

    }


}
