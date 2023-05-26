package com.revature.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    // class variables and methods
    private static ConnectionFactory instance;
    private Connection connection;

   // Method to get instance from the database
    public static ConnectionFactory getInstance() throws ClassNotFoundException, IOException, SQLException {
        if (instance == null || instance.getConnection().isClosed()){
            instance = new ConnectionFactory();
        }
        return instance;
    }

    // constructor
    private ConnectionFactory() throws ClassNotFoundException, IOException, SQLException{
        Class.forName("org.postgresql.Driver");
        System.out.println("we get to this line");
        Properties props = getProperties();
        connection = DriverManager.getConnection(props.getProperty("url"), 
        props.getProperty("username"), props.getProperty("password"));
    }
    
    // instance variables and methods
    

    public Connection getConnection(){ return connection; }

    /* ***********************
     * Helper Methods
     * ***********************
     * This helper method used to get information from our application properties
     */
    private Properties getProperties() throws IOException{
        Properties props = new Properties();

        try(InputStream iStream = getClass().getClassLoader().getResourceAsStream("application.properties")){
            if(iStream == null){
                throw new IOException("Unable to find application.properties");
            }
            props.load(iStream);
        }
        return props;
    }
}
