package com.todo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection getConnection() {
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
			if(con!=null)
				return con;
			else
				return null;
		}catch (Exception e) {
			return null;
		}
	}
}
