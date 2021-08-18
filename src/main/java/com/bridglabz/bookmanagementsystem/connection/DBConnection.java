package com.bridglabz.bookmanagementsystem.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DBConnection {
	static Connection connection;
	final static String URL = "jdbc:mysql://localhost:3306/books";
	final static String USER_NAME = "root";
	final static String PASSWORD = "Pratik@22";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver is loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("Database is connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
