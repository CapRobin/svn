package com.way.chat.common.bean;

import java.io.Serializable;

public class AddFri implements Serializable{
	private static final long serialVersionUID = 1L;
	private int addid;
	private int userid;
	private int frid;
	public int getAddid() {
		return addid;
	}
	public void setAddid(int addid) {
		this.addid = addid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFrid() {
		return frid;
	}
	public void setFrid(int frid) {
		this.frid = frid;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "addid="+this.addid+"\n"+
				"userid="+this.userid+"\n"+
		        "frid="+this.frid;
	}

}
