package com.intellijeep.ui.employee;

import com.intellijeep.model.User;
import com.intellijeep.services.UserService;
import com.intellijeep.ui.AbstractMenu;

import javax.swing.text.View;
import java.util.Scanner;

public class ViewCustomerPaymentsMenu extends AbstractMenu {

    private UserService us;
    public ViewCustomerPaymentsMenu(User u) {
        this.u = u;
        this.us = new UserService();
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            System.out.println("Enter customer id to view payment info for or B to Go Back");
            us.showAllUsers();
            String response = scan.nextLine();
            if (response.toLowerCase().equals("b")) {
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
            }
            else if(response.matches("[0-9]+")) {
                int convertedResponse = Integer.parseInt(response);
                User u = us.findUserByUserID(convertedResponse);
                if(us.doesUsernameExist(u.getUsername())){
                    System.out.println(us.showAllPayments(convertedResponse));
                }
                else {
                    System.out.println("Not a valid user id");
                }
            }
            nextMenu = menuFactory.getUserAccountTypeMenu(u);
        } while (nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
