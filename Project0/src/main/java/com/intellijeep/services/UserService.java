package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.*;
import com.intellijeep.model.info.UserAccountInfo;
import com.intellijeep.model.info.UserLocationInfo;
import com.intellijeep.model.info.UserPersonalInfo;

public class UserService {
    private UserDao userDao;
    private CarDao carDao;

    public UserService() {
        this.userDao = (UserDao) DaoFactory.createDao(User.class);
        this.carDao = (CarDao) DaoFactory.createDao(Car.class);
    }

    //private static IntelliJeepArrayList<User> userCollection = new IntelliJeepArrayList<>(User.class,0);

    public boolean doesUsernameExist(String username) {
        return findUserByUsername(username) != null;
    }

    public User findUserByUsername(String username) {
        return userDao.getByUsername(username);
    }

    public User findUserByUserID(Integer id) {
        return userDao.getByID(id);
    }

    public int makeUser(UserAccountInfo accountData, UserPersonalInfo personalInfo, UserLocationInfo locationData) {
        if(!doesUsernameExist(accountData.getUsername())) {
            User user =
                    new User.UserBuilder()
                            .accountInfo(accountData)
                            .personalInfo(personalInfo)
                            .locationData(locationData)
                            .build();
            return userDao.save(user);
        } else {
            System.out.println("Username is taken");
        }
        return -1;
    }
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    //TODO: If account type changes to customer, add to customer table
    public void changeUserAccountType(AccountType accountType, int userID){
        User u = userDao.getByID(userID);
        u.getAccountData().setAccountType(accountType);
        userDao.update(u);
    }

    public void viewLotCars(){
        System.out.println(carDao.getByStatus(CarStatus.ON_LOT));
    }


}
