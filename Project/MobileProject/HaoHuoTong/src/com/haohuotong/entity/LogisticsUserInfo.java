package com.haohuotong.entity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name��LogisticsUserInfo.java
 * @Describe��������˾�˻���Ϣ
 * @Author yufarong_yfr5734@163.com
 * @Date��2013-12-19 ����7:42:33
 * @Version v1.0
 */
public class LogisticsUserInfo {

	private String Add_id; 				// id
	private String LPRId; 					// ������˾ID
	private String userlogin_id;  		// �û���_id
	private String userloginname; 	// �û��˺�
	private String UserloginPwd; 		// �û���¼����

	private String userTname; 			// �û���ʵ����
	private String userSex; 				// �û��Ա�
	private String userPost; 				// �û�ְλ
	private String UserMoble; 			// �û��绰
	private String UserPhoto; 			// �û�ͷ��

	private String Add_time; 			// ����޸�ʱ��

	public String getAdd_id() {
		return Add_id;
	}

	public void setAdd_id(String add_id) {
		Add_id = add_id;
	}

	public String getLPRId() {
		return LPRId;
	}

	public void setLPRId(String lPRId) {
		LPRId = lPRId;
	}

	public String getUserlogin_id() {
		return userlogin_id;
	}

	public void setUserlogin_id(String userlogin_id) {
		this.userlogin_id = userlogin_id;
	}

	public String getUserloginname() {
		return userloginname;
	}

	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}

	public String getUserloginPwd() {
		return UserloginPwd;
	}

	public void setUserloginPwd(String userloginPwd) {
		UserloginPwd = userloginPwd;
	}

	public String getUserTname() {
		return userTname;
	}

	public void setUserTname(String userTname) {
		this.userTname = userTname;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserPost() {
		return userPost;
	}

	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}

	public String getUserMoble() {
		return UserMoble;
	}

	public void setUserMoble(String userMoble) {
		UserMoble = userMoble;
	}

	public String getUserPhoto() {
		return UserPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		UserPhoto = userPhoto;
	}

	public String getAdd_time() {
		return Add_time;
	}

	public void setAdd_time(String add_time) {
		Add_time = add_time;
	}

}
