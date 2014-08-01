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

	private int id; 								// ID
	private String titleName; 			// 标题
	private String imageUrl; 				// 图片地址
	private String buyInfo; 				// 求购信息
	private int buyAmount; 				// 求购数量
	private String buyPrice; 				// 价格要求
	private String companyAddress; // 商家地址
	private String mobile; 					// 联系电话
	private String creatTime; 			// 创建时间
	
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
	public String getBuyInfo() {
		return buyInfo;
	}
	public void setBuyInfo(String buyInfo) {
		this.buyInfo = buyInfo;
	}
	public int getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	
	
}
