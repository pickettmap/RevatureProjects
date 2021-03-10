package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.model.Car;
import com.intellijeep.model.CarStatus;
import com.intellijeep.model.User;
import com.intellijeep.util.IntelliJeepArrayList;

public class CustomerService {

    private CarDao carDao;

    public CustomerService(CarDao carDao) {
        this.carDao = carDao;
    }

    public void viewLotCars(){
        System.out.println(carDao.getByStatus(CarStatus.ON_LOT));
    }

    public void viewOwnedCars(User u) {
        IntelliJeepArrayList<Car> ownedCars = new IntelliJeepArrayList<Car>(Car.class, 0);
        
    }

    public void makeOffer(int CarID){

    }


}
