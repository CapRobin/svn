package com.steellogistics.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.steellogistics.R;
import com.steellogistics.entity.SupplyInfo;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SupplyInfoAdapter.java
 * @Describe：钢材供应数据适配
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月25日 上午10:28:58
 * @Version v1.0
 */
public class SupplyInfoAdapter extends BaseAdapter {

	private static String TAG = "ImageListAdapter";
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private Context mContext;
	private LayoutInflater mInflater;
	private List<SupplyInfo> mData;

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param data
	 */
	public SupplyInfoAdapter(Context context, List<SupplyInfo> data) {
		this.mContext = context;
		this.mData = data;
		this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			// 使用自定义的list_items作为Layout
			convertView = mInflater.inflate(R.layout.supply_info_item, parent, false);
			// 减少findView的次数
			holder = new ViewHolder();
			// 初始化布局中的元素
			holder.titleName = ((TextView) convertView.findViewById(R.id.titleName));
			holder.sellScope = ((TextView) convertView.findViewById(R.id.sellScope));
			holder.price = ((TextView) convertView.findViewById(R.id.price));
			holder.address = ((TextView) convertView.findViewById(R.id.address));
			holder.creatTime = ((TextView) convertView.findViewById(R.id.creatTime));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.titleName.setText(mData.get(position).getTitleName());
		holder.sellScope.setText("经营范围: " + mData.get(position).getSellScope());
		holder.price.setText("商品单价: ¥" + mData.get(position).getPrice());
		holder.address.setText(mData.get(position).getCompanyAddress());
		holder.creatTime.setText(mData.get(position).getCreatTime());

		return convertView;
	}

	/**
	 * View元素
	 */
	static class ViewHolder {
		// TextView msg_content;
		// TextView titleName;
		// TextView msg_date;

		TextView titleName;
		TextView sellScope;
		TextView price;
		TextView address;
		TextView creatTime;
	}

}
