package com.xibeiwuliu.database;

import java.util.ArrayList;
import java.util.List;

import com.xibeiwuliu.entity.AreaInfo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * Copyright (c) 2012 All rights reserved 名称：SqliteDaoArea.java 描述：查看地区数据
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date：2014年1月17日 上午7:14:33
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

	/**
	 * 
	 * 描述：测试地区数据库
	 * 
	 * @param docId
	 * @return
	 * @throws
	 * @date：2014年1月17日 上午7:27:35
	 * @version v1.0
	 */
	public synchronized List<AreaInfo> getAreaInfo(int ParentID) {
//		List<String> list = new ArrayList<String>();
		List<AreaInfo> areaList = new ArrayList<AreaInfo>();
		AreaInfo areaInfo =null;
//		String sql_1 = "SELECT * FROM LocationTable WHERE id = " + docId + "";
		String sql_1 = "SELECT * FROM LocationTable WHERE ParentID = " + ParentID + "";
		Cursor cursor = null;
		try {
			db = openHelper.getReadableDatabase();
			cursor = db.rawQuery(sql_1, null);
			if (cursor != null) {
				while (cursor.moveToNext()) {
					areaInfo = new AreaInfo();
					int cityId = Integer.valueOf(cursor.getString(cursor.getColumnIndex("ID")));
					String cityName = cursor.getString(cursor.getColumnIndex("Location"));
					int parentID = Integer.valueOf(cursor.getString(cursor.getColumnIndex("ParentID")));
					areaInfo.setCcityId(cityId);
					areaInfo.setCcityName(cityName);
					areaInfo.setParentId(parentID);
					areaList.add(areaInfo);
//					list.add(string);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB(cursor, db);
		}
		return areaList;
	}

}
