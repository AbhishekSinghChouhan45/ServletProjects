package com.mailer.register;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
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
//					ServletContext sc = req.getServletContext();
							HttpSession se= req.getSession(false);
							String name=req.getParameter("name");
							String password=req.getParameter("password");
							String email=req.getParameter("email");
							String gender=req.getParameter("gender");
							String dob=req.getParameter("dob");
							String addressLine=req.getParameter("addressLine");
							String city=req.getParameter("city");
							String state=req.getParameter("state");
							String country=req.getParameter("country");
							String contact=req.getParameter("contact");			
							int status = RegisterDAO.register(name, email, password, gender, dob, addressLine, city, state, country, contact);
							if(status > 0) {
								if(status==5) {
									pw.println("<p>Already registerd email");
									req.getRequestDispatcher("login.html").include(req, res);
								}
								else {
								pw.println("<p>Request Forwrded Successfully");
								req.getRequestDispatcher("login.html").include(req, res);
								}
							}
							req.getRequestDispatcher("footer.html").include(req, res);
					}catch (Exception e) {
						System.out.println("Exception in RegisterServlet:\n\t\t"+e.getLocalizedMessage());
					} 
		}
}
