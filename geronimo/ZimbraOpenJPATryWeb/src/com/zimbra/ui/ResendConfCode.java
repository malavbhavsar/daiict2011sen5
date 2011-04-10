package com.zimbra.ui;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.*;

import biz.source_code.base64Coder.Base64Coder;

import com.zimbra.SMS.SMS;
import com.zimbra.resource.MessageQueuePool;
import com.zimbra.user.zuser;

import java.net.URL;
import java.util.*;

public class ResendConfCode extends HttpServlet {

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

    private static final String PERSISTENCE_UNIT_NAME_M = "m";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
	try {
	    HttpSession session = request.getSession(true);
	    EntityManagerFactory emf = Persistence
		    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME_M);
	    EntityManager em = emf.createEntityManager();
	    zuser z = em.find(zuser.class,session.getAttribute("gid_zuser"));
	    if(z.getConfirmationCodeSentTimes()>=5)
	    {
		if(z.getFifthConfirmationCodeSentTime().getTime() + 7200L >(new Date()).getTime())
		{
		    getServletConfig().getServletContext()
		    .getRequestDispatcher("/sorry.jsp")
		    .forward(request, response);
	    	}
		else
		{
		    z.setConfirmationCodeSentTimes(0);
		    z.setFifthConfirmationCodeSentTime(new Date());
		}
	    }
	    if(z.getConfirmationCodeSentTimes()<5)
	    {
		SMS s = new SMS(z.getMobilePhoneNo(),"confirmation code for "+z.getUsername()+" is "+z.getConfirmationCode());
		MessageQueuePool.getLinkedqueue().add(s);
		z.setConfirmationCodeSentTimes(z.getConfirmationCodeSentTimes()+1);
		if(z.getConfirmationCodeSentTimes()==5)
		{
		    z.setFifthConfirmationCodeSentTime(new Date());
		}
		em.getTransaction().begin();
		em.persist(z);
		em.flush();
		em.getTransaction().commit();
		    	getServletConfig().getServletContext()
		    		.getRequestDispatcher("/confcode.jsp")
			    .forward(request, response);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}
