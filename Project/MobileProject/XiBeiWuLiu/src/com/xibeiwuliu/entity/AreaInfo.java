package com.xibeiwuliu.entity;

/**
 * 
  * Copyright (c) 2013 All rights reserved
  * @Name��AreaInfo.java 
  * @Describe������������Ϣ
  * @Author:  yfr5734@gmail.com
  * @Date��2014��5��5�� ����10:53:44
  * @Version v1.0 *
  *
 */
public class AreaInfo {

	private int ccityId; 							// 	����ID
	private String ccityName; 				// 	��������
	private int	parentId; 						// 	���ڵ�ID
	
	public int getCcityId() {
		return ccityId;
	}
	public void setCcityId(int ccityId) {
		this.ccityId = ccityId;
	}
	public String getCcityName() {
		return ccityName;
	}
	public void setCcityName(String ccityName) {
		this.ccityName = ccityName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}
