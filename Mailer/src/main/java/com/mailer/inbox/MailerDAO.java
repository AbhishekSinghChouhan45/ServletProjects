package com.mailer.inbox;

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
@WebServlet("/inbox")
public class MailerDAO extends HttpServlet{
			@Override
			public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doPost(req, resp);
			}
			@Override
			public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
					res.setContentType("text/html");
					PrintWriter pw = res.getWriter();
					HttpSession se = req.getSession(false);
					String emails = (String)se.getAttribute("email");
					String adminMail = (String)se.getAttribute("adminMail");
					if(se==null) {
						req.getRequestDispatcher("index.html").include(req, res);
					}else {
						try {
						if(emails.equals(adminMail)) {
							req.getRequestDispatcher("header.html").include(req, res);
							req.getRequestDispatcher("links.html").include(req, res);
						}else {
							req.getRequestDispatcher("header.html").include(req, res);
							req.getRequestDispatcher("link.html").include(req, res);
						}
					pw.print("<h5 style='text-align: right'>Hi "+se.getAttribute("email")+"</h5>");
					String msg = (String)req.getAttribute("msg");
					if(msg!=null) {
						pw.println(msg);
					}
						Connection con = ConnectionDAO.getCon();
						PreparedStatement ps = con.prepareStatement("Select * from company_mailer_message where (RECEIVER,TRASH) = (?,?)");
						String email = (String)se.getAttribute("email");
						ps.setString(1, email);
						ps.setString(2, "no");
						ResultSet rs = ps.executeQuery();
						pw.println("<table border='2' style='background-color: wheat;'>");
					if(rs.next()!=false) {
						pw.println("<tr><th>Subject</th><th>Message</th><tr>");
						do{
							pw.println("<tr><td>"+rs.getString("SUBJECT")+"</td><td><a href='viewMessage?id="+rs.getString(1)+"'> "+rs.getString("MESSAGE").subSequence(0,6)+"</a></td></tr>");
						}
						while(rs.next());
					}else {
						pw.println("<h2>No messages yet..</h1>");
					}
						pw.println("</table>");
						req.getRequestDispatcher("footer.html").include(req, res);
						
					}catch (Exception e) {
						System.out.println("Exception in MailerDAO \n \t"+e.getLocalizedMessage());
					}
					}
			}
}
