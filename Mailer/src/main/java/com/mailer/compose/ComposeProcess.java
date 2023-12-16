package com.mailer.compose;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/ComposeProcess")
public class ComposeProcess extends HttpServlet {
			@Override
			public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doGet(req, resp);
			}
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
					res.setContentType("text/html");
					PrintWriter pw = res.getWriter();
					HttpSession se = req.getSession(false);
					String msg = (String)req.getAttribute("msg");
					String emails = (String)se.getAttribute("email");
					String adminMail = (String)se.getAttribute("adminMail");
						if(emails.equals(adminMail)) {
							req.getRequestDispatcher("header.html").include(req, res);
							req.getRequestDispatcher("links.html").include(req, res);
						}else {
							req.getRequestDispatcher("header.html").include(req, res);
							req.getRequestDispatcher("link.html").include(req, res);
						}
					pw.print("<h5 style='text-align: right'>Hi "+se.getAttribute("email")+"</h5>");
					if(msg!=null) {
						pw.println("<p>"+msg+"</p>");
					}
					req.getRequestDispatcher("composeform.html").include(req, res);
					req.getRequestDispatcher("footer.html").include(req, res);
			}
}
