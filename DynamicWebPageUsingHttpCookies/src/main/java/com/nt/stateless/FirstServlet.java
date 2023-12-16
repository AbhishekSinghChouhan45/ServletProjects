package com.nt.stateless;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck1 = new Cookie("TN","Andhra");
		res.addCookie(ck1);
		Cookie ck2 = new Cookie("name","abhi");
		res.addCookie(ck2);
		Cookie ck3 = new Cookie("age","20");
		ck3.setMaxAge(60);
		res.addCookie(ck3);
		Cookie ck4 = new Cookie("status","singal");
		ck4.setMaxAge(90);
		res.addCookie(ck4);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
