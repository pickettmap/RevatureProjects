package com.intellijeep.services;

import com.intellijeep.db.GenericDao;
import com.intellijeep.model.*;
import com.intellijeep.model.info.UserAccountInfo;
import com.intellijeep.model.info.UserLocationInfo;
import com.intellijeep.model.info.UserPersonalInfo;
import com.intellijeep.util.IntelliJeepArrayList;

public class UserService {
    GenericDao<User> userDao;

    public UserService(GenericDao<User> userDao) {
        this.userDao = userDao;
    }

    private static IntelliJeepArrayList<User> userCollection = new IntelliJeepArrayList<>(User.class,0);

    public boolean doesUsernameExist(String username) {
        return findUserByUsername(username) != null;
    }

    public User findUserByUsername(String username) {
        for (User u : userCollection) {
            String userName = u.getAccountData().getUsername();
            if(userName.equals(username)) {
                return u;
            }
        }
        return null;
    }

    public User findUserByUserID(Integer userID2) {
        for(User u : userCollection) {
            int userID = u.getAccountData().getUserID();
            if(userID == userID2) {
                return userCollection.get(u);
            }
        }
        return null;
    }

    public int makeUser(UserAccountInfo accountData, UserPersonalInfo personalInfo, UserLocationInfo locationData) {
        if(!doesUsernameExist(accountData.getUsername())) {
            User user =
                    new User.UserBuilder()
                            .accountData(accountData)
                            .personalInfo(personalInfo)
                            .locationData(locationData)
                            .build();
            userCollection.add(user);
            return userDao.save(user);
        } else {
            System.out.println("Username is taken");
        }
        return -1;
    }

    //TODO: If account type changes to customer, add to customer table
    public void changeUserAccountType(AccountType accountType, int userID){
        findUserByUserID(userID).getAccountData().setAccountType(accountType);
    }

    //TODO: Delete after testing
    public void showUsers() {
        System.out.println(userCollection.toString());
    }

}
