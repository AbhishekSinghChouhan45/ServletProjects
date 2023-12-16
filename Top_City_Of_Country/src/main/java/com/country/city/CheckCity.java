package com.country.city;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class CheckCity extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)  {
			try{PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			String cityname[][]= {{"Delhi","Bhopal","Ahemdabad","Indore","Chennai"},
								  {"New York City","Los Angeles","Chicago, IL","Houston, TX","Phoenix, AZ "},
								  {"Canberra","Adelaide","Brisbane","Darwin","Gold Coast"},
								  {"Paris","Nice","Lyon","Bordeaux","Marseilles"},
								  {"Karachi","Lahore","	Faisalabad","Rawalpindi","Gujranwala"},
								};
			int country = Integer.parseInt(req.getParameter("country"));
			pw.println("Top city name of: ");
			for(int i=0;i<cityname.length;i++) {
			pw.print("<center><h1 style='color:red';>"+cityname[country][i]+"</h1></center>");
			}
			pw.println("<a href='CountrySite.html'>Home</a>");
			pw.close();
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
	}
}
