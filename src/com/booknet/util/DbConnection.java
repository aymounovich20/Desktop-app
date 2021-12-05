package com.booknet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection openConnection(){
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/booknetbd","root","");
			System.out.println("Connected to database");

			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("database connection error");
		}
		return conn;
	}

}
