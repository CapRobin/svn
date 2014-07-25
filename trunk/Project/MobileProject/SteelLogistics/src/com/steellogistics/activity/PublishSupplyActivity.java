package com.steellogistics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
	private Spinner lbSpinner,pmSpinner,ggSpinner,cdSpinner,slSpinner,gbSpinner;
	private Button cdEdit;
	
	private static String gcPinming[]= null;
	private static String steelStyle_x[]=null; 
	private static String gcPinming_x_01[]=null; 
	private static String gcPinming_x_02[]=null; 
	private static String gcPinming_x_03[]=null; 

	private static String gcPinming_x_04[]=null; 
	private static String gcPinming_x_05[]=null; 
	private static String gcPinming_x_06[]=null; 
	private static String gcPinming_x_07[]=null; 

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
	 * @Describe：初始化
	 * @Throws:  
	 * @Date：2014年7月25日 下午4:35:13
	 * @Version v1.0
	 */
	private void initView(){


		lbSpinner=(Spinner)findViewById(R.id.lbSpinner);
		pmSpinner=(Spinner)findViewById(R.id.pmSpinner);
		ggSpinner=(Spinner)findViewById(R.id.ggSpinner);
		cdEdit=(Button)findViewById(R.id.cdEdit);
		slSpinner=(Spinner)findViewById(R.id.slSpinner);
		gbSpinner=(Spinner)findViewById(R.id.gbSpinner);
		
		steelStyle_x = this.getResources().getStringArray(R.array.steelStyle_x);
		gcPinming_x_01 = this.getResources().getStringArray(R.array.gcPinming_x_01);
		 gcPinming_x_02 = this.getResources().getStringArray(R.array.gcPinming_x_02);
		gcPinming_x_03 = this.getResources().getStringArray(R.array.gcPinming_x_03);
		
		gcPinming_x_04 = this.getResources().getStringArray(R.array.gcPinming_x_04);
		gcPinming_x_05 = this.getResources().getStringArray(R.array.gcPinming_x_05);
		gcPinming_x_06 = this.getResources().getStringArray(R.array.gcPinming_x_06);
		gcPinming_x_07 = this.getResources().getStringArray(R.array.gcPinming_x_07);
		gcPinming = gcPinming_x_01; 
		

		lbSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//				textview.setText("您的血型是："+m[arg2]);
				Toast.makeText(PublishSupplyActivity.this, "您选择了"+steelStyle_x[arg2], 5).show();
//				steelStyleSelect = arg2;
				setPmData(arg2);				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		pmSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Toast.makeText(PublishSupplyActivity.this, "您选择了"+gcPinming[arg2], 5).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
//		cdEdit.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				// 显示对话框
//				if(m_iStartSign==0)//省份
//				{
//					ShowProvinceDlg(cdEdit,0);
//				}else if(m_iStartSign==1)//城市
//				{
//					ShowCityDlg(cdEdit,0);
//				}else if(m_iStartSign==2)//县区
//				{
//					ShowTownDlg(cdEdit,0);
//				}
//			}
//		});
		
		
	};

//	/**
//	 * 
//	 * @Describe：选择发布信息类型
//	 * @Throws:
//	 * @Date：2014年7月25日 下午2:36:33
//	 * @Version v1.0
//	 */
//	private void publishType() {
//		AlertDialog.Builder builder = new AlertDialog.Builder(PublishSupplyActivity.this);
//		builder.setTitle("列表选择框");
//		builder.setItems(mItems, new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int which) {
//				// 点击后弹出窗口选择了第几项
//				// showDialog("你选择的id为" + which + " , " + mItems[which]);
//				Toast.makeText(PublishSupplyActivity.this, "您选择了" + mItems[which], 5).show();
//				if (which == 1) {
//					titlebarName = "发布求购";
//				}
//
//				titleName.setText(titlebarName);
//			}
//		});
//		builder.create().show();
//	}
//
//	private void showDialog(String str) {
//		new AlertDialog.Builder(PublishSupplyActivity.this).setMessage(str).show();
//	}
	

	private void setPmData(int selectId) {

		switch (selectId) {
		case 0:
			gcPinming = gcPinming_x_01;
			break;
		case 1:
			gcPinming = gcPinming_x_02;
			break;
		case 2:
			gcPinming = gcPinming_x_03;
			break;
		case 3:
			gcPinming = gcPinming_x_04;
			break;
		case 4:
			gcPinming = gcPinming_x_05;
			break;
		case 5:
			gcPinming = gcPinming_x_06;
			break;
		case 6:
			gcPinming = gcPinming_x_07;
			break;
		}
		//配置品名数据
		ArrayAdapter mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gcPinming);
        //设置下拉列表风格
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pmSpinner.setAdapter(mAdapter);
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,gcPinming);
////		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		pmSpinner.setAdapter(adapter);
	}

}
