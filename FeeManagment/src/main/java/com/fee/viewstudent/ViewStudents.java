package com.fee.viewstudent;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fee.connection.ConnectionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ViewStudents")
public class ViewStudents extends HttpServlet {
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
						Connection con = ConnectionDAO.getCon();
						PreparedStatement ps = con.prepareStatement("Select * from students_data");
						ResultSet rs = ps.executeQuery();
						int count = 0;
						double total = 0.0;
						pw.println("<table align='center' border='2' style=' color:black; margin-top:3%;'>");
						pw.println("<tr><th>Sno</th><th>Name</th><th>Email</th><th>Course</th><th>Total fee</th><th>Paid fee</th><th>Due fee</th><th>Contact</th></tr>");
						if(rs.next()) {
						do {
							total = rs.getDouble("totaldues") + total;
							pw.println("<tr><td>"+(count = count + 1)+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("email")+"</td><td>"+rs.getString("course")+"</td><td>"+rs.getString("fee")+"</td><td>"+rs.getString("paid")+"</td><td>"+rs.getString("due")+"</td><td>"+rs.getString("contact")+"</td></tr>");
						}while(rs.next());
						}
						pw.println("<tfoot><tr><td></td><td></td><td></td><td></td><td></td><td></td>");
						pw.println("<td><b>Total Dues of Students</b></td><td><b>"+total+"</b></td></tr></tfoot>");
						pw.println("</table>");
					}catch (Exception e) {
						System.out.println("Exception in ViewStudent:\n\t\t"+e.getMessage());
					}
					req.getRequestDispatcher("footer.html").include(req, res);
		}
}
