package com.haohuotong.entity;

import java.io.Serializable;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * @Name��VehicleInfo.java 
 * @Describe����Դ��Ϣʵ����
 * @Author yufarong_yfr5734@163.com
 * @Date��2014-1-3 ����2:33:33
 * @Version v1.0
 */
public class VehicleInfo implements Serializable {

	private String Add_id; 						// ID
	private String info_connect; 				// ��Ϣ����
	private String Car_Status; 					// ����״̬
	private String Car_InfoAddTime; 		// ��Ϣ���ʱ��
	
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
	
//	private String Add_id; 						// ID
//	private String Duser_id; 						// ����_id
//	private String Car_key; 						// ����
//	private String Car_type; 						// ����
//	private String Car_length; 					// ����(M)
//	private String Car_height; 					// ����
//	
//	private String Car_max_dun; 			// �����ض�λ(Kg)
//	private String Car_bulk; 						// �ݻ�(M3)
//	private String Car_Fkey; 					// ��������
//	private String Car_Jkey; 					// ���ܺ���
//	private String Car_Ykey; 					// ��Ӫ֤��
//	
//	private String Car_Bkey; 					// ���տ���
//	private String Car_Gunit; 					// �ҿ���λ
//	private String Add_time; 					// ���ʱ��
//	private String Car_Bway; 					// ������
//	private String Car_Eway; 					// Ŀ�ĵ�
//	private String Car_Status; 					// ����״̬
//	private String Car_InfoAddTime; 		// ��Ϣ���ʱ��
	
}
