package com.cai.reader.entity;

public class RssAddressInfo {
	public int id;
	public String title;
	public String address;
	public RssAddressInfo(String title,String address){
		this.address=address;
		this.title=title;
	}
	public RssAddressInfo(){
		super();
	}
}
