package com.intellijeep.model;

public class Car implements Comparable<Car> {
    private int carID;
    private CarStatus carStatus;
    private String model;
    private int modelYear;

    public Car(){}
    public Car(int carID, CarStatus carStatus, String model, int modelYear) {
        this.carID = carID;
        this.carStatus = carStatus;
        this.model = model;
        this.modelYear = modelYear;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    @Override
    public int compareTo(Car c) {
        return carID-c.getCarID();
    }

    @Override
    public String toString() {
        return "[" +
                "carID=" + carID +
                ", carStatus=" + carStatus +
                ", model='" + model + '\'' +
                ", modelYear=" + modelYear +
                ']';
    }
}