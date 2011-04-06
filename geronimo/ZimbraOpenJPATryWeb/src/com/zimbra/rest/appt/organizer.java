package com.zimbra.rest.appt;

public class organizer {
    String or_d;// This is the part before @ in the mail id
    String or_a;// This might be the email address
    String or_url;

    public String getOr_a() {
	return or_a;
    }

    public String getOr_d() {
	return or_d;
    }

    public String getOr_url() {
	return or_url;
    }

    public void setOr_a(String orA) {
	or_a = orA;
    }

    public void setOr_d(String orD) {
	or_d = orD;
    }

    public void setOr_url(String orUrl) {
	or_url = orUrl;
    }
}
