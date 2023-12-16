package com.nt.servlet;

import jakarta.servlet.*;
import java.io.*;
import java.util.*;

class DateServlet extends GenericServlet{
    public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
      res.setContentType("text/html");
      PrintWriter pw = res.getWriter();
      Date d = new Date();
	pw.println(d);
	pw.close(); 
     }
}