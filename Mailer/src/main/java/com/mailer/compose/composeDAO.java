package com.mailer.compose;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import com.mailer.register.ConnectionDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
public class composeDAO {
	private static Connection con = null;
	private static PreparedStatement ps =null;
		public static int composeMail(String sender,String receiver,String subject,String message) {
			int status = 0;
			long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);  
			try {
				con = ConnectionDAO.getCon();
				ps = con.prepareStatement("Select Email from company_mailer_user Where EMAIL = (?)");
				ps.setString(1, receiver);
				ResultSet rs = ps.executeQuery();
				rs.next();
				String email = rs.getString("EMAIL");
				if(email!=null) {
				ps = con.prepareStatement("insert into company_mailer_message(sender,receiver,subject,message,trash,messagedate) values(?,?,?,?,?,?)");
				ps.setString(1,sender);
				ps.setString(2,receiver);
				ps.setString(3,subject);
				ps.setString(4,message);
				ps.setString(5,"no");
				ps.setDate(6,date);
				status = ps.executeUpdate();
				}else {
					status = 0;
				}
			}catch (Exception e) {
				System.out.println("Exception in Compose: \n\t\t"+e.getLocalizedMessage());
			}
			return status;
		}
}
