package com.fee.admin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/AdminAccess")
public class AdminAccess extends HttpServlet {
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();
				try {
						req.getRequestDispatcher("header.html").include(req, res);
						req.getRequestDispatcher("links.html").include(req, res);
						
				}catch (Exception e) {
					System.out.println("Exception in AdminAccess:\n\t\t"+e.getLocalizedMessage());
				}
		}
}
