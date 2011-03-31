package com.zimbra.jobSchedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CreatingEntityManager implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		System.setProperty("javax.net.ssl.keyStore", "/home/malav/Desktop/SEN_PROGRAMING_STUFF/jssecacerts");
		System.setProperty("javax.net.ssl.keyStorePassword","changeit");
		System.setProperty("javax.net.ssl.trustStore","/home/malav/Desktop/SEN_PROGRAMING_STUFF/jssecacerts");		
		EntityManagerofMPool.create();
	}
	
	public CreatingEntityManager()
	{
		
	}

}
