package com.zimbra.rest.m;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.zimbra.user.zuser;

@Entity
public class m {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gid_m;
    @Basic
    private int id;
    @Basic
    private String f;
    @Basic
    private int rev;
    @Basic
    private String d;
    @Basic
    private int s;
    @Basic
    private int l;
    @Basic
    private int cid;
    @Basic
    private String su;
    @Basic
    private String fr;
    @Basic
    private String dine;
    @Basic
    private String tine;
    @Basic
    private String aine;
    @Basic
    private boolean ifSent;

    @ManyToOne
    @JoinColumn(name = "gid_zuser", nullable = false)
    protected zuser zuserInstance;

    public m() {
    }

    public String getAine() {
	return aine;
    }

    public int getCid() {
	return cid;
    }

    public String getD() {
	return d;
    }

    public String getDine() {
	return dine;
    }

    public String getF() {
	return f;
    }

    public String getFr() {
	return fr;
    }

    public int getGid_m() {
	return gid_m;
    }

    public int getId() {
	return id;
    }

    public int getL() {
	return l;
    }

    public int getRev() {
	return rev;
    }

    public int getS() {
	return s;
    }

    public String getSu() {
	return su;
    }

    public String getTine() {
	return tine;
    }

    public zuser getZuserInstance() {
	return zuserInstance;
    }

    public boolean isIfSent() {
	return ifSent;
    }

    public void setAine(String aine) {
	this.aine = aine;
    }

    public void setCid(int cid) {
	this.cid = cid;
    }

    public void setD(String d) {
	this.d = d;
    }

    public void setDine(String dine) {
	this.dine = dine;
    }

    public void setF(String f) {
	this.f = f;
    }

    public void setFr(String fr) {
	this.fr = fr;
    }

    public void setGid_m(int gid_m) {
	this.gid_m = gid_m;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setIfSent(boolean ifSent) {
	this.ifSent = ifSent;
    }

    public void setL(int l) {
	this.l = l;
    }

    public void setRev(int rev) {
	this.rev = rev;
    }

    public void setS(int s) {
	this.s = s;
    }

    public void setSu(String su) {
	this.su = su;
    }

    public void setTine(String tine) {
	this.tine = tine;
    }

    public void setZuserInstance(zuser zuserInstance) {
	this.zuserInstance = zuserInstance;
    }
}
