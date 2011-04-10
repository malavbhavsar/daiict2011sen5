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
		System.out.print("------queue not empty------");
	   // TODO Send SMS 
	    try {
		SMSSender.sendSMS(MessageQueuePool.getLinkedqueue().poll());
	    } catch (Exception e) {
		// TODO some problem in net connectivity
		// TODO Let's turn exception handling based on if the mobilenumber is not valid.
		e.printStackTrace();
	    }
	}
	System.out.print("------SMS END------");
    }
}
