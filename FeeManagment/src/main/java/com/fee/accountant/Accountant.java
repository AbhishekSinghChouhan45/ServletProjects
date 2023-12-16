package com.fee.accountant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fee.connection.ConnectionDAO;

public class Accountant {
	public static int access(String email,String pass) {
				int count = 0;
			try {
					Connection con = ConnectionDAO.getCon();
					PreparedStatement ps = con.prepareStatement("Select email,password from accountants where (email,password) = (?,?);");
					ps.setString(1, email);
					ps.setString(2, pass);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
					if(rs.getString("email").equals(email) && rs.getString("password").equals(pass)) {
						count = 1;
					 }
					}
			}catch (Exception e) {
				System.out.println("Exception in Accountant:\n\t\t"+e.getMessage());
			}
			return count;
	}

}
