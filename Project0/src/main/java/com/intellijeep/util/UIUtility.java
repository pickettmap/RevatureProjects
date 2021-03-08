package com.intellijeep.util;

import java.util.Locale;

public class UIUtility {
    public boolean YesorNo(String s){
        s = s.toLowerCase();
        if(s.equals("yes") | s.equals("y")){
            return true;
        }
        else if (s.equals("no") | s.equals("n")){
            return false;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
