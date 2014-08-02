package com.steellogistics.entity;


/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SupplyInfo.java
 * @Describe：供货信息实体类
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月25日 上午10:19:51
 * @Version v1.0
 */
public class SupplyInfoDetail {
	//ListItem展示
	private int id; 								// ID
	private String titleName; 			// 标题
	private String imageUrl; 				// 图片地址
	private String sellScope; 				// 经营范围
	private String price; 					// 商品单价
	private String productName; 		// 产品名称
	private String productsInfo; 		// 商品介绍
	private String content; 				// 发布内容
	private String companyName; 	// 商家名称
	private String companyAddress; // 商家地址
	private String mobile; 					// 联系电话
	private String creatTime; 			// 创建时间
	
	//详情页面展示
	private String type; 						// 钢材类型
	private String specification; 		// 规格
	private String makeAddress; 		// 产地
	private String amount; 					// 数量
	private String isGb; 						// 是否国标
	private String contacts; 				// 联系人
	private String email; 					// 电子邮箱
	private String dealType; 				// 交易方式
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getMakeAddress() {
		return makeAddress;
	}
	public void setMakeAddress(String makeAddress) {
		this.makeAddress = makeAddress;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIsGb() {
		return isGb;
	}
	public void setIsGb(String isGb) {
		this.isGb = isGb;
	}
	public String getSellScope() {
		return sellScope;
	}
	public void setSellScope(String sellScope) {
		this.sellScope = sellScope;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductsInfo() {
		return productsInfo;
	}
	public void setProductsInfo(String productsInfo) {
		this.productsInfo = productsInfo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	
	
}
