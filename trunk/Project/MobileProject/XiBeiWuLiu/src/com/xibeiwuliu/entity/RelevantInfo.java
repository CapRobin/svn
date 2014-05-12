package com.xibeiwuliu.entity;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：RelevantInfo.java
 * @Describe：行业资讯实体类
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月8日 下午3:37:02
 * @Version v1.0 *
 * 
 */
public class RelevantInfo {

	private int msgId; // 信息ID
	private String titleName; // 信息标题
	private String content; // 信息内容
	private String time; // 发布时间
	private String imageUrl; // 图片地址

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

}
