package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/secondurl")
public class SecondUrl extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession(false);
		String name =(String)session.getAttribute("name");
		String age =(String)session.getAttribute("age");
		session.getAttribute("age");
		
//		String name = req.getParameter("name");
//		String age = req.getParameter("age");
		String v1 = req.getParameter("t1v1");
		String v2 = req.getParameter("t1v2");
		pw.println("<h4 style='text-align:center; color: gold;'>Name: "+name+"</h4>");
		pw.println("<h4 style='text-align:center; color: gold;'>Age: "+age+"</h4>");
		pw.println("<h4 style='text-align:center; color: gold;'>V1: "+v1+"</h4>");
		pw.println("<h4 style='text-align:center; color: gold;'>V2: "+v2+"</h4>");
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
