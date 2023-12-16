package com.vote.checker;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class VoterEligibility extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("vname");
		String address = req.getParameter("vadd");
		String age = req.getParameter("vage");
		int iage = Integer.parseInt(age);
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		if(iage >= 18)
			pw.println("Hey "+name+" You'r able to vote");
		else
			pw.println("Hey "+name+" You'r not able to vote");
		pw.println("<a href='Voting.html'>Home</a>");
	
	}
}
