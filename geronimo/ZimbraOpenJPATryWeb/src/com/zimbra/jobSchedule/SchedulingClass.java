package com.zimbra.jobSchedule;

//~--- non-JDK imports --------------------------------------------------------

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.ScheduleBuilder.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.JobKey.*;
import static org.quartz.impl.matchers.KeyMatcher.*;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SchedulingClass implements ServletContextListener {

    /**
     * @see javax.servlet.ServletContextListener#void
     *      (javax.servlet.ServletContextEvent)
     */
    Scheduler sched;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
	try {
	    System.out.println("------- Shutting Down ---------------------"); //$NON-NLS-1$
	    sched.shutdown(true);
	    System.out.println("------- Shutdown Complete -----------------"); //$NON-NLS-1$
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * @see javax.servlet.ServletContextListener#void
     *      (javax.servlet.ServletContextEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
	try {
	    System.out.println("------- Initializing -------------------"); //$NON-NLS-1$

	    // First we must get a reference to a scheduler
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();

	    System.out.println("------- Initialization Complete --------"); //$NON-NLS-1$
	    System.out.println("------- Scheduling Jobs ----------------"); //$NON-NLS-1$

	    // jobs can be scheduled before sched.start() has been called
	    JobDetail job = newJob(CreatingEntityManager.class).withIdentity(
		    "job1", "group1").build(); //$NON-NLS-1$ //$NON-NLS-2$

	    JobDetail job2 = newJob(InputFromRest.class)
		    .withIdentity("job2", "group1").storeDurably().build();

	    JobDetail job3 = newJob(Queueing.class)
		    .withIdentity("job3", "group1").storeDurably().build();
	    
	    JobDetail job4 = newJob(SendingSMSJob.class)
	    	    .withIdentity("job4", "group1").storeDurably().build();
	    // Trigger
	    Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //$NON-NLS-1$ //$NON-NLS-2$
		    .startNow().build();
	    sched.addJob(job2, true);
	    sched.addJob(job3, true);
	    sched.addJob(job4, true);
	    
	    // Set up the listener
	    JobListener listener = new CreatingEntityManagerJobListner();
	    Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getKey());
	    sched.getListenerManager().addJobListener(listener, matcher);

	    JobListener listener2 = new InputFromRestJobListener();
	    Matcher<JobKey> matcher2 = KeyMatcher.keyEquals(job2.getKey());
	    sched.getListenerManager().addJobListener(listener2, matcher2);

	    JobListener listener3 = new QueueingJobListener();
	    Matcher<JobKey> matcher3 = KeyMatcher.keyEquals(job3.getKey());
	    sched.getListenerManager().addJobListener(listener3, matcher3);
	    
	    JobListener listener4 = new SendingSMSJobListener();
	    Matcher<JobKey> matcher4 = KeyMatcher.keyEquals(job4.getKey());
	    sched.getListenerManager().addJobListener(listener4, matcher4);
	    
	    sched.scheduleJob(job, trigger);

	    System.out.println("------- Starting Scheduler ----------------"); //$NON-NLS-1$

	    // All of the jobs have been added to the scheduler, but none of the
	    // jobs
	    // will run until the scheduler has been started
	    sched.start();
	    System.out.println("------- Started Scheduler -----------------"); //$NON-NLS-1$
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}