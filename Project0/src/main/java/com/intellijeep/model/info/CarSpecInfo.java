package com.intellijeep.model.info;

public class CarSpecInfo {
    private String model;
    private int modelYear;

    public CarSpecInfo(String model, int modelYear) {
        this.model = model;
        this.modelYear = modelYear;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getModel() {
        return model;
    }

    public int getModelYear() {
        return modelYear;
    }
}
