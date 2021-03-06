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
	private String realName; 			// 真实姓名(公司或个人真实姓名)
	private String userName; 			// 用户名
	private String password; 				// 密码
	private String sex; 						// 性别
	private String age; 						// 年龄
	private String contacts; 				// 联系人
	private String mobile; 					// 移动电话
	private String telephone; 			// 座机电话
	
	private String grade; 					// 等级
	private String identityCard; 		// 身份证号
	private String email; 					// 邮箱
	private String address; 				// 地址
	private String industry; 		   		// 所属行业
	private String companyInfo; 		// 公司简介
	private int userType; 					// 用户类型（0:普通会员；1:公司商户）
	private String mibaoQ; 				// 密码保护信息-问题
	private String mibaoA; 				// 密码保护信息-答案
	
	private String lasttime; 				// 到期期限（还有多少天到期）
	private int couldPublic; 				// 是否可以发布信息（1：是；0：否）
	private String userInfo; 				// 加密字符串组成，服务端做用户请求身份验证使用
	private String longitude; 				// 经度
	private String latitude; 				// 纬度
	private String imei; 						// 移动设备识码
	private String other2; 					// 其他参数
	private String other3; 					// 其他参数
	
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
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
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public int getCouldPublic() {
		return couldPublic;
	}
	public void setCouldPublic(int couldPublic) {
		this.couldPublic = couldPublic;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
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
	
	
}
