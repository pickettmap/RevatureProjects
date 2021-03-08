package com.intellijeep.db;

public class DaoFactory {

    public static GenericDao createDao(Class c) {
        switch (c.getName()) {
            case "com.intellijeep.model.User":
                return UserDao.getInstance();
            case "com.intellijeep.model.Car":
                return CarDao.getInstance();
            case "com.intellijeep.model.Offer":
                return OfferDao.getInstance();
            case "com.intellijeep.model.Payment":
                return PaymentDao.getInstance();
            default:
                throw new IllegalArgumentException("The class provided does not have a corresponding dao object");
        }
    }
}
