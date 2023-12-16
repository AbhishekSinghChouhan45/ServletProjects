package com.nt.sbi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletCommunication extends HttpServlet{
	private final String Query = "Select name,address,gender,balance from sbi_customers WHERE id=(?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Connection con =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServletContext sc = getServletContext();
		try {
			String url = sc.getInitParameter("url");
			String username = sc.getInitParameter("username");
			String pass = sc.getInitParameter("pass");
			con = DriverManager.getConnection(url,username,pass);
			int eid = Integer.parseInt(req.getParameter("eid"));
			ps = con.prepareStatement(Query);
			ps.setInt(1,eid);
			rs = ps.executeQuery();
			if(rs.next()!=false) {
			pw.println("<table align='center' border='2'>");
			pw.println("<caption>State Bank Of India Customers</caption>");
			pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
			pw.println("</table>");
			}else {
				pw.println("<h1 style='color:red; text-align:center;'>No Customer data found of account number "+eid+"</h1>");
			}
		}catch (Exception e) {
			pw.println(e.getLocalizedMessage());
			RequestDispatcher rd = sc.getRequestDispatcher("/er");
				rd.forward(req, res);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
