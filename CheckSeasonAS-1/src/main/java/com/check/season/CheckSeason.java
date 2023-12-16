package com.check.season;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.time.LocalDateTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class CheckSeason extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Date d =new Date();
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		LocalDateTime ld = LocalDateTime.now();
		int month = ld.getMonthValue();
		res.setHeader("refresh","1");
		pw.print("<center>");
		if(month<=5 && month>2)
			pw.println("<h1 style = color:red>Summer Season Feb to May</h1>");
		else if(month<9 && month > 5)
			pw.println("<h1 style = color:Orange>Monsoon Season June to Aug</h1>");
		else
			pw.println("<h1 style = color:Green>Winter season Sept to Jan</h1>");
		pw.print("</center>");
		
		pw.println("<h3 style=\"text-align: right;color: black;\">"+d+"</h3>");
		pw.println("<h3><a href = 'Season.html'>Home</a></h3>");
	}
}
