package com.zimbra.rest.appt;

public class inv implements java.io.Serializable {
	long inv_id;//="430"
	long inv_seq;//="0" 
	long inv_compNum;//="0" 
	String inv_type;//="appt">
	comp component;
	tz timezone;
	public tz getTimezone() {
		return timezone;
	}

	public void setTimezone(tz timezone) {
		this.timezone = timezone;
	}

	public comp getComponent() {
		return component;
	}

	public void setComponent(comp component) {
		this.component = component;
	}

	public long getInv_id() {
		return inv_id;
	}

	public void setInv_id(long invId) {
		inv_id = invId;
	}

	public long getInv_seq() {
		return inv_seq;
	}

	public void setInv_seq(long invSeq) {
		inv_seq = invSeq;
	}

	public long getInv_compNum() {
		return inv_compNum;
	}

	public void setInv_compNum(long invCompNum) {
		inv_compNum = invCompNum;
	}

	public String getInv_type() {
		return inv_type;
	}

	public void setInv_type(String invType) {
		inv_type = invType;
	}

	public inv()
	{
		
	}
}
