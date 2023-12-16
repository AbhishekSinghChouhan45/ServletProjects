package com.mailer.delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mailer.register.ConnectionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/DeleteMailServlet")
public class Trash extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			int id  = Integer.parseInt(req.getParameter("id"));
			try {
					Connection con = ConnectionDAO.getCon();
					PreparedStatement ps = con.prepareStatement("update company_mailer_message set trash=? where id=?");
					ps.setString(1, "yes");
					ps.setInt(2, id);
					int count = ps.executeUpdate();
					if(count > 0) {
						req.setAttribute("msg","Mail Deleted Succefully!");
						req.getRequestDispatcher("inbox").include(req, res);
					}
			}catch (Exception e) {
				System.out.println("Exception in Trash: \n\t\t"+e.getLocalizedMessage());
			}
	}
}
