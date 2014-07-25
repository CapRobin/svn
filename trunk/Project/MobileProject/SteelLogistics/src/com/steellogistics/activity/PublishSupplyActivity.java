package com.steellogistics.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.steellogistics.R;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PublishSupplyActivity.java
 * @Describe：发布供货信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:08:10
 * @Version v1.0
 */
public class PublishSupplyActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private String titlebarName = "发布供货";
	private String[] mItems = { "供货信息", "求购信息" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.publish_supply);
		titleBarInitView();
	}

	/**
	 * 
	 * @Describe：初始化标题栏
	 * @Throws:
	 * @Date：2014年7月24日 上午9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo(titlebarName, isShowLeftBut, "返回", isShowRightBut, "发布求购");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
//					publishType();
//					startActivity(new Intent(PublishSupplyActivity.this, PublishBuyActivity.class));

					Intent intent = new Intent(PublishSupplyActivity.this, PublishBuyActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
					startActivity(intent);
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：选择发布信息类型
	 * @Throws:
	 * @Date：2014年7月25日 下午2:36:33
	 * @Version v1.0
	 */
	private void publishType() {
		AlertDialog.Builder builder = new AlertDialog.Builder(PublishSupplyActivity.this);
		builder.setTitle("列表选择框");
		builder.setItems(mItems, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// 点击后弹出窗口选择了第几项
				// showDialog("你选择的id为" + which + " , " + mItems[which]);
				Toast.makeText(PublishSupplyActivity.this, "您选择了" + mItems[which], 5).show();
				if (which == 1) {
					titlebarName = "发布求购";
				}

				titleName.setText(titlebarName);
			}
		});
		builder.create().show();
	}

	private void showDialog(String str) {
		new AlertDialog.Builder(PublishSupplyActivity.this).setMessage(str).show();
	}

}
