package com.intellijeep.model.info;

import com.intellijeep.model.CarStatus;
import com.intellijeep.model.Offer;

public class CarSaleInfo {
    private int carID;
    private CarStatus carStatus;

    public CarSaleInfo(int carID, CarStatus carStatus) {
        this.carID = carID;
        this.carStatus = carStatus;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return "CarSaleInfo{" +
                "carID=" + carID +
                ", carStatus=" + carStatus +
                '}';
    }
}
