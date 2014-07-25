package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.ab.view.pullview.AbPullListView;
import com.steellogistics.R;
import com.steellogistics.adapter.SupplyInfoAdapter;
import com.steellogistics.entity.BuyInfo;
import com.steellogistics.entity.SupplyInfo;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SupplyActivity.java
 * @Describe：供货信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:09:30
 * @Version v1.0
 */
public class SupplyInfoListActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private List<SupplyInfo> mSupplyInfoList = null;
	private SupplyInfoAdapter myListViewAdapter = null;
	private AbPullListView supplyCargoList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.supply);
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
		setTitleInfo("供货信息", isShowLeftBut, "返回", isShowRightBut, "发布");
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
//					finish();
					startActivity(new Intent(SupplyInfoListActivity.this,PublishActivity.class));
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年4月29日 上午10:52:17
	 * @Version v1.0
	 * 
	 */
	private void initView() {
		// mAbTaskQueue = AbTaskQueue.getInstance();

		// 获取ListView对象
		supplyCargoList = (AbPullListView) findViewById(R.id.supplyCargoList);
		// 打开关闭下拉刷新加载更多功能
		supplyCargoList.setPullRefreshEnable(true);
		supplyCargoList.setPullLoadEnable(true);

		mSupplyInfoList = new ArrayList<SupplyInfo>();

		// 制作假数据
		for (int i = 0; i < 20; i++) {
			SupplyInfo supplyInfo = new SupplyInfo();
			supplyInfo.setId(i);
			supplyInfo.setTitleName("供货信息" + (i + 1));
			supplyInfo.setImageUrl("http://i.steelcn.cn/member/cg/buyadd.aspx");
			supplyInfo.setContent((i + 1) + "供货信息内容供货信息内容供货信息内容供货信息内容供货信息内容供货信息内容供货信息内容");
			supplyInfo.setCreatTime("2014_07_25");
			mSupplyInfoList.add(supplyInfo);
		}

		// 使用自定义的Adapter
		myListViewAdapter = new SupplyInfoAdapter(SupplyInfoListActivity.this, mSupplyInfoList);
		supplyCargoList.setAdapter(myListViewAdapter);

//		supplyCargoList.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				SupplyInfo cargoInfo = (SupplyInfo) myListViewAdapter.getItem(position - 1);
//				// Intent intent = new Intent(CargoListActivity.this,
//				// CargoListDetailActivity.class);
//				// Bundle bundle = new Bundle();
//				// bundle.putSerializable("cargoInfo", cargoInfo);
//				// intent.putExtra("bundle", bundle);
//				// startActivity(intent);
//				Toast.makeText(SupplyInfoListActivity.this, cargoInfo.getContent(), 5).show();
//			}
//		});
		
		supplyCargoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SupplyInfo supplyInfo = (SupplyInfo) myListViewAdapter.getItem(position - 1);
				String infoName = supplyInfo.getTitleName();
				String infoContent = supplyInfo.getContent();
				String infoTime = supplyInfo.getCreatTime();
				Intent intent = new Intent(SupplyInfoListActivity.this, SupplyInfoDetailActivity.class);
				// Bundle bundle = new Bundle();
				// bundle.putSerializable("cargoInfo", cargoInfo);
				// intent.putExtra("bundle", bundle);
				intent.putExtra("infoName", infoName);
				intent.putExtra("infoContent", infoContent);
				intent.putExtra("infoTime", infoTime);
				startActivity(intent);
			}
		});
		
	}
}
