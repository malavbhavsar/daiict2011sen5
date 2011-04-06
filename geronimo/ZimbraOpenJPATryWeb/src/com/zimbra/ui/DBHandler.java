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

public class DBHandler extends HttpServlet {

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
	    FormBean f = (FormBean) request.getAttribute("formHandler");
	    boolean userExists = false;
	    EntityManagerFactory emf = Persistence
		    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME_M);
	    EntityManager em = emf.createEntityManager();
	    String usernameEntered = f.getUserName();
	    String qString = "select username from zuser where username=" + "'"
		    + usernameEntered + "'";
	    System.out.println(qString);
	    Query q = em.createNativeQuery(qString);
	    List l = q.getResultList();
	    if (l.size() == 0) {
		userExists = false;
	    } else {
		userExists = true;
	    }
	    if (userExists) {
		f.setErrors("userName",
			"Duplicate User: Try a different username");
		getServletConfig().getServletContext()
			.getRequestDispatcher("/retry.jsp")
			.forward(request, response);
	    } else {
		boolean passwordRight = false;
		URL url = new URL("https://webmail.daiict.ac.in/home/"
			+ f.getUserName() + "/inbox.xml?query=is:unread");
		String encoding = Base64Coder.encodeString(f.getUserName()
			+ ":" + f.getPassword1());
		HttpsURLConnection conn = (HttpsURLConnection) url
			.openConnection();
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.addRequestProperty("Authorization", "Basic " + encoding);
		HttpsURLConnection
			.setDefaultHostnameVerifier((new HostnameVerifier() {
			    @Override
			    public boolean verify(String arg0, SSLSession arg1) {
				return true;
			    }
			}));
		int responseCode = conn.getResponseCode();
		String responseMessage = conn.getResponseMessage();

		if (responseCode >= 300 || responseCode < 200) {
		    f.setErrors("userName", "ERROR-" + responseCode + "-"+responseMessage);
		    f.setErrors("password1", "ERROR-" + responseCode + "-"+responseMessage);
		    getServletConfig().getServletContext()
			    .getRequestDispatcher("/retry.jsp")
			    .forward(request, response);
		} else {
		    zuser newUser = new zuser();
		    newUser.setUsername(f.getUserName());
		    newUser.setPassword(f.getPassword1());
		    newUser.setMobilePhoneNo(f.getMobileNo());
		    newUser.setActive(true);
		    Random r = new Random();
		    int codeInt=r.nextInt(9999);
		    String confirmationCode = Integer.toString(codeInt);
		    while(confirmationCode.length()<4)
		    {
			confirmationCode="0"+confirmationCode;
		    }
		    newUser.setConfirmationCode(confirmationCode);
		    
		    //Added SMS to sending queue
		    SMS confirmationCodeSMS= new SMS(f.getMobileNo(),"Confirmation code for MNC is: "+confirmationCode+"\nIf you haven't signed up for the service. Contact daiict.sen5.2011@gmail.com");	
		    MessageQueuePool.getLinkedqueue().add(confirmationCodeSMS);
		    
		    newUser.setExcludeSubject(tokenizeByComma(f
			    .getExcludeSubject()));
		    newUser.setExcludeUser(tokenizeByComma(f.getExcludeUser()));
		    em.getTransaction().begin();
		    em.persist(newUser);
		    em.getTransaction().commit();
		    em.close();
		    getServletConfig().getServletContext()
			    .getRequestDispatcher("/success.jsp")
			    .forward(request, response);
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    private ArrayList<String> tokenizeByComma(String s) {
	ArrayList<String> coll = new ArrayList<String>();
	if(s!=null)
	{
		StringTokenizer st = new StringTokenizer(s, ",");
		while (st.hasMoreTokens()) {
		    coll.add(st.nextToken());
		}
		for (int i = 0; i <= coll.size() - 1; i++) {
		    System.out.println("--------" + coll.get(i));
		}
	}
	return coll;
    }
}
