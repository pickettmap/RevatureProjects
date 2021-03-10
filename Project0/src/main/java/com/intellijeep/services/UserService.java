package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.*;
import com.intellijeep.model.User;

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

    public int makeUser(User u) {
        if(!doesUsernameExist(u.getUsername())){
            return userDao.save(u);
        }
        else {
            System.out.println("Username is taken");
        }
        return -1;
    }

    public int makeUser(int id, String username, String password, AccountType accountType) {
        if(!doesUsernameExist(username)) {
            User u = new User(id, username, password, accountType);
            return userDao.save(u);
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
        u.setAccountType(accountType);
        userDao.update(u);
    }

    public void viewLotCars(){
        System.out.println(carDao.getByStatus(CarStatus.ON_LOT));
    }


}
