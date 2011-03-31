package com.zimbra.rest.m;


import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zimbra.user.zuser;

@Entity
public class m implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="username")
	zuser zuserInstance;
	@Id
	int id;
	@Basic
	String f;
	@Basic
	int rev;
	@Basic
	String d;
	@Basic
	int s;
	@Basic
	int l;
	@Basic
	int cid;
	@Basic
	String su;
	@Basic
	String fr;
	@Basic
	String dine;
	@Basic
	String tine;
	@Basic
	String aine;
	

	
    public m() 
    {
    }
    
	public zuser getUserInstance() {
		return zuserInstance;
	}
	public void setUserInstance(zuser zuserInstance) {
		this.zuserInstance = zuserInstance;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getSu() {
		return su;
	}

	public void setSu(String su) {
		this.su = su;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getDine() {
		return dine;
	}

	public void setDine(String dine) {
		this.dine = dine;
	}

	public String getTine() {
		return tine;
	}

	public void setTine(String tine) {
		this.tine = tine;
	}

	public String getAine() {
		return aine;
	}

	public void setAine(String aine) {
		this.aine = aine;
	}

}
