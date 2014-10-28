package com.steel.xbmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.steel.xbmt.entity.UserInfo;
import com.steel.xbmt.util.DBManager;

/**
 * 
 * @author
 */
public class SteelInfoDao{
	private DBManager db = new DBManager();

	/**
	 * 通过用户名称和密码登录，登录成功返回User对象，登录失败返回null
	 */
	public UserInfo userLogin(String userName, String userPwd) {
//		info.setRealName(resultSet.getString("realName"));
//		info.setUserName(resultSet.getString("userName"));
//		info.setPassword(resultSet.getString("password"));
//		info.setSex(resultSet.getString("sex"));
//		info.setAge(resultSet.getString("age"));
//		info.setContacts(resultSet.getString("contacts"));
//		info.setMobile(resultSet.getString("mobile"));
//		info.setTelephone(resultSet.getString("telephone"));
//		String sql = " select id,userName,password from userinfo where userName=? and password=?";
		String sql = " select * from userinfo where userName=? and password=?";
//		DBManager db = new DBManager();
		Connection conn = db.openConnection();
		UserInfo info =  null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPwd);
			
			ResultSet resultSet = preparedStatement.executeQuery();
//			List<String> list = new ArrayList<>();
//			list.add(userName);
//			list.add(userPwd);
//			ResultSet resultSet = db.excuteQuery(sql,list);
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				info = new UserInfo();
				info.setId(resultSet.getInt("id"));
				info.setRealName(resultSet.getString("realName"));
				info.setUserName(resultSet.getString("userName"));
				info.setPassword(resultSet.getString("password"));
				info.setSex(resultSet.getString("sex"));
				info.setAge(resultSet.getString("age"));
				info.setContacts(resultSet.getString("contacts"));
				info.setMobile(resultSet.getString("mobile"));
				info.setTelephone(resultSet.getString("telephone"));
				return info;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
