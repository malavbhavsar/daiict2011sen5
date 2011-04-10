package com.zimbra.ui;

import java.util.Hashtable;

public class ConfCodeBean {
    private String confcode;
    private Hashtable error;
    
    public ConfCodeBean(){
	confcode="";
	error = new Hashtable();
    }
    public String getConfcode() {
        return confcode;
    }
    public void setConfcode(String confcode) {
        this.confcode = confcode;
    }
    public void setErrors(String key, String msg) {
	error.put(key, msg);
    }
    public String getErrorMsg(String s) {
	String errorMsg = (String) error.get(s.trim());
	return (errorMsg == null) ? "" : errorMsg;
    }
}
