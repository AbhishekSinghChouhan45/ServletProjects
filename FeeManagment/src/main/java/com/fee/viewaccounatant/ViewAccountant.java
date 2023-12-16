package com.fee.viewaccounatant;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.plaf.synth.SynthScrollBarUI;

import com.fee.connection.ConnectionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ViewAccountant")
public class ViewAccountant extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();
				req.getRequestDispatcher("header.html").include(req, res);
				req.getRequestDispatcher("links.html").include(req, res);
				int status = 0 ;
				try {
						Connection con = ConnectionDAO.getCon();
						PreparedStatement ps = con.prepareStatement("Select * from accountants");
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
						pw.println("<table align='center' border='2' style='margin-top:3%'>");
						pw.println("<tr><th>SNO</th><th>Accountant</th><th>Email</th></tr>");
						do{
							pw.println("<tr><td>"+(status = status + 1)+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("Email")+"</td></tr>");
						}while(rs.next());
						pw.println("</table>");
						}else {
							pw.println("<p>No Accountants Found</p>");
						}
				}catch (Exception e) {
					System.out.println("Exception in ViewAccountant:\n\t\t"+e.getMessage());
				}
				req.getRequestDispatcher("footer.html").include(req, res);
	}
}
