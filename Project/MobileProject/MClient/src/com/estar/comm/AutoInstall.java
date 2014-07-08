package com.estar.comm;

import java.io.File;  

import android.content.Context;  
import android.content.Intent;  
import android.net.Uri; 

public class AutoInstall {
	
	private static String mUrl;  
    private static Context mContext;  
  
    /** 
     * 外部传进来的url以便定位需要安装的APK 
     *  
     * @param url 
     */  
    public static void setUrl(String url) {  
        mUrl = url;  
    }  
    /** 
     * 安装 
     *  
     * @param context 
     *            接收外部传进来的context 
     */  
    public static void install(Context context) {  
        mContext = context;  
        // 核心是下面几句代码   
        Intent intent = new Intent(Intent.ACTION_VIEW);  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        intent.setAction(android.content.Intent.ACTION_VIEW);  
        intent.setDataAndType(Uri.fromFile(new File(mUrl)),  
                "application/vnd.android.package-archive");  
        mContext.startActivity(intent);  
    }  

}
