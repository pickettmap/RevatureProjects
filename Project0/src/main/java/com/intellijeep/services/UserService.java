package com.intellijeep.services;

import com.intellijeep.db.GenericDao;
import com.intellijeep.model.*;
import com.intellijeep.util.IntelliJeepCollection;

public class UserService {

    //GenericDao userDao holds user and userID
    GenericDao<User, Integer> userDao;

    public UserService(GenericDao<User, Integer> userDao) {
        this.userDao = userDao;
    }

    private static IntelliJeepCollection<User> userCollection = new IntelliJeepCollection<>(User.class, 0);
    private static User currentUser;

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
            currentUser = user;
            return 69;
            //return userDao.save(user);
        } else {
            System.out.println("Username is taken");
        }
        return -1;
    }

    //TODO: Delete after testing
    public void showUsers() {
        System.out.println(userCollection.toString());
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void setCurrentUser(User u){
        currentUser = u;
    }
}
