package com.lyq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lyq.model.User;
/**
 * �û����ݿ������
 * @author Li Yong Qiang
 */
public class UserDao {
	/**
	 * ����û�
	 * @param user �û�����
	 */
	public void saveUser(User user){
		// ��ȡ���ݿ�����Connection����
		Connection conn = DataBaseUtil.getConnection();
		// �����û�ע����Ϣ��SQL���
		String sql = "insert into tb_user(username,password,sex,tel,photo,email) values(?,?,?,?,?,?)";
		try {
			// ��ȡPreparedStatement����
			PreparedStatement ps = conn.prepareStatement(sql);
			// ��SQL����ռλ���������ж�̬��ֵ
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSex());
			ps.setString(4, user.getTel());
			ps.setString(5, user.getPhoto());
			ps.setString(6, user.getEmail());
			// ִ�и��²���
			ps.executeUpdate();
			// �ͷŴ� PreparedStatement ��������ݿ�� JDBC ��Դ
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// �ر����ݿ�����
			DataBaseUtil.closeConnection(conn);
		}
	}
	/**
	 * �û���¼
	 * @param username �û���
	 * @param password ����
	 * @return �û�����
	 */
	public User login(String username, String password){
		User user = null;
		// ��ȡ���ݿ�����Connection����
		Connection conn = DataBaseUtil.getConnection();
		// �����û����������ѯ�û���Ϣ
		String sql = "select * from tb_user where username = ? and password = ?";
		try {
			// ��ȡPreparedStatement����
			PreparedStatement ps = conn.prepareStatement(sql);
			// ��SQL����ռλ���������ж�̬��ֵ
			ps.setString(1, username);
			ps.setString(2, password);
			// ִ�в�ѯ��ȡ�����
			ResultSet rs = ps.executeQuery();
			// �жϽ�����Ƿ���Ч
			if(rs.next()){
				// ʵ����һ���û�����
				user = new User();
				// ���û��������Ը�ֵ
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setTel(rs.getString("tel"));
				user.setPhoto(rs.getString("photo"));
				user.setEmail(rs.getString("email"));
			}
			// �ͷŴ� ResultSet ��������ݿ�� JDBC ��Դ
			rs.close();
			// �ͷŴ� PreparedStatement ��������ݿ�� JDBC ��Դ
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// �ر����ݿ�����
			DataBaseUtil.closeConnection(conn);
		}
		return user;
	}
	/**
	 * �ж��û��������ݿ����Ƿ����
	 * @param username �û���
	 * @return ����ֵ
	 */
	public boolean userIsExist(String username){
		// ��ȡ���ݿ�����Connection����
		Connection conn = DataBaseUtil.getConnection();
		// ����ָ���û�����ѯ�û���Ϣ
		String sql = "select * from tb_user where username = ?";
		try {
			// ��ȡPreparedStatement����
			PreparedStatement ps = conn.prepareStatement(sql);
			// ���û��������Ը�ֵ
			ps.setString(1, username);
			// ִ�в�ѯ��ȡ�����
			ResultSet rs = ps.executeQuery();
			// �жϽ�����Ƿ���Ч
			if(!rs.next()){
				// �����Ч��֤�����û�������
				return true;
			}
			// �ͷŴ� ResultSet ��������ݿ�� JDBC ��Դ
			rs.close();
			// �ͷŴ� PreparedStatement ��������ݿ�� JDBC ��Դ
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			// �ر����ݿ�����
			DataBaseUtil.closeConnection(conn);
		}
		return false;
	}
}
