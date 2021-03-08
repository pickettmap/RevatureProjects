// Resource for enum inversion solution: https://www.javaspecialists.eu/archive/Issue113-Enum-Inversion-Problem.html

package com.intellijeep.model;

import com.intellijeep.util.EnumConverter;
import com.intellijeep.util.ReverseEnumMap;

public enum AccountType implements EnumConverter<AccountType> {
    UNREGISTERED(0),
    USER(1),
    CUSTOMER(2),
    EMPLOYEE(3),
    ADMIN(5);

    private static ReverseEnumMap<AccountType> map = new ReverseEnumMap<AccountType>(AccountType.class);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    @Override
    public int convert() {
        return value;
    }

    public AccountType convert(int val) {
        return map.get(val);
    }
}