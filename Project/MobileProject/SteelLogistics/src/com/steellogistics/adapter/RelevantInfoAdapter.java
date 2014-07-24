package com.steellogistics.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xibeiwuliu.activity.R;
import com.xibeiwuliu.entity.RelevantInfo;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：RelevantInfoAdapter.java
 * @Describe：行业资讯数据适配
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月8日 下午3:39:17
 * @Version v1.0 *
 * 
 */
public class RelevantInfoAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<RelevantInfo> mData;

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param data
	 */
	public RelevantInfoAdapter(Context context, List<RelevantInfo> data) {
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
			convertView = mInflater.inflate(R.layout.relevant_info_item, parent, false);
			// 减少findView的次数
			holder = new ViewHolder();
			// 初始化布局中的元素
			holder.titleName = ((TextView) convertView.findViewById(R.id.msg_title));
			holder.context = ((TextView) convertView.findViewById(R.id.msg_content));
			holder.time = ((TextView) convertView.findViewById(R.id.msg_time));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String getTitleName = mData.get(position).getTitleName();
		String getContext = mData.get(position).getContent();
		String getAddTime = mData.get(position).getTime();
		String getImageUrl = mData.get(position).getImageUrl();

		holder.titleName.setText(getTitleName);
		holder.context.setText(getContext);
		holder.time.setText(getAddTime);

		return convertView;
	}

	/**
	 * View元素
	 */
	static class ViewHolder {
		TextView titleName;
		TextView context;
		TextView time;
		ImageView imageview;
	}

}
