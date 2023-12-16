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
@WebServlet("w2")
public class WebServlet2 extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		RequestDispatcher rd = req.getRequestDispatcher("/w3");
		HttpSession ses = req.getSession();
		ServletContext sc = req.getServletContext();
		pw.println("<b>WebServlet 2 attr1 "+req.getAttribute("attr1")+"<b><br>");
		pw.println("<b>WebServlet 2 attr2 "+ses.getAttribute("attr2")+"<b><br>");
		pw.println("<b>WebServlet 2 attr3 "+sc.getAttribute("attr3")+"<b><br>");
		rd.forward(req, res);
	}
}
