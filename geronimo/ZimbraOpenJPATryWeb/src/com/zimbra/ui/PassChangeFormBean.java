package com.zimbra.ui;

import java.net.URL;
import java.util.Hashtable;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import biz.source_code.base64Coder.Base64Coder;

import com.zimbra.user.zuser;

public class PassChangeFormBean {

    private String userName;

    private String oldPassword;
    private String newPassword1;
    private String newPassword2;
    private Hashtable error;

    private static final String PERSISTENCE_UNIT_NAME_M = "m";

    public PassChangeFormBean() {
	userName = "";
	oldPassword = "";
	newPassword1 = "";
	newPassword2 = "";
	error = new Hashtable();
    }

    public String getErrorMsg(String s) {
	String errorMsg = (String) error.get(s.trim());
	return (errorMsg == null) ? "" : errorMsg;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getOldPassword() {
	return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
	this.oldPassword = oldPassword;
    }

    public String getNewPassword1() {
	return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
	this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
	return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
	this.newPassword2 = newPassword2;
    }

    public void setErrors(String key, String msg) {
	error.put(key, msg);
    }

    public boolean validate() {
	boolean allOk = true;
	boolean userExists;
	EntityManagerFactory emf = Persistence
		.createEntityManagerFactory(PERSISTENCE_UNIT_NAME_M);
	EntityManager em = emf.createEntityManager();
	String usernameEntered = this.getUserName();
	String qString = "select gid_zuser from zuser where username=" + "'"
		+ usernameEntered + "'";
	System.out.println(qString);
	Query q = em.createNativeQuery(qString);
	List l = q.getResultList();
	if (l.size() == 0) {
	    userExists = false;
	} else {
	    userExists = true;
	}
	if (!userExists) {
	    setErrors("userName", "This usename doesn't exist in our database.");
	    allOk = false;
		System.out.println("1-----"+allOk+"------");
	    return allOk;
	} else {
	    zuser z = em.find(zuser.class, l.get(0));
	    if (z.getPassword().equals(this.getOldPassword())) {
		int responseCode = -1;
		String responseMessage = " ";
		allOk = true;
		if (this.getNewPassword1().equals(this.getNewPassword2())) {
		    allOk = true;
			System.out.println("2-----"+allOk+"------");

		    try {
			URL url = new URL("https://webmail.daiict.ac.in/home/"
				+ this.getUserName()
				+ "/inbox.xml?query=is:unread");
			String encoding = Base64Coder.encodeString(this
				.getUserName() + ":" + this.getNewPassword1());
			HttpsURLConnection conn = (HttpsURLConnection) url
				.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Authorization", "Basic "
				+ encoding);
			HttpsURLConnection
				.setDefaultHostnameVerifier((new HostnameVerifier() {
				    @Override
				    public boolean verify(String arg0,
					    SSLSession arg1) {
					return true;
				    }
				}));
			responseCode = conn.getResponseCode();
			responseMessage = conn.getResponseMessage();
		    } catch (Exception e) {
			e.printStackTrace();
		    }

		    if (responseCode >= 300 || responseCode < 200) {
			this.setErrors("userName", "ERROR-" + responseCode
				+ "-" + responseMessage);
			this.setErrors("newPassword1", "ERROR-" + responseCode
				+ "-" + responseMessage);
			allOk = false;
			System.out.println("3-----"+allOk+"------");

		    } else {
			allOk = true;
			em.getTransaction().begin();
			z.setPassword(this.getNewPassword1());
			em.persist(z);
			em.getTransaction().commit();
			// TODO Send mail
			System.out.println("4-----"+allOk+"------");

		    }
		} else {
		    allOk = false;
		    setErrors("newPassword2", " Password doesn't match.");
			System.out.println("5-----"+allOk+"------");

		}
	    } else {
		allOk = false;
		setErrors("oldPassword", "Incorrect old password.");
		System.out.println("6-----"+allOk+"------");

	    }
	}
	System.out.println("7-----"+allOk+"------");
	return allOk;
    }
}
