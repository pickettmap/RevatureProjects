// Resource for Abstracted Builder Pattern: https://medium.com/@KonfHub/refactoring-long-parameter-list-in-constructors-java-e9c9f2cb1fb


package com.intellijeep.model;

import com.intellijeep.model.info.UserAccountInfo;
import com.intellijeep.model.info.UserLocationInfo;
import com.intellijeep.model.info.UserPersonalInfo;

public class User implements Comparable<User>{
    private UserAccountInfo accountData;
    private UserPersonalInfo personalInfo;
    private UserLocationInfo locationData;

    public UserAccountInfo getAccountData() {
        return accountData;
    }

    public void setAccountData(UserAccountInfo accountData) {
        this.accountData = accountData;
    }

    public UserPersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(UserPersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public UserLocationInfo getLocationData() {
        return locationData;
    }

    public void setLocationData(UserLocationInfo locationData) {
        this.locationData = locationData;
    }

    @Override
    public String toString() {
        return "User{" +
                "accountData=" + accountData.toString() +
                ", personalInfo=" + personalInfo.toString() +
                ", locationData=" + locationData.toString() +
                '}';
    }

    @Override
    public int compareTo(User u) {
        return this.accountData.getUserID()-u.accountData.getUserID();
    }

    public static class UserBuilder {

        User user = new User();

        public UserBuilder(){}

        public UserBuilder accountData(UserAccountInfo accountData) {
            user.accountData = accountData;
            return this;
        }

        public UserBuilder personalInfo(UserPersonalInfo personalInfo) {
            user.personalInfo = personalInfo;
            return this;
        }

        public UserBuilder locationData(UserLocationInfo locationData) {
            user.locationData = locationData;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
