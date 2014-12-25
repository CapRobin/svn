package com.haohuotong.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * Copyright (c) 2012 All rights reserved ���ƣ�SqliteDaoArea.java �������鿴��������
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date��2014��1��17�� ����7:14:33
 * @version v1.0
 */
public class SqliteDaoArea {

	private SqliteDaoHelperArea openHelper;
	private SQLiteDatabase db;
	public static SqliteDaoArea doctorDao = null;

	public static SqliteDaoArea getInstance(Context context) {
		if (doctorDao == null) {
			doctorDao = new SqliteDaoArea(context);
		}
		return doctorDao;
	}

	public SqliteDaoArea(Context context) {
		this.openHelper = new SqliteDaoHelperArea(context);
	}

	/**
	 * �õ���ֵ
	 * 
	 * @param columnName
	 * @param cursor
	 */
	public String getStringColumnValue(String columnName, Cursor cursor) {
		return cursor.getString(cursor.getColumnIndex(columnName));
	}

	/**
	 * �õ���ֵ
	 * 
	 * @param columnName
	 * @param cursor
	 */
	public int getIntColumnValue(String columnName, Cursor cursor) {
		return cursor.getInt(cursor.getColumnIndex(columnName));
	}

	/**
	 * �������ر�������ݿ��
	 * 
	 * @param cursor
	 * @param db
	 * @throws
	 * @date��2013-5-16 ����5:32:47
	 * @version v1.0
	 */
	public void closeDB(Cursor cursor, SQLiteDatabase db) {
		if (cursor != null) {
			cursor.close();
			cursor = null;
		}
		if (db != null && db.isOpen()) {
			db.close();
			db = null;
		}
	}

	/**
	 * 
	 * ���������Ե������ݿ�
	 * 
	 * @param docId
	 * @return
	 * @throws
	 * @date��2014��1��17�� ����7:27:35
	 * @version v1.0
	 */
	public synchronized List<String> getAreaInfo(int docId) {
		List<String> list = new ArrayList<String>();
		String sql_1 = "SELECT * FROM LocationTable WHERE id = " + docId + "";
		Cursor cursor = null;
		try {
			db = openHelper.getReadableDatabase();
			cursor = db.rawQuery(sql_1, null);
			if (cursor != null) {
				while (cursor.moveToNext()) {

					String string = cursor.getString(cursor.getColumnIndex("Location"));
					list.add(string);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB(cursor, db);
		}
		return list;
	}

}
