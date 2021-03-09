package com.intellijeep.ui;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.*;
import com.intellijeep.model.info.UserAccountInfo;
import com.intellijeep.model.info.UserLocationInfo;
import com.intellijeep.model.info.UserPersonalInfo;
import com.intellijeep.services.UserService;

import java.util.Scanner;

public class SignUpMenu extends AbstractMenu{

    private final UserService us;

    private final UserAccountInfo accountInfo; //id, username, password, accountType
    private final UserPersonalInfo personalInfo; //first name, last name, email, phone number
    private final UserLocationInfo locationInfo; //street address, city, state, zip

    public SignUpMenu() {
        this.us = new UserService((UserDao) DaoFactory.createDao(User.class));
        this.accountInfo = new UserAccountInfo();
        this.personalInfo = new UserPersonalInfo();
        this.locationInfo = new UserLocationInfo();
    }

    Boolean validUser = false;
    //TODO: Implement input validation (Utilize IVU and SignUpService)
    @Override
    public void showMenu(Scanner scan) {
        do {
            System.out.println("Please provide Username");
            accountInfo.setUsername(scan.nextLine());
        }
        while(us.doesUsernameExist(accountInfo.getUsername()));

        System.out.println("Please provide Password");
        accountInfo.setPassword(scan.nextLine());

        System.out.println("Please provide First Name");
        personalInfo.setFirstName(scan.nextLine());

        System.out.println("Please provide Last Name");
        personalInfo.setLastName(scan.nextLine());

        System.out.println("Please provide Email");
        personalInfo.setEmail(scan.nextLine());

        System.out.println("Please provide Phone Number");
        personalInfo.setPhoneNumber(scan.nextLine());

        System.out.println("Please provide Street Address");
        locationInfo.setStreetAddress(scan.nextLine());

        System.out.println("Please provide City");
        locationInfo.setCity(scan.nextLine());

        System.out.println("Please provide State");
        locationInfo.setState(scan.nextLine());

        System.out.println("Please provide Zipcode");
        locationInfo.setZipCode(scan.nextLine());

        accountInfo.setAccountType(AccountType.USER);
        accountInfo.setUserID(us.makeUser(accountInfo, personalInfo, locationInfo));

        System.out.println("Successfully Created " + accountInfo.getUsername());
        System.out.println("Now redirecting you");

        nextMenu = menuFactory.getUserAccountTypeMenu(us.findUserByUserID(accountInfo.getUserID()));
        nextMenu.showMenu(scan);
    }
}
