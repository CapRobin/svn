package com.haohuotong.entity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：LogisticsUserInfo.java
 * @Describe：物流公司账户信息
 * @Author yufarong_yfr5734@163.com
 * @Date：2013-12-19 下午7:42:33
 * @Version v1.0
 */
public class LogisticsUserInfo {

	private String Add_id; 				// id
	private String LPRId; 					// 物流公司ID
	private String userlogin_id;  		// 用户登_id
	private String userloginname; 	// 用户账号
	private String UserloginPwd; 		// 用户登录密码

	private String userTname; 			// 用户真实姓名
	private String userSex; 				// 用户性别
	private String userPost; 				// 用户职位
	private String UserMoble; 			// 用户电话
	private String UserPhoto; 			// 用户头像

	private String Add_time; 			// 添加修改时间

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
