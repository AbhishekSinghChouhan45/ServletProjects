package com.mailer.compose;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/ComposeServletProcess")
public class ComposeMail extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession se = req.getSession(false);
		String emails = (String)se.getAttribute("email");
		String adminMail = (String)se.getAttribute("adminMail");
			if(emails.equals(adminMail)) {
				req.getRequestDispatcher("header.html").include(req, res);
				req.getRequestDispatcher("links.html").include(req, res);
			}else {
				req.getRequestDispatcher("header.html").include(req, res);
				req.getRequestDispatcher("link.html").include(req, res);
			}
		
		try {
		String sender = (String)se.getAttribute("email");
		String receiver = req.getParameter("to");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		int status = composeDAO.composeMail(sender, receiver, subject, message);
		if(status > 0) {
			req.setAttribute("msg","Mail Sended Suucessfully");
			req.getRequestDispatcher("ComposeProcess").forward(req, res);
//			pw.println("<p>Mail Sended Suucessfully</P>");
		}
		else {
			req.setAttribute("msg","Receiver Mail Not Found");
			req.getRequestDispatcher("ComposeProcess").forward(req, res);
//			pw.println("<p>Receiver Mail Not Found</P>");
		}
		req.getRequestDispatcher("footer.html").include(req, res);
	}catch (Exception e) {
		System.out.println("Exception in composeMail: \n\t\t"+e.getLocalizedMessage());
	}
	}
}
