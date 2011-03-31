package com.zimbra.jobSchedule;

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
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

public class CreatingEntityManagerJobListner implements JobListener {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Creating Entity Manager to Input From Rest";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		// TODO Auto-generated method stub
        JobDetail job2 = newJob(InputFromRest.class)
        .withIdentity("job2","group1")
        .build();
    
    Trigger trigger = newTrigger() 
        .withIdentity("trigger2","group1")
        .startNow()
        .withSchedule(calendarIntervalSchedule()
        .withIntervalInMinutes(1))
        .build();
    
    try {
        // schedule the job to run!
        context.getScheduler().scheduleJob(job2, trigger);
    } catch (SchedulerException e) {
        System.out.println("Unable to schedule job2!");
        e.printStackTrace();
    }
	}

}
