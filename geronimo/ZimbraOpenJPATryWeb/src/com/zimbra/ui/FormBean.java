package com.zimbra.ui;

import java.util.*;

public class FormBean {
    private String userName;

    private String mobileNo;
    private String password1;
    private String password2;
    private String excludeSubject;
    private String excludeUser;
    private Hashtable errors;

    public FormBean() {
	userName = "";
	password1 = "";
	password2 = "";
	errors = new Hashtable();
    }

    public String getErrorMsg(String s) {
	String errorMsg = (String) errors.get(s.trim());
	return (errorMsg == null) ? "" : errorMsg;
    }

    public String getMobileNo() {
	return mobileNo;
    }

    public String getPassword1() {
	return password1;
    }

    public String getPassword2() {
	return password2;
    }

    public String getUserName() {
	return userName;
    }

    public void setErrors(String key, String msg) {
	errors.put(key, msg);
    }

    public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
    }

    public void setPassword1(String p1) {
	password1 = p1;
    }

    public void setPassword2(String p2) {
	password2 = p2;
    }

    public void setUserName(String u) {
	userName = u;
    }

    public String getExcludeSubject() {
	return excludeSubject;
    }

    public void setExcludeSubject(String excludeSubject) {
	this.excludeSubject = excludeSubject;
    }

    public String getExcludeUser() {
	return excludeUser;
    }

    public void setExcludeUser(String excludeUser) {
	this.excludeUser = excludeUser;
    }

    public boolean validate() {
	boolean allOk = true;
	if (userName == null || userName.equals("")) {
	    errors.put("userName", "Please enter a username");
	    userName = "";
	    allOk = false;
	}
	if (mobileNo == null || mobileNo.equals("") || mobileNo.length() != 10) {
	    errors.put("mobileNo", "Please enter a valid Mobile no.");
	    mobileNo = "";
	    allOk = false;
	} else {
	    try {
		long x = Long.parseLong(mobileNo);
	    } catch (NumberFormatException e) {
		errors.put("mobileNo", "Please enter a valid Mobile no.");
		mobileNo = "";
		allOk = false;
	    }
	}
	if (password1 == null || password1.equals("")) {
	    errors.put("password1", "Please enter a valid password");
	    password1 = "";
	    allOk = false;
	}
	if (!password1.equals("")
		&& (password2 == null || (password2.equals("")) || !password1
			.equals(password2))) {
	    errors.put("password2", "Please confirm your password");
	    password2 = "";
	    allOk = false;
	}
	return allOk;
    }
}
