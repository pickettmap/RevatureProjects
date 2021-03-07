package com.intellijeep.model;

import java.util.Objects;

public class Offer {
    private int offerID;
    private int carID;
    private int customerID;
    private int amount;
    private OfferStatus offerStatus;

    public Offer(int offerID, int carID, int customerID, int amount, OfferStatus offerStatus) {
        this.offerID = offerID;
        this.carID = carID;
        this.customerID = customerID;
        this.amount = amount;
        this.offerStatus = offerStatus;
    }

    public int getOfferID() {
        return offerID;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return offerID == offer.offerID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerID);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerID=" + offerID +
                ", carID=" + carID +
                ", customerID=" + customerID +
                ", amount=" + amount +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
