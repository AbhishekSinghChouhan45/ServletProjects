package com.nt.lifecycle;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
@WebListener
public class SevletListener implements ServletContextListener {
	private long start,end;
	public SevletListener() {
		System.out.println("ServletListener.SevletListener()");
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletListener.contextInitialized()");
		start = System.currentTimeMillis();
		System.out.println("Web Application Deployed or reloaded at:: "+LocalDateTime.now());
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletListener.contextDestroyed()");
		end = System.currentTimeMillis();
		System.out.println("Your Total Context Time is:: "+(end-start)+"ms");
		System.out.println("Web Application Stopped at:: "+LocalDateTime.now());
	}
}
