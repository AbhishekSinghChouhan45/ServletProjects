package com.fee.addstudent;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fee.connection.ConnectionDAO;

public class AddStudentDAO {
	public static int add(String name,String email,String sex,String course,long fee, long paid,long due,String address,String contact) {
		try {
				Connection con = ConnectionDAO.getCon();
//				PreparedStatement ps1 = con.prepareStatement("Select email")
				PreparedStatement ps = con.prepareStatement("INSERT INTO students_data(name,email,sex,course,fee,paid,due,address,contact,clear,totaldues) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
				if(fee / 2 > paid) {
					return 5;
				}
				Double totaldue = (double) (fee - paid); 
				String clear = null;
				if(due==0)
					clear = "yes";
				else
					clear = "no";
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, sex);
				ps.setString(4,course );
				ps.setLong(5,fee);
				ps.setLong(6,paid);
				ps.setLong(7,due);
				ps.setString(8,address);
				ps.setString(9,contact);
				ps.setString(10,clear);
				ps.setDouble(11,totaldue);
				int count = ps.executeUpdate();
				if(count > 0) {
					return count;
				}
				con.close();
		}catch (Exception e) {
			System.out.println("Exception in AddStudentDAO:\n\t\t"+e.getMessage());
		}
		return 0;
	}
}
