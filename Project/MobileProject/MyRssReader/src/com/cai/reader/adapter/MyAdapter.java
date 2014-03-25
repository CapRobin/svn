package com.cai.reader.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cai.reader.ShowDescriptionActivity;
import com.cai.reader.R;
import com.cai.reader.util.RssFeed;

public class MyAdapter extends BaseAdapter  {
		private LayoutInflater mInflator;
		private List<Map<String, Object>> mList;
		private Context context;
		private RssFeed feed;
		public MyAdapter(Context context,List<Map<String, Object>> mList,RssFeed feed) {
			this.mInflator = LayoutInflater.from(context);
			this.context=context;
			setMList(mList);
					this.feed=feed;
		}
		private void setMList(List<Map<String, Object>> mList){
			this.mList=mList==null?new ArrayList<Map<String,Object>>():mList;
			
		}
		public void chanageDataSet(List<Map<String, Object>> mList){
			setMList(mList);
			notifyDataSetChanged();
		}
		public int getCount() {
			return mList.size();
		}

		public Object getItem(int position) {
			return mList.get(position);
		}

		public long getItemId(int position) {
			return mList.get(position).hashCode();
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewRss vRss = null;
			final int row = position;
			if(convertView == null) {
				vRss = new ViewRss();
				convertView = mInflator.inflate(R.layout.main2_item, null);
				
				vRss.title = (TextView)convertView.findViewById(R.id.title);
				
				vRss.pubdate = (TextView)convertView.findViewById(R.id.pubdate);				
				vRss.delBtn = (Button)convertView.findViewById(R.id.del_btn);
				
				convertView.setTag(vRss);
			} else {
				vRss = (ViewRss)convertView.getTag();
			}
			String pubDate =  (String) mList.get(position).get("pubDate"); 
			String title = (String)mList.get(position).get("title");
			vRss.title.setText(title);
			vRss.pubdate.setText(pubDate);					
				
			vRss.delBtn.setOnClickListener(new OnClickListener() {				
				public void onClick(View v) {
					delRssInfo();					
				}

				private void delRssInfo() {
					//
					mList.remove(row);
					notifyDataSetChanged();					
				}

			});
			
			vRss.title.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
				if(feed!=null){
					Intent itemintent = new Intent(context, ShowDescriptionActivity.class);
					Bundle b = new Bundle();
					b.putString("title", feed.getItem(row).getTitle());
					b.putString("description", feed.getItem(row).getDescription());
					b.putString("link", feed.getItem(row).getLink());
					b.putString("pubdate", feed.getItem(row).getPubDate());
					
					itemintent.putExtra("com.rss.data.RssFeed", b);
					context.startActivity(itemintent);
					
				}
				}
			});
			return convertView;
		}
		 class ViewRss {
		TextView title;
		TextView pubdate;
		 Button delBtn;
	}
	
	
	}
	