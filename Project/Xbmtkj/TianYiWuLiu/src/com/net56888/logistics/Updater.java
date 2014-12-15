package com.net56888.logistics;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.util.Log;

import com.net56888.logistics.data.Entity;
import com.net56888.logistics.downloads.Constants;

public class Updater implements DataSource.Callback {
    private final static String TAG = "Updater";
    private Context mContext;

    public Updater(Context context) {
        mContext = context;
    }

    public void check() {
        DataSource.getInstance().checkUpdateAsync(this);
    }

    private void doDownloadAndInstall(String url) {
        if (null == url)
            return;
        File pathFile = new File(Environment.getExternalStorageDirectory()
                + Constants.DEFAULT_DL_SUBDIR);
        if ((pathFile != null) && (!pathFile.exists())) {
            pathFile.mkdirs();
        }
        DownloadUtil.enqueueDownload(mContext, url);
        Log.i(TAG, "download url: " + url);
    }

    private void confirmForUpdate(final String url) {
        new AlertDialog.Builder(mContext)
                .setIcon(R.drawable.logo)
                .setTitle(R.string.update)
                .setMessage(R.string.update_message)
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub
                                return;
                            }
                        })
                .setPositiveButton(R.string.sure,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                doDownloadAndInstall(url);
                            }
                        }).show();
    }

    @Override
    public void onCheckUpdate(String result) {
        if (null == result)
            return;
        String[] updateInfo = result.split("#");
        if (updateInfo.length < 3)
            return;
        if (isGreaterVersion(updateInfo[1])) {
            final String url = updateInfo[2];
            if (true == (mContext instanceof Activity)) {
                Activity a = (Activity) mContext;
                a.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        confirmForUpdate(url);
                    }
                });
            }
        }
    }

    private boolean isGreaterVersion(String version) {
        String curVersion = "1.0";
        try {
            curVersion = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return compareVer(version, curVersion);
    }

    private boolean compareVer(String ver1, String ver2) {
        String[] vers1 = ver1.split("\\.");
        String[] vers2 = ver2.split("\\.");
        int major1 = Integer.parseInt(vers1[0]);
        int minor1 = Integer.parseInt(vers1[1]);
        int major2 = Integer.parseInt(vers2[0]);
        int minor2 = Integer.parseInt(vers2[1]);

        if (major1 > major2)
            return true;
        if (major1 == major2 && minor1 > minor2)
            return true;
        return false;
    }

    @Override
    public void onLogin(Entity entity) {
        // TODONOTHING

    }

    @Override
    public void onPublish(String result) {
        // TODONOTHING

    }

    @Override
    public void onCheckConnection(String result, int statusCode) {
        // TODONOTHING

    }

    @Override
    public void onRegisterUser(String result) {
        // TODONOTHING

    }

}
