package com.zimbra.rest.appt;

public class attendee {
	String at_d;//This is the part before @ in the mail id
	String at_a;//This might be the email address
	int at_rsvp;
	String at_role;
	String at_ptst;
	String at_url;
	
	public String getAt_url() {
		return at_d;
	}
	public void setAt_url(String atD) {
		at_d = atD;
	}
	
	public String getAt_d() {
		return at_d;
	}
	public void setAt_d(String atD) {
		at_d = atD;
	}
	public String getAt_a() {
		return at_a;
	}
	public void setAt_a(String atA) {
		at_a = atA;
	}
	public int getAt_rsvp() {
		return at_rsvp;
	}
	public void setAt_rsvp(int atRsvp) {
		at_rsvp = atRsvp;
	}
	public String getAt_role() {
		return at_role;
	}
	public void setAt_role(String atRole) {
		at_role = atRole;
	}
	public String getAt_ptst() {
		return at_ptst;
	}
	public void setAt_ptst(String atPtst) {
		at_ptst = atPtst;
	}

}
