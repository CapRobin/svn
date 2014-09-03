package com.steel.xbmt.entity;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：UserInfo.java
 * @Describe：用户信息实体类
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月29日 上午9:42:45
 * @Version v1.0
 */
public class UserInfo {

	private int id; 								// 会员ID
	private String realName; 			// 真实姓名(公司或个人真实姓名)
	private String userName; 			// 用户名
	private String password; 				// 密码
	private String sex; 						// 性别
	private String age; 						// 年龄
	private String contacts; 				// 联系人
	private String mobile; 					// 移动电话
	private String telephone; 			// 座机电话
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
