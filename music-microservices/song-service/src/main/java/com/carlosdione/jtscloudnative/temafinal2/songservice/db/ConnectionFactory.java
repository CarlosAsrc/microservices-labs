package com.carlosdione.jtscloudnative.temafinal2.songservice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.carlosdione.jtscloudnative.temafinal2.songservice.exception.DatabaseAccesError;

@Component
public class ConnectionFactory {

	private static String URL;
	private static String USER;
    private static String PASSWORD;
    
    @Value("${database.url}")
    public void setDatabaseUrl(String URL) {
        ConnectionFactory.URL = URL;
    }
    
    @Value("${database.user}")
    public void setDatabaseUser(String USER) {
        ConnectionFactory.USER = USER;
    }
    
    @Value("${database.password}")
    public void setDatabasePassword(String PASSWORD) {
        ConnectionFactory.PASSWORD = PASSWORD;
    }
    
    public static Connection getConnection() throws SQLException {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException error) {
			throw new SQLException("Connection failed! " + error.getMessage());
		}
	}

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
        	throw new DatabaseAccesError("Fail on close connection!");
        }
    }
}