package com.net56888.logistics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.net56888.logistics.data.InfoEntity;
import com.net56888.logistics.data.UserEntity;

/**
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：BrowserActivity.java
 * @Describe：首页数据
 * @Author: yfr5734@gmail.com
 * @Date：2014年12月22日 下午1:46:16
 * @Version v1.0
 */

public class BrowserActivity extends BaseActivity {
	private ListView list = null;
	private BrowserAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_browser_tab_x);
		intView();
	}

	// 初始化View
	private void intView() {
		Button lbtn = (Button) findViewById(R.id.left_title_btn);
		list = (ListView) findViewById(R.id.list);
		Button rbtn = (Button) findViewById(R.id.right_title_btn);
		TextView tv = (TextView) findViewById(R.id.title);

		lbtn.setText(R.string.btn_refresh);
		rbtn.setText(R.string.btn_cat);
		tv.setText(R.string.title_browser);

		lbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				refreshData();
			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				InfoEntity ieEntity = (InfoEntity) adapter.getItem(position);
				Intent i = new Intent(BrowserActivity.this, Detail.class);
				i.putExtra("DATA", ieEntity);
				startActivity(i);
			}
		});

		rbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String Cargo = BrowserActivity.this.getString(R.string.info_filter_opt_cargo);
				final String Truck = BrowserActivity.this.getString(R.string.info_filter_opt_truck);
				final CharSequence[] items = { Cargo, Truck };

				AlertDialog.Builder builder = new AlertDialog.Builder(BrowserActivity.this);
				builder.setTitle(R.string.info_filter_title);
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						Toast.makeText(BrowserActivity.this, items[item], Toast.LENGTH_SHORT).show();

						if (item == 0)
							Config.getInstance().setType(InfoEntity.Type.CargoInfo);
						if (item == 1)
							Config.getInstance().setType(InfoEntity.Type.TruckInfo);
						refreshData();

						dialog.dismiss();
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}

		});

		// 初始化数据
		refreshData();
	}

	/**
	 * @Describe：刷新数据
	 * @Throws:
	 * @Date：2014年12月22日 下午1:45:54
	 * @Version v1.0
	 */
	public void refreshData() {
		UserEntity user = Config.getInstance().getUser();
		String city = Config.getInstance().getCity();
		if (null != user)
			city = user.getMsg_City();
		loadData(city, Config.getInstance().getType());
	}

	/**
	 * @Describe：加载数据
	 * @param city
	 * @param type
	 * @Throws:
	 * @Date：2014年12月22日 下午1:46:20
	 * @Version v1.0
	 */
	public void loadData(String city, InfoEntity.Type type) {
		adapter = new BrowserAdapter(BrowserActivity.this, city, "", type);

		list.setAdapter(adapter);
	}
}
