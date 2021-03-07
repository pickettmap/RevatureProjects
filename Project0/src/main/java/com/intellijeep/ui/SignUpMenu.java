package com.intellijeep.ui;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.*;
import com.intellijeep.services.UserService;

import java.util.Scanner;

public class SignUpMenu extends AbstractMenu{

    private UserService us = new UserService(DaoFactory.createDao(User.class));

    private UserAccountInfo accountInfo = new UserAccountInfo(); //id, username, password, accountType
    private UserPersonalInfo personalInfo = new UserPersonalInfo(); //first name, last name, email, phone number
    private UserLocationInfo locationInfo = new UserLocationInfo(); //street address, city, state, zip

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

        accountInfo.setUserID(us.makeUser(accountInfo, personalInfo, locationInfo));
        accountInfo.setAccountType(AccountType.USER);

        System.out.println("Successfully Created " + accountInfo.getUsername());
        System.out.println("Now redirecting you");

        nextMenu = menuFactory.getUserAccountTypeMenu(accountInfo.getAccountType());
        nextMenu.showMenu(scan);
    }
}
