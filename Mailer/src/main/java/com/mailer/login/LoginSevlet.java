package com.mailer.login;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/LoginServlet")
public class LoginSevlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			req.getRequestDispatcher("header.html").include(req, res);
			String email = req.getParameter("email");
			String pass = req.getParameter("password");
			
		HttpSession se = req.getSession(true); 
		ServletContext cg = getServletContext();
		String adminmail = cg.getInitParameter("mail");
		String adminpass = cg.getInitParameter("pass");
		boolean get = LoginDAO.getLog(email, pass);
		se.setAttribute("adminMail", adminmail);
		se.setAttribute("adminPass", adminpass);
		se.setAttribute("email", email);
		se.setAttribute("password", pass);
		if(email.equals(adminmail) && pass.equals(adminpass)){
				res.sendRedirect("AdminAccess");
		}
		else if(get==true) {
				res.sendRedirect("inbox");
			}
		else{
//				pw.println("<p>Check Username or Password");
				String msg = LoginDAO.getMsg();
				pw.println("<p>"+msg+"</p>");
				req.getRequestDispatcher("login.html").include(req, res);
			}
			req.getRequestDispatcher("footer.html").include(req, res);
	}

}
