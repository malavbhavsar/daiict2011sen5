package com.zimbra.rest.appt;
import java.util.Vector;
public class comp implements java.io.Serializable{
	
	String comp_uid;
    long comp_d;
    int comp_allDay;//check if this can be boolean since="1"
    String comp_status;
    int comp_noBlob;//check if this can be boolean since="1"
    int comp_isOrg;//check if this can be boolean since="1"
    String comp__class;//="PUB"
    String comp_loc;//=""
    long comp_compNum;//check if this can be int
    long comp_apptId;//="1018"
    String comp_url;//="" 
    String comp_fb;//check if this can be char since ="B"
    long comp_calItemId;//="1018"
    String comp_x_uid;//="f23b0efd-af80-4e82-bffa-c906ae1880a1"
    String comp_name;//="competition"
    int comp_rsvp;//check if this can be boolean since="0"
    String comp_fba;//check if this can be char since ="B"
    long comp_seq;//check if this can be int since ="1"
    String comp_method;//="PUBLISH"
    String comp_transp;//check if this can be char="O">
    
    Vector<attendee> att;//since there can be more than one attendees a vector is used
    alarm alarm_desc;
    String notes_fr;//this is the notes that is entered while setting the appointment
    String desc;//this is the information in the form of text
    String deschtml;//this is the html description of the text
    organizer or;//this stores the details of the organizer of that appointment or inshort who created that appointment
    recur recur_info;//this contains the data telling if the alarm should be repeated
    String startdate;//this is the <s /> element
    String startdate_tz;
    String enddate;//this is the <e /> element
    String enddate_tz;
    
    
    public String getNotes_fr() {
		return notes_fr;
	}

	public void setNotes_fr(String notesFr) {
		notes_fr = notesFr;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDeschtml() {
		return deschtml;
	}

	public void setDeschtml(String deschtml) {
		this.deschtml = deschtml;
	}

	public organizer getOr() {
		return or;
	}

	public void setOr(organizer or) {
		this.or = or;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStartdate_tz() {
		return startdate_tz;
	}

	public void setStartdate_tz(String startdateTz) {
		startdate_tz = startdateTz;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEnddate_tz() {
		return enddate_tz;
	}

	public void setEnddate_tz(String enddateTz) {
		enddate_tz = enddateTz;
	}

	public recur getRecur_info() {
		return recur_info;
	}

	public void setRecur_info(recur recurInfo) {
		recur_info = recurInfo;
	}
	
    public Vector<attendee> getAtt() {
		return att;
	}

	public void setAtt(Vector<attendee> att) {
		this.att = att;
	}

	public alarm getAlarm_desc() {
		return alarm_desc;
	}

	public void setAlarm_desc(alarm alarmDesc) {
		alarm_desc = alarmDesc;
	}
	
    
	public comp()
	{
		
	}
	
	public String getComp_uid() {
		return comp_uid;
	}
	public void setComp_uid(String compUid) {
		comp_uid = compUid;
	}
	public long getComp_d() {
		return comp_d;
	}
	public void setComp_d(long compD) {
		comp_d = compD;
	}
	public int getComp_allDay() {
		return comp_allDay;
	}
	public void setComp_allDay(int compAllDay) {
		comp_allDay = compAllDay;
	}
	public String getComp_status() {
		return comp_status;
	}
	public void setComp_status(String compStatus) {
		comp_status = compStatus;
	}
	public int getComp_noBlob() {
		return comp_noBlob;
	}
	public void setComp_noBlob(int compNoBlob) {
		comp_noBlob = compNoBlob;
	}
	public int getComp_isOrg() {
		return comp_isOrg;
	}
	public void setComp_isOrg(int compIsOrg) {
		comp_isOrg = compIsOrg;
	}
	public String getComp__class() {
		return comp__class;
	}
	public void setComp__class(String compClass) {
		comp__class = compClass;
	}
	public String getComp_loc() {
		return comp_loc;
	}
	public void setComp_loc(String compLoc) {
		comp_loc = compLoc;
	}
	public long getComp_compNum() {
		return comp_compNum;
	}
	public void setComp_compNum(long compCompNum) {
		comp_compNum = compCompNum;
	}
	public long getComp_apptId() {
		return comp_apptId;
	}
	public void setComp_apptId(long compApptId) {
		comp_apptId = compApptId;
	}
	public String getComp_url() {
		return comp_url;
	}
	public void setComp_url(String compUrl) {
		comp_url = compUrl;
	}
	public String getComp_fb() {
		return comp_fb;
	}
	public void setComp_fb(String compFb) {
		comp_fb = compFb;
	}
	public long getComp_calItemId() {
		return comp_calItemId;
	}
	public void setComp_calItemId(long compCalItemId) {
		comp_calItemId = compCalItemId;
	}
	public String getComp_x_uid() {
		return comp_x_uid;
	}
	public void setComp_x_uid(String compXUid) {
		comp_x_uid = compXUid;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String compName) {
		comp_name = compName;
	}
	public int getComp_rsvp() {
		return comp_rsvp;
	}
	public void setComp_rsvp(int compRsvp) {
		comp_rsvp = compRsvp;
	}
	public String getComp_fba() {
		return comp_fba;
	}
	public void setComp_fba(String compFba) {
		comp_fba = compFba;
	}
	public long getComp_seq() {
		return comp_seq;
	}
	public void setComp_seq(long compSeq) {
		comp_seq = compSeq;
	}
	public String getComp_method() {
		return comp_method;
	}
	public void setComp_method(String compMethod) {
		comp_method = compMethod;
	}
	public String getComp_transp() {
		return comp_transp;
	}
	public void setComp_transp(String compTransp) {
		comp_transp = compTransp;
	}
}
