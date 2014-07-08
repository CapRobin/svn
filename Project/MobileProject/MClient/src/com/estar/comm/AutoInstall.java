package com.estar.comm;

import java.io.File;  

import android.content.Context;  
import android.content.Intent;  
import android.net.Uri; 

public class AutoInstall {
	
	private static String mUrl;  
    private static Context mContext;  
  
    /** 
     * �ⲿ��������url�Ա㶨λ��Ҫ��װ��APK 
     *  
     * @param url 
     */  
    public static void setUrl(String url) {  
        mUrl = url;  
    }  
    /** 
     * ��װ 
     *  
     * @param context 
     *            �����ⲿ��������context 
     */  
    public static void install(Context context) {  
        mContext = context;  
        // ���������漸�����   
        Intent intent = new Intent(Intent.ACTION_VIEW);  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        intent.setAction(android.content.Intent.ACTION_VIEW);  
        intent.setDataAndType(Uri.fromFile(new File(mUrl)),  
                "application/vnd.android.package-archive");  
        mContext.startActivity(intent);  
    }  

}
