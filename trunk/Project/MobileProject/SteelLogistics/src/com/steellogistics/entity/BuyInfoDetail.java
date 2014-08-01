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
public class BuyInfoDetail {

	private int id; 								// ID
	private String titleName; 			// 标题
	private String imageUrl; 				// 图片地址
	private String buyInfo; 				// 求购信息
	private String buyPrice; 				// 价格要求
	private int buyAmount; 				// 求购数量
	private String buyRequire; 			// 求购要求
	private String packRequire; 		// 包装要求
	private String companyName; 	// 商家名称
	private String companyAddress; // 商家地址
	private String contacts; 				// 联系人
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
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}
	public String getBuyRequire() {
		return buyRequire;
	}
	public void setBuyRequire(String buyRequire) {
		this.buyRequire = buyRequire;
	}
	public String getPackRequire() {
		return packRequire;
	}
	public void setPackRequire(String packRequire) {
		this.packRequire = packRequire;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
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
