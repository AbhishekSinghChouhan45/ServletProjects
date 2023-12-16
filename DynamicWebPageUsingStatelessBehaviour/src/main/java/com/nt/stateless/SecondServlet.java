package com.nt.stateless;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String name = req.getParameter("name");
		String add = req.getParameter("add");
		String age = req.getParameter("age");
		String v1 = req.getParameter("t1v1");
		String v2 = req.getParameter("t1v2");
		pw.println("<h1 style='align:center; color: green;'>"+name+"...."+add+".... "+age+"</h1>");
		pw.println("<h1 style='align:center; color: red;'>"+v1+"...."+v2+"</h1>");
		pw.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
