package com.zimbra.rest.appt;

//every sub element of recur in the xml file is included in this without creating a separate class for each of them

public class recur {
	String recur_rule_freq;//this is the frequence at which the alarm should be repeated
	int recur_until_date;//this is the date until which alarm should be repeated
	int recur_interval; // this is the intervel period between two consecutive repeats
	public String getRecur_rule_freq() {
		return recur_rule_freq;
	}
	public void setRecur_rule_freq(String repeatRuleFreq) {
		recur_rule_freq = repeatRuleFreq;
	}
	public int getRecur_until_date() {
		return recur_until_date;
	}
	public void setRecur_until_date(int recurUntilDate) {
		recur_until_date = recurUntilDate;
	}
	public int getRecur_interval() {
		return recur_interval;
	}
	public void setRecur_interval(int recurInterval) {
		recur_interval = recurInterval;
	}
	

}
