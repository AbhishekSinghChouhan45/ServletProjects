package com.todo.list;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/list")
public class Todo_list extends HttpServlet {
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			try {
			String check = req.getParameter("submit");
			if(check.equalsIgnoreCase("Add TODO")) {
				RequestDispatcher rd = req.getRequestDispatcher("addtodo.html");
				rd.include(req, res);
			}else if(check.equalsIgnoreCase("Get TODO")){
				RequestDispatcher rd = req.getRequestDispatcher("/gettodo");
				rd.include(req, res);
			}else if(check.equalsIgnoreCase("Edit TODO")){
				System.out.println("Editing to do");
			}else{
				System.out.println("Deleting to do");
			}
			}catch (Exception e) {
				System.out.println("tlist "+e.getLocalizedMessage());
			}
			
		}
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
		
}
