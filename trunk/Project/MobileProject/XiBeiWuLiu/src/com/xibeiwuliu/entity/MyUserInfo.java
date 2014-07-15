package com.xibeiwuliu.entity;


/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：UserInfo.java
 * @Describe：TODO
 * @Author yufarong_yfr5734@163.com
 * @Date：2013-12-19 下午6:42:30
 * @Version v1.0
 */
public class MyUserInfo {

	private boolean isOk; 														// 登录是否成功
	private int userType; 															// 用户类型
	private DriverUserInfo driverUserInfo; 							// 司机个人信息
	private DriverCarInfo driverCarInfo; 								// 司机车辆信息
	private LogisticsUserInfo logisticsUserInfo; 						// 物流个人信息
	private LogisticsCompanyInfo logisticsCompanyInfo; 	// 物流公司信息

	
	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public DriverUserInfo getDriverUserInfo() {
		return driverUserInfo;
	}

	public void setDriverUserInfo(DriverUserInfo driverUserInfo) {
		this.driverUserInfo = driverUserInfo;
	}

	public DriverCarInfo getDriverCarInfo() {
		return driverCarInfo;
	}

	public void setDriverCarInfo(DriverCarInfo driverCarInfo) {
		this.driverCarInfo = driverCarInfo;
	}

	public LogisticsUserInfo getLogisticsUserInfo() {
		return logisticsUserInfo;
	}

	public void setLogisticsUserInfo(LogisticsUserInfo logisticsUserInfo) {
		this.logisticsUserInfo = logisticsUserInfo;
	}

	public LogisticsCompanyInfo getLogisticsCompanyInfo() {
		return logisticsCompanyInfo;
	}

	public void setLogisticsCompanyInfo(LogisticsCompanyInfo logisticsCompanyInfo) {
		this.logisticsCompanyInfo = logisticsCompanyInfo;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

}
