package com.zimbra.jobSchedule;

import java.util.Iterator;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zimbra.SMS.SMS;
import com.zimbra.SMS.SMSSender;
import com.zimbra.resource.MessageQueuePool;

public class SendingSMSJob implements Job{

    public SendingSMSJob() {
	// TODO Auto-generated constructor stub
    }
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
	// TODO Auto-generated method stub
	System.out.print("------SMS Start------");
	while(!MessageQueuePool.getLinkedqueue().isEmpty())
	{
	    Iterator<SMS> it =MessageQueuePool.getLinkedqueue().iterator();
	    System.out.print("------Queue Start------");
	    while(it.hasNext())
	    {
		SMS s = it.next();
		System.out.println(s.getMobileNumber());
		System.out.println(s.getContent());
	    }
	    System.out.print("------Queue End------");

	   // TODO Send SMS 
	    try {
		SMSSender.sendSMS(MessageQueuePool.getLinkedqueue().peek());
	    } catch (Exception e) {
		// TODO some problem in net connectivity
		// TODO Let's turn exception handling based on if the mobilenumber is not valid.
		e.printStackTrace();
	    }
	    
	}
	System.out.print("------SMS END------");
    }
}
