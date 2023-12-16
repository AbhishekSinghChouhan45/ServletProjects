package com.fee.logout;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/Logout")
public class Logout extends HttpServlet {
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					doGet(req, resp);
		}
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
					res.setContentType("text/html");
					PrintWriter pw = res.getWriter();
					HttpSession se = req.getSession();
					try {
						Thread.sleep(1000);
						se.invalidate();
						res.sendRedirect("index.html");
					}catch (Exception e) {
						System.out.println("Exception in Logout:\n\t\t"+e.getMessage());
					}
		}
}
