package com.nt.lifecycle;

import org.apache.catalina.SessionEvent;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
@WebListener
public class SessionListener implements HttpSessionListener {
	private long start,end;
	public SessionListener() {
		System.out.println("SessionListener.SessionListener()");
	}
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("SessionListener.sessionCreated()");
		start = System.currentTimeMillis();
		
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("SessionListener.sessionDestroyed()");
		end = System.currentTimeMillis();
		System.out.println("Your Total time on session is:: "+(end-start)+"ms");
	}

}
