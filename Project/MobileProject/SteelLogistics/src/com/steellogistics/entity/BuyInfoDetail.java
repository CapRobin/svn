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

	//ListItem展示
	private int id; 								// 用户ID
	private String titleName; 			// 信息标题
	private String imageUrl; 				// 图片地址
	private String otherInfo; 			// 补充信息
	private String buyAmount; 		// 求购数量
	private String buyPrice; 				// 求购价格
	private String userAddress; 			// 用户地址
	private String contactNumber; 	// 联系电话
	private String creatTime; 			// 创建时间
	
	//详情页面展示
	private String buyRequire; 			// 求购要求
	private String packRequire; 		// 包装要求
	private String userRealName; 	// 商家名称
	private String contacts; 				// 联系人
	
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
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(String buyAmount) {
		this.buyAmount = buyAmount;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
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
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
}
