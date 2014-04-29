package com.xibeiwuliu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * Copyright (c) 2012 All rights reserved ���ƣ�SqliteDaoHelperArea.java
 * �������������ݿ������
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date��2014��1��17�� ����7:29:10
 * @version v1.0
 */
public class SqliteDaoHelperArea extends SQLiteOpenHelper {
	// ���ݿ�����
	private static final String DATABASE_NAME = "logistics";
	// ���ݿ�汾
	private static final int DATABASE_VERSION = 1;
	private Context mContext;

	public SqliteDaoHelperArea(Context context) {
		// ���ø���Ĺ�����
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
