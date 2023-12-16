package com.code.arthmetic;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class Arthmetics extends HttpServlet {
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			String val = req.getParameter("s1");
			LocalDateTime ld = LocalDateTime.now();
			int a=0,b=0;
			if(!val.equalsIgnoreCase("link1")&&!val.equalsIgnoreCase("link2")){
				try {
				a = Integer.parseInt(req.getParameter("t1"));
				b = Integer.parseInt(req.getParameter("t2"));
				}catch (NumberFormatException e) {
					pw.println("<h1 style='color:Orange; text-align:center;>Please Enter Values</h1>");
					pw.println("<h1 style='color:Orange; text-align:center;>Please Enter Values First</h1>");
				}
			}
			if(val.equalsIgnoreCase("add")) {
			   pw.println(a+b);
			}
			else if(val.equalsIgnoreCase("sub")) {
				pw.println(a-b);
			}
			else if(val.equalsIgnoreCase("mul")) {
				pw.println(a*b);
			}
			else if(val.equalsIgnoreCase("div")) {
				pw.println(a/b);
			}
			else if(val.equalsIgnoreCase("link1")) {
				int hour = ld.getHour();
				if(hour<12)
					pw.println("<h1 style='color:red; text-align:center;'>Good Morning");
				else if(hour<16)
					pw.println("<h1 style='color:Green; text-align:center;'>Good Afternoon");
				else if(hour<20)
					pw.println("<h1 style='color:Orange; text-align:center;'>Good Evening");
				else
					pw.println("<h1 style='color:black; text-align:center;'>Good Night");
			}
			else if(val.equalsIgnoreCase("link2")) {
				int month = ld.getMonthValue();
				if(month>=2 && month<=5)
					pw.println("<h1 style='color:red; text-align:center;'>Summer Season");
				else if(month>=6&&month<=9)
					pw.println("<h1 style='color:Green; text-align:center;'>Rainy Season");
				else
					pw.println("<h1 style='color:black; text-align:center;'>Winter Season");
			}
			pw.println("<h2 style='color:red; text-align:right;'><a href='Arthmethic.html'>Home<a></h2>");
			pw.close();
		}
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
}
