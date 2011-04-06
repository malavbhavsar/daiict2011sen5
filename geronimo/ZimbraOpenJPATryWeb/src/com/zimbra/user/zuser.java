package com.zimbra.user;

import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.apache.openjpa.persistence.InverseLogical;

import com.zimbra.rest.m.m;

@Entity
public class zuser implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gid_zuser;
    @Basic
    private String username;
    @Basic
    private String password;
    @Basic
    private boolean active;
    @Basic
    private String mobilePhoneNo;
    @Basic
    private ArrayList<String> excludeUser;
    @Basic
    private ArrayList<String> excludeSubject;
    @Basic
    private String confirmationCode;
    @OneToMany(targetEntity = com.zimbra.rest.m.m.class, cascade = CascadeType.ALL, mappedBy = "zuserInstance")
    ArrayList<m> ms; /* (m)essage(s) */
    

    public void addM(m e) {
	ms.add(e);
    }

    public int getGid_zuser() {
        return gid_zuser;
    }

    public void setGid_zuser(int gid_zuser) {
        this.gid_zuser = gid_zuser;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
    }

    public String getMobilePhoneNo() {
	return mobilePhoneNo;
    }

    public ArrayList<m> getMs() {
	return ms;
    }

    public String getPassword() {
	return password;
    }

    public String getUsername() {
	return username;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public void setMobilePhoneNo(String mobilePhoneNo) {
	this.mobilePhoneNo = mobilePhoneNo;
    }

    public void setMs(ArrayList<m> ms) {
	this.ms = ms;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public ArrayList<String> getExcludeUser() {
	return excludeUser;
    }

    public void setExcludeUser(ArrayList<String> excludeUser) {
	this.excludeUser = excludeUser;
    }

    public ArrayList<String> getExcludeSubject() {
	return excludeSubject;
    }

    public void setExcludeSubject(ArrayList<String> excludeSubject) {
	this.excludeSubject = excludeSubject;
    }

}
