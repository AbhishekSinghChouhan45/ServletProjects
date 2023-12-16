package com.nt.mime;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
public class XmlServlet extends HttpServlet{
	static {
		System.out.println("IN SB XMl");
	}
	@Override
	public void destroy() {
		System.out.println("IN Destroy xml");
	}
	protected void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/xml");
		PrintWriter pw = res.getWriter();
		System.out.println("In service xml");
		pw.println("<table border = '1' align='centre' bgcolor = 'Red'>");
		pw.println("<tr><th>Movie</th><th>Actor</th><th>Actress</th><th>Collection</th></tr>");
		pw.println("<tr><td>Tiger Zinda Hai</td><td>Salman</td><td>Katrina</td><td>200Cr</td></tr>");
		pw.println("<tr><td>Kantara</td><td>Rishabh Shetty</td><td>Unknown</td><td>350Cr</td></tr>");
		pw.println("<tr><td>3Idiots</td><td>Amir khan</td><td>Kareena</td><td>150Cr</td></tr>");
		pw.println("<tr><td>Dangal</td><td>Amir khan</td><td>Unkown</td><td>1200Cr</td></tr>");
		pw.println("<tr><td>Avenger's Endgame</td><td>Tonny stark</td><td>Unkown</td><td>2200Cr</td></tr>");
		pw.println("</table>");
		pw.close();
	}
	@Override
	public void init() throws ServletException {
		System.out.println("In init xml");
	}
}
