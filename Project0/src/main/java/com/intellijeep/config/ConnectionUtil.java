package com.intellijeep.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static ConnectionUtil instance;

    private ConnectionUtil(){}

    public static ConnectionUtil getInstance() {
        if(instance == null) {
            instance = new ConnectionUtil();
        }
        return instance;
    }

    public static Connection getConnection(String profile) {
        Properties props = new Properties();

        try {
            props.load(new FileReader(new File("src/main/resources/db.properties")));

            String connectionTemplate = "com.intellijeep.profile." + profile;
            return DriverManager.getConnection(
                    props.getProperty(connectionTemplate + ".url"),
                    props.getProperty(connectionTemplate + ".username"),
                    props.getProperty(connectionTemplate + ".password"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
