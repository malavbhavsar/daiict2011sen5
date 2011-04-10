package com.zimbra.ui;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.zimbra.user.zuser;

public class UnregFormBean {
    private String unregusername;
    private String unregpassword;
    private Hashtable errors;

    public UnregFormBean() {
	unregusername="";
	unregpassword="";
	errors = new Hashtable();
    }

    public String getErrorMsg(String s) {
	String errorMsg = (String) errors.get(s.trim());
	return (errorMsg == null) ? "" : errorMsg;
    }
    
    public String getUnregusername() {
        return unregusername;
    }

    public void setUnregusername(String unregusername) {
        this.unregusername = unregusername;
    }

    public String getUnregpassword() {
        return unregpassword;
    }

    public void setUnregpassword(String unregpassword) {
        this.unregpassword = unregpassword;
    }
    
    public void setErrors(String key, String msg) {
	errors.put(key, msg);
    }
    
    public boolean validate()
    {
	boolean allOk=true;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("m");
	EntityManager em = emf.createEntityManager();
	if(unregusername==null || unregusername.equals(""))
	{
	    setErrors("unregusername", "Enter Username");
	    allOk=false;
	}
	if(unregpassword==null || unregpassword.equals(""))
	{
	    setErrors("unregpassword", "Enter Password");
	    allOk=false;
	}
	em.getTransaction().begin();
	String statement = "select gid_zuser from zuser where username='"+unregusername+"'";
	System.out.println(statement);
	Query q = em.createNativeQuery(statement);
	List l = q.getResultList();
	em.getTransaction().commit();
	if(l.size()>0)
	{
	    zuser z = em.find(zuser.class, l.get(0));
	    allOk=true;
	    if(z.getPassword().equals(unregpassword))
	    {
		allOk=true;
		em.getTransaction().begin();
		em.remove(z);
		em.flush();
		em.getTransaction().commit();
	    }
	    else
	    {
		allOk=false;
		setErrors("unregpassword", "Password doesn't match.");
	    }
	}
	else
	{
	    allOk=false;
	    setErrors("unregusername", "User doesn't exist in our database.");
	}
	em.close();
	return allOk;
    }
}
