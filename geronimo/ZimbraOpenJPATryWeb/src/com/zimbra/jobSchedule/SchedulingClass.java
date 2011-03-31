package com.zimbra.jobSchedule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.JobKey.*;
import static org.quartz.TriggerKey.*;
import static org.quartz.DateBuilder.*;
import static org.quartz.impl.matchers.KeyMatcher.*;
import static org.quartz.impl.matchers.GroupMatcher.*;
import static org.quartz.impl.matchers.AndMatcher.*;
import static org.quartz.impl.matchers.OrMatcher.*;
import static org.quartz.impl.matchers.EverythingMatcher.*;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.examples.example9.Job1Listener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchedulingClass implements ServletContextListener
{
   /**
 * @see javax.servlet.ServletContextListener#void (javax.servlet.ServletContextEvent)
 */
Scheduler sched;


 public void contextDestroyed(ServletContextEvent arg0)
 {
	 try
	 {

     System.out.println("------- Shutting Down ---------------------");

     sched.shutdown(true);

     System.out.println("------- Shutdown Complete -----------------");
     
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
 }

 /**
 * @see javax.servlet.ServletContextListener#void (javax.servlet.ServletContextEvent)
 */
public void contextInitialized(ServletContextEvent arg0)
 {
	 try
	 {

     System.out.println("------- Initializing -------------------");

     // First we must get a reference to a scheduler
     SchedulerFactory sf = new StdSchedulerFactory();
     Scheduler sched = sf.getScheduler();

     System.out.println("------- Initialization Complete --------");

     System.out.println("------- Scheduling Jobs ----------------");

     // jobs can be scheduled before sched.start() has been called

     

     
     JobDetail job = newJob(CreatingEntityManager.class)
         .withIdentity("job1", "group1")
         .build();
     
     Trigger trigger = newTrigger()
         .withIdentity("trigger1", "group1")
         .startNow()
         .build();

     // Set up the listener
     JobListener listener = new CreatingEntityManagerJobListner();
     Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getKey());
     sched.getListenerManager().addJobListener(listener, matcher); 

     Date ft = sched.scheduleJob(job, trigger);
     System.out.println(job.getKey() + " has been scheduled to run at: " + ft);
     
     System.out.println("------- Starting Scheduler ----------------");

     // All of the jobs have been added to the scheduler, but none of the
     // jobs
     // will run until the scheduler has been started
     sched.start();

     System.out.println("------- Started Scheduler -----------------");


	 }
     catch(Exception e)
     {
    	 e.printStackTrace();
     }          
 }
}