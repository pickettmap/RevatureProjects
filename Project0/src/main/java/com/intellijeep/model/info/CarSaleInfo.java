package com.intellijeep.model.info;

import com.intellijeep.model.CarStatus;
import com.intellijeep.model.Offer;

public class CarSaleInfo {
    private int vin;
    private CarStatus carStatus;
    private Offer[] offers;

    public CarSaleInfo(int vin, CarStatus carStatus) {
        this.vin = vin;
        this.carStatus = carStatus;
        this.offers = new Offer[0];
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
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
                "vin=" + vin +
                ", carStatus=" + carStatus +
                '}';
    }
}
