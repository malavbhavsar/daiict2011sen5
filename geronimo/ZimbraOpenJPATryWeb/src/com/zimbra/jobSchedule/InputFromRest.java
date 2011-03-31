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

import org.apache.openjpa.persistence.EntityExistsException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import biz.source_code.base64Coder.Base64Coder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.*;

import com.zimbra.rest.m.m;
import com.zimbra.rest.parser.m_parser;
import com.zimbra.user.zuser;


public class InputFromRest implements Job{

    private static final Logger log = Logger.getLogger(InputFromRest.class.getName());
	private InputStream isr;
	private EntityManager em;
	private boolean firstTime;
	
	public InputFromRest(){		
	}
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		  firstTime=true;
		// TODO Auto-generated method stub		
	      m_parser p1 = new m_parser();
	      try {
	    	  
	    	  em = EntityManagerofMPool.getEntityManagerofM();
	    	  
	    	  Query q = em.createNativeQuery("select username from zuser");
		      List l1 = q.getResultList();
		      System.out.println("Size of userlist is---->"+l1.size());
		      for(int i=0; i<=l1.size()-1; i++)
		      {
		    	  System.out.println("USER is ---->"+l1.get(i));
		  		  em.getTransaction().begin();
		    	  zuser z = em.find(zuser.class, l1.get(i));
		    	  p1.parse(getXMLStream(z),z,em);
			      em.getTransaction().commit();
		      }
	  		  // Commit the transaction, which will cause the entity to
		      // be stored in the database
		      // Go through each of the entities and print out each of their
		      // messages, as well as the date on which it was created
		      q = em.createQuery("select a from m a");
		      List<m> l2 = q.getResultList();
		      System.out.println("Size of the M list is ---->"+l2.size());
		      for(int j=0; j<=l2.size()-1; j++)
		      {
		    	  m a = (m)l2.get(j);
		    	  System.out.println(a.getSu());
		    	  System.out.println(a.getFr());
		      }
	      } catch (Exception e) {
	    	  e.printStackTrace();
		  }
	}
	
	private String getXMLStream(zuser z)
	{
	        try { 	
	            URL url = new URL ("https://webmail.daiict.ac.in/home/"+z.getUsername()+"/inbox.xml?query=is:unread");
	            String encoding = Base64Coder.encodeString(z.getUsername()+":"+z.getPassword());
	            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	            conn.setDoInput(true);
	            conn.setRequestMethod("GET");
	            conn.addRequestProperty("Authorization", "Basic " + encoding);
	            conn.setHostnameVerifier(new NullHostnameVerifier());
	            conn.connect();
	            isr = conn.getInputStream();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        String s = "";
			try {
				s = new String(convertStreamToString(isr));
		        System.out.println("the string -----"+s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s;
	}
    /**
     * @author malav
     * To convert the InputStream to String we use the
     * Reader.read(char[] buffer) method. We iterate until the
     * Reader return -1 which means there's no more data to
     * read. We use the StringWriter class to produce the string.
     */
	public String convertStreamToString(InputStream is) throws IOException 
	{
		if (is != null)
		{
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) 
				{
					writer.write(buffer, 0, n);
				}
				System.out.println("printing buffer: "+buffer);
			} finally {
				is.close();
			}
			return writer.toString();
		} 
		else 
		{  
			System.out.println("It's null!");
			return "";
		}
	}
	/**
	 * @author malav
	 * Have to give NullHostNameVerfier instead of DefaultHostNameVerfier
	 * Because, webmail.daiict.ac.in generates its own SSL certificate. 
	 * Which is not certified by Thwate like agencies.
	 * So we cannot verify it. We will just return true.
	 */
	private static class NullHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
				return true;
		}
	}
}