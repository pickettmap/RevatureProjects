package org.reform.connection;
import org.reform.config.PropertyConfig;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory implements Closeable {

    public static final int MAX_CONNECTIONS = 4;
    private final Connection[] connectionPool = new Connection[MAX_CONNECTIONS];

    private static ConnectionFactory instance;

    private ConnectionFactory() {
        for(int i = 0; i< MAX_CONNECTIONS; i++){
            connectionPool[i] = createConnection();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }


    // Refactor with config
    private Connection createConnection() {
        PropertyConfig config = new PropertyConfig();
        //TODO: abstract this to the user
        config.configureProperties("persist");

        try {
            return DriverManager.getConnection(config.getUrl(),
                    config.getUsername(),
                    config.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection[] getConnectionPool() {
        return connectionPool;
    }

    @Override
    public void close() throws IOException {
        for(Connection con: connectionPool){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
