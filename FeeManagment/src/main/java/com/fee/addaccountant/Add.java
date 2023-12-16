package com.fee.addaccountant;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Add")
public class Add extends HttpServlet {
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
				try {
						String name = req.getParameter("acname");
						String pass = req.getParameter("acpass");
						String email = req.getParameter("email");
						String add = req.getParameter("acaddress");
						String contact = req.getParameter("accontact");
						int status = AddingDAO.getLog(name, pass, email, add, contact);
						if(status > 0) {
							if(status == 5) {
								pw.println("<p style='color: Red;'>Accountant already registered with this mail</p>");
								req.getRequestDispatcher("AddAccountant.html").include(req, res);
							}else {
								pw.println("<p style='color: Green;'>Accountant registered successfully</p>");
								req.getRequestDispatcher("AddAccountant.html").include(req, res);
							}
						}else {
							pw.println("<p style='color: Red;'>Some problem while registring</p>");
							req.getRequestDispatcher("AddAccountant.html").include(req, res);
						}
				}catch (Exception e) {
					System.out.println("Exception in Add:\n\t\t"+e.getMessage());
				}
				req.getRequestDispatcher("footer.html").include(req, res);
		}
}
