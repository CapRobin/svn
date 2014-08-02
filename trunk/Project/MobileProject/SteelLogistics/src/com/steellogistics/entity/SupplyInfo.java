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
public class SupplyInfo {

	private int id; 								// ID
	private String titleName; 			// 标题
	private String imageUrl; 				// 图片地址
	private String sellScope; 				// 经营范围
	private String price; 					// 销售单价
	private String productName; 		// 产品名称
	private String productsInfo; 		// 商品介绍
	private String content; 				// 发布内容
	private String companyName; 	// 商家名称
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
