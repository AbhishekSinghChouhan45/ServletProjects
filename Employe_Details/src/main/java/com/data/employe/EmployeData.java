package com.data.employe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class EmployeData extends HttpServlet {
	private static final String Query1 = "SELECT * FROM Employe_info where employe_id = (?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		try(PrintWriter pw = res.getWriter();){
			try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employe","root","root");
				PreparedStatement ps1 = con.prepareStatement(Query1);
				){
				res.setContentType("text/html");
					ps1.setInt(1,Integer.parseInt(req.getParameter("eid")));
					ResultSet rs = ps1.executeQuery();
					pw.println("<table  border = '5' align='center'>");
					pw.println("<caption style='color:red';>Employe Data</caption>");
					pw.println("<tr><th>Employe ID</th><th>Employe Name</th><th>Employe Address</th><th>Employe age</th><th>Employe DOB</th><th>Employe Gender</th><th>Employe Salary</th></tr>");
				while (rs.next()==true) {
					pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>");
				}
					pw.println("</table>");
					pw.println("<a href='EmployeHome.html'><img src='Downloads/business-people-blue-background.jpg'></a>");
				}catch (SQLException e) {
					pw.println(e.getLocalizedMessage());
				}
		}catch (Exception e) {	System.out.println(e.getLocalizedMessage());	}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
