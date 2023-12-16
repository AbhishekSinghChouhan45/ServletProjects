package com.nt.vote;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("vote")
public class Voting extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String hidden = req.getParameter("hidden");
		String age = req.getParameter("age");
		int iage = 0;
		if(hidden.equals("disabled")) {
			if(age.equals("") || age.equals(null)) {
				pw.println("<h1 style='color: red; text:align: center;'>Please Enter Age</h1>");
			}
			else{
				try {
					iage = Integer.parseInt(age);
				}catch (NumberFormatException e) {
					pw.println("<h1 style='color: red; text:align: center;'>Please Enter Numeric Age</h1>");
				}
			}
		}
		else {
			ServletConfig cg = getServletConfig();
			iage = Integer.parseInt(age);
			if(iage < 18)
				pw.println("<h1 style='color: red; text:align: center;'>Your not eligible to vote</h1>");
			else	
				pw.println("<h1 style='color: green; text:align: center;'>Your eligible to vote</h1>");
		}
	}

}
