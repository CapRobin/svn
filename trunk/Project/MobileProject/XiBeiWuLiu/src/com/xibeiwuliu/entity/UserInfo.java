package com.xibeiwuliu.entity;

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

	private String id; 							// 会员ID
	private String names; 					// 真实姓名
	private String telphone; 				// 电话
	private String username; 			// 会员名
	private String password; 				// 密码
	private String usertypes; 				// 用户类型（1:司机或0:物流公司）

	private String email; 					// 邮箱
	private String mibaoQ; 				// 密码保护信息-问题
	private String mibaoA; 				// 密码保护信息-答案
	private String lasttime; 				// 到期期限（还有多少天到期）
	private String addfunc; 				// 是否可以发布信息（0：否；1：是）
	private String handwrite; 			// 是否可以手写（0：否；1：是）

	private String AddTimes; 			// 时间间隔(单位S)[0:无时间间隔;默认:45S]
	private String InfoNum; 			// 信息条数数目[默认100]
	private String CharNum; 			// 信息字数[默认60]
	private String changeinfo; 			// 是否可以更改注册信息(0:无[默认];1;是)
	private String changepwd; 			// 是否可以更改密码(0:无[默认];1;是)
	private String userinfos; 				// 加密字符串组成，服务端做用户请求身份验证使用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertypes() {
		return usertypes;
	}

	public void setUsertypes(String usertypes) {
		this.usertypes = usertypes;
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

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public String getAddfunc() {
		return addfunc;
	}

	public void setAddfunc(String addfunc) {
		this.addfunc = addfunc;
	}

	public String getHandwrite() {
		return handwrite;
	}

	public void setHandwrite(String handwrite) {
		this.handwrite = handwrite;
	}

	public String getAddTimes() {
		return AddTimes;
	}

	public void setAddTimes(String addTimes) {
		AddTimes = addTimes;
	}

	public String getInfoNum() {
		return InfoNum;
	}

	public void setInfoNum(String infoNum) {
		InfoNum = infoNum;
	}

	public String getCharNum() {
		return CharNum;
	}

	public void setCharNum(String charNum) {
		CharNum = charNum;
	}

	public String getChangeinfo() {
		return changeinfo;
	}

	public void setChangeinfo(String changeinfo) {
		this.changeinfo = changeinfo;
	}

	public String getChangepwd() {
		return changepwd;
	}

	public void setChangepwd(String changepwd) {
		this.changepwd = changepwd;
	}

	public String getUserinfos() {
		return userinfos;
	}

	public void setUserinfos(String userinfos) {
		this.userinfos = userinfos;
	}

}
