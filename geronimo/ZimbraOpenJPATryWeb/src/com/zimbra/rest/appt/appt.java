package com.zimbra.rest.appt;

public class appt implements java.io.Serializable {

    long appt_id;// check if this can be int="431"
    String appt_uid;// ="bd696cee-cd5a-43f2-aebd-29f194208343"
    long appt_d;// check if this can be int="1279000849000"
    long appt_rev;// check if this can be int="325"
    int appt_s;// ="0"
    int appt_l;// check if this should be long ="10">
    inv invite;

    replies reply;

    public appt() {

    }

    public long getAppt_d() {
	return appt_d;
    }

    public long getAppt_id() {
	return appt_id;
    }

    public int getAppt_l() {
	return appt_l;
    }

    public long getAppt_rev() {
	return appt_rev;
    }

    public int getAppt_s() {
	return appt_s;
    }

    public String getAppt_uid() {
	return appt_uid;
    }

    public inv getInvite() {
	return invite;
    }

    public replies getReply() {
	return reply;
    }

    public void setAppt_d(long apptD) {
	appt_d = apptD;
    }

    public void setAppt_id(long apptId) {
	appt_id = apptId;
    }

    public void setAppt_l(int apptL) {
	appt_l = apptL;
    }

    public void setAppt_rev(long apptRev) {
	appt_rev = apptRev;
    }

    public void setAppt_s(int apptS) {
	appt_s = apptS;
    }

    public void setAppt_uid(String apptUid) {
	appt_uid = apptUid;
    }

    public void setInvite(inv invite) {
	this.invite = invite;
    }

    public void setReply(replies reply) {
	this.reply = reply;
    }
}