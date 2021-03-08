package com.intellijeep.model;

public class Payment {
    private int customerID;
    private int monthlyAmount;
    private int loanAmount;
    private int paymentTerm;
    private int paymentRemaining;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(int monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public int getPaymentRemaining() {
        return paymentRemaining;
    }

    public void setPaymentRemaining(int paymentRemaining) {
        this.paymentRemaining = paymentRemaining;
    }
}
