package com.haohuotong.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.haohuotong.R;
import com.haohuotong.entity.AuctionInfo;
import com.haohuotong.entity.ContactInfo;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：ContactAdapter.java
 * @Describe：联系人数据适配
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-11-29 下午4:40:51
 * @Version v1.0
 */
public class AuctionInfoAdapter extends BaseAdapter {
//	public List<Boolean> mChecked;
	private List<AuctionInfo> auctionInfoList;
	private HashMap<Integer, View> map = new HashMap<Integer, View>();
	private Context mContext = null;

	public AuctionInfoAdapter(Context context, List<AuctionInfo> list) {
		auctionInfoList = list;
		mContext = context;
	}

	@Override
	public int getCount() {
		return auctionInfoList.size();
	}

	@Override
	public Object getItem(int position) {
		return auctionInfoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder = null;

		if (map.get(position) == null) {
			//getSystemService()是activity的一个方法。根据传入的参数可以返回相应的对象。
			LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = mInflater.inflate(R.layout.auc_adapter_item, null);
			holder = new ViewHolder();
			holder.auctionId = (TextView) view.findViewById(R.id.auctionId);
			holder.driverName = (TextView) view.findViewById(R.id.driverName);
			holder.auction_money = (TextView) view.findViewById(R.id.driver_money);
			holder.auction_time = (TextView) view.findViewById(R.id.auction_time);
			final int p = position;
			map.put(position, view);
			/*holder.box.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					mChecked.set(p, cb.isChecked());
				}
			});*/
			view.setTag(holder);
		} else {
			view = map.get(position);
			holder = (ViewHolder) view.getTag();
		}

		holder.auctionId.setText(auctionInfoList.get(position).getAdd_id()+"");
		String username = auctionInfoList.get(position).getDuser_name();
		holder.driverName.setText(username.substring(0, 8)+"****");
		holder.auction_money.setText(auctionInfoList.get(position).getMoney_value());
		holder.auction_time.setText(auctionInfoList.get(position).getJP_time());
		return view;
	}

	static class ViewHolder {
		public TextView auctionId;
		public TextView driverName;
		public TextView auction_money;
		public TextView auction_time;
	}

}
