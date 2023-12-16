package com.todo.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.todo.DAO.DBConnection;
import com.todo.DAO.Queries;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegistringUser extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			DBConnection c = new DBConnection();
			Connection con = c.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			try {
				if(con!=null) {
					ps = con.prepareStatement(Queries.insert());
					ps.setString(1,req.getParameter("fname"));
					ps.setString(2,req.getParameter("lname"));
					ps.setString(3,req.getParameter("uname"));
					ps.setString(4,req.getParameter("upass"));
					Date date = Date.valueOf(req.getParameter("dob"));
					ps.setDate(5,date);
					int count =ps.executeUpdate();
					if(count>0){
						pw.println("<h1 style='color:green; text-align:center'>Registerd Successfully</h1>");
					}else {
						pw.println("<h1 style='color:red; text-align:center'>Registerd Unsuccessfully</h1>");
					}
				}else {
					pw.println("<h1 style='color:red; text-align:center'>Some Database Problem</h1>");
				}
			}catch (Exception e) {
				pw.println("<h1 style='color:red; text-align:center'>"+e.getLocalizedMessage()+"</h1>");
			}
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
