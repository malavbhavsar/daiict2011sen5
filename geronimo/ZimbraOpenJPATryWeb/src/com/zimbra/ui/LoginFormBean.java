package com.zimbra.ui;

import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.zimbra.user.zuser;

public class LoginFormBean {
    
    private String userName;
    private String passWord;
    private Hashtable error;
    
    private static final String PERSISTENCE_UNIT_NAME_M = "m";
    
    public LoginFormBean(){
	userName="";
	passWord="";
	error=new Hashtable();
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
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public void setErrors(String key, String msg) {
	error.put(key, msg);
    }
    public boolean validate() {
	boolean allOk = true;
	boolean userExists;
	EntityManagerFactory emf= Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME_M);
	EntityManager em = emf.createEntityManager();
	String usernameEntered = this.getUserName();
	String qString = "select gid_zuser from zuser where username=" + "'"+ usernameEntered + "'";
	System.out.println(qString);
	Query q = em.createNativeQuery(qString);
	List l = q.getResultList();
	if (l.size() == 0) {
	    userExists = false;
	} else {
	    userExists = true;
	}
	if(!userExists)
	{
	    setErrors("userName", "This usename doesn't exist in our database.");
	    allOk=false;
	    return allOk;
	}
	else
	{
	    zuser z = em.find(zuser.class, l.get(0));
	    if(z.getPassword().equals(this.getPassWord()))
	    {
		allOk=true;
	    }
	    else
	    {
		allOk=false;
		setErrors("passWord", "Usename Password doesn't match.");
	    }
	    return allOk;
	}
    }
}
