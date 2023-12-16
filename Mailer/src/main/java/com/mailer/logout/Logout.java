package com.mailer.logout;

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
	public  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			HttpSession se = req.getSession(false);
			Thread.sleep(2000);
			se.invalidate();
		}catch (Exception e) {
			System.out.println("Exception in Logout:\n\t\t"+e.getLocalizedMessage());
		}
		req.getRequestDispatcher("index.html").include(req, res);
	}
}
