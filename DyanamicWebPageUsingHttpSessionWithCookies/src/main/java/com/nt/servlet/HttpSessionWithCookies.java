package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/first")
public class HttpSessionWithCookies extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String name = req.getParameter("name");
		HttpSession session = req.getSession(true);
		session.setAttribute("name", name);
		session.setAttribute("age", req.getParameter("age"));
		String ms = req.getParameter("ms");
		if(ms.equalsIgnoreCase("married")) {
			pw.println("<form method='GET' action='secondurl'>");
			pw.println("<table align='center' style='background-color: Green;'>");
			//Wife Name
			pw.println("<tr>");
			pw.println("<td>Enter Wife Name: </td>");
			pw.println("<td><input type='text' name='t1v1'></td>");
			pw.println("</tr>");
			//No of kids
			pw.println("<tr>");
			pw.println("<td>No of kids ?: </td>");
			pw.println("<td><input type='text' name='t1v2'></td>");
			pw.println("</tr>");
			//submit
			pw.println("<tr>");
			pw.println("<td><input type='submit' value='Submit'></td>");
			pw.println("</tr>");
			
			pw.println("</table>");
			pw.println("</form>");
		}
		else {
			pw.println("<form method='GET' action='secondurl'>");
			pw.println("<table align='center' style='background-color: Green;'>");
			//Wife Name
			pw.println("<tr>");
			pw.println("<td>When you'r going to marry:</td>");
			pw.println("<td><input type='text' name='t1v1'></td>");
			pw.println("</tr>");
			//No of kids
			pw.println("<tr>");
			pw.println("<td>Why you'r going to marry:</td>");
			pw.println("<td><input type='text' name='t1v2'></td>");
			pw.println("</tr>");
			//submit
			pw.println("<tr>");
			pw.println("<td><input type='submit' value='Submit'></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
		}
		
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
