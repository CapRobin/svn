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
		RssAddressInfo info=new RssAddressInfo("��������", 
				"http://news.163.com/special/00011K6L/rss_newstop.xml");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("���������ǵ���", 
				"http://feed.feedsky.com/alibuybuy");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("΢��ѧ��Ʒ�Ķ�", 
				"http://www.3wbx.com/rss/rssdo.asp?sign=4");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("�ҵ�QQ�ռ�", 
				"http://feeds.qzone.qq.com/cgi-bin/cgi_rss_out?uin=714632274");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("��ҵ�����", 
				"http://www.911usa.com.cn/news/feed.php?go=category_17");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("���п�", 
				"http://m.metroer.com/feed.php");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("��������Ц������", 
				"http://www.laifu.org/rssfeed_wangwen.xml");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("��Ѷ����", 
				"http://astro.lady.qq.com/12star/rss_12star.xml");
		rssList.add(info);
		addRssAddress(context, info);
		
		info=new RssAddressInfo("��Ѷ�������", 
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
