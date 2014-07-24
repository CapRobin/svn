package com.slidingmenu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 名称：MenuItemAdapter.java
 * 
 * 描述：菜单栏数据适配
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date：2013-8-21 上午11:16:20
 * @version v1.0
 */
public class MenuItemAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<String> itemList = null;
	private Context mContext;

	public MenuItemAdapter(Context context, List<String> item) {
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
			convertView = mInflater.inflate(R.layout.menu_layout_item, null);
			head.menuItemText = (TextView) convertView.findViewById(R.id.menuItemText);
			convertView.setTag(head);
		} else {
			head = (HeadView) convertView.getTag();
		}

		String itemNameStr = itemList.get(position);
		head.menuItemText.setText(itemNameStr);
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
		public TextView menuItemText;
	}
}
