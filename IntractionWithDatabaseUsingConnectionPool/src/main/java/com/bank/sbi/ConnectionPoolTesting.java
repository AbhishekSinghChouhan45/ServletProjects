package com.bank.sbi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("sbi")
public class ConnectionPoolTesting extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		System.out.println("ION");
		pw.println("<h1>HIdI</h1>");
		try {
		Connection con = getConnectionp();
		if(con!=null)
			pw.println("CE");
		else
			pw.println("CNE");
		}catch (Exception e) {
			pw.println(e.getLocalizedMessage());
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	private Connection getConnectionp()throws Exception{
		InitialContext ic = new InitialContext();
		DataSource ds =(DataSource)ic.lookup("java://MySqlDS");
		Connection con = ds.getConnection();
		Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbi","root","root");
		return con1;
	}
}
