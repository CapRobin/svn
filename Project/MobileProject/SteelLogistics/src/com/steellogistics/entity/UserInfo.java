package com.steellogistics.entity;

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
	private String realName; 			// 真实姓名
	private String telphone; 				// 电话
	private String userName; 			// 用户名
	private String password; 				// 密码
	
	private int userType; 					// 用户类型（0:普通会员；1:公司商户）
	private String email; 					// 邮箱
	private String mibaoQ; 				// 密码保护信息-问题
	private String mibaoA; 				// 密码保护信息-答案
	private int lasttime; 					// 到期期限（还有多少天到期）
	
	private int couldPublic; 				// 是否可以发布信息（1：是；0：否）
	private String userInfo; 				// 加密字符串组成，服务端做用户请求身份验证使用
	private String longitude; 				// 经度
	private String latitude; 				// 纬度
	private String imei; 						// 移动设备识码

	private String other1; 					// 其他参数
	private String other2; 					// 其他参数
	private String other3; 					// 其他参数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNames() {
		return realName;
	}
	public void setNames(String names) {
		this.realName = names;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUsertypes() {
		return userType;
	}
	public void setUsertypes(int usertypes) {
		this.userType = usertypes;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMibaoQ() {
		return mibaoQ;
	}
	public void setMibaoQ(String mibaoQ) {
		this.mibaoQ = mibaoQ;
	}
	public String getMibaoA() {
		return mibaoA;
	}
	public void setMibaoA(String mibaoA) {
		this.mibaoA = mibaoA;
	}
	public int getLasttime() {
		return lasttime;
	}
	public void setLasttime(int lasttime) {
		this.lasttime = lasttime;
	}
	public int getAddfunc() {
		return couldPublic;
	}
	public void setAddfunc(int addfunc) {
		this.couldPublic = addfunc;
	}
	public String getUserinfos() {
		return userInfo;
	}
	public void setUserinfos(String userinfos) {
		this.userInfo = userinfos;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getOther1() {
		return other1;
	}
	public void setOther1(String other1) {
		this.other1 = other1;
	}
	public String getOther2() {
		return other2;
	}
	public void setOther2(String other2) {
		this.other2 = other2;
	}
	public String getOther3() {
		return other3;
	}
	public void setOther3(String other3) {
		this.other3 = other3;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
}
