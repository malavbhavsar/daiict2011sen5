package com.zimbra.jobSchedule;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.JobKey.*;

import java.util.Calendar;
import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.KeyMatcher;

public class InputFromRestJobListener implements JobListener {

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return "Input From Rest to Queueing";
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
		    .withIdentity("trigger3", "group1")
		    .forJob(jobKey("job3", "group1"))
		    .startAt(
			    DateBuilder.futureDate(1,
				    DateBuilder.IntervalUnit.MINUTE)).build();
	    context.getScheduler().scheduleJob(trigger);
	    // context.getScheduler().triggerJob(jobKey("job3","group1"));
	} catch (SchedulerException e) {
	    System.out.println("Unable to schedule job3!");
	    e.printStackTrace();
	}
    }
}
