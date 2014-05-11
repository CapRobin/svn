package com.xibeiwuliu.entity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：DriverCarInfo.java
 * @Describe：司机车辆信息
 * @Author yufarong_yfr5734@163.com
 * @Date：2013-12-19 下午6:57:00
 * @Version v1.0
 */
public class DriverCarInfo {

	private String Add_id; 				// id
	private String Duser_id; 				// 车主_id
	private String Car_key; 				// 车牌
	private String Car_type; 				// 车型
	private String Car_length; 			// 车长(M)

	private String Car_height; 			// 车高
	private String Car_max_dun; 	// 最大承载吨位(Kg)
	private String Car_bulk; 				// 容积(M3)
	private String Car_Fkey; 			// 发动机号
	private String Car_Jkey; 			// 车架号码

	private String Car_Ykey; 			// 运营证号
	private String Car_Bkey; 			// 保险卡号
	private String Car_Gunit; 			// 挂靠单位
	private String Add_time; 			// 添加时间

	public String getAdd_id() {
		return Add_id;
	}

	public void setAdd_id(String add_id) {
		Add_id = add_id;
	}

	public String getDuser_id() {
		return Duser_id;
	}

	public void setDuser_id(String duser_id) {
		Duser_id = duser_id;
	}

	public String getCar_key() {
		return Car_key;
	}

	public void setCar_key(String car_key) {
		Car_key = car_key;
	}

	public String getCar_type() {
		return Car_type;
	}

	public void setCar_type(String car_type) {
		Car_type = car_type;
	}

	public String getCar_length() {
		return Car_length;
	}

	public void setCar_length(String car_length) {
		Car_length = car_length;
	}

	public String getCar_height() {
		return Car_height;
	}

	public void setCar_height(String car_height) {
		Car_height = car_height;
	}

	public String getCar_max_dun() {
		return Car_max_dun;
	}

	public void setCar_max_dun(String car_max_dun) {
		Car_max_dun = car_max_dun;
	}

	public String getCar_bulk() {
		return Car_bulk;
	}

	public void setCar_bulk(String car_bulk) {
		Car_bulk = car_bulk;
	}

	public String getCar_Fkey() {
		return Car_Fkey;
	}

	public void setCar_Fkey(String car_Fkey) {
		Car_Fkey = car_Fkey;
	}

	public String getCar_Jkey() {
		return Car_Jkey;
	}

	public void setCar_Jkey(String car_Jkey) {
		Car_Jkey = car_Jkey;
	}

	public String getCar_Ykey() {
		return Car_Ykey;
	}

	public void setCar_Ykey(String car_Ykey) {
		Car_Ykey = car_Ykey;
	}

	public String getCar_Bkey() {
		return Car_Bkey;
	}

	public void setCar_Bkey(String car_Bkey) {
		Car_Bkey = car_Bkey;
	}

	public String getCar_Gunit() {
		return Car_Gunit;
	}

	public void setCar_Gunit(String car_Gunit) {
		Car_Gunit = car_Gunit;
	}

	public String getAdd_time() {
		return Add_time;
	}

	public void setAdd_time(String add_time) {
		Add_time = add_time;
	}

}
