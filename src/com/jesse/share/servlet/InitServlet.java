package com.jesse.share.servlet; 
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;

import com.jesse.share.task.SimpleJob;
import com.jesse.share.task.TaskOfGettingShare;

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
		try {
			Scheduler scheduler = org.quartz.impl.StdSchedulerFactory.getDefaultScheduler();
			JobDetail am_job_1 = JobBuilder.newJob(TaskOfGettingShare.class).withIdentity("am_job_1", "group1").build();
			CronTrigger am_trigger_1 = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("am_trigger_1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 30-59/1 9,10 ? * MON-FRI")).build();
			
			JobDetail am_job_2 = JobBuilder.newJob(TaskOfGettingShare.class).withIdentity("am_job_2", "group1").build();
			CronTrigger am_trigger_2 = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("am_trigger_2", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0-29/1 10,11 ? * MON-FRI")).build();
			
			JobDetail pm_job = JobBuilder.newJob(TaskOfGettingShare.class).withIdentity("pm_job", "group1").build();
			CronTrigger pm_trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("pm_trigger", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 13,14 ? * MON-FRI")).build();
			 
			
		    Date am = scheduler.scheduleJob(am_job_1, am_trigger_1);
		    logger.info("任务将于" + am + "运行");
		    Date am_2 = scheduler.scheduleJob(am_job_2, am_trigger_2);
		    logger.info("任务将于" + am_2 + "运行");
		    Date pm = scheduler.scheduleJob(pm_job, pm_trigger);
		    logger.info("任务将于" + pm + "运行");
			scheduler.start(); 
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			logger.catching(e);
			logger.error(e.getLocalizedMessage());
		}
		
	}

}
