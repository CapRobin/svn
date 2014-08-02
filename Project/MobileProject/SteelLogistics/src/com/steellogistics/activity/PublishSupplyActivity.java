package com.steellogistics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.steellogistics.R;
import com.steellogistics.entity.SupplyInfoDetail;

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
public class PublishSupplyActivity extends BaseActivity implements OnClickListener {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private String titlebarName = "发布供货";
	private String[] mItems = { "供货信息", "求购信息" };
	private Spinner lbSpinner, pmSpinner, ggSpinner, slSpinner, gbSpinner, jyfsSpinner;
	private EditText btEdit, cdEdit, slEdit, jgEdit, bcnrEdit = null;
	private Button addinfoBtn = null;

	private static String gcPinming[] = null;
	private static String steelStyle[] = null;
	private static String gcPinming_01[] = null;
	private static String gcPinming_02[] = null;
	private static String gcPinming_03[] = null;

	private static String gcPinming_04[] = null;
	private static String gcPinming_05[] = null;
	private static String gcPinming_06[] = null;
	private static String gcPinming_07[] = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.publish_supply);
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
					Intent intent = new Intent(PublishSupplyActivity.this, PublishBuyActivity.class);
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
		cdEdit = (EditText) findViewById(R.id.cdEdit);
		slEdit = (EditText) findViewById(R.id.slEdit);
		jgEdit = (EditText) findViewById(R.id.jgEdit);
		bcnrEdit = (EditText) findViewById(R.id.bcnrEdit);

		lbSpinner = (Spinner) findViewById(R.id.lbSpinner);
		pmSpinner = (Spinner) findViewById(R.id.pmSpinner);
		ggSpinner = (Spinner) findViewById(R.id.ggSpinner);
		slSpinner = (Spinner) findViewById(R.id.slSpinner);
		gbSpinner = (Spinner) findViewById(R.id.gbSpinner);
		jyfsSpinner = (Spinner) findViewById(R.id.jyfsSpinner);

		steelStyle = this.getResources().getStringArray(R.array.steelStyle);
		gcPinming_01 = this.getResources().getStringArray(R.array.gcPinming_01);
		gcPinming_02 = this.getResources().getStringArray(R.array.gcPinming_02);
		gcPinming_03 = this.getResources().getStringArray(R.array.gcPinming_03);

		gcPinming_04 = this.getResources().getStringArray(R.array.gcPinming_04);
		gcPinming_05 = this.getResources().getStringArray(R.array.gcPinming_05);
		gcPinming_06 = this.getResources().getStringArray(R.array.gcPinming_06);
		gcPinming_07 = this.getResources().getStringArray(R.array.gcPinming_07);
		gcPinming = gcPinming_01;

		addinfoBtn = (Button) findViewById(R.id.addinfoBtn);
		addinfoBtn.setOnClickListener(this);

		lbSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// textview.setText("您的血型是："+m[arg2]);
				Toast.makeText(PublishSupplyActivity.this, "您选择了" + steelStyle[arg2], 5).show();
				// steelStyleSelect = arg2;
				setPmData(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		pmSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Toast.makeText(PublishSupplyActivity.this, "您选择了" + gcPinming[arg2], 5).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

	};

	/**
	 * 
	 * @Describe：设置钢材类别名称
	 * @param selectId
	 * @Throws:  
	 * @Date：2014年8月2日 下午4:15:02
	 * @Version v1.0
	 */
	private void setPmData(int selectId) {
		switch (selectId) {
		case 0:
			gcPinming = gcPinming_01;
			break;
		case 1:
			gcPinming = gcPinming_02;
			break;
		case 2:
			gcPinming = gcPinming_03;
			break;
		case 3:
			gcPinming = gcPinming_04;
			break;
		case 4:
			gcPinming = gcPinming_05;
			break;
		case 5:
			gcPinming = gcPinming_06;
			break;
		case 6:
			gcPinming = gcPinming_07;
			break;
		}
		// 配置品名数据
		ArrayAdapter mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gcPinming);
		// 设置下拉列表风格
		mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		pmSpinner.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addinfoBtn:

			String btEditStr = btEdit.getText().toString();
			String cdEditStr = cdEdit.getText().toString();
			String slEditStr = slEdit.getText().toString();
			String jgEditStr = jgEdit.getText().toString();
			String bcnrEditStr = bcnrEdit.getText().toString();
			String jyfwEditStr = "所有钢材";
