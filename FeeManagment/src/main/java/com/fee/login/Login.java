package com.fee.login;

import java.io.IOException;
import java.io.PrintWriter;

import com.fee.accountant.Accountant;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/Login")
public class Login extends HttpServlet{
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();
				req.getRequestDispatcher("header.html").include(req, res);
				try {
				ServletContext sc = getServletContext();
				String adminMail = sc.getInitParameter("email");
				String adminPass = sc.getInitParameter("pass");
				String email = req.getParameter("email");
				String pass = req.getParameter("password");
				HttpSession se = req.getSession(true);
				int count = Accountant.access(email, pass);
				if(email.equals(adminMail) && pass.equals(adminPass)) {
					se.setAttribute("adminemail",adminMail);
					res.sendRedirect("AdminAccess");
				}else if(count > 0){
					se.setAttribute("email",email);
					res.sendRedirect("AccountantPanel");
				}else {
					pw.println("<p>Invalid Username or Password</p>");
					req.getRequestDispatcher("index.html").include(req, res);
				}
				}catch (Exception e) {
					System.out.println("Exception in Login:\n\t\t"+e.getLocalizedMessage());
				}
		}
}
