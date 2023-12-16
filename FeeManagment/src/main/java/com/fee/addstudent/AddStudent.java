package com.fee.addstudent;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doGet(req, resp);
		}
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();
				req.getRequestDispatcher("header.html").include(req, res);
				req.getRequestDispatcher("link.html").include(req, res);
				HttpSession se = req.getSession(false);
				try {
						String name = req.getParameter("name");
						String email = req.getParameter("email");
						String sex = req.getParameter("sex");
						String course = req.getParameter("course");
						long fee = Long.parseLong(req.getParameter("fee"));
						long paid = Long.parseLong(req.getParameter("paid"));
						long due = fee - paid;
						String add = req.getParameter("addressLine");
						String contact = req.getParameter("contact");
						int status = AddStudentDAO.add(name, email, sex, course, fee, paid, due, add, contact);
						if(status > 0) {
							if(status ==5) {
								se.setAttribute("msg","Minimum amount for "+course+" is "+fee / 2);
								res.sendRedirect("AccountantPanel");
							}else {
								se.setAttribute("msg","Student Registered for course:"+course );
								res.sendRedirect("AccountantPanel");
							}
						}else {
							se.setAttribute("msg","Student Not Registered for course: "+course);
							res.sendRedirect("AccountantPanel");
						}
				}catch (Exception e) {
					System.out.println("Exception in AddStudent:\n\t\t"+e.getMessage());
				}
				req.getRequestDispatcher("footer.html").include(req, res);
				
		}
}
