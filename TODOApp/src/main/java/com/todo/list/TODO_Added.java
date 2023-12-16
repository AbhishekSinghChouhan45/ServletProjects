package com.todo.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.todo.DAO.DBConnection;
import com.todo.DAO.Queries;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/todoadded")
public class TODO_Added extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		RequestDispatcher rd;
		try {
		HttpSession s1 = req.getSession(false);
		String uname = (String)s1.getAttribute("uname");
		String pass = (String)s1.getAttribute("pass");
		String title = req.getParameter("title");
		String desc = req.getParameter("description");
		String status = req.getParameter("status");
		Date  td = Date.valueOf(req.getParameter("tdate"));
		DBConnection sb = new DBConnection();
		Connection con = sb.getConnection();
		PreparedStatement ps = con.prepareStatement(Queries.addlist());
		ps.setString(1, title);
		ps.setString(2, desc);
		ps.setString(3, status);
		ps.setDate(4, td);
		ps.setString(5, uname);
		ps.setString(6, pass);
		int count = ps.executeUpdate();
		if(count>0) {
			pw.println("<h1 style = 'text-align: center; color: green'>Todo list added</h1>");
			rd = req.getRequestDispatcher("check.html");
			rd.include(req, res);
		}else {
			rd = req.getRequestDispatcher("Error.html");
			rd.forward(req, res);
		}
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			rd = req.getRequestDispatcher("Error.html");
			rd.forward(req, res);
		}
		
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
