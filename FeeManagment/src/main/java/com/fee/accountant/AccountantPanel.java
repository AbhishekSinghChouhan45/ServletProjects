package com.fee.accountant;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/AccountantPanel")
public class AccountantPanel extends HttpServlet {
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
					res.setContentType("text/html");
					PrintWriter pw = res.getWriter();
				try {
					HttpSession se = req.getSession();
					String msg = (String) se.getAttribute("msg");
					req.getRequestDispatcher("header.html").include(req, res);
					req.getRequestDispatcher("link.html").include(req, res);
					if(msg!=null) {
						pw.println("<p>"+msg+"</p>");
					}
					req.getRequestDispatcher("student.html").include(req, res);
				}catch (Exception e) {
					System.out.println("Exception in AccountantPanel:\n\t\t"+e.getMessage());
				}
					req.getRequestDispatcher("footer.html").include(req, res);
		}
}
