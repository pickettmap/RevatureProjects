package org.reform.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertyConfig {
    private String url = "";
    private String username = "";
    private String password = "";
    private String profile = "";

    public void configureProperties() {
        Properties props = new Properties();

        try {
            props.load(new FileReader(new File("src/main/resources/db.properties")));

            String connectionTemplate = "org.reform.profile." + profile;
            this.url = props.getProperty(connectionTemplate + ".url");
            this.username = props.getProperty(connectionTemplate + ".username");
            this.password = props.getProperty(connectionTemplate + ".password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
