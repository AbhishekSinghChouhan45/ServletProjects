package com.nt.sendredirection;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/sc")
public class SendRedirection extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String search = req.getParameter("search");
		String engine = req.getParameter("engine");
		if(engine.equalsIgnoreCase("google"))
			pw.println("<h1 style='text-align: center; color: blue;'><a href='https://www.google.com/search?q="+search+"'>Search in Google</a></h1>");
		else if(engine.equalsIgnoreCase("yahoo"))
			pw.println("<h1 style='text-align: center; color: blue;'><a href='https://search.yahoo.com/search?p="+search+"'>Search in Yahoo</a></h1>");
		else
			pw.println("<h1 style='text-align: center; color: blue;'><a href='https://www.bing.com/search?q="+search+"'>Search in Bing</a></h1>");
			
		pw.close();
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
