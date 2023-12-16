package com.mailer.request;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mailer.register.ConnectionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/Approval")
public class Approval extends HttpServlet {
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			HttpSession se = req.getSession(false);
			try {
				int id = Integer.parseInt(req.getParameter("hidden"));
				String check = req.getParameter("check");
				System.out.println(id+"   "+check);
					Connection con = ConnectionDAO.getCon();
					PreparedStatement ps = con.prepareStatement("update company_mailer_user set AUTHORIZED = (?) where id=(?)");
					String msg = null;
					if(check.equalsIgnoreCase("yes")) {
						ps.setString(1,"yes");
						ps.setInt(2,id);
						msg = "User Approved";
					}else if(check.equalsIgnoreCase("no")) {
						ps.setString(1,"no");
						ps.setInt(2,id);
						msg = "User not Approved";
					}else {
						ps.setString(1,"delete");
						ps.setInt(2,id);
						msg = "User not Approved and deleted";
					}
					int count = ps.executeUpdate();
					if(count>0) {
						se.setAttribute("msg",msg);
						res.sendRedirect("AdminAccess");
					}
			}catch (Exception e) {
				System.out.println("Exception in Approval:\n\t\t"+e.getLocalizedMessage());
			}
		}
}
