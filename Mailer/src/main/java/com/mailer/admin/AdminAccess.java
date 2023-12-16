package com.mailer.admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mailer.register.ConnectionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/AdminAccess")
public class AdminAccess extends HttpServlet {
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();
				HttpSession se = req.getSession(false);
				req.getRequestDispatcher("header.html").include(req, res);
				req.getRequestDispatcher("links.html").include(req, res);
				String msg = (String)req.getAttribute("msg");
				if(msg!=null) {
					pw.println("<p>"+msg+"</p>");
				}
				try {
				int i = 0;
				Connection con = ConnectionDAO.getCon();
				PreparedStatement ps = con.prepareStatement("Select * from company_mailer_user where AUTHORIZED = 'no'");
				ResultSet rs = ps.executeQuery();
				String email = (String)se.getAttribute("umail");
				if(rs.next()!=false) {
					pw.println("<form action='Request' method = 'post'>");
					pw.println("<table style='background-color: wheat' border='2'>");
				pw.println("<tr><th>No</th><th>Email</th><th>Aprroval</th></tr>");
				do {
					pw.print("<tr><td>"+(i = i+1)+"</td><td><a href='Request?id="+rs.getString(1)+"'>"+rs.getString("email")+"</a></td><td>");
				} while (rs.next());
				pw.println("</table>");
				pw.println("</form>");
				}else {
					pw.println("<p>No Requests are there</p>");
				}
				}catch (Exception e) {
					System.out.println("Exception in AdminAccess:\n\t"+e.getLocalizedMessage());
				}
		}
}
