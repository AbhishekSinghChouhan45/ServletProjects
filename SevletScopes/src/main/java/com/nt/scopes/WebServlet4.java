package com.nt.scopes;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("w4")
public class WebServlet4 extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession ses = req.getSession();
		ServletContext sc = req.getServletContext();
		pw.println("<b>WebServlet 4 attr1 "+req.getAttribute("attr1")+"<b><br>");
		pw.println("<b>WebServlet 4 attr2 "+ses.getAttribute("attr2")+"<b><br>");
		pw.println("<b>WebServlet 4 attr3 "+sc.getAttribute("attr3")+"<b><br>");
	}
}
