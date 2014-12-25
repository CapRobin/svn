package com.haohuotong.entity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name��UserInfo.java
 * @Describe��TODO
 * @Author yufarong_yfr5734@163.com
 * @Date��2013-12-19 ����6:42:30
 * @Version v1.0
 */
public class UserInfo {

	private boolean isOk; 														// ��¼�Ƿ�ɹ�
	private int userType; 															// �û�����
	private DriverUserInfo driverUserInfo; 							// ˾��������Ϣ
	private DriverCarInfo driverCarInfo; 								// ˾��������Ϣ
	private LogisticsUserInfo logisticsUserInfo; 						// ����������Ϣ
	private LogisticsCompanyInfo logisticsCompanyInfo; 	// ������˾��Ϣ

	
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
