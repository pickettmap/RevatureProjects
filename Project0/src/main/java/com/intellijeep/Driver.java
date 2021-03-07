package com.intellijeep;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.*;
import com.intellijeep.services.UserService;

public class Driver {
    public static void main(String[] args) {

        UserService us = new UserService(DaoFactory.createDao(User.class));
        us.makeUser(new UserAccountInfo(1,"fat fucking jujortsu","password",AccountType.CUSTOMER),
                new UserPersonalInfo("yue","ren", "ren@gmail.com","123"),
                new UserLocationInfo("yo","chicago","il","123"));
        us.showUsers();
    }
}
