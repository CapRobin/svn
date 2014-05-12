package com.xibeiwuliu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SqliteDaoHelperArea.java
 * @Describe：数据库辅助类
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 上午9:40:09
 * @Version v1.0 *
 * 
 */
public class SqliteDaoHelperArea extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "logistics";
	private static final int DATABASE_VERSION = 1;
	private Context mContext;

	public SqliteDaoHelperArea(Context context) {
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
