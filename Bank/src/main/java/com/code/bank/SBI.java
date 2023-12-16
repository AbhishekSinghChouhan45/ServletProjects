package com.code.bank;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class SBI extends HttpServlet {
	private Connection con = null;
	private ResultSet rs = null;
	private PrintWriter pw =null;
	private PreparedStatement ps =null;
	private Scanner sc = null;
	private String name =null;
	private String address =null;
	private String number =null;
	private String gender =null;
	private String dob =null;
	private String balance =null;
	private String username =null;
	private String password =null;
	private String clientSideValidation = null;
	private String amt = null;
	private String amt2 = null;
	private String dusername = null;
	private List<String> errList = null;
	private long num = 0;
	 private double bal = 0;
	private final static String checkAccountquery = "Select * from sbi_customers where(username,password) = (?,?);";
	private final static String openaccountquery = "Insert into sbi_customers(name,address,number,gender,dob,balance,username,password)VALUES(?,?,?,?,?,?,?,?)";
	private final static String WithdrawAmmountquery = "Update sbi_customers SET balance=(?) Where (username,password)=(?,?)";
	private final static String transferAmmountquery = "Update sbi_customers SET balance=(?) Where (username)=(?)";
	private final static String readbalance = "Select balance from sbi_customers where (username)=(?)";
	@Override
	public void init() throws ServletException {
			try{
				sc = new Scanner(System.in);
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbi","root","root");
			}catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
	}
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html");
			pw =  res.getWriter();
			clientSideValidation = req.getParameter("vstatus");
			String requ = req.getParameter("hiddenButton");
			name = req.getParameter("name");
			address = req.getParameter("address");
			number = req.getParameter("number");
			gender = req.getParameter("gender");
			dob = req.getParameter("dob");
			balance = req.getParameter("balance");
			username = req.getParameter("username");
			password = req.getParameter("password");
			amt = req.getParameter("amount");
			dusername = req.getParameter("dusername");
			errList =  new ArrayList();
			
			if(requ.equals("open")) {
				openAccont();
			}
			else if(requ.equals("checkAccount")) {
				checkAccount(username, password);
			}
			else if(requ.equals("withdraw")) {
				Withdraw(username, password, amt);
			}
			else if(requ.equals("deposite")) {
				deposite(username, password, amt);
//				up(username, password);
//				pw.println("HUIH "+amt2);
			}
			else if(requ.equals("transfer")) {
				System.out.println("in t");
				transfer(username, dusername, password, amt);
			}
		}

	private void openAccont() {
	loop: if(clientSideValidation.equals("disabled")) {
		if(name.equals("")||name.equals(null)) {
			errList.add("Name can not be empty");
		}
		else if(name.length()<5 || name.length()>20) {
			errList.add("Name can not be < 5 || > 20");
		}
		if(address.equals("")||address.equals(null)) {
			errList.add("Address can not be empty");
		}
		if(number.equals("")||number.equals(null)) {
			errList.add("Number can not be empty");
		}
		else if(number.length()<10 || number.length()>10) {
			errList.add("Enter Valid Number");
			try {
				num = Long.parseLong(number);
			}catch(NumberFormatException e) {
				errList.add("Enter Only Values");
			}
		}
		if(dob.equals("")||dob.equals(null)) {
			errList.add("Date of Birth can not be empty");
		}
		if(balance.equals("")||balance.equals(null)) {
			errList.add("Balance can not be empty");
		}
		else {
			try {
				bal = Double.parseDouble(balance);
				if(bal<1000)
					errList.add("Minimum balance required is 1000");
				else if(bal>200000)
					errList.add("You can try our Banks Primium Services");
			}catch(NumberFormatException e) {
				errList.add("Enter Valid Balance");
			}
		}
		if(username.equals("")||username.equals(null)) {
			errList.add("Username can not be empty");
		}
		else if(username.length()<3 || username.length()>20) {
			errList.add("Username can not be < 3 || > 20");
		}
		if(password.equals("")||password.equals(null)) {
			errList.add("Password can not be empty");
		}
		else if(password.length()<5 || password.length()>30) {
			errList.add("Password can not be < 5 || > 30");
		}
		for(String error:errList) {
			pw.println("<ol>");
			pw.println("<li style = 'color:red; text-align:center'>"+error+"</li>");
			pw.println("</ol>");
		}
		break loop;
	}
	if(errList.size()==0) { 
		if(con!=null && sc!=null && pw!=null) {
		try {
			Date date = Date.valueOf(dob);
			ps = con.prepareStatement(openaccountquery);
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setLong(3,Long.parseLong(number));
			ps.setString(4, gender);
			ps.setDate(5, date);
			ps.setDouble(6,Double.parseDouble(balance));
			ps.setString(7, username);
			ps.setString(8, password);
			int count = ps.executeUpdate();
		if(count!=0) {
			pw.println("<h1 style='color:Green; text-align: center;'>Data Entered Successfully<h1>");
		}
		else {
			pw.println("<h1 style='color:red; text-align: center;'>Data Not Entered<h1>");
		}
			
		}catch (Exception e) {
			pw.println(e.getLocalizedMessage());
			}
		}
		else {
			pw.println("Some Problem buddy");
		}
	}
	}
	private void checkAccount(String username,String password) {
		loop: if(clientSideValidation.equals("disabled")) {
				if(username.equals("")||username.equals(null)) {
					errList.add("Username can not be empty");
				}
				else if(username.length()<3 || username.length()>20) {
					errList.add("Username can not be < 3 || > 20");
				}
				if(password.equals("")||password.equals(null)) {
					errList.add("Password can not be empty");
				}
				else if(password.length()<5 || password.length()>30) {
					errList.add("Password can not be < 5 || > 30");
				}
				for(String error:errList) {
					pw.println("<ol>");
					pw.println("<li style = 'color:red; text-align:center'>"+error+"</li>");
					pw.println("</ol>");
				}
				break loop;
		}
		if(errList.size()==0) {
			if(con!=null && sc!=null && pw!=null) {	
				try {
						ps = con.prepareStatement(checkAccountquery);
						ps.setString(1, username);
						ps.setString(2, password);
						rs = ps.executeQuery();
						if(rs.next()!=false){
							pw.println("<table align='center' style='background-color: cyan' border='2'>");
							pw.println("<caption style='color: blue;'>State Bank of India</caption>");
							pw.println("<tr>");
							pw.println("<th>Id</th>");
							pw.println("<th>Customer Name</th>");
							pw.println("<th>Customer Address</th>");
							pw.println("<th>Customer Number</th>");
							pw.println("<th>Customer Gender</th>");
							pw.println("<th>Customer Date of Birth</th>");
							pw.println("<th>Total balance</th>");
							pw.println("</tr>");
							pw.println("<tr>");
							pw.println("<td>"+rs.getString(1)+"</td>");
							pw.println("<td>"+rs.getString(2)+"</td>");
							pw.println("<td>"+rs.getString(3)+"</td>");
							pw.println("<td>"+rs.getString(4)+"</td>");
							pw.println("<td>"+rs.getString(5)+"</td>");
							pw.println("<td>"+rs.getString(6)+"</td>");
							pw.println("<td>"+rs.getString(7)+"</td>");
							pw.println("</tr>");
							
							pw.println("</table>");

						}
						else {
							pw.println("<h2 style='color: red; text-align:center;'>No Account Found Check You'r Username or Password</h2>");
						}
					}catch (Exception e) {
						pw.println(e.getLocalizedMessage());
					}
				}
		}
	}
	private void Withdraw(String username,String password,String amt){
		Double amount = 0.0;
	loop:if(clientSideValidation.equals("disabled")){
			if(username.equals("")||username.equals(null)) {
				errList.add("Username can not be empty");
			}
			else if(username.length()<3 || username.length()>20) {
				errList.add("Username can not be < 3 || > 20");
			}
			if(password.equals("")||password.equals(null)) {
				errList.add("Password can not be empty");
			}
			else if(password.length()<5 || password.length()>30) {
				errList.add("Password can not be < 5 || > 30");
			}
			if(amt.equals("")||amt.equals(null)) {
				errList.add("Amount can not be empty");
			}
			else {
				try {
					amount = Double.parseDouble(amt);
				}catch (NumberFormatException e) {
					errList.add("Please Enter Valid Amount");
				}
			}
			for(String error:errList) {
				pw.println("<ol>");
				pw.println("<li style = 'color:red; text-align:center'>"+error+"</li>");
				pw.println("</ol>");
			}
			break loop;
		}
	if(errList.size()==0) {
		String check = up(username, password);
		if(check.equals("Noaccount") || check.equals("No")) {
			pw.println("<h2 style='color: red; text-align:center;'>No Account Found Check You'r Username or Password</h2>");
		}
		else {
			if(con!=null || pw!=null || sc!= null) {
				try {
				ps = con.prepareStatement(WithdrawAmmountquery);
				Double total = Double.parseDouble(amt2) - Double.parseDouble(amt);
				if(total<=0) {
					pw.println("<h2 style='color: red; text-align:center;'>Inssuficient Funds</h2>");
				}else {
				ps.setDouble(1,total);
				ps.setString(2,username);
				ps.setString(3, password);
				int count = ps.executeUpdate();
				if(count!=0) {
					pw.println("<h1 Style='color: Green; text-align: center'>Hey "+username+" rs "+amt+" Withdrawed successfully from your account <br> you'r Total balance:"+total+"</h1>");
				}else {
					pw.println("<h1 Style='color: red; text-align: center'>Hey "+name+" rs "+amount+" Withdrawed Unsuccessfully from your account</h1>");
				}
				}
				}catch (Exception e) { pw.println(e.getLocalizedMessage());}
			}
			else {
				pw.println("Some Data Base Problem");
			}
		}
	}
	}
	private void deposite(String username,String password,String amt) {
		Double amount = 0.0;
	  loop:	if(clientSideValidation.equals("disabled")) {
			if(username.equals("")||username.equals(null)) {
				errList.add("Username can not be empty");
			}
			else if(username.length()<3 || username.length()>20) {
				errList.add("Username can not be < 3 || > 20");
			}
			if(password.equals("")||password.equals(null)) {
				errList.add("Password can not be empty");
			}
			else if(password.length()<5 || password.length()>30) {
				errList.add("Password can not be < 5 || > 30");
			}
			if(amt.equals("")||amt.equals(null)) {
				errList.add("Amount can not be empty");
			}
			else {
				try {
					amount = Double.parseDouble(amt);
				}catch (NumberFormatException e) {
					errList.add("Please Enter Valid Amount");
				}
			}
			for(String error:errList) {
				pw.println("<ol>");
				pw.println("<li style = 'color:red; text-align:center'>"+error+"</li>");
				pw.println("</ol>");
			}
			break loop;
		}
	  if(errList.size()==0) {
		String check =  up(username, password);
			 if(check.equals("Noaccount") || check.equals("No")) {
					pw.println("<h2 style='color: red; text-align:center;'>No Account Found Check You'r Username or Password</h2>");
			 }else {
				 if(con!=null || pw!=null || sc!= null) {
				 try {
					 amount = Double.parseDouble(amt);
					 Double total = Double.parseDouble(amt2) + amount;
					 ps = con.prepareStatement(WithdrawAmmountquery);
					 ps.setDouble(1,total);
					 ps.setString(2, username);
					 ps.setString(3, password);
					 int count = ps.executeUpdate();
					 if(count!=0) {
						 pw.println("<h1 Style='color: Green; text-align: center'>Hey "+username+" rs "+amt+" Deposited successfully to your account <br> you'r Total balance:"+total+"</h1>");
					 }else {
							pw.println("<h1 Style='color: red; text-align: center'>Hey "+username+" rs "+amt+" Deposite Unsuccessfully to your account</h1>");
					 }
					 
				 }catch (Exception e) {	pw.println(e.getLocalizedMessage());			}
			    }
			}
	  }
	  
	}
	private void transfer(String username,String dusername,String pass, String amt) {
		Double amount = 0.0;
		System.out.println(clientSideValidation);
		 loop:	if(clientSideValidation.equals("disabled")) {
			 System.out.println(0);
				if(username.equals("")||username.equals(null)) {
					errList.add("Username can not be empty");
				}
				else if(username.length()<3 || username.length()>20) {
					errList.add("Username can not be < 3 || > 20");
				}
				if(dusername.equals("")||dusername.equals(null)) {
					errList.add("Destianti4on Username can not be empty");
				}
				else if(dusername.length()<3 || dusername.length()>20) {
					errList.add("Destiantion Username can not be < 3 || > 20");
				}
				if(password.equals("")||password.equals(null)) {
					errList.add("Password can not be empty");
				}
				else if(password.length()<5 || password.length()>30) {
					errList.add("Password can not be < 5 || > 30");
				}
				if(amt.equals("")||amt.equals(null)) {
					errList.add("Amount can not be empty");
				}
				else {
					try {
						amount = Double.parseDouble(amt);
					}catch (NumberFormatException e) {
						errList.add("Please Enter Valid Amount");
					}
				}
				for(String error:errList) {
					pw.println("<ol>");
					pw.println("<li style = 'color:red; text-align:center'>"+error+"</li>");
					pw.println("</ol>");
				}
				break loop;
			}
				String check = up(username, pass);
				if(check.equals("Noaccount") || check.equals("No")) {
					pw.println("<h2 style='color: red; text-align:center;'>No Account Found Check You'r Username or Password</h2>");
				}else {
				if(con!=null || pw!=null || sc!= null) {
					try {
						ps = con.prepareStatement(WithdrawAmmountquery);
						Double total = Double.parseDouble(amt2) - Double.parseDouble(amt);
						if(total<=0) {
							pw.println("<h2 style='color: red; text-align:center;'>Inssuficient Funds</h2>");
						}else {
						ps.setDouble(1, total);
						ps.setString(2, username);
						ps.setString(3, pass);
						int count = ps.executeUpdate();
						if(count!=0) {
							
							ps = con.prepareStatement(readbalance);
							ps.setString(1,dusername);
							rs = ps.executeQuery();
							if(rs.next()!=true) {
								pw.println("<h2 style='color: red; text-align:center;'>No Account Found Check You'r Destination Username</h2>");
							}
							amount = rs.getDouble(1);
							ps = con.prepareStatement(transferAmmountquery);
							total = amount + Double.parseDouble(amt);
							ps.setDouble(1, total);
							ps.setString(2, dusername);
							count = ps.executeUpdate();
							if(count!=0) {
								pw.println("<h1 style = 'color: Green; text-align: center'>rs "+amt+" Transferd Successfully</h1>");
							}else {
								pw.println("No Transferd" + total);
							}
						}else {
							pw.println("No some Transferd" + total);
						}
 					}
					}catch (Exception e) {	 			pw.println(e.getLocalizedMessage());		}
				}else {
					pw.println("Some database problem");
				}
			  }
			
	}
	private String up(String username,String password){
		try {
			ps = con.prepareStatement(checkAccountquery);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()!=false) {
					amt2 = rs.getString(7);
				return amt2;
			}else {
				return "Noaccount";
			}
			
			} catch (SQLException e) {pw.println(e.getLocalizedMessage());}
		pw.println(2);
		return "No";
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
