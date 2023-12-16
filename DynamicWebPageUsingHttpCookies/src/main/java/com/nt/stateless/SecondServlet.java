package com.nt.stateless;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
		Cookie ck[] = req.getCookies();
		if(ck==null) {
			pw.println("<h1 style='text-align:center; color:red;'>Cookies are deleted</h1>");
		}
		else {
		pw.println("<table align='center' border='2'>");
		pw.println("<tr><th>name</th><th>value</th></tr>");
		for(Cookie ckk:ck) {
			pw.println("<tr><td>"+ckk.getName()+"</td><th>"+ckk.getValue()+"</th></tr>");
		}
		pw.println("</table>");
		}
		pw.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
