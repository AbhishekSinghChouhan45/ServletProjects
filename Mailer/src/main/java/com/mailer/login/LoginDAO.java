package com.mailer.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mailer.register.ConnectionDAO;

public class LoginDAO {
	private static boolean status = false;
	private static String msg;
	public static boolean getLog(String name,String Password) {
		status = false;
		msg = null;
		try {
			Connection con = ConnectionDAO.getCon();
			PreparedStatement ps = con.prepareStatement("select * from company_mailer_user where email=(?) and password=(?)");
			ps.setString(1, name);
			ps.setString(2, Password);
//			ps.setString(3, "yes");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if(rs.getString("AUTHORIZED").equalsIgnoreCase("no")) {
					System.out.println("saying no");
					msg = "You'r request is not approved by admin";
//					status = false;
					return false;
				}
				if(rs.getString("AUTHORIZED").equalsIgnoreCase("delete")) {
					msg = "You'r request is deleted by admin try again";
//					status = false;
					return false;
				}
				status = true;
			}
			msg = "Check Username or Password";
		}catch (Exception e) {
			System.out.println("Exception in LoginDAO \n \t:\t"+e.getLocalizedMessage());
		}
		return status;
	}
//	public static String getLog(String name,String Password) {
////		status = false;
//		msg = "false";
//		try {
//			Connection con = ConnectionDAO.getCon();
//			PreparedStatement ps = con.prepareStatement("select * from company_mailer_user where email=(?) and password=(?)");
//			ps.setString(1, name);
//			ps.setString(2, Password);
////			ps.setString(3, "yes");
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				if(rs.getString("AUTHORIZED").equalsIgnoreCase("no")) {
//					System.out.println("saying no");
//					msg = "request";
//					return msg;
//				}
//				if(rs.getString("AUTHORIZED").equalsIgnoreCase("delete")) {
//					msg = "delete";
//				}
//				msg = "true";
//			}
//		}catch (Exception e) {
//			System.out.println("Exception in LoginDAO \n \t:\t"+e.getLocalizedMessage());
//		}
//		return msg;
//	}
	public static String getMsg() {
		return msg;
	}
}
