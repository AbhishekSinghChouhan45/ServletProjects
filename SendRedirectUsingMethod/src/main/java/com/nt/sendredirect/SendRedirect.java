package com.nt.sendredirect;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/sc")
public class SendRedirect extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String search = req.getParameter("search");
		String engine = req.getParameter("engine");
		if(engine.equalsIgnoreCase("google"))
			res.sendRedirect("https://www.google.com/search?q="+search);
		else if(engine.equalsIgnoreCase("yahoo"))
			res.sendRedirect("https://search.yahoo.com/search?p="+search);
		else
			res.sendRedirect("https://www.bing.com/search?q="+search);
		pw.close();
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
