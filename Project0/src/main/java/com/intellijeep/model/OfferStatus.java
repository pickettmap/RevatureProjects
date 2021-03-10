package com.intellijeep.model;

public enum OfferStatus {
    NONE,
    ACCEPTED,
    REJECTED,
    PENDING;

    public static OfferStatus convert(int value) {
        switch (value) {
            case 0:
                return NONE;
            case 1:
                return ACCEPTED;
            case 2:
                return REJECTED;
            case 3:
                return PENDING;
            default:
                return null;
        }
    }
}
