package com.server.an;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author
 */
public class UserDaoImpl{

	/**
	 * 通过用户名称和密码登录，登录成功返回User对象，登录失败返回null
	 */
	public User login(String username, String password) {

		String sql = " select id,username,password from usertbl where username=? and password=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				User u = new User();
				u.setId(id);
				u.setPassword(password);
				u.setUsername(username);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}

}
