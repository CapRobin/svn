package com.haohuotong.other;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.haohuotong.R;


public class Detail extends FragmentActivity {
	private String mTel = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_detail);

		View titleBar = this.findViewById(R.id.title_bar);

		Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
		lbtn.setText("返回");
		lbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Detail.this.finish();
			}

		});

		Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
		rbtn.setVisibility(View.INVISIBLE);

		TextView tv = (TextView) titleBar.findViewById(R.id.title);
		tv.setText("信息详情");

		Intent intent = getIntent();
		InfoEntity ie = (InfoEntity) intent.getParcelableExtra("DATA");

		tv = (TextView) this.findViewById(R.id.detail_content);
		tv.setText(ie.getMsg_Content());

		tv = (TextView) this.findViewById(R.id.detail_date);
		tv.setText(ie.getMsg_Date());

		mTel = ie.getMsg_Tel();

		Button contactBtn = (Button) this.findViewById(R.id.contact_btn);
		contactBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UserEntity user = Config.getInstance().getUser();
				if (null == user) {
					Toast.makeText(Detail.this, "请先登录", Toast.LENGTH_SHORT).show();
					return;
				}

				String[] numStrings = Detail.this.mTel.split("－");
				if (numStrings.length == 0)
					return;

				// FIXME: by now, two number will be show at most.
				ArrayList<String> numArr = new ArrayList<String>();
				if (numStrings[0].length() == 4) {
					if (numStrings.length > 1)
						numArr.add(numStrings[0] + "-" + numStrings[1]);
					if (numStrings.length > 2) {
						if (numStrings[2].length() < 10) {
							numArr.add(numStrings[0] + "-" + numStrings[2]);
						} else {
							numArr.add(numStrings[2]);
						}
					}

				} else if (numStrings[0].length() != 4) {
					numArr.add(numStrings[0]);
					if (numStrings.length > 1)
						numArr.add(numStrings[1]);
				} else {
					return;
				}

				final String[] items = numArr.toArray(new String[numArr.size()]);

				AlertDialog.Builder builder = new AlertDialog.Builder(Detail.this);
				builder.setTitle("拨打电话");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						Intent callIntent = new Intent(Intent.ACTION_DIAL);
						callIntent.setData(Uri.parse("tel:" + items[item]));
						startActivity(callIntent);
						dialog.dismiss();
					}
				});
				builder.create().show();
			}

		});
	}

}
