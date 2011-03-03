package com.zimbra.rest.parser;

import java.util.logging.Logger;

public class m implements java.io.Serializable{
	int id;
	String f;
	int rev;
	String d;
	int s;
	int l;
	int cid;
	String su;
	String fr;
	e instanceOfe;
	
    public m() 
    {
    	instanceOfe = new e(); 
    }

	public int getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
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

	public void setRev(String rev) {
		this.rev = Integer.parseInt(rev);
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

	public void setS(String s) {
		this.s = Integer.parseInt(s);
	}

	public int getL() {
		return l;
	}

	public void setL(String l) {
		this.l = Integer.parseInt(l);
	}

	public int getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = Integer.parseInt(cid);
	}

	public String getSu() {
		return su;
	}

	public void setSu(String su) {
		this.su = new String(su);
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public e getInstanceOfe() {
		return instanceOfe;
	}

	public void setInstanceOfe(e instanceOfe) {
		this.instanceOfe = instanceOfe;
	}
}
