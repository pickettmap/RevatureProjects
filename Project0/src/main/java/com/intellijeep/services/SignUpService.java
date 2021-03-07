package com.intellijeep.services;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.User;

public class SignUpService {
    private UserService us = new UserService(DaoFactory.createDao(User.class));


}
