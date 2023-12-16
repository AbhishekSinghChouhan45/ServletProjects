package com.mailer.register;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RegisterDAO {
	private static PreparedStatement ps =null;
	public static int register(String name,String email,String password,String gender,String dob,String addressLine,String city,String state,String country,String contact) {
		int status = 0;
		try {
			long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);  
			Connection con = ConnectionDAO.getCon();
			ps = con.prepareStatement("Select email from company_mailer_user");
			ResultSet rs = ps.executeQuery();
			while(rs.next()!=false) {
				if(rs.getString(1).equals(email)) {
					return 5;
				}
			}
			ps = con.prepareStatement("insert into company_mailer_user(name,email,password,gender,dob,addressLine,city,state,country,contact,registereddate,authorized) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, gender);
			ps.setDate(5, Date.valueOf(dob));
			ps.setString(6, addressLine);
			ps.setString(7, city);
			ps.setString(8, state);
			ps.setString(9, country);
			ps.setString(10, contact);
			ps.setDate(11,date);
			ps.setString(12,"no");			
			status = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return status;
	}
}
