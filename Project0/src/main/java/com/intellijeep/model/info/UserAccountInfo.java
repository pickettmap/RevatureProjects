package com.intellijeep.model.info;

import com.intellijeep.model.AccountType;

public class UserAccountInfo {
    private int userID;
    private String username;
    private String password;
    private AccountType accountType;

    public UserAccountInfo(){}

    public UserAccountInfo(int userID, String username, String password, AccountType accountType) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "UserAccountInfo{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}

