package com.xibeiwuliu.entity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：DriverUserInfo.java
 * @Describe：司机个人信息实体类
 * @Author yufarong_yfr5734@163.com
 * @Date：2013-12-20 上午11:05:42
 * @Version v1.0
 */
public class DriverUserInfo {

	private String Duser_id; // 用户ID
	private String Duser_name; // 司机姓名
	private String Duser_mobile; // 司机电话
	private String Duser_pwd; // 司机登录密码
	private String Duser_tle; // 司机随车电话
	private String Duser_key; // 司机身份证号
	private String Duser_tleJI; // 司机紧急联系电话
	private String Duser_photo; // 司机照片(身份证件照片)
	private String Duser_sex; // 司机性别
	private String Duser_Addr; // 司机家庭地址
	private String RoutePath; // 车辆路线跟车辆路线表(LPR_Duser_area)关联
	private String Duser_J_key; // 驾驶执照号
	private String Duser_level; // 用户级别(详见司机机别表)
	private String Duser_PJ; // 司机评价
	private String Duser_reamrk; // 备注
	private String Add_time; // 添加或修时间

	public String getDuser_id() {
		return Duser_id;
	}

	public void setDuser_id(String duser_id) {
		Duser_id = duser_id;
	}

	public String getDuser_name() {
		return Duser_name;
	}

	public void setDuser_name(String duser_name) {
		Duser_name = duser_name;
	}

	public String getDuser_mobile() {
		return Duser_mobile;
	}

	public void setDuser_mobile(String duser_mobile) {
		Duser_mobile = duser_mobile;
	}

	public String getDuser_pwd() {
		return Duser_pwd;
	}

	public void setDuser_pwd(String duser_pwd) {
		Duser_pwd = duser_pwd;
	}

	public String getDuser_tle() {
		return Duser_tle;
	}

	public void setDuser_tle(String duser_tle) {
		Duser_tle = duser_tle;
	}

	public String getDuser_key() {
		return Duser_key;
	}

	public void setDuser_key(String duser_key) {
		Duser_key = duser_key;
	}

	public String getDuser_tleJI() {
		return Duser_tleJI;
	}

	public void setDuser_tleJI(String duser_tleJI) {
		Duser_tleJI = duser_tleJI;
	}

	public String getDuser_photo() {
		return Duser_photo;
	}

	public void setDuser_photo(String duser_photo) {
		Duser_photo = duser_photo;
	}

	public String getDuser_sex() {
		return Duser_sex;
	}

	public void setDuser_sex(String duser_sex) {
		Duser_sex = duser_sex;
	}

	public String getDuser_Addr() {
		return Duser_Addr;
	}

	public void setDuser_Addr(String duser_Addr) {
		Duser_Addr = duser_Addr;
	}

	public String getDuser_J_key() {
		return Duser_J_key;
	}

	public void setDuser_J_key(String duser_J_key) {
		Duser_J_key = duser_J_key;
	}

	public String getDuser_level() {
		return Duser_level;
	}

	public void setDuser_level(String duser_level) {
		Duser_level = duser_level;
	}

	public String getDuser_PJ() {
		return Duser_PJ;
	}

	public void setDuser_PJ(String duser_PJ) {
		Duser_PJ = duser_PJ;
	}

	public String getDuser_reamrk() {
		return Duser_reamrk;
	}

	public void setDuser_reamrk(String duser_reamrk) {
		Duser_reamrk = duser_reamrk;
	}

	public String getAdd_time() {
		return Add_time;
	}

	public void setAdd_time(String add_time) {
		Add_time = add_time;
	}

	public String getRoutePath() {
		return RoutePath;
	}

	public void setRoutePath(String routePath) {
		RoutePath = routePath;
	}

}
