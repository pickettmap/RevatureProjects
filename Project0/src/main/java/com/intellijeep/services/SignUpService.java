package com.intellijeep.services;

import com.intellijeep.ui.util.InputValidationUtility;

public class SignUpService {

    private InputValidationUtility ivu;

    public boolean checkPassword(String s){
        return ivu.isValid(s);
    }
}