//			String dealType = "线下交易";
			// 输入标题验证
			if (TextUtils.isEmpty(btEditStr)) {
				btEdit.setError("请输入标题");
				btEdit.setFocusable(true);
				btEdit.setFocusableInTouchMode(true);
				btEdit.requestFocus();
				return;
			}
			// 输入产地验证
			if (TextUtils.isEmpty(cdEditStr)) {
				cdEdit.setError("请输入产地");
				cdEdit.setFocusable(true);
				cdEdit.setFocusableInTouchMode(true);
				cdEdit.requestFocus();
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
			// 输入价格验证
			if (TextUtils.isEmpty(jgEditStr)) {
				jgEdit.setError("请输入价格");
				jgEdit.setFocusable(true);
				jgEdit.setFocusableInTouchMode(true);
				jgEdit.requestFocus();
				return;
			}

			String lbSpinnerStr = lbSpinner.getSelectedItem().toString();
			String pmSpinnerStr = pmSpinner.getSelectedItem().toString();
			String ggSpinnerStr = ggSpinner.getSelectedItem().toString();
			String slSpinnerStr = slSpinner.getSelectedItem().toString();
			String gbSpinnerStr = gbSpinner.getSelectedItem().toString();
			String jyfsSpinnerStr = jyfsSpinner.getSelectedItem().toString();

			String companyName = application.userInfo.getRealName();
			int userId = application.userInfo.getId();
			String mobile = application.userInfo.getMobile();
			String email = application.userInfo.getEmail();
			String contacts = application.userInfo.getRealName();

			SupplyInfoDetail supplyInfoDetail = new SupplyInfoDetail();
			supplyInfoDetail.setId(01);
			supplyInfoDetail.setTitleName(btEditStr);
			supplyInfoDetail.setImageUrl("http://pic4.jiancai.com/2013/04/19/20130419010145823.jpg");
			supplyInfoDetail.setSellScope(jyfwEditStr);
			supplyInfoDetail.setPrice(jgEditStr);
			supplyInfoDetail.setProductName(pmSpinnerStr);
			supplyInfoDetail.setProductsInfo(bcnrEditStr);
			supplyInfoDetail.setContent("");
			supplyInfoDetail.setCompanyName(companyName);
			supplyInfoDetail.setCompanyAddress(cdEditStr);
			supplyInfoDetail.setMobile(mobile);
			supplyInfoDetail.setCreatTime("2014_07_30");

			supplyInfoDetail.setType(lbSpinnerStr);
			supplyInfoDetail.setSpecification(ggSpinnerStr);
			supplyInfoDetail.setMakeAddress(cdEditStr);
			supplyInfoDetail.setAmount(slEditStr + slSpinnerStr);
			supplyInfoDetail.setIsGb(gbSpinnerStr);
			supplyInfoDetail.setContacts(contacts);
			supplyInfoDetail.setEmail(email);
			supplyInfoDetail.setDealType(jyfsSpinnerStr);

			publicJk(userId, supplyInfoDetail);
			break;
		}
	}

	// 模拟调用接口
	private void publicJk(int userId, SupplyInfoDetail supplyInfo) {
		application.supplyInfoList.add(supplyInfo);

//		 supplyInfoDetail对象打包成Json字符串
		 Gson gson = new Gson();
		 String getSupplyInfoStr = gson.toJson(supplyInfo);
		 System.out.println("getSupplyInfoStr is ----------->>" + getSupplyInfoStr);
		 Toast.makeText(PublishSupplyActivity.this, "发布供货信息成功", 5).show();
	}
}
