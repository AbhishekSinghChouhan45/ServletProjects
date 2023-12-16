package com.mailer.request;
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
@WebServlet(value = "/Request")
public class Request extends HttpServlet {
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();
//				int id = Integer.parseInt(req.getParameter("id"));
				String id = null; 
				try {
				id = 	req.getParameter("id");
				req.getRequestDispatcher("header.html").include(req, res);
				req.getRequestDispatcher("links.html").include(req, res);
				System.out.println("in Request baby");
				}catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				pw.println("<form action='Approval' method='post'>");
				pw.println("<table style='background-color:wheat'>");
				pw.println("<p style='text-align:left'>Do you want to approve ?</p>");
				pw.println("<tr><td><input type='radio' name='check' value= 'yes'>yes</td><td><input type='radio' name='check' value='no'>no</td><td> <input type='radio' name='check' value='delete'>delete</td></tr>");
				pw.println("<tr><td><input type='submit'></td></tr>");
				pw.println("<input type='hidden' value='"+id+"' name = 'hidden' >");
				pw.println("</table>");
				pw.println("</form>");
				System.out.println("Request is "+req.getParameter("check"));
				req.getRequestDispatcher("footer.html").include(req, res);
		}
}
