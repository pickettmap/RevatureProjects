package com.intellijeep.model;

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

        public CarBuilder carSaleInfoBuilder (CarSaleInfo carSaleInfo) {
            car.carSaleInfo = carSaleInfo;
            return this;
        }

        public CarBuilder carSpecInfoBuilder (CarSpecInfo carSpecInfo) {
            car.carSpecInfo = carSpecInfo;
            return this;
        }

        public Car build() {
            return car;
        }
    }

    @Override
    public int compareTo(Car c) {
        return carSaleInfo.getVin()-c.carSaleInfo.getVin();
    }
}
