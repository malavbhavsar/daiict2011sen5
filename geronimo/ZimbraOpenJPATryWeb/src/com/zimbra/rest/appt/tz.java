package com.zimbra.rest.appt;

public class tz implements java.io.Serializable {
	String tz_id;//="Asia/Kolkata" 
	long tz_stdoff;//="330"
	String tz_stdname;//="IST" /> 
	public tz()
	{
		
	}
	public String getTz_id() {
		return tz_id;
	}
	public void setTz_id(String tzId) {
		tz_id = tzId;
	}
	public long getTz_stdoff() {
		return tz_stdoff;
	}
	public void setTz_stdoff(long tzStdoff) {
		tz_stdoff = tzStdoff;
	}
	public String getTz_stdname() {
		return tz_stdname;
	}
	public void setTz_stdname(String tzStdname) {
		tz_stdname = tzStdname;
	}

}
