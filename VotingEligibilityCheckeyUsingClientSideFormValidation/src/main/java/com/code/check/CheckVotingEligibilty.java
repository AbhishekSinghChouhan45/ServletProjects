package com.code.check;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class CheckVotingEligibilty extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String name = req.getParameter("name");
		String age  = req.getParameter("age");
		String gender = req.getParameter("gender");
		
	    int iage = Integer.parseInt(age);
			if(iage<18) {
				 pw.println("<h1 style='color: red; text-align: center;'>"+name+" you'r not Eligible for vote</h1>");
			 }
			 else
				 pw.println("<h1 style='color: red; text-align: center;'>"+name+" you'r Eligible for vote</h1>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
	}

}
