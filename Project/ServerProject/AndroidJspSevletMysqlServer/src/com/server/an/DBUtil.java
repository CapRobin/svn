package com.server.an;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author hz.guo ���ݿ⹤����
 */
public class DBUtil {

	/*
	 * �ر����ݿ�����
	 */
	public void closeConn(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * �����ݿ�����
	 */
	public Connection openConnection() {
		String url = null;
		String username = null;
		String password = null;

		try {
//			Class.forName("org.gjt.mm.mysql.Driver");
//			url = "jdbc:mysql://localhost:3307/police_db?useUnicode=true&characterEncoding=utf-8";
//			username = "root";
//			password = "694687424";
//			Connection con = DriverManager.getConnection(url, username,
//					password);
			
//			Class.forName("org.gjt.mm.mysql.Driver");
////			String url = "jdbc:mysql://localhost:3307/police_db?useUnicode=true&characterEncoding=utf-8";
//			String url = "jdbc:mysql://localhost:3306/hsx";
//			String username = "root";
//			String password = "694687424";
//			connection = DriverManager.getConnection(url, username,
//					password);
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://192.168.1.103:3306/police_db","root","694687424");		
			
			
			
			if (!con.isClosed()) {
				System.out.println("�ɹ���");
			}
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�޷��������ݿ⣡");
		}

		return null;
	}

}
