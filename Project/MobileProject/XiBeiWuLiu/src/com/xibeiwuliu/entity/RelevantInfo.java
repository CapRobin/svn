package com.xibeiwuliu.entity;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name��RelevantInfo.java
 * @Describe����ҵ��Ѷʵ����
 * @Author: yfr5734@gmail.com
 * @Date��2014��5��8�� ����3:37:02
 * @Version v1.0 *
 * 
 */
public class RelevantInfo {

	private int msgId; // ��ϢID
	private String titleName; // ��Ϣ����
	private String content; // ��Ϣ����
	private String time; // ����ʱ��
	private String imageUrl; // ͼƬ��ַ

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
