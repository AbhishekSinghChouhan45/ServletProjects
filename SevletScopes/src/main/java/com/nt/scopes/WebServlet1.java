package com.nt.scopes;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
@WebServlet("w1")
public class WebServlet1 extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Request Scope
		req.setAttribute("attr1","Value 1");
		//Session Scope
		HttpSession se = req.getSession();
		se.setAttribute("attr2","value 2");
		//Application Scope
		ServletContext sc = req.getServletContext();
		sc.setAttribute("attr3","Value 3");
		RequestDispatcher rd = req.getRequestDispatcher("/w2");
		rd.forward(req, resp);
		
	}
}
