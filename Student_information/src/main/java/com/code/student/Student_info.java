package com.code.student;
import java.awt.Cursor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class Student_info extends HttpServlet{
	private static final String C_TO_CALL_STUDENT_BY_ID = "{CALL P_TO_READ_STUDENT_INFO_BY_ID(?)}";
	private static final String P_TO_CALL_STUDENT_BY_ID = "SELECT Student_id,Student_name,Student_class,Student_address,Student_fathername,Student_number,Student_joiningdate from Student_info WHERE Student_id = (?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		try(InputStream inputstream = new FileInputStream("D:\\Adv java by natraj sir\\03 Adv java workspace-servlet\\Student_information\\src\\main\\java\\com\\data\\common\\Student.properties");
				PrintWriter pw = res.getWriter();
				){
				res.setContentType("text/html");
				Properties properties =  new Properties();
				if(inputstream != null)
						properties.load(inputstream);
					try(Connection con = DriverManager.getConnection(properties.getProperty("Student.url"),properties.getProperty("Student.username"),properties.getProperty("Student.password"));
//						PreparedStatement preparestatement = con.prepareStatement(P_TO_CALL_STUDENT_BY_ID,ResultSet.TYPE_SCROLL_SENSITIVE);
							CallableStatement preparestatement = con.prepareCall(C_TO_CALL_STUDENT_BY_ID);
						Scanner sc = new Scanner(System.in);
					){
						int sid = Integer.parseInt(req.getParameter("sid"));
						preparestatement.setInt(1,sid);
						ResultSet resultset = preparestatement.executeQuery();
						ResultSetMetaData rsmd = resultset.getMetaData();
						resultset.next();
						if(con!=null && sc != null)
							pw.println("<table border = '1' align='center' bgcolor = 'orange'>");
								pw.println("<h1><caption style='color:red;'>DPS Student Information</caption></h1>");
								pw.println("<tr><th>"+rsmd.getColumnName(1)+"</th><th>"+rsmd.getColumnName(2)+"</th><th>"+rsmd.getColumnName(3)+"</th><th>"+rsmd.getColumnName(4)+"</th><th>"+rsmd.getColumnName(5)+"</th><th>"+rsmd.getColumnName(6)+"</th><th>"+rsmd.getColumnName(7)+"</th></tr>");
								pw.println("<tr><td>"+resultset.getString(1)+"</td><td>"+resultset.getString(2)+"</td><td>"+resultset.getString(3)+"</td><td>"+resultset.getString(4)+"</td><td>"+resultset.getString(5)+"</td><td>"+resultset.getString(6)+"</td><td>"+resultset.getString(7)+"</td></tr>");
							pw.println("</table>");
							pw.println("<a href='Student.html'>Home Page</a>");
					}catch (SQLException e ) { 
						if(e.getErrorCode()==1045)
							pw.println("Please check you'r username or password");
						else if(e.getErrorCode()==1049)
							pw.println("Please check you'r url"); 
						else if(e.getErrorCode()==0)
							pw.println("No student found of student number "+req.getParameter("sid"));
						else
							pw.println("Some database problem"); 
					}
				
			}catch (Exception e) {
				System.out.println(e.getLocalizedMessage() +""+e.getLocalizedMessage());
			}
	
	}

}
