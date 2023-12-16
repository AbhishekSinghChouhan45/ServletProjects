package com.todo.DAO;

public class Queries  {
	public static String insert() {
		return "INSERT INTO todo_list(firstname,lastname,username,password,dob) VALUES (?,?,?,?,?)";
	}
	public static String delete() {
		return "DELETE FROM todo_list WHERE (username,password) = (?,?)";
	}
	public static String addlist() {
		return "INSERT INTO todo(title,discription,status,tdate,username,password) VALUES (?,?,?,?,?,?)";
	}
	public static String Login() {
		return "Select (id) from todo_list where (username,password) = (?,?)";
	}
	public static String getTodo() {
		return "SELECT title,discription,status,tdate FROM todo WHERE (username,password) = (?,?)";
	}
	
}
