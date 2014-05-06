package com.xibeiwuliu.adapter;

import java.util.List;

import com.xibeiwuliu.activity.R;
import com.xibeiwuliu.entity.AreaInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：UserAuctionAdapter.java
 * @Describe：区域显示适配器
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月6日 下午2:07:59
 * @Version v1.0 *
 * 
 */
public class MyLocationAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<AreaInfo> itemList = null;
	private Context mContext;

	public MyLocationAdapter(Context context, List<AreaInfo> item) {
		this.mContext = context;
		itemList = item;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final HeadView head;
		if (convertView == null) {
			head = new HeadView();
			convertView = mInflater.inflate(R.layout.view_item, null);
			head.layout = (RelativeLayout) convertView.findViewById(R.id.layout);
			head.itemText = (TextView) convertView.findViewById(R.id.itemText);
			convertView.setTag(head);
		} else {
			head = (HeadView) convertView.getTag();
		}

		String itemTextStr = itemList.get(position).getCcityName();
		head.itemText.setText(itemTextStr);
		
//		head.itemText.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				head.layout.setBackgroundResource(R.drawable.view_item_bg);
//			}
//		});
		head.layout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				head.layout.setBackgroundResource(R.drawable.view_item_bg);
				return false;
			}
		});
		return convertView;
	}

	/**
	 * 描述：成员声明
	 * 
	 * @author Yu Farong - yfr5734@gmail.com
	 * @date：2013-5-9 下午2:37:28
	 * @version v1.0
	 */
	public class HeadView {
		public TextView itemText;
		public RelativeLayout layout;
	}
}
