import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.tomcat.jdbc.pool.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/emp")
public class Database extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		int eid = Integer.parseInt(req.getParameter("enum"));
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employe","root","root");
			ps = con.prepareStatement("Select employe_id,employe_name,employe_address,employe_age,employe_dob from employe_info Where employe_id = (?)");
			ps.setInt(1, eid);
			rs = ps.executeQuery();
			if(rs.next()) {
				pw.println("<table align='center' border='2' style='background-color: green;'>");
				pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
				pw.println("</table>");
			}else {
				pw.println("<h1 style='color:red; text-align:center;'>No Employe Found</h1>");
			}
			
		}catch (Exception e) {
			System.out.println("DB Probleem "+e.getLocalizedMessage());
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			try {
				if(ps!=null)
					ps.close();
			}catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			try {
				if(con!=null)
					con.close();
			}catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			try {
				if(pw!=null)
					pw.close();
			}catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
