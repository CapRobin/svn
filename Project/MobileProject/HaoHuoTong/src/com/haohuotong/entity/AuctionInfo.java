package com.haohuotong.entity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name��AuctionInfo.java
 * @Describe������ʵ����
 * @Author FaRong����yfr5734@gmail.com
 * @Date��2013-12-13 ����1:10:52
 * @Version v1.0
 */
public class AuctionInfo {

	private int Add_id; 					// id int
	private int User_id;
	private String Duser_name; 		// ����������
	private int goods_info_id; 		// ���ͻ�����Ϣ��¼ID int
	private int vehicle_info_id;     //��Դ��Ϣ��¼Id
	private int Car_ID; 				// ����ID int
	private String Car_NOI; 		// ���ƺ� varchar
	private String Car_type;        //��������
	private String Money_value; 	// ���ļ� money
	private String JP_time; 			// ����ʱ�� datetime
	private String JP_remark; 		// ���ı�ע
	private String other; 				// ����
	private String RoutePath;       //·��

	public int getAdd_id() {
		return Add_id;
	}

	public void setAdd_id(int add_id) {
		Add_id = add_id;
	}

	public int getUser_id() {
		return User_id;
	}

	public void setUser_id(int user_id) {
		User_id = user_id;
	}

	public String getDuser_name() {
		return Duser_name;
	}

	public void setDuser_name(String duser_name) {
		Duser_name = duser_name;
	}

	public int getGoods_info_id() {
		return goods_info_id;
	}

	public void setGoods_info_id(int goods_info_id) {
		this.goods_info_id = goods_info_id;
	}

	public int getCar_ID() {
		return Car_ID;
	}

	public void setCar_ID(int car_ID) {
		Car_ID = car_ID;
	}

	public String getCar_NOI() {
		return Car_NOI;
	}

	public void setCar_NOI(String car_NOI) {
		Car_NOI = car_NOI;
	}

	public String getMoney_value() {
		return Money_value;
	}

	public void setMoney_value(String money_value) {
		Money_value = money_value;
	}

	public String getJP_time() {
		return JP_time;
	}

	public void setJP_time(String jP_time) {
		JP_time = jP_time;
	}

	public String getJP_remark() {
		return JP_remark;
	}

	public void setJP_remark(String jP_remark) {
		JP_remark = jP_remark;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public int getVehicle_info_id() {
		return vehicle_info_id;
	}

	public void setVehicle_info_id(int vehicle_info_id) {
		this.vehicle_info_id = vehicle_info_id;
	}

	public String getCar_type() {
		return Car_type;
	}

	public void setCar_type(String car_type) {
		Car_type = car_type;
	}

	public String getRoutePath() {
		return RoutePath;
	}

	public void setRoutePath(String routePath) {
		RoutePath = routePath;
	}

}
