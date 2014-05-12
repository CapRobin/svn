package com.xibeiwuliu.entity;

import java.io.Serializable;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：VehicleInfo.java
 * @Describe：车源信息实体类
 * @Author yufarong_yfr5734@163.com
 * @Date：2014-1-3 下午2:33:33
 * @Version v1.0
 */
public class VehicleInfo implements Serializable {

	private String Add_id; // ID
	private String info_connect; // 信息内容
	private String Car_Status; // 车辆状态
	private String Car_InfoAddTime; // 信息添加时间

	public String getAdd_id() {
		return Add_id;
	}

	public void setAdd_id(String add_id) {
		Add_id = add_id;
	}

	public String getInfo_connect() {
		return info_connect;
	}

	public void setInfo_connect(String info_connect) {
		this.info_connect = info_connect;
	}

	public String getCar_Status() {
		return Car_Status;
	}

	public void setCar_Status(String car_Status) {
		Car_Status = car_Status;
	}

	public String getCar_InfoAddTime() {
		return Car_InfoAddTime;
	}

	public void setCar_InfoAddTime(String car_InfoAddTime) {
		Car_InfoAddTime = car_InfoAddTime;
	}
}
