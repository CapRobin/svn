package com.haohuotong.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * @Name：SqliteDao.java 
 * @Describe：数据库类
 * @Author yufarong_yfr5734@163.com
 * @Date：2014-1-16 下午1:15:51
 * @Version v1.0
 */
public class SqliteDao {

	private SqliteDaoHelper doctorDaoDBHelper;
	private SQLiteDatabase db;
	public static SqliteDao doctorDao = null;

	public static SqliteDao getInstance(Context context) {
		if (doctorDao == null) {
			doctorDao = new SqliteDao(context);
		}
		return doctorDao;
	}

	public SqliteDao(Context context) {
		this.doctorDaoDBHelper = new SqliteDaoHelper(context);
	}

	/**
	 * 得到列值
	 * 
	 * @param columnName
	 * @param cursor
	 */
	public String getStringColumnValue(String columnName, Cursor cursor) {
		return cursor.getString(cursor.getColumnIndex(columnName));
	}

	/**
	 * 得到列值
	 * 
	 * @param columnName
	 * @param cursor
	 */
	public int getIntColumnValue(String columnName, Cursor cursor) {
		return cursor.getInt(cursor.getColumnIndex(columnName));
	}

	/**
	 * 描述：关闭相关数据库等
	 * 
	 * @param cursor
	 * @param db
	 * @throws
	 * @date：2013-5-16 下午5:32:47
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
}