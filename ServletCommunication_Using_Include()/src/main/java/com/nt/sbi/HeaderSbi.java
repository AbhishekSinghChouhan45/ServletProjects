package com.nt.sbi;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HeaderSbi extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			pw.println("<h1><marquee style='color: blue;'>State Bank Of India</marquee></h1>");
			pw.println("<table align='center'style='background-color: darkblue;' border='1'>");
					pw.println("<tr style='color: white;'>");
					pw.println("<td>Services</td>");
					pw.println("<td>Faq</td>");
					pw.println("<td>Corporate Website </td>");
					pw.println("<td>Donations</td>");
					pw.println("<td>SB Collect</td>");
					pw.println("<td>NPS</td>");
					pw.println("<td>SBI Unipays</td>");
					pw.println("<td>SBI Loans</td>");
					pw.println("</tr>");
					pw.println("</table>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
