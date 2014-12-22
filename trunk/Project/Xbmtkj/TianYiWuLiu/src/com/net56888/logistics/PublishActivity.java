package com.net56888.logistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.net56888.logistics.data.InfoEntity;

public class PublishActivity extends Activity {
	public final static String PUBLISH_TYPE = "com.net56888.logistics.PublishTab.Publish_Type";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_publish_tab_x);
		intView();
	}

	// 初始化View
	private void intView() {
		Button lbtn = (Button) findViewById(R.id.left_title_btn);
		Button rbtn = (Button) findViewById(R.id.right_title_btn);
		TextView tv = (TextView) findViewById(R.id.title);
		Button cargoBtn = (Button) findViewById(R.id.publish_info_cargo_btn);
		Button truckBtn = (Button) findViewById(R.id.publish_info_truck_btn);
		lbtn.setVisibility(View.INVISIBLE);

		rbtn.setVisibility(View.GONE);
		tv.setText(R.string.title_publish);

		cargoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(PublishActivity.this, InfoPub.class);
				i.putExtra(PUBLISH_TYPE, InfoEntity.Type.toInteger(InfoEntity.Type.CargoInfo));
				startActivity(i);
			}
		});

		truckBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// 原始代码
				Intent i = new Intent(PublishActivity.this, InfoPub.class);
				i.putExtra(PUBLISH_TYPE, InfoEntity.Type.toInteger(InfoEntity.Type.TruckInfo));
				startActivity(i);

			}
		});
	}
}
