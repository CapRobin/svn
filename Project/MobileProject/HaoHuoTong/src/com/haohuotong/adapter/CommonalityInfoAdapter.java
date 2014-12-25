package com.haohuotong.adapter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.andbase.global.Constant;
import com.haohuotong.R;
import com.haohuotong.entity.CargoInfo;
import com.haohuotong.other.EntitySet;
import com.haohuotong.other.InfoEntity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name��CommonalityInfoAdapter.java
 * @Describe��������Ϣ��������
 * @Author Administrator yfr5734@gmail.com
 * @Date��2013-11-18 ����6:13:47
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
	 * ���췽��
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
			// ʹ���Զ����list_items��ΪLayout
			convertView = mInflater.inflate(R.layout.partial_row, parent, false);
			// ����findView�Ĵ���
			holder = new ViewHolder();
			// ��ʼ�������е�Ԫ��
			holder.msg_content = ((TextView) convertView.findViewById(R.id.msg_content));
			holder.msg_date = ((TextView) convertView.findViewById(R.id.msg_date));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String addTime = mData.get(position).getInfo_AddTime();
		// ��ȡ���е�����
//		if (addTime == null) {
//			Date   curDate   =   new   Date(System.currentTimeMillis());//��ȡ��ǰʱ��     
//			addTime   =   formatter.format(curDate);    
//		}
		holder.msg_content.setText(mData.get(position).getInfo_connect());
		holder.msg_date.setText(addTime);

		return convertView;
	}

	/**
	 * ViewԪ��
	 */
	static class ViewHolder {
		TextView msg_content;
		TextView msg_date;
	}

}
