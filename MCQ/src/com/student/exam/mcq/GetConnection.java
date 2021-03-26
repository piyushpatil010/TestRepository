package com.student.exam.mcq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//This class is designed by Pushkar
public class GetConnection {
	static Connection connection;
	java.sql.PreparedStatement statement;

	public static Connection setConnection() { // method for connection reference
		try {
			FileInputStream fis = new FileInputStream("src/Connection.properties");
			Properties properties = new Properties();
			properties.load(fis);
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String userName = properties.getProperty("username");
			String password = properties.getProperty("password");
			Class.forName(driver); // loads Driver class
			connection = DriverManager.getConnection(url, userName, password); // established the connection
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return connection; // returns connection reference

	}

}