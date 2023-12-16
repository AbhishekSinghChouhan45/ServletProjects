package com.code.wish;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WishingApp extends HttpServlet {
@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		LocalDateTime ld = LocalDateTime.now();
		int hour = ld.getHour();
		if(hour<12)
			pw.print("<h1 style ='color:orange;text-align:center'>Good Morning "+req.getParameter("wname")+"</h1>");
		else if(hour<16)
			pw.print("<h1 style ='color:Red;text-align:center'>Good Afternoon "+req.getParameter("wname")+"</h1>");
		else if(hour<20)
			pw.print("<h1 style ='color:Green;text-align:center'>Good Evening "+req.getParameter("wname")+"</h1>");
		else
			pw.print("<h1 style ='color:Black;text-align:center'>Good Night "+req.getParameter("wname")+"</h1>");
		
		pw.println("<a href ='wish.html'>Home</a>");
	}
}
