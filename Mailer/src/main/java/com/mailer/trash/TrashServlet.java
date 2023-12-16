package com.mailer.trash;

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
@WebServlet("/TrashServlet")
public class TrashServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			HttpSession se = req.getSession();
			String emails = (String)se.getAttribute("email");
			String adminMail = (String)se.getAttribute("adminMail");
				if(emails.equals(adminMail)) {
					req.getRequestDispatcher("header.html").include(req, res);
					req.getRequestDispatcher("links.html").include(req, res);
				}else {
					req.getRequestDispatcher("header.html").include(req, res);
					req.getRequestDispatcher("link.html").include(req, res);
				}
				pw.print("<h5 style='text-align: right'>Hi "+emails+"</h5>");
			try {
				String id = req.getParameter("id");
				Connection con = ConnectionDAO.getCon();
				PreparedStatement ps = con.prepareStatement("Select * from company_mailer_message where id = (?)");
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					pw.print("<h1>"+rs.getString("subject")+"</h1><hr/>");
					pw.print("<p><b>Message:</b><br/> "+rs.getString("message")+" <br/> <b>To: "+rs.getString("RECEIVER")+"</b></p>");
					pw.print("<p><a href='Trashing?id="+rs.getString(1)+"'>Trash</a></p>");			
				}
			}catch (Exception e) {
				System.out.println("Exception in TrashServlet:\n\t\t"+e.getLocalizedMessage());
			}
			req.getRequestDispatcher("footer.html").include(req, res);
	}
}
