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
@WebServlet("/viewMessage")
public class ViewMessage extends HttpServlet {
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();
				req.getRequestDispatcher("header.html").include(req, res);
				req.getRequestDispatcher("link.html").include(req, res);
					try {
						int id = Integer.parseInt(req.getParameter("id"));
						Connection con = ConnectionDAO.getCon();
						PreparedStatement ps = con.prepareStatement("Select * from company_mailer_message where id =(?)");
						ps.setString(1, ""+id);
						ResultSet rs = ps.executeQuery();
						if(rs.next()){
							pw.print("<h1>"+rs.getString("subject")+"</h1><hr/>");
							pw.print("<p><b>Message:</b><br/> "+rs.getString("message")+" <br/> <b>By: "+rs.getString("sender")+"</b></p>");
							pw.print("<p><a href='DeleteMailServlet?id="+rs.getString(1)+"'>Delete Mail</a></p>");			
						}
					}catch (Exception e) {
						System.out.println("Exception in ViewMessage \n \t\t"+e.getLocalizedMessage());
					}
				
		}
}
