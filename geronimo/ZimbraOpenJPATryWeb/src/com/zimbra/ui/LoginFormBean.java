package com.zimbra.ui;

import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.zimbra.user.zuser;

public class LoginFormBean {
    
    private String myusername;
    private String mypassword;
    private Hashtable error;
    
    private static final String PERSISTENCE_UNIT_NAME_M = "m";
    
    public LoginFormBean(){
	mypassword="";
	myusername="";
	error = new Hashtable();
    }

    public String getMyusername() {
        return myusername;
    }

    public void setMyusername(String myusername) {
        this.myusername = myusername;
    }

    public String getMypassword() {
        return mypassword;
    }

    public void setMypassword(String mypassword) {
        this.mypassword = mypassword;
    }

    public String getErrorMsg(String s) {
	String errorMsg = (String) error.get(s.trim());
	return (errorMsg == null) ? "" : errorMsg;
    }
    public void setErrors(String key, String msg) {
	error.put(key, msg);
    }
    
    public boolean validate()
    {
	boolean allOk=true;
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("m",
		System.getProperties());

	// Create a new EntityManager from the EntityManagerFactory. The
	// EntityManager is the main object in the persistence API, and is
	// used to create, delete, and query objects, as well as access
	// the current transaction
	EntityManager em = factory.createEntityManager();
	em.getTransaction().begin();
	String statement = "select gid_zuser from zuser where username='"+this.getMyusername()+"'";
	System.out.println(statement);
	Query q = em.createNativeQuery(statement);
	List l = q.getResultList();
	em.getTransaction().commit();
	if(l.size()>0)
	{
	    zuser z = em.find(zuser.class, l.get(0));
	    allOk=true;
	    if(z.getPassword().equals(getMypassword()))
	    {
		allOk=true;
	    }
	    else
	    {
		allOk=false;
		setErrors("mypassword", "Password doesn't match.");
	    }
	}
	else
	{
	    allOk=false;
	    setErrors("myusername", "User doesn't exist in our database.");
	}
	em.close();
	return allOk;
    }
}
