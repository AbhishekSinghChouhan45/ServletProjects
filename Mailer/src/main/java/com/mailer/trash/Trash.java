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
@WebServlet("/Trash")
public class Trash extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			HttpSession se = req.getSession(false);
			String emails = (String)se.getAttribute("email");
			String adminMail = (String)se.getAttribute("adminMail");
				if(emails.equals(adminMail)) {
					req.getRequestDispatcher("header.html").include(req, res);
					req.getRequestDispatcher("links.html").include(req, res);
				}else {
					req.getRequestDispatcher("header.html").include(req, res);
					req.getRequestDispatcher("link.html").include(req, res);
				}
			pw.print("<h5 style='text-align: right'>Hi "+se.getAttribute("email")+"</h5>");
			try {
				String email = (String)se.getAttribute("email");
				Connection con = ConnectionDAO.getCon();
				PreparedStatement ps = con.prepareStatement("Select * from company_mailer_message where (RECEIVER,Trash) = (?,?)");
				ps.setString(1, email);
				ps.setString(2, "yes");
				ResultSet rs = ps.executeQuery();
				String msg = (String)req.getAttribute("msg");
				if(msg!=null) {
					pw.println("<p>"+msg+"</p>");
				}
				pw.println("<table border = 2 style='background-color:wheat'>");
				pw.println("<tr><th>Subject</th><th>Message</th></tr>");
				while(rs.next()!=false) {
				pw.println("<tr><td>"+rs.getString("SUBJECT")+"</td><td><a href='TrashServlet?id="+rs.getString(1)+"'>"+rs.getString("MESSAGE").subSequence(0,6)+"</td></tr>");
				}
				pw.println("</table>");
			}catch (Exception e) {
				System.out.println("Exception in Trash:\n\t\t"+e.getLocalizedMessage());
			}
			req.getRequestDispatcher("footer.html").include(req, res);
	}
}
