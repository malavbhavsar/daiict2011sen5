package com.zimbra.rest.appt;

public class inv implements java.io.Serializable {
    long inv_id;// ="430"
    long inv_seq;// ="0"
    long inv_compNum;// ="0"
    String inv_type;// ="appt">
    comp component;
    tz timezone;

    public inv() {

    }

    public comp getComponent() {
	return component;
    }

    public long getInv_compNum() {
	return inv_compNum;
    }

    public long getInv_id() {
	return inv_id;
    }

    public long getInv_seq() {
	return inv_seq;
    }

    public String getInv_type() {
	return inv_type;
    }

    public tz getTimezone() {
	return timezone;
    }

    public void setComponent(comp component) {
	this.component = component;
    }

    public void setInv_compNum(long invCompNum) {
	inv_compNum = invCompNum;
    }

    public void setInv_id(long invId) {
	inv_id = invId;
    }

    public void setInv_seq(long invSeq) {
	inv_seq = invSeq;
    }

    public void setInv_type(String invType) {
	inv_type = invType;
    }

    public void setTimezone(tz timezone) {
	this.timezone = timezone;
    }
}
