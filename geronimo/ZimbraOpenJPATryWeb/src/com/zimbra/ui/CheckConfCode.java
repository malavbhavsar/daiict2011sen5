package com.zimbra.ui;

import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biz.source_code.base64Coder.Base64Coder;

import com.zimbra.SMS.SMS;
import com.zimbra.resource.MessageQueuePool;
import com.zimbra.user.zuser;



public class CheckConfCode extends HttpServlet{
    private static final String PERSISTENCE_UNIT_NAME_M = "m";

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
	try {
	    ConfCodeBean f = (ConfCodeBean) request.getAttribute("confCodeFormHandler");
	    HttpSession session = request.getSession(true);
	    EntityManagerFactory emf = Persistence
		    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME_M);
	    EntityManager em = emf.createEntityManager();

	    zuser z = em.find(zuser.class,session.getAttribute("gid_zuser"));
	    if(z.getConfirmationCode().equals(f.getConfcode()))
	    {
		em.getTransaction().begin();
		z.setActive(true);
		em.persist(z);
		em.flush();
		em.getTransaction().commit();
	    	getServletConfig().getServletContext()
    		.getRequestDispatcher("/new_operationsuccess.html")
    		.forward(request, response);
	    }
	    else
	    {
		f.setErrors("confcode", "Confirmation code wrong!");
	    	getServletConfig().getServletContext()
    		.getRequestDispatcher("/confcoderetry.jsp")
    		.forward(request, response);
	    }
	    

	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}
