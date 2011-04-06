package com.zimbra.SMS;

public class SMS {

    private String mobileNumber;
    private String content;

    public SMS(String mobileNumber, String content) {
	System.out.println("Making sms->"+content);
	this.mobileNumber = mobileNumber;
	this.content = content;
    }

    public String getMobileNumber() {
	return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }
}
