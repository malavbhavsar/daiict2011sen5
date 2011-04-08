package com.zimbra.jobSchedule;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.JobKey.*;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class QueueingJobListener implements JobListener {

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return "Queueing to Input From Rest";
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
	try {
	    // schedule the job to run!
	    Trigger trigger = newTrigger()
		    .withIdentity("trigger2", "group1")
		    .forJob(jobKey("job2", "group1"))
		    .startAt(
			    DateBuilder.futureDate(1,
				    DateBuilder.IntervalUnit.MINUTE)).build();
	    context.getScheduler().scheduleJob(trigger);
	} catch (SchedulerException e) {
	    System.out.println("Unable to schedule job2!");
	    e.printStackTrace();
	}
    }

}
