package com.cai.reader.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class ChooseRssProvider extends ContentProvider {
	
	private ChooseRssOpenHelper helper;
	
   static{
       UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
       uriMatcher.addURI(ChooseRssOpenHelper.AUTHORITY,ChooseRssOpenHelper.MUL_CHOOSE_PATH, 1);
       uriMatcher.addURI(ChooseRssOpenHelper.AUTHORITY,ChooseRssOpenHelper.SINGLE_CHOOSE_PATH, 2);
   
   }
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Log.i("info", "delete");
		int count = db.delete(ChooseRssOpenHelper.RSS_TABLE,selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		Log.i("info", "count:"+count);
		db.close();
		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db=	helper.getWritableDatabase();
		Uri nuri = null;
		long rowId = db.insert(ChooseRssOpenHelper.RSS_TABLE, null, values);
		//·µ»ØµÄrowId > 0
		if(rowId > 0) {
			nuri = ContentUris.withAppendedId(ChooseRssOpenHelper.CHOOSE_RSS_URI, rowId);
			getContext().getContentResolver().notifyChange(nuri, null);
		}
	
		return nuri;
		
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		helper=new ChooseRssOpenHelper(getContext());
    if(helper==null)		
		return false;
    else{
		return true;
	}
    }

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
	SQLiteDatabase db=	helper.getReadableDatabase();
	Cursor c = db.query(ChooseRssOpenHelper.RSS_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
   c.setNotificationUri(getContext().getContentResolver(), uri);
	
	return c;
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
