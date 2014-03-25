package com.cai.reader.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cai.reader.MainRssReaderActivity;
import com.cai.reader.ShowDescriptionActivity;
import com.cai.reader.R;
import com.cai.reader.entity.RssAddressInfo;
import com.cai.reader.provider.ChooseRssOpenHelper;
import com.cai.reader.util.ChooseRssData;
import com.cai.reader.util.RssFeed;

public class ChooseRssAdapter extends BaseAdapter  {
		private LayoutInflater mInflator;
		private List<RssAddressInfo> mList;
		private Context context;
	
		public ChooseRssAdapter(Context context,List<RssAddressInfo> mList) {
			this.mInflator = LayoutInflater.from(context);
			this.context=context;
			setRssList(mList);
					
		}
		private void setRssList(List<RssAddressInfo> mList){
			this.mList=mList==null?new ArrayList<RssAddressInfo>():mList;
			
		}
		public void chanageDataSet(List<RssAddressInfo> mList){
			setRssList(mList);
			notifyDataSetChanged();
		}
		public int getCount() {
			return mList.size();
		}

		public Object getItem(int position) {
			return mList.get(position);
		}

		public long getItemId(int position) {
			return mList.get(position).id;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewRss vRss = null;
			if(convertView == null) {
				vRss = new ViewRss();
				convertView = mInflator.inflate(R.layout.main_choose_rss_item, null);
				vRss.tvRssTitle = (TextView)convertView.findViewById(R.id.tv_rss_title);
				vRss.tvRssAddress = (TextView)convertView.findViewById(R.id.tv_rss_address);
				vRss.btnchooseIn = (Button)convertView.findViewById(R.id.btn_rss_choose);
				vRss.btnDelete= (Button)convertView.findViewById(R.id.btn_rss_delete);
				convertView.setTag(vRss);
			} else {
				vRss = (ViewRss)convertView.getTag();
			}
			vRss.tvRssTitle.setText(mList.get(position).title);
			vRss.tvRssAddress.setText(mList.get(position).address);
			vRss.btnchooseIn.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
			
					Intent itemintent = new Intent(context, MainRssReaderActivity.class);
				    itemintent.putExtra(MainRssReaderActivity.INTENT_EXTRA_RSS_NAME, mList.get(position).address);
				    itemintent.putExtra(MainRssReaderActivity.INTENT_EXTRA_RSS_TITLE, mList.get(position).title);
					context.startActivity(itemintent);
					
				}
				
			});
			vRss.btnDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Log.i("info", ChooseRssData.deleteChooseRssById(
							context,mList.remove(position).id)+"--------------i");
					;
					notifyDataSetChanged();
				}
			});
			
		
			return convertView;
		}
		 class ViewRss {
	    TextView tvRssTitle;
		TextView tvRssAddress;
		Button btnchooseIn;
		 Button btnDelete;
	}

	
	}
	