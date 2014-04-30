package com.xibeiwuliu.adapter;

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

import com.xibeiwuliu.activity.R;
import com.xibeiwuliu.entity.ContactInfo;

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
public class ContactAdapter extends BaseAdapter {
	public List<Boolean> mChecked;
	private List<ContactInfo> listPerson;
	private HashMap<Integer, View> map = new HashMap<Integer, View>();
	private Context mContext = null;

	public ContactAdapter(Context context, List<ContactInfo> list) {
		listPerson = new ArrayList<ContactInfo>();
		listPerson = list;
		mContext = context;
		mChecked = new ArrayList<Boolean>();
		for (int i = 0; i < list.size(); i++) {
			mChecked.add(false);
		}
	}

	@Override
	public int getCount() {
		return listPerson.size();
	}

	@Override
	public Object getItem(int position) {
		return listPerson.get(position);
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
			Log.e("MainActivity", "position1 = " + position);

			LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = mInflater.inflate(R.layout.contact_item, null);
			holder = new ViewHolder();
			holder.box = (CheckBox) view.findViewById(R.id.box);
			holder.contactName = (TextView) view.findViewById(R.id.contactName);
			holder.contactPhone = (TextView) view.findViewById(R.id.contactPhone);
			final int p = position;
			map.put(position, view);
			holder.box.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					mChecked.set(p, cb.isChecked());
				}
			});
			view.setTag(holder);
		} else {
			Log.e("MainActivity", "position2 = " + position);
			view = map.get(position);
			holder = (ViewHolder) view.getTag();
		}

		holder.box.setChecked(mChecked.get(position));
		holder.contactName.setText(listPerson.get(position).getName());
		holder.contactPhone.setText(listPerson.get(position).getPhone());

		return view;
	}

	static class ViewHolder {
		public ImageView contactImage;
		public TextView contactName;
		public TextView contactPhone;
		public TextView contactother;
		public CheckBox box;
	}

}
