package com.net56888.logistics;


import java.io.File;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.net56888.logistics.DownloadUtil.DownloadInfo;
import com.net56888.logistics.downloads.Downloads;


public class DownloadManagerReceiver extends BroadcastReceiver {
	private static final String TAG = "DownloadManagerReceiver";
	
	protected static final String ACTION_DOWNLOAD_SUCCESS = "jru_logistics.intent.action.DOWNLOAD_SUCCESS";
	
    public void onReceive(Context context, Intent intent) {
     	if (intent.getAction().equals(Downloads.ACTION_DOWNLOAD_COMPLETED)) {
     		Uri uri = intent.getData();
     		handleDownloadCompletedAction(context, uri);
        } else if (intent.getAction().equals(Downloads.ACTION_NOTIFICATION_CLICKED)) {
			Uri uri = intent.getData();
			handleDownloadClickedAction(context, uri);
        }
    }
    
    private void handleDownloadCompletedAction(Context context, Uri uri) {
    	DownloadInfo info = DownloadUtil.getDownloadInfo(context, uri);
    	if (info == null) {
        	Log.d(TAG, "The download record is not exist");
        	return;
    	}

        String fileName = info.mFileName;
        int status = info.mStatus;

        if (Downloads.isStatusSuccess(status)) {
        	Log.d(TAG, "download success fileName = " + fileName);
        	broadcastDownloadSuccess(context);
        	Intent i = new Intent();
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setAction(Intent.ACTION_VIEW);
			i.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
			context.startActivity(i);
        } else {
            Toast.makeText(context,
            		DownloadUtil.getErrorText(status),
                    Toast.LENGTH_LONG).show();
        }
    }
    
    private void handleDownloadClickedAction(Context context, Uri uri) {
    	DownloadInfo info = DownloadUtil.getDownloadInfo(context, uri);
    	if (info == null) {
        	Log.d(TAG, "The download record is not exist");
        	return;
    	}
    	
        if (Downloads.isStatusCompleted(info.mStatus)){
        	Log.d(TAG, "The download has completed");
        	return;
        }
/*
        Intent i = new Intent(context, DownloadActivity.class);
        i.putExtra("guid", guid);
        i.setData(uri);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
*/
    }
    
	private void broadcastDownloadSuccess(Context context) {
		Intent intent = new Intent();
		intent.setAction(ACTION_DOWNLOAD_SUCCESS);
		context.sendBroadcast(intent);
	}
}
