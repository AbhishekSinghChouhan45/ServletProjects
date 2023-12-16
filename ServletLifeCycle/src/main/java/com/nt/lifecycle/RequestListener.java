package com.nt.lifecycle;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebListener
public class RequestListener implements ServletRequestListener{
	private long start,end;
	public RequestListener() {
		System.out.println("RequestListener.RequestListener()");
	}
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("RequestListener.requestInitialized()");
		start = System.currentTimeMillis();
	}
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("RequestListener.requestDestroyed()");
		end = System.currentTimeMillis();
		System.out.println("Your request taken time is:: "+(end-start)+"ms");
	}
}
