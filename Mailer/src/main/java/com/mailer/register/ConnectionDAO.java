package com.mailer.register;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDAO {
		private static Connection con = null;
		public static Connection getCon() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mailer","root","root");
			}catch (Exception e) {
				System.out.println("Exception in Connection \n\n\t"+e.getLocalizedMessage());
			}
			return con;
		}
}
