package com.steellogistics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.steellogistics.R;
import com.steellogistics.entity.BuyInfoDetail;
import com.steellogistics.entity.UserInfo;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PublishActivity.java
 * @Describe：发布求购信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:08:10
 * @Version v1.0
 */
public class PublishBuyActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private String titlebarName = "发布求购";
	private String[] mItems = { "供货信息", "求购信息" };
	private EditText btEdit, qgyqEdit, slEdit, jgEdit, bzyqEdit, bcnrEdit = null;
	private Button publishBuyBtn = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.publish_buy);
		titleBarInitView();
		initView();
	}

	/**
	 * 
	 * @Describe：初始化标题栏
	 * @Throws:
	 * @Date：2014年7月24日 上午9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo(titlebarName, isShowLeftBut, "返回", isShowRightBut, "发布供货");
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
					// publishType();
					Intent intent = new Intent(PublishBuyActivity.this, PublishSupplyActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：初始化
	 * @Throws:
	 * @Date：2014年7月25日 下午4:35:13
	 * @Version v1.0
	 */
	private void initView() {
		btEdit = (EditText) findViewById(R.id.btEdit);
		jgEdit = (EditText) findViewById(R.id.jgEdit);
		slEdit = (EditText) findViewById(R.id.slEdit);
		qgyqEdit = (EditText) findViewById(R.id.qgyqEdit);
		bzyqEdit = (EditText) findViewById(R.id.bzyqEdit);
		bcnrEdit = (EditText) findViewById(R.id.bcnrEdit);
		publishBuyBtn = (Button) findViewById(R.id.publishBuyBtn);
		publishBuyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UserInfo info = application.userInfo;
				String btEditStr = btEdit.getText().toString();
				String jgEditStr = jgEdit.getText().toString();
				String slEditStr = slEdit.getText().toString();
				String qgyqEditStr = qgyqEdit.getText().toString();
				String bzyqEditStr = bzyqEdit.getText().toString();
				String bcnrEditStr = bcnrEdit.getText().toString();

				int userId = info.getId();
				String buyerName = info.getRealName();
				String mobile = info.getMobile();
				String buyerAddress = info.getAddress();
				String contacts = info.getContacts();

				// String dealType = "线下交易";
				// 输入标题验证
				if (TextUtils.isEmpty(btEditStr)) {
					btEdit.setError("请输入标题");
					btEdit.setFocusable(true);
					btEdit.setFocusableInTouchMode(true);
					btEdit.requestFocus();
					return;
				}
				// 输入价格验证
				if (TextUtils.isEmpty(jgEditStr)) {
					jgEdit.setError("请输入价格");
					jgEdit.setFocusable(true);
					jgEdit.setFocusableInTouchMode(true);
					jgEdit.requestFocus();
					return;
				}
				// 输入数量验证
				if (TextUtils.isEmpty(slEditStr)) {
					slEdit.setError("请输入数量");
					slEdit.setFocusable(true);
					slEdit.setFocusableInTouchMode(true);
					slEdit.requestFocus();
					return;
				}
				BuyInfoDetail buyInfoDetail = new BuyInfoDetail();

				buyInfoDetail.setId(001);
				buyInfoDetail.setTitleName(btEditStr);
				buyInfoDetail.setImageUrl("http://img3.cache.netease.com/catchimg/20100808/85U286RU_0.jpg");
				buyInfoDetail.setOtherInfo(bcnrEditStr);
				buyInfoDetail.setBuyAmount(Integer.valueOf(slEditStr));
				buyInfoDetail.setBuyPrice(jgEditStr);
				buyInfoDetail.setUserAddress(buyerAddress);

				buyInfoDetail.setContactNumber(mobile);
				buyInfoDetail.setCreatTime("2014_08_05");
				buyInfoDetail.setBuyRequire(qgyqEditStr);
				buyInfoDetail.setPackRequire(bzyqEditStr);
				buyInfoDetail.setUserRealName(buyerName);
				buyInfoDetail.setContacts(contacts);
				publicJk(userId, buyInfoDetail);
			}
		});

	};

	/**
	 * 
	 * @Describe：模拟调用接口
	 * @param userId
	 * @param supplyInfo
	 * @Throws:
	 * @Date：2014年8月6日 下午3:01:37
	 * @Version v1.0
	 */
	private void publicJk(int userId, BuyInfoDetail buyInfo) {
		application.buyInfoList.add(buyInfo);

		// BuyInfoDetail对象打包成Json字符串
		Gson gson = new Gson();
		String getBuyInfoStr = gson.toJson(buyInfo);
		System.out.println("getBuyInfoStr is ----------->>" + getBuyInfoStr);
		Toast.makeText(PublishBuyActivity.this, "发布求购信息成功", 5).show();
	}

}
