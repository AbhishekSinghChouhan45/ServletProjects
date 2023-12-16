package com.todo.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.todo.DAO.DBConnection;
import com.todo.DAO.Queries;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/checkuser")
public class CheckingUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String btn = req.getParameter("s");
	try {
		if(btn.equalsIgnoreCase("signup")) {
			RequestDispatcher rd = req.getRequestDispatcher("Registration.html");
			rd.forward(req, res);
		}else if(btn.equalsIgnoreCase("log in")) {
			String username = req.getParameter("uname");
			String password = req.getParameter("pass");
			DBConnection db = new DBConnection();
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(Queries.Login());
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()!=false) {
				System.out.println("found");
				HttpSession s1 = req.getSession(true);
				s1.setAttribute("uname", username);
				s1.setAttribute("pass", password);
//				s1.setMaxInactiveInterval(120);
				System.out.println("cookies added");
				RequestDispatcher rd = req.getRequestDispatcher("check.html");
				rd.forward(req, res); 
			}else {
				pw.println("<h1 style='text-align: center; color: red;'>Check you'r Username or Password</h1>");
			}
		}else {
			System.out.println("forgot");
		}
	}catch (Exception e) {
		System.out.println(e.getLocalizedMessage());
	}
		
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
