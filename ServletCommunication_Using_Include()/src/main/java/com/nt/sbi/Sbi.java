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

public class Sbi extends HttpServlet{
	private final String Query = "Select name,address,gender,balance from sbi_customers WHERE id=(?)";
	private String url = null;
	private String username = null;
	private String pass = null;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			ServletContext sc =  getServletContext();
			url = sc.getInitParameter("url");
			username = sc.getInitParameter("username");
			pass = sc.getInitParameter("pass");
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			RequestDispatcher rd;
		try {
			con = DriverManager.getConnection(url,username,pass);
			rd = sc.getRequestDispatcher("/header");
			rd.include(req, res);
			int cid = Integer.parseInt(req.getParameter("cid"));
			ps = con.prepareStatement(Query);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			if(rs.next()!=false) {
			pw.println("<table align='center' border='2' style='background-color: cyan;'>");
			pw.println("<caption>State Bank Of India Customers</caption>");
			pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");
			pw.println("</table>");
			}else {
				pw.println("<h1 style='color:red; text-align:center;'>No Customer Found of Customer Id: "+cid+"</h1>");
			}
			rd = sc.getRequestDispatcher("/footer.html");
			rd.include(req, res);
		}catch (Exception e) {
			rd = sc.getRequestDispatcher("/error.html");
			rd.forward(req, res);
		}
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
