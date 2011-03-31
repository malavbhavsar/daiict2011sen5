package com.zimbra.user;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.openjpa.persistence.InverseLogical;

import com.zimbra.rest.m.m;

@Entity
public class zuser implements java.io.Serializable{
	
	@Id
	String username;
	@Basic
	String password;
	@Basic
	boolean active;
	@Basic
	String mobilePhoneNo;    
	
	@OneToMany(mappedBy="zuserInstance",cascade = CascadeType.ALL)
	@InverseLogical("zuserInstance")
	private Collection<m> ms; /*(m)essage(s)*/
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}
	public void setMs(Collection<m> ms) {
		this.ms = ms;
	}
	public Collection<m> getMs() {
		return ms;
	}
	public void addM(m e)
	{
		ms.add(e);
	}
}
