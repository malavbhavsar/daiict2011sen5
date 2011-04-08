package com.zimbra.jobSchedule;

import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.DateBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class SendingSMSJobListener implements JobListener {

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return "to self";
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void jobToBeExecuted(JobExecutionContext arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context,
	    JobExecutionException arg1) {
	// TODO Auto-generated method stub
	
	
	System.out.println("-----SMS SENDING DONE-----");
	try {
	    // schedule the job to run!
	    Trigger trigger = newTrigger()
		    .withIdentity("trigger4", "group1")
		    .forJob(jobKey("job4", "group1"))
		    .startAt(
			    DateBuilder.futureDate(1,
				    DateBuilder.IntervalUnit.MINUTE)).build();
	    context.getScheduler().rescheduleJob(trigger.getKey(), trigger);
	} catch (SchedulerException e) {
	    System.out.println("Unable to schedule job4!");
	    e.printStackTrace();
	}
    }
}
