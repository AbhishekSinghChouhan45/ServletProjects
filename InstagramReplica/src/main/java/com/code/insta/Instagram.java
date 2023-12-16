package com.code.insta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class Instagram extends HttpServlet {
	private static final String PreparedStatement = "Insert into Insta(username,password)Values(?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String uname = req.getParameter("username");
		String upass = req.getParameter("password");
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/InstaData","root","root");
			PreparedStatement ps = con.prepareStatement(PreparedStatement);){
			ps.setString(1,uname);
			ps.setString(2,upass);
			ps.execute();
			
		}catch (Exception e) {
			pw.println(e.getMessage()); 
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
