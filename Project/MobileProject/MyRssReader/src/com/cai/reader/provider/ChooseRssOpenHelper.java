package com.cai.reader.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ChooseRssOpenHelper extends SQLiteOpenHelper {
	public static final String AUTHORITY="com.cai.rss.activity";
	public static final String MUL_CHOOSE_PATH="choose_rss";
	public static final String SINGLE_CHOOSE_PATH="choose_rss/#";

	private static final int MUL_CHOOSE_RSS = 1;
	public static  final  Uri CHOOSE_RSS_URI = Uri.parse("content://"+AUTHORITY+"/"+MUL_CHOOSE_PATH);
	//database名
	private static final String DATABASE="rss.db";
	//表名
	public static final String RSS_TABLE="choose_rss_tab";
    //列名
	public static final String _ID="_id";
	public static final String RSS_ADDRESS="rss_name";
	public static final String RSS_TITLE="rss_title";
	
	
	public ChooseRssOpenHelper(Context context) {
		super(context, DATABASE,null, MUL_CHOOSE_RSS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
 String sql="CREATE TABLE IF NOT EXISTS " + RSS_TABLE + "("
			+_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ RSS_TITLE + " TEXT, "
			+ RSS_ADDRESS + " TEXT);";
 db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
