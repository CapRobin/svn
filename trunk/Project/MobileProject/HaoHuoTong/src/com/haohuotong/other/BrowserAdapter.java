package com.haohuotong.other;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.haohuotong.R;
import com.haohuotong.adapter.EndlessAdapter;
import com.haohuotong.global.MyApplication;


public class BrowserAdapter extends EndlessAdapter {

	private EntitySet mEntitySet = null;
	private int mTotalNum = -1;
	private int mTotalPage = -1;
	private int mCurrPage = -1;
	private String mCity = "";
	private String mKeywords = "";
	private InfoEntity.Type mType = InfoEntity.Type.All;
	private MyApplication application = null;

	public BrowserAdapter(Context context, String city, String keywords, InfoEntity.Type type) {
		super(context, new EntityArrayAdapter(context, -1, new ArrayList<Entity>()));
		this.mCity = city;
		this.mKeywords = keywords;
		this.mType = type;
		this.mTotalNum = -1;
		this.mTotalPage = -1;
		this.mCurrPage = -1;
		application = (MyApplication) context.getApplicationContext();
	}

	@Override
	protected boolean cacheInBackground() throws Exception {
		DataSource ds = DataSource.getInstance();
		if (this.mEntitySet != null)
			this.mEntitySet.clear();
		if (this.getWrappedAdapter().getCount() == 0) {
			this.mCurrPage = 1;
			this.mEntitySet = ds.getPagedInfoListSync2(this.mCity, this.mKeywords, this.mType, this.mCurrPage);
			// this.mEntitySet = ds.getPagedInfoListSync2(this.mCity,
			// this.mKeywords, this.mType, this.mCurrPage);
			this.mTotalNum = this.mEntitySet.getTotalNum();
			this.mTotalPage = this.mEntitySet.getTotalPage();
		} else {
			this.mEntitySet = ds.getPagedInfoListSync2(this.mCity, this.mKeywords, this.mType, ++this.mCurrPage);
		}

		return (this.mEntitySet != null && this.mEntitySet.size() > 0);
	}

	@Override
	protected void appendCachedData() {
		if (this.mEntitySet != null && this.mEntitySet.size() > 0) {
			@SuppressWarnings("unchecked")
			ArrayAdapter<Entity> entities = (ArrayAdapter<Entity>) getWrappedAdapter();

			for (int i = 0; i < this.mEntitySet.size(); i++) {
				entities.add(this.mEntitySet.get(i));
			}
			application.entitySet = mEntitySet;
		}
	}

	@Override
	protected View getPendingView(ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.partial_pending, null);
		return (row);
	}

	public static class EntityArrayAdapter extends ArrayAdapter<Entity> {

		public EntityArrayAdapter(Context context, int textViewResourceId, List<Entity> objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.partial_row, null);
				holder = new ViewHolder();
				holder.msg_content = (TextView) convertView.findViewById(R.id.msg_content);
				holder.msg_date = (TextView) convertView.findViewById(R.id.msg_date);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			InfoEntity e = (InfoEntity) this.getItem(position);
			holder.msg_content.setText(e.getMsg_Content());
			holder.msg_date.setText(e.getMsg_Date());
			return convertView;
		}

		public class ViewHolder {
			public TextView msg_content;
			public TextView msg_date;
		}
	}

}
