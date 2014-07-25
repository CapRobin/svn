package com.steellogistics.entity;


/**
 * 
 * Copyright (c) 2013 All rights reserved
 * @Name：BuyInfo.java 
 * @Describe：求购信息实体类
 * @Author:  yfr5734@gmail.com
 * @Date：2014年7月25日 上午10:57:59
 * @Version v1.0
 */
public class BuyInfo {

	private int id; 					// ID
	private String titleName; 	// 标题
	private String imageUrl; 		// 图片地址
	private String content; 		// 发布内容
	private String creatTime; 	// 创建时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	
	
}
