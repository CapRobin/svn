package com.xibeiwuliu.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xibeiwuliu.activity.R;
import com.xibeiwuliu.entity.CargoInfo;
//import com.andbase.global.Constant;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：CommonalityInfoAdapter.java
 * @Describe：公共信息数据适配
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-11-18 下午6:13:47
 * @Version v1.0
 */
public class CommonalityInfoAdapter extends BaseAdapter {

	private static String TAG = "ImageListAdapter";
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	 
//	private static final boolean D = Constant.DEBUG;

	private Context mContext;
	private LayoutInflater mInflater;
	private List<CargoInfo> mData;

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param data
	 */
	public CommonalityInfoAdapter(Context context, List<CargoInfo> data) {
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
			convertView = mInflater.inflate(R.layout.partial_row, parent, false);
			// 减少findView的次数
			holder = new ViewHolder();
			// 初始化布局中的元素
			holder.msg_content = ((TextView) convertView.findViewById(R.id.msg_content));
			holder.msg_date = ((TextView) convertView.findViewById(R.id.msg_date));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String addTime = mData.get(position).getInfo_AddTime();
		// 获取该行的数据
//		if (addTime == null) {
//			Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间     
//			addTime   =   formatter.format(curDate);    
//		}
		holder.msg_content.setText(mData.get(position).getInfo_connect());
		holder.msg_date.setText(addTime);

		return convertView;
	}

	/**
	 * View元素
	 */
	static class ViewHolder {
		TextView msg_content;
		TextView msg_date;
	}

}
