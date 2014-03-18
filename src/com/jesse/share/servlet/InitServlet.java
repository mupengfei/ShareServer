package com.jesse.share.servlet;


import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import com.jesse.share.config.GlobalConfig;

public class InitServlet extends HttpServlet {
	private Logger logger = LogManager.getLogger(InitServlet.class.getName());  
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here  
		Calendar ca = Calendar.getInstance();
		logger.info(ca.get(Calendar.HOUR_OF_DAY));
		logger.info(ca.get(Calendar.HOUR));
		logger.info(ca.get(Calendar.MINUTE));
	}

}
