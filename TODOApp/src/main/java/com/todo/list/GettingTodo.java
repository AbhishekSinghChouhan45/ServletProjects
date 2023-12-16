package com.todo.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.todo.DAO.DBConnection;
import com.todo.DAO.Queries;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(value = "/gettodo",name = "get")
public class GettingTodo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		DBConnection db = new DBConnection();
		try (Connection con = db.getConnection()){
			HttpSession se = req.getSession(false);
			String uname = (String) se.getAttribute("uname");
			String pass = (String) se.getAttribute("pass");
			PreparedStatement ps = con.prepareStatement(Queries.getTodo());
			ps.setString(1, uname);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			pw.println("<body background='get.jpg' style='background-repeat: no-repeat; height: 10%;'>");
			pw.println("<table align='center' border='1' style='margin-top: 14%;'>");
			pw.println("<tr>");
			pw.println("<th>Title</th>");
			pw.println("<th>Description</th>");
			pw.println("<th>Status</th>");
			pw.println("<th>Target Date</th>");
			pw.println("</tr>");
			while(rs.next()!=false) {
				String status = rs.getString(3);
				if(status.equalsIgnoreCase("ndone"))
					status = "Not Done";
				else
					status = "Done";
				pw.println("<tr>");
				pw.println("<td>"+rs.getString(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+status+"</td>");
				pw.println("<td>"+rs.getString(4)+"</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("</body>");
//				pw.println("<h1 style='text-align:center; color:red;'>You have not created Todo list</h1>");
			
		}catch (NullPointerException e) {
			pw.println("<h1 style='text-align:center; color:red;'>Session Time Out</h1>");
		}
		catch (Exception e) {
			System.out.println("get "+e.getLocalizedMessage());
		}
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
