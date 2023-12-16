package com.code.vote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class VotingChecker extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String ClientSideValidation = req.getParameter("vhidden");
		String name = req.getParameter("vname");
		String vage = req.getParameter("vage");
		String gender = req.getParameter("vgender");
		int age = 0;
		if(ClientSideValidation.equalsIgnoreCase("Disable")) {
			List<String> err = new ArrayList();
			if(name.equals("") || name==null) {
				err.add("Must Enter Name");
			}
			else if (name.length()<5 || name.length()>20) {
				err.add("Name can not be < 5 && > 20");
			}
			if(vage.equals("") || vage==null) {
				err.add("Age can not be empty");
			}
			else {
				try {
					age = Integer.parseInt(vage);
					if(age<5||age>125) {
						err.add("Age can not be < 5 && >125");
					}
				}catch (NumberFormatException e) {
					err.add("Enter Only Values");
				}
			}
			if(gender.equals("") || gender == null) {
				err.add("Gender can not be empty");
			}
			if(err.size()!=0) {
			for(String msg:err) {
				pw.println("<ol style='color:red; text-align:center;'>");
				pw.println("<ul>"+msg+"</ul>");
				pw.println("</ol>");
			}
			return;
			
			}
		}
		else {
			age = Integer.parseInt(vage);
		}
		//Writing Bussiness Logic
		if(age>=18)
			pw.println("<h1 style='color: red; text-align: center'>Hey "+name+" you'r eligible for vote</h1>");
		else
			pw.println("<h1 style='color: red; text-align: center'>Hey "+name+" you'r not eligible for vote</h1>");
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
