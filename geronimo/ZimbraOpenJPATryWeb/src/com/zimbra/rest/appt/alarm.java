package com.zimbra.rest.appt;

public class alarm implements java.io.Serializable {

    private String alarm_action;// this action is usually DISPLAY in the
				// settings
    trigger_rel alm_trig_rel;

    public String getAlarm_action() {
	return alarm_action;
    }

    public trigger_rel getAlm_trig_rel() {
	return alm_trig_rel;
    }

    public void setAlarm_action(String alarmAction) {
	alarm_action = alarmAction;
    }

    public void setAlm_trig_rel(trigger_rel almTrigRel) {
	alm_trig_rel = almTrigRel;
    }

}
