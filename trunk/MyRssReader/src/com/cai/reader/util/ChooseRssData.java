package com.cai.reader.util;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.cai.reader.entity.RssAddressInfo;
import com.cai.reader.provider.ChooseRssOpenHelper;

public class ChooseRssData {
	public static List<RssAddressInfo> initChooseRssData(Context context){
		
		List<RssAddressInfo> rssList=new ArrayList<RssAddressInfo>();
		context.getContentResolver().delete(ChooseRssOpenHelper.CHOOSE_RSS_URI, null, null);
		RssAddressInfo info=new RssAddressInfo("网易新闻", 
				"http://news.163.com/special/00011K6L/rss_newstop.xml");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("互联网的那点事", 
				"http://feed.feedsky.com/alibuybuy");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("微文学精品阅读", 
				"http://www.3wbx.com/rss/rssdo.asp?sign=4");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("我的QQ空间", 
				"http://feeds.qzone.qq.com/cgi-bin/cgi_rss_out?uin=714632274");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("创业与感悟", 
				"http://www.911usa.com.cn/news/feed.php?go=category_17");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("都市客", 
				"http://m.metroer.com/feed.php");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("来福岛爆笑娱乐网", 
				"http://www.laifu.org/rssfeed_wangwen.xml");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("腾讯星座", 
				"http://astro.lady.qq.com/12star/rss_12star.xml");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("腾讯心理测试", 
				"http://astro.lady.qq.com/psy/rss_psy.xml");
		rssList.add(info);
		addRssAddress(context, info);
		
		
		return rssList;
	}

	
public static List<RssAddressInfo> getChooseRssListData(Context context){
	List<RssAddressInfo> rssList=new ArrayList<RssAddressInfo>();
	Cursor cursor=context.getContentResolver().query(ChooseRssOpenHelper.CHOOSE_RSS_URI,null,null,null,null);
	if(cursor!=null&&cursor.getCount()>0){
		while(cursor.moveToNext()){
			RssAddressInfo info=new RssAddressInfo();
			info.id=cursor.getInt(cursor.getColumnIndex(ChooseRssOpenHelper._ID));
			info.title=cursor.getString(cursor.getColumnIndex(ChooseRssOpenHelper.RSS_TITLE));
			info.address=cursor.getString(cursor.getColumnIndex(ChooseRssOpenHelper.RSS_ADDRESS));
			rssList.add(info);
		}
		
			cursor.close();
	}

	return rssList;
}

public static Uri addRssAddress(Context context,RssAddressInfo info){
	ContentValues values=new ContentValues();
	values.clear();
	values.put(ChooseRssOpenHelper.RSS_TITLE, info.title);
	values.put(ChooseRssOpenHelper.RSS_ADDRESS, info.address);
	
	Uri uri= context.getContentResolver().insert(ChooseRssOpenHelper.CHOOSE_RSS_URI, values);
	return uri;
}
public static int deleteChooseRssById(Context context,int id){
 return	context.getContentResolver().delete(ChooseRssOpenHelper.CHOOSE_RSS_URI,ChooseRssOpenHelper._ID+" = "+id,null);
	
}
}
