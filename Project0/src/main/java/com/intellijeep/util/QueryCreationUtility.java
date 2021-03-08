package com.intellijeep.util;

import com.intellijeep.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryCreationUtility {
    public String createUserInsertionQuery(User user, PreparedStatement ps) throws SQLException {
        String query = "insert into app_user values(?,?,?,?,?,?,?,?,?,?,?)";
        ps.setString(1, user.getAccountData().getUsername());
        ps.setString(2, user.getAccountData().getPassword());
        ps.setInt(3, user.getAccountData().getAccountType().convert());
        ps.setString(4, user.getPersonalInfo().getFirstName());
        ps.setString(5, user.getPersonalInfo().getLastName());
        ps.setString(6, user.getPersonalInfo().getEmail());
        ps.setString(7, user.getPersonalInfo().getPhoneNumber());
        ps.setString(8, user.getLocationData().getStreetAddress());
        ps.setString(9, user.getLocationData().getCity());
        ps.setString(10, user.getLocationData().getState());
        ps.setString(11, user.getLocationData().getZipCode());
        return query;
    }
}
