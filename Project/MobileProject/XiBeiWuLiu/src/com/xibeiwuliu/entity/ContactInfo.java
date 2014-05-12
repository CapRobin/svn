package com.xibeiwuliu.entity;

import android.graphics.Bitmap;

/**
 * 
  * Copyright (c) 2013 All rights reserved
  * @Name：ContactInfo.java 
  * @Describe：联系人实体类
  * @Author:  yfr5734@gmail.com
  * @Date：2014年5月12日 上午9:44:23
  * @Version v1.0 *
  *
 */
public class ContactInfo {
	private String name;
	private String phone;
	private Bitmap head;
	private long contactId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Bitmap getHead() {
		return head;
	}
	public void setHead(Bitmap head) {
		this.head = head;
	}
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	
	
}
