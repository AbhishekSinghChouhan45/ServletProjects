package com.fee.addaccountant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fee.connection.ConnectionDAO;

public class AddingDAO {
		public static int getLog(String name,String pass,String email,String address,String contact){
			int count = 0;
			System.out.println(email +"  "+pass);
			try {
				System.out.println(1);
					Connection con = ConnectionDAO.getCon();
					PreparedStatement ps1 = con.prepareStatement("Select email from accountants");
					ResultSet rs = ps1.executeQuery();
					System.out.println(2);
					while(rs.next()) {
						if(rs.getString("email").equals(email)) {
							System.out.println(3);
							return 5;
						}
					}
					System.out.println(4);
					PreparedStatement ps = con.prepareStatement("Insert into accountants(name,password,email,address,contact) VALUES (?,?,?,?,?)");
					System.out.println(5);
					ps.setString(1, name);
					ps.setString(2, pass);
					ps.setString(3, email);
					ps.setString(4, address);
					ps.setString(5, contact);
					count = ps.executeUpdate();
					System.out.println(6);
			}catch (Exception e) {
				System.out.println("Exception in LoginDAO :\n\t\t"+e.getLocalizedMessage());
			}
			return count;
		}
}
