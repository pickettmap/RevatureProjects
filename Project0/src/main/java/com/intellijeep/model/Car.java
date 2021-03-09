package com.intellijeep.model;

import com.intellijeep.model.info.CarSaleInfo;
import com.intellijeep.model.info.CarSpecInfo;

public class Car implements Comparable <Car>{
    private CarSaleInfo carSaleInfo;
    private CarSpecInfo carSpecInfo;

    public CarSaleInfo getCarSaleInfo() {
        return carSaleInfo;
    }

    public CarSpecInfo getCarSpecInfo() {
        return carSpecInfo;
    }

    public static class CarBuilder{
        Car car = new Car();

        public CarBuilder(){}

        public CarBuilder carSaleInfo(CarSaleInfo carSaleInfo) {
            car.carSaleInfo = carSaleInfo;
            return this;
        }

        public CarBuilder carSpecInfo(CarSpecInfo carSpecInfo) {
            car.carSpecInfo = carSpecInfo;
            return this;
        }

        public Car build() {
            return car;
        }
    }

    @Override
    public int compareTo(Car c) {
        return carSaleInfo.getCarID()-c.carSaleInfo.getCarID();
    }

    @Override
    public String toString() {
        return "Car{" +
                "carSaleInfo=" + carSaleInfo +
                ", carSpecInfo=" + carSpecInfo +
                '}';
    }
}
