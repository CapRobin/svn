package com.xibeiwuliu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * Copyright (c) 2012 All rights reserved 名称：SqliteDaoHelperArea.java
 * 描述：地区数据库管理类
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date：2014年1月17日 上午7:29:10
 * @version v1.0
 */
public class SqliteDaoHelperArea extends SQLiteOpenHelper {
	// 数据库名称
	private static final String DATABASE_NAME = "logistics";
	// 数据库版本
	private static final int DATABASE_VERSION = 1;
	private Context mContext;

	public SqliteDaoHelperArea(Context context) {
		// 调用父类的构造器
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}
}
