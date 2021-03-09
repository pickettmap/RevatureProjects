package com.intellijeep.model;

public enum CarStatus {
    ON_LOT,
    OFF_LOT,
    CUSTOMER_OWNED,
    PENDING;

    public static CarStatus convert(int value) {
        switch (value) {
            case 0:
                return ON_LOT;
            case 1:
                return OFF_LOT;
            case 2:
                return CUSTOMER_OWNED;
            case 3:
                return PENDING;
            default:
                return null;
        }
    }
}
