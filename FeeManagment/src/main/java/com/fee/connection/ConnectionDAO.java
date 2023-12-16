package com.fee.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDAO {
	private static Connection con = null;
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FeeManagment","root","root");
		}catch (Exception e) {
			System.out.println("Exception in ConnectionDAO:\n\t\t"+e.getMessage());
		}
		return con;
	}
}
