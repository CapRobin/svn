package com.cai.reader.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class RssProvider extends ContentProvider {
	private static final String RSS_DATABASE = "rss.db";
	private static final String RSS_TABLE = "rss_item";
	private static final int RSS_DATABASE_VERSION = 1;
	
	//URI
	public static final Uri RSS_URI = Uri.parse("content://com.rss.activity/rss");
	
	//鍒楀悕
	public static final String RSS_ID = "_id";
	public static final String RSS_TITLE = "title";
	public static final String RSS_DESCRIPTION = "description";
	public static final String RSS_LINK = "link";
	public static final String RSS_PUBDATE = "pubDate";
	
	//鍒楃殑绱㈠紩
	public static final int TITLE_INDEX = 1;
	public static final int DESCRIPTION_INDEX = 2;
	public static final int LINK_INDEX = 3;
	public static final int PUBDATE_INDEX = 4;
	
	//鍒涘缓琛ㄧ殑sql璇彞
	private static final String RSS_SQL = "CREATE TABLE " + RSS_TABLE + "("
				+ RSS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ RSS_TITLE + " TEXT, "
				+ RSS_DESCRIPTION + " TEXT, "
				+ RSS_LINK + " TEXT, "
				+ RSS_PUBDATE + " DATE);";
	
	SQLiteDatabase rssDb = null;
	
	//鍒涘缓鐢ㄦ潵鍖哄垎涓嶅悓URI鐨勫父閲�	
	private static final int RSS = 1;
	private static final int RSSID = 2;
	
	private static UriMatcher uriMatcher;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.rss.activity", "rss", RSS);
		uriMatcher.addURI("com.rss.activity", "rss/#", RSSID);
	}
	
	//鍒涘缓绠＄悊鏁版嵁鐨刪elper绫�	
	private static class RssDatabaseHelper extends SQLiteOpenHelper {
		
		public RssDatabaseHelper(Context context) {
			super(context, RSS_DATABASE, null, RSS_DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(RSS_SQL);	
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + RSS_TABLE);
			onCreate(db);//閲嶆柊鍒涘缓琛�		
		}
		
	}
	
	
	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case RSS:
			return "vnd.android.cursor.dir/com.rss.activity";
		case RSSID:
			return "vnd.android.cursor.item/com.rss.activity";
		default:
			throw new IllegalArgumentException("Unsupport URI:" + uri);
		}
	}

	@Override
	public Uri insert(Uri _uri, ContentValues values) {
		Uri uri = null;
		long rowId = rssDb.insert(RSS_TABLE, null, values);
		//杩斿洖鐨剅owId > 0
		if(rowId > 0) {
			uri = ContentUris.withAppendedId(RSS_URI, rowId);
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return uri;
		
	}

	@Override
	public boolean onCreate() {
		Context context = getContext();
		
		RssDatabaseHelper rssDbHelper = new RssDatabaseHelper(context);
		rssDb = rssDbHelper.getWritableDatabase();
		
		return (rssDb == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
		sqb.setTables(RSS_TABLE);//璁剧疆鏌ヨ鐨勮〃
		
		switch (uriMatcher.match(uri)) {
		case RSSID:
			sqb.appendWhere(RSS_ID + "=" + uri.getPathSegments().get(1));			
			break;

		default:
			break;
		}
		
		//
		String orderBy;
		
		if(TextUtils.isEmpty(sortOrder)) {
			orderBy = RSS_PUBDATE;
		}else {
			orderBy = sortOrder;
		}
		
		//瀵瑰簳灞傛暟鎹簱鐨勫簲鐢ㄦ煡璇�	
		Cursor c = sqb.query(rssDb, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count;
		switch (uriMatcher.match(uri)) {
		case RSS:
			count = rssDb.update(RSS_TABLE, values, selection, selectionArgs);
			break;
		case RSSID:
			String segment = uri.getPathSegments().get(1);
			count = rssDb.update(RSS_TABLE, values, RSS_ID + "=" + segment
					+ (!TextUtils.isEmpty(selection) ? " AND ("
					+ selection +')' : ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unkown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
