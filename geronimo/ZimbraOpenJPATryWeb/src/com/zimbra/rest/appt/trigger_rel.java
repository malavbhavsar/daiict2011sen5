package com.zimbra.rest.appt;

public class trigger_rel implements java.io.Serializable {
	//clubbed both trigger and rel elements together
	int tri_rel_neg;//="1"
	int tri_rel_m;//how much time before should the alarm be triggered="5"
	String related;//="START" /> 
	public trigger_rel()
	{
		
	}
	public int getTri_rel_neg() {
		return tri_rel_neg;
	}
	public void setTri_rel_neg(int triRelNeg) {
		tri_rel_neg = triRelNeg;
	}
	public int getTri_rel_m() {
		return tri_rel_m;
	}
	public void setTri_rel_m(int triRelM) {
		tri_rel_m = triRelM;
	}
	public String getRelated() {
		return related;
	}
	public void setRelated(String related) {
		this.related = related;
	}
}
