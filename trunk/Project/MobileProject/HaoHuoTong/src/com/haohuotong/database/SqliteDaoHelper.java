package com.haohuotong.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * @Name：SqliteDaoHelper.java 
 * @Describe：数据库管理类
 * @Author yufarong_yfr5734@163.com
 * @Date：2014-1-16 下午1:16:16
 * @Version v1.0
 */
public class SqliteDaoHelper extends SQLiteOpenHelper {
    private static final String DBPATH = "/data/data/com.haohuotong/databases/";
	private static final String DBNAME = "HaoHuoTong.db";
	private static final int VERSION = 1;

	/**
	 * 构造器
	 * 
	 * @param context
	 */
	public SqliteDaoHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// 医生个人信息
		String userInfoSql = 
				"CREATE TABLE USERINFO (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
				"id text," +
				"name text," +
				"callName text," +
				"sex text," +
				"mobile text," +
				"workPhone text," +
				"photoUrl text," +
				"birthday text," +
				"serviceAddress text," +
				"createDatetime text," +
				"doctorLoginInfo text," +
				"professionalTitle text," +
				"passWord text," +
				"description text," +
				"modifyDatetime text," +
				"serviceFamilies text," +
				"visitDays text," +
				"forenoonWorkDays text," +
				"afternoonWorkDays text,specialty text," +
				"hospital text,serviceFeePerMonth text,modifyBy text,createBy text,institution text," +
				"signValidDateTo text,signValid text,profession text,manageArea text,visitCommunityPlan text,otherNotice text)";
		db.execSQL(userInfoSql);
		
		// 签约家庭咨询列表
		String familyTaskSql = "CREATE TABLE FAMILTASK (_ID INTEGER PRIMARY KEY AUTOINCREMENT,id text,doctorId text,taskType text,dataId text,description text,familyId  text,familyUrl text,familName text,warnFinishFlag text,freashTime text,aboutUserId text,aboutUserName text,aboutUserphotoUrl text)";
		db.execSQL(familyTaskSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS USERINFO");
		db.execSQL("DROP TABLE IF EXISTS FAMILTASK");
		onCreate(db);
	}
}