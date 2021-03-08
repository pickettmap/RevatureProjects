package com.intellijeep.model.info;

import com.intellijeep.model.CarStatus;
import com.intellijeep.model.Offer;

public class CarSaleInfo {
    private int carID;
    private CarStatus carStatus;
    private Offer[] offers;

    public CarSaleInfo(int carID, CarStatus carStatus) {
        this.carID = carID;
        this.carStatus = carStatus;
        this.offers = new Offer[0];
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

    public Offer[] getOffers() {
        return offers;
    }

    public void setOffers(Offer[] offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "CarSaleInfo{" +
                "carID=" + carID +
                ", carStatus=" + carStatus +
                '}';
    }
}
