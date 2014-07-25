package com.steellogistics.entity;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：AreaInfo.java
 * @Describe：地理区域信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月5日 上午10:53:44
 * @Version v1.0 *
 * 
 */
public class AreaInfo {

	private int ccityId; // 城市ID
	private String ccityName; // 城市名称
	private int parentId; // 父节点ID

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
