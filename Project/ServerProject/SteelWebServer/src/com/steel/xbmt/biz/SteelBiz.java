package com.steel.xbmt.biz;

import com.steel.xbmt.dao.SteelInfoDao;
import com.steel.xbmt.entity.UserInfo;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name��SteelBiz.java
 * @Describe�����ݴ������
 * @Author: yfr5734@gmail.com
 * @Date��2014��9��3�� ����4:02:31
 * @Version v1.0
 */
public class SteelBiz {
	private SteelInfoDao dao = new SteelInfoDao();

	// ��¼
	public UserInfo login(String userName, String userPwd) {
		return dao.userLogin(userName, userPwd);
	}
}
