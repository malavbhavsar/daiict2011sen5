package com.zimbra.jobSchedule;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.JobKey.*;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class CreatingEntityManagerJobListner implements JobListener {

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return "Creating Entity Manager to Input From Rest";
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
	// TODO Auto-generated method stub

    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
	// TODO Auto-generated method stub

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context,
	    JobExecutionException jobException) {
	// TODO Auto-generated method stub
	try {
	    // schedule the job to run!
	    context.getScheduler().triggerJob(jobKey("job2", "group1"));
	} catch (SchedulerException e) {
	    System.out.println("Unable to schedule job2!");
	    e.printStackTrace();
	}
	try {
	    // schedule the job to run!
	    Trigger trigger = newTrigger()
		    .withIdentity("trigger4", "group1")
		    .forJob(jobKey("job4", "group1"))
		    .startNow().build();
	    context.getScheduler().scheduleJob(trigger);
	} catch (SchedulerException e) {
	    System.out.println("Unable to schedule job4!");
	    e.printStackTrace();
	}
    }
}
