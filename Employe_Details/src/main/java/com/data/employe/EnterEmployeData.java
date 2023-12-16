package com.data.employe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class EnterEmployeData extends HttpServlet {
	private static final String Query = "Insert into employe_info(employe_name,employe_address,employe_age,employe_dob,employe_gender,employe_sal) values(?,?,?,?,?,?)";
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employe","root","root");
				PreparedStatement ps = con.prepareStatement(Query);
				Scanner sc = new Scanner(System.in);){
				if(con!=null && ps !=null && sc!=null) {
					if(req.getParameter("ename")=="" || req.getParameter("eage")=="" || req.getParameter("eadd")=="" || req.getParameter("edate")=="" || req.getParameter("egender")=="" || req.getParameter("esal")=="" ) { 
					pw.println("<h1 style='color: red; text-align: center;'>Please Enter All Details First</h1>");
					}
					else {
						int count =0;
						String name = req.getParameter("ename");
						int age = Integer.parseInt(req.getParameter("eage"));
						String add = req.getParameter("eadd");
						String dob = req.getParameter("edate");
						String gen = req.getParameter("egender");
						float sal = Float.parseFloat(req.getParameter("esal"));
						Date date = Date.valueOf(dob);
					if(age>18) {
						ps.setString(1,name); 
						ps.setString(2,add);
						ps.setInt(3,age);
						ps.setDate(4,date);
						ps.setString(5,gen);
						ps.setFloat(6,sal);
						count = ps.executeUpdate();
					}else 
							pw.println("<h1 style='color:red; text-align: center;'>Age < 18 can not be entered</h1>");
					if(count >=1)
						pw.println("<h1 style='color: green; text-align: center;'>Employe Registration Successfull</h1>");
					else 
						pw.println("<h1 style='color: Red; text-align: center;'>Employe Registration Unsuccessfull</h1><br>");
				    }
				}
				else 
					pw.println("Connection Not Established");
			}catch (Exception e) {
				pw.println(e.getLocalizedMessage());
			}
			
			pw.println("<a style='color: red; text-align: center;' href='FrontEmploye.html'> Home </h1><br>");
			pw.println("<a style='color: red; text-align: center;' href='EmployeRegistration.html'> Back </h1>");
			pw.close();
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doGet(req, res);
//			res.setContentType("text/html");
//			PrintWriter pw = res.getWriter(); 
//			try {
//				String date = req.getParameter("edate");
////					SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
////				   java.util.Date udob=sdf1.parse(Date);
////				   long ms=udob.getTime();
////				   java.sql.Date sqdob=new java.sql.Date(ms);
////				   
//				   SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
//	                java.util.Date ud1 = sdf1.parse(date);
//	                long ms1 = ud1.getTime();
//	                java.sql.Date sqd1 = new java.sql.Date(ms1);
//	                
//	                Date d = Date.valueOf(date);
//				pw.println("SQLnDate : "+sqd1);
//				pw.println("SQLDate : "+d);
//				pw.println("Dated : "+date);
//			} catch (ParseException e) {pw.println(e.getLocalizedMessage());			}
			
		}
}
