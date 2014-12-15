package com.net56888.logistics;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.net56888.logistics.downloads.Downloads;


public class DownloadUtil {
	private static final String LOG_TAG = "DownloadUtil";

    private static final int COLUMN_GUID = 0;
    private static final int COLUMN_DATA = 1;
    private static final int COLUMN_TITLE = 2;
    private static final int COLUMN_DESC = 3;
    private static final int COLUMN_CURRENT_BYTES = 4;
    private static final int COLUMN_TOTAL_BYTES = 5;
    private static final int COLUMN_STATUS = 6;

	private static final String[] DOWNLOADS_PROJECTION = new String[] {
			Downloads.COLUMN_APP_GUID,
			Downloads._DATA,
			Downloads.COLUMN_TITLE,
			Downloads.COLUMN_DESCRIPTION,
			Downloads.COLUMN_CURRENT_BYTES,
			Downloads.COLUMN_TOTAL_BYTES,
			Downloads.COLUMN_STATUS,
	};
	
	static class DownloadInfo {
		public String mGuid;
		public String mFileName;
		public String mTitle;
		public String mDesc;
		public int mCurrentBytes;
		public int mTotalBytes;
		public int mStatus;
	    
		DownloadInfo(String guid, String fileName, String title, String desc,
				int currentBytes, int totalBytes, int status) {
			mGuid = guid;
			mFileName = fileName;
			mTitle = title;
			mDesc = desc;
			mCurrentBytes = currentBytes;
			mTotalBytes = totalBytes;
			mStatus = status;
	    }
	}

    public static DownloadInfo getDownloadInfo(Context context, Uri uri) {
		Cursor c = context.getContentResolver().query(uri,
				DOWNLOADS_PROJECTION, null, null, null);
		
        if (c != null) {
	        try {
		        c.moveToFirst();
		        String guid = c.getString(COLUMN_GUID);
		        String fileName = c.getString(COLUMN_DATA);
		        String title = c.getString(COLUMN_TITLE);
		        String desc = c.getString(COLUMN_DESC);
	            int currentBytes = c.getInt(COLUMN_CURRENT_BYTES);
	            int totalBytes = c.getInt(COLUMN_TOTAL_BYTES);
		        int status = c.getInt(COLUMN_STATUS);
		        return new DownloadInfo(guid, fileName, title, desc,
						currentBytes, totalBytes, status);
	        } catch (Exception e) {
	        	
	        } finally {
	            if (c != null) {
	                c.close();
	            }
	        }
        }
        return null;
    }
    
    
    public static Uri enqueueDownload(Context context, String url) {
    	Log.d(LOG_TAG, "enqueueDownload:" + url);
		ContentValues values = new ContentValues();
		//values.put(Downloads.COLUMN_APP_GUID, guid);
		values.put(Downloads.COLUMN_URI, url);
		values.put(Downloads.COLUMN_VISIBILITY, Downloads.VISIBILITY_VISIBLE);
		values.put(Downloads.COLUMN_NOTIFICATION_PACKAGE, context.getPackageName());
		values.put(Downloads.COLUMN_NOTIFICATION_CLASS,
				"com.net56888.logistics.DownloadManagerReceiver");
		values.put(Downloads.COLUMN_TITLE, context.getPackageName());

		Uri uri = context.getContentResolver().insert(Downloads.CONTENT_URI, values);
		
		return uri;
    }
/*
    public static void removeDownload(Context context, Uri uri, String guid) {
    	if ((uri != null) && (uri.toString() != null)) {
    		if (!uri.equals(Uri.EMPTY)) {
    			context.getContentResolver().delete(uri, null, null);
    			if (guid != null) {
    				LocalAppDBUtil.updateDatabaseOnDownloadCancel(guid);
    			}
    		}
    	}
    }
    */
    
    /*
     * Helper function to build the downloading text.
     */
    public static String getDownloadingText(long totalBytes, long currentBytes) {
    	long progress = 0;
    	if (totalBytes > 0) {
    		progress = currentBytes * 100 / totalBytes;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(progress);
        sb.append('%');
        return sb.toString();
    }
    
    public static int getErrorText(int status) {
        switch (status) {
            case Downloads.Impl.STATUS_INSUFFICIENT_SPACE_ERROR:
                return R.string.download_no_space;
                
            default:
                return R.string.notification_download_failed;
        }
    }
}