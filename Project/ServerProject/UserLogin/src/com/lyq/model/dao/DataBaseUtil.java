package com.lyq.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * ���ݿ����ӹ�����
 * @author Li Yong Qiang
 */
public class DataBaseUtil {
	/**
	 * ��ȡ���ݿ�����
	 * @return Connection����
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			// ��������
			Class.forName("com.mysql.jdbc.Driver");
			// ���ݿ�����url
			String url = "jdbc:mysql://192.168.1.103:3306/db_database05"; 
			// ��ȡ���ݿ�����
			conn = DriverManager.getConnection(url, "root", "694687424");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * �ر����ݿ�����
	 * @param conn Connection����
	 */
	public static void closeConnection(Connection conn){
		// �ж�conn�Ƿ�Ϊ��
		if(conn != null){
			try {
				conn.close();	// �ر����ݿ�����
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
