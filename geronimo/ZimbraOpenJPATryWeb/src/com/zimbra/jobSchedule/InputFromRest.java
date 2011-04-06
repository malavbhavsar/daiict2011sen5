package com.zimbra.jobSchedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.zimbra.mailsender.Sender;
import com.zimbra.mailsender.Sender.MAILTYPE;
import com.zimbra.resource.EntityManagerofMPool;
import com.zimbra.rest.m.m;
import com.zimbra.rest.parser.m_parser;
import com.zimbra.user.zuser;

public class InputFromRest implements Job {
    /**
     * @author malav Have to give NullHostNameVerfier instead of
     *         DefaultHostNameVerfier Because, webmail.daiict.ac.in generates
     *         its own SSL certificate. Which is not certified by Thwate like
     *         agencies. So we cannot verify it. We will just return true.
     */
    private static class NullHostnameVerifier implements HostnameVerifier {
	@Override
	public boolean verify(String hostname, SSLSession session) {
	    return true;
	}
    }

    private static final Logger log = Logger.getLogger(InputFromRest.class
	    .getName());
    private static final String AT_DAIICT_AC_IN = "@daiict.ac.in";
    private InputStream isr;
    private EntityManager em;
    private ArrayList<String> errorFulUsers;

    public InputFromRest() {
    }

    @Override
    public void execute(JobExecutionContext context)
	    throws JobExecutionException {
	// TODO Auto-generated method stub
	System.out.println("---------Input from rest started---------");
	errorFulUsers = new ArrayList<String>();
	m_parser p1 = new m_parser();
	try {
	    em = EntityManagerofMPool.getEntityManagerofM();
	    Query q = em.createNativeQuery("select gid_zuser from zuser");
	    List l1 = q.getResultList();
	    for (int i = 0; i <= l1.size() - 1; i++) {
		zuser z = em.find(zuser.class, l1.get(i));
		if (z.isActive()) {
		    p1.parse(getXMLStream(z), z, em);
		}
		// TODO To make the m isSent True
	    }
	    if(errorFulUsers.size()>0)
	    {
		System.out.println("--------SENDING MAILS--------");
	    	Sender.sendEmail(errorFulUsers, MAILTYPE.PASSWORD_CHANGED);
	    	System.out.println("--------SENT MAILS--------");
	    	errorFulUsers.clear();
	    }
	} catch (Exception e) {
	    log.info("--- Error in job2!");
	}
	System.out.println("---------Input from rest completed---------");

    }

    private String getXMLStream(zuser z) {
	try {
	    URL url = new URL("https://webmail.daiict.ac.in/home/"
		    + z.getUsername() + "/inbox.xml?query=is:unread");
	    String encoding = Base64Coder.encodeString(z.getUsername() + ":"
		    + z.getPassword());
	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	    conn.setDoInput(true);
	    conn.setRequestMethod("GET");
	    conn.addRequestProperty("Authorization", "Basic " + encoding);
	    conn.setHostnameVerifier(new NullHostnameVerifier());
	    conn.connect();
	    int responsecode = conn.getResponseCode();
	    if (responsecode < 200 || responsecode >= 300) {
		errorFulUsers.add(z.getUsername()+AT_DAIICT_AC_IN);
		z.setActive(false);
		em.persist(z);
		return "<items></items>";
	    }
	    else
	    {
		isr = conn.getInputStream();
	    }
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	String s = "<items></items>";
	try {
	    s = new String(convertStreamToString(isr));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return s;
    }
    /**
     * @author malav To convert the InputStream to String we use the
     *         Reader.read(char[] buffer) method. We iterate until the Reader
     *         return -1 which means there's no more data to read. We use the
     *         StringWriter class to produce the string.
     */
    public String convertStreamToString(InputStream is) throws IOException {
	if (is != null) {
	    Writer writer = new StringWriter();
	    char[] buffer = new char[1024];
	    try {
		Reader reader = new BufferedReader(new InputStreamReader(is,
			"UTF-8"));
		int n;
		while ((n = reader.read(buffer)) != -1) {
		    writer.write(buffer, 0, n);
		}
	    } finally {
		is.close();
	    }
	    return writer.toString();
	} else {
	    return "<items></items>";
	}
    }
}