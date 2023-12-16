package com.code.check;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class CheckVotingEligibilty extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String name = req.getParameter("name");
		String age  = req.getParameter("age");
		String gender = req.getParameter("gender");
		int iage =0;
		List<String> errMsg = new ArrayList<String>();
		if(name.equals("")) {
			errMsg.add("Please Enter Your Name");
		}
		else if(name.length()>20) {
			errMsg.add("Name can be only less than 20 character");
		}
		if(age.equals("")) {
			errMsg.add("Please Enter Age");
		}
		else{
			try {
				    iage = Integer.parseInt(age);
				if(iage<5 || iage>125) {
					errMsg.add("Age can not be <5 and >125");
				}
			}catch (NumberFormatException e) {	errMsg.add("Enter only numeric value");		}
		}
		if(gender.equals("") || gender==null) {
			errMsg.add("Please Validate Gender");
		}
		 if(errMsg.size()!=0) {
			 pw.println("<ol style='color: red;'");
			 for(String err:errMsg) {
				 pw.println("<h3 style='color:red; text-align: center;'>"+err+"</h3>"); 
			 } 
			 pw.println("</ol>");
		 }
		 else {
			 	iage = Integer.parseInt(age);
			 if(iage<18) {
				 pw.println("<h1 style='color: red; text-align: center;'>"+name+" you'r not Eligible for vote</h1>");
			 }
			 else
				 pw.println("<h1 style='color: red; text-align: center;'>"+name+" you'r Eligible for vote</h1>");
		 }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
	}

}
