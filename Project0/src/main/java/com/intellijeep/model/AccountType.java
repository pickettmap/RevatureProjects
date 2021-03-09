// Resource for enum inversion solution: https://www.javaspecialists.eu/archive/Issue113-Enum-Inversion-Problem.html

package com.intellijeep.model;

public enum AccountType {
    UNREGISTERED,
    USER,
    CUSTOMER,
    EMPLOYEE,
    ADMIN;

    public static AccountType convert(int value) {
        switch (value) {
            case 0:
                return UNREGISTERED;
            case 1:
                return USER;
            case 2:
                return CUSTOMER;
            case 3:
                return EMPLOYEE;
            case 4:
                return ADMIN;
            default:
                return null;
        }
    }
}