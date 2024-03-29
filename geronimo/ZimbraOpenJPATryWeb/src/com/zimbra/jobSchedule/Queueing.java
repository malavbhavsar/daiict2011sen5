package com.zimbra.jobSchedule;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zimbra.SMS.SMS;
import com.zimbra.SMS.SMSSender;
import com.zimbra.resource.EntityManagerofMPool;
import com.zimbra.resource.MessageQueuePool;
import com.zimbra.rest.m.m;
import com.zimbra.user.zuser;

public class Queueing implements Job {

    EntityManager em;

    private static final Logger log = Logger
	    .getLogger(Queueing.class.getName());

    private static final int SMS_MAX_CHARS = 100;

    private static final int MAXFROM = 15;

    private static final int MAXSUB = 30;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
	// TODO Auto-generated method stub
	System.out.println("----Started Queueing Job----");
	try {
	    em = EntityManagerofMPool.getEntityManagerofM();
	    //Query 1
	    Query q = em.createNativeQuery("select gid_zuser from zuser");
	    List l1 = q.getResultList();
	    System.out.println("Size of userlist is " + l1.size());
	    for (int i = 0; i <= l1.size() - 1; i++) 
	    {
	    	System.out.println("USER is " + l1.get(i));
			zuser z = em.find(zuser.class, l1.get(i));
		
			//Only take active ussers in the loop
			if (z.isActive()) {
				System.out.println("Here 2");
		    	// TODO Try to do subject keywords filtering by SQL query
		    	String content = "";
		    	String cuurentUserMobile =  z.getMobilePhoneNo();
		    
		    	//Building query 2
		    	String queryStatement = "select gid_m from m where gid_zuser="
			    	+ z.getGid_zuser() + " and ifsent=0";
		    	System.out.println("Here 2.0");
		    	List<String> l2 = z.getExcludeUser();
		    	System.out.println("Here 2.1");
		    	for (int i2 = 0; i2 <= l2.size() - 1; i2++) {
			    	System.out.println("Here 2.2");
			    	queryStatement += " and aine!=" + "'" + l2.get(i2)
					+ "'" + " and dine!=" + "'" + l2.get(i2) + "' ";
		    	}
		    	System.out.println("Query statement fired "+queryStatement);
		    	System.out.println("Here 2.3");
		    	Query q2 = em.createNativeQuery(queryStatement);
		    	List l3 = q2.getResultList();
		    	System.out.println("Here 3");
		    	//Manually executing 3rd query
		    	List<String> l4 = z.getExcludeSubject();
		    	System.out.println("L3 size is "+l3.size()+"L4 size is "+l4.size());
		    	for (int i3 = 0; i3 <= l3.size() - 1; i3++) 
		    	{
		    		System.out.println("abc"+i3);
					m message = em.find(m.class, l3.get(i3));
					boolean allok = true;
					System.out.println("Message id"+l3.get(i3));
					for (int i4 = 0; i4 <= l4.size() - 1; i4++) {
			    		if (message.getSu().toUpperCase().matches(".*" + l4.get(i4).toUpperCase()+ ".*"))
			    		{
			    			allok = false;
			    			break;
			    		}
					}
					if (allok == true) 
					{
			    		String newContent = "FROM " + reduceString(message.getDine(),MAXFROM).toLowerCase()
				  			+ " " + "SUB " + reduceString(message.getSu(),MAXSUB).toLowerCase() + " ";
			    		System.out.println("NEW Content = " + newContent);
			    		if (newContent.length() + content.length() > SMS_MAX_CHARS) 
			    		{
			    			SMS sms = new SMS(cuurentUserMobile, content);
							MessageQueuePool.getLinkedqueue().add(sms);
							content=newContent;
			    		} 
			    		else
			    		{
			    			content += newContent;
			    		}
			    		System.out.println("Here 5");
					}
		    	}
		    		if (content != "") {
		    			SMS sms = new SMS(cuurentUserMobile, content);
						MessageQueuePool.getLinkedqueue().add(sms);
		    		}
		    		System.out.println("Here 6");
		    		String queryStatement2 = "update m set ifsent=1 where gid_zuser="
		    			+ z.getGid_zuser() + " and ifsent=0";
		    		em.getTransaction().begin();
		    		Query q3=em.createNativeQuery(queryStatement2);
		    		q3.executeUpdate();
		    		em.flush();
		    		em.getTransaction().commit();
				}
	    	}
		} catch (Exception e) {
	    	e.printStackTrace();
	    	log.info("--- Error in job3!");
		}
		System.out.println("----Done Queueing Job----");
    }
    
    private String reduceString(String s,int maxSize)
    {
    	s = changeForSMS(s);
		if(s.length()>=maxSize)
		{
	    	s=s.substring(0, maxSize-3)+"...";
		}
		return s;
    }
    
    private String changeForSMS(String s)
    {
    	String s1 = "";
    	if(s!=null)
    	{
    		for(int i =0 ; i<=s.length()-1; i++)
    		{
    			if(Character.isLetterOrDigit(s.charAt(i)) || s.charAt(i)==' ')
    			{
    				s1+=s.charAt(i);
    			}
    		}
    	}
    	if(s1==null || s1.equals(""))
    	{
    		s1="nill";
    	}
    	return s1;
    }
    public Queueing() {
    }
}
