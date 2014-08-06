package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.steellogistics.R;
import com.steellogistics.adapter.SupplyInfoAdapter;
import com.steellogistics.entity.SupplyInfo;
import com.steellogistics.entity.SupplyInfoDetail;

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
					startActivity(new Intent(SupplyInfoListActivity.this, PublishSupplyActivity.class));
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

		// 构造数据
		List<SupplyInfoDetail> supplyInfoDetailList = application.supplyInfoList;
		if (supplyInfoDetailList != null && supplyInfoDetailList.size() > 0) {
			SupplyInfo getSupplyInfo = null;
			try {
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();
				String objectItem = gson.toJson(supplyInfoDetailList);
				JSONArray array = new JSONArray(objectItem);
				for (int i = 0; i < array.length(); i++) {
					JSONObject item = array.getJSONObject(i);
					getSupplyInfo = gson.fromJson(item.toString(), SupplyInfo.class);
					mSupplyInfoList.add(getSupplyInfo);
				}

				// 输出得到的列表Json数据字符
				String getSupplyInfoListStr = gson.toJson(mSupplyInfoList);
				System.out.println("getSupplyInfoListStr is ------------>>" + getSupplyInfoListStr);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		// for (int i = 0; i < 20; i++) {
		// SupplyInfo supplyInfo = new SupplyInfo();
		// supplyInfo.setId(i);
		// supplyInfo.setTitleName("供应钢材信息" + (i + 1));
		// supplyInfo.setImageUrl("http://i.steelcn.cn/member/cg/buyadd.aspx");
		// supplyInfo.setSellScope("所有钢材");
		// supplyInfo.setPrice(String.valueOf(100 + i));
		// supplyInfo.setCompanyAddress("山东济南市");
		// supplyInfo.setMobile("13800000002");
		// supplyInfo.setCreatTime("2014_07_25");
		// mSupplyInfoList.add(supplyInfo);
		// }

		// 使用自定义的Adapter
		myListViewAdapter = new SupplyInfoAdapter(SupplyInfoListActivity.this, mSupplyInfoList);
		supplyCargoList.setAdapter(myListViewAdapter);

		supplyCargoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SupplyInfo supplyInfo = (SupplyInfo) myListViewAdapter.getItem(position - 1);
				String infoName = supplyInfo.getTitleName();
				String infoContent = supplyInfo.getTitleName();
				String infoTime = supplyInfo.getCreatTime();
				Intent intent = new Intent(SupplyInfoListActivity.this, SupplyInfoDetailActivity.class);
				intent.putExtra("infoName", infoName);
				intent.putExtra("infoContent", infoContent);
				intent.putExtra("infoTime", infoTime);
				startActivity(intent);
			}
		});

	}
}
