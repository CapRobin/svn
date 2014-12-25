package com.haohuotong.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haohuotong.R;
import com.haohuotong.entity.AuctionInfo;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：UserAuctionAdapter.java
 * @Describe：竞拍列表数据适配
 * @Author FaRong——yfr5734@gmail.com
 * @Date：2013-12-13 下午1:51:48
 * @Version v1.0
 */

public class UserAuctionAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<AuctionInfo> itemList = null;
	private Context mContext;

	public UserAuctionAdapter(Context context, List<AuctionInfo> item) {
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
			convertView = mInflater.inflate(R.layout.auction_list_item, null);
			head.auctionUser = (TextView) convertView.findViewById(R.id.auctionUser);
			head.auctionSize = (TextView) convertView.findViewById(R.id.auctionSize);
			head.carNumber = (TextView) convertView.findViewById(R.id.carNumber);
			head.auctionTime = (TextView) convertView.findViewById(R.id.auctionTime);
			convertView.setTag(head);
		} else {
			head = (HeadView) convertView.getTag();
		}

		String actionUserStr = itemList.get(position).getRoutePath()+"";
		String actionSizeStr = itemList.get(position).getMoney_value();
		String carNumberStr = itemList.get(position).getCar_type()+"";
		String actionTimeStr = itemList.get(position).getJP_time();

//		head.auctionUser.setText(actionUserStr);
//		head.auctionSize.setText(actionSizeStr);
//		head.carNumber.setText(carNumberStr);
//		head.auctionTime.setText(actionTimeStr);
		
		head.auctionUser.setText("路线："+actionUserStr.subSequence(0, actionUserStr.indexOf("|")));
		head.auctionSize.setText("出价："+actionSizeStr+"元");
		head.carNumber.setText("车型："+carNumberStr);
		head.auctionTime.setText(actionTimeStr);

		return convertView;
	}

	/**
	 * 描述：声明成员
	 * 
	 * @author Yu Farong - yfr5734@gmail.com
	 * @date：2013-5-9 下午2:37:28
	 * @version v1.0
	 */
	public class HeadView {
		public TextView auctionUser;
		public TextView auctionSize;
		public TextView carNumber;
		public TextView auctionTime;
	}
}
