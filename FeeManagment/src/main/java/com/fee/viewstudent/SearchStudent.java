package com.fee.viewstudent;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/SearchStudent")
public class SearchStudent extends HttpServlet {
	    @Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
					res.setContentType("text/html");
					PrintWriter pw = res.getWriter();
					req.getRequestDispatcher("header.html").include(req, res);
					req.getRequestDispatcher("link.html").include(req, res);
					req.getRequestDispatcher("SerchStudent.html").include(req, res);
//					try {
//						
//					}
					req.getRequestDispatcher("footer.html").include(req, res);
		}
}
