package com.mailer.trash;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mailer.register.ConnectionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Trashing")
public class Trashing extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			try {
				String id = req.getParameter("id");
				Connection con = ConnectionDAO.getCon();
				PreparedStatement ps = con.prepareStatement("update company_mailer_message SET Trash = (?) Where id = (?)");
				ps.setString(1, "trash");
				ps.setString(2, id);
				int count = ps.executeUpdate();
				if(count>0) {
					req.setAttribute("msg","Trashed Successfully");
					req.getRequestDispatcher("Trash").forward(req, res);
				}else {
					req.setAttribute("msg","Error in Trashing");
					req.getRequestDispatcher("Trash").forward(req, res);
				}
			}catch (Exception e) {
				System.out.println("Exception in Trashing:\n\t\t"+e.getLocalizedMessage());
			}
	}
}
