package com.fee.addaccountant;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/AddAccountant")
public class AddAccountant extends HttpServlet {
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
					req.getRequestDispatcher("AddAccountant.html").include(req, res);
					req.getRequestDispatcher("footer.html").include(req, res);
		}
}
