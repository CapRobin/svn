package com.steel.xbmt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

//	public static final String DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";		//SqlServer����
	public static final String DRIVER = "com.mysql.jdbc.Driver";		//SqlServer����
	public static final String URL = "jdbc:mysql://192.168.1.103:3306/steel";
	public static final String DBUSER = "root";
	public static final String DBPWD = "694687424";

	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;

	// ��ȡ����
	public void setConection() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, DBUSER, DBPWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * �����ݿ�����
	 */
	public Connection openConnection() {
		try {
			Class.forName(DRIVER);
			Connection con=DriverManager.getConnection(URL,DBUSER,DBPWD);		
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

	// ��ɾ��
	public void excuteUpdate(String sql, Object... objects) throws SQLException {
		setConection();
		pstmt = conn.prepareStatement(sql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				pstmt.setObject(i + 1, objects[i]);
			}
		}
		pstmt.executeUpdate();
	}

	// ��ѯ
	public ResultSet excuteQuery(String sql, Object... objects) throws SQLException {
		System.out.println(sql);
		setConection();
		pstmt = conn.prepareStatement(sql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				pstmt.setObject(i + 1, objects[i]);
			}
		}
		this.rs = pstmt.executeQuery();
		return this.rs;
	}

	// �ͷ���Դ
	public void reRes(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
	}

	// ��ɾ�ķ��������� ��Object���͵ķ�Χ����String���� �˴�ֻ��String��ֵ ����Object��
	/*
	 * 
	 * public static void main(String[] args) { DBManager db=new DBManager();
	 * db.setConection(); String price="14"; String id="111";
	 * 
	 * String []st={price,id}; String
	 * sql="delete med_Info where med_price=? and med_id=?"; try {
	 * db.pstmt=db.conn.prepareStatement(sql);
	 * 
	 * for (int i = 0; i < st.length; i++) { db.pstmt.setString(i+1, st[i]); }
	 * db.pstmt.setString(1,st[0]); db.pstmt.setString(2,st[1]);
	 * db.pstmt.executeUpdate(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

}
