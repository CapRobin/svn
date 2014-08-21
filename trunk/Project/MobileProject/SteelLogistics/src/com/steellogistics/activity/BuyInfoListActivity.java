package com.steellogistics.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.steellogistics.R;
import com.steellogistics.adapter.BuyInfoAdapter;
import com.steellogistics.entity.BuyInfo;
import com.steellogistics.entity.BuyInfoDetail;
import com.steellogistics.util.MethodUtil;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：BuyInfoListActivity.java
 * @Describe：求购信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:10:01
 * @Version v1.0
 */
public class BuyInfoListActivity extends BaseActivity {

	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;

	// private List<BuyInfo> mBuyInfoList = null;
	private BuyInfoAdapter myListViewAdapter = null;
	private AbPullListView buyCargoList = null;
	private List<BuyInfo> mBuyInfoInfoList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.buy);
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
		setTitleInfo("求购信息", isShowLeftBut, "返回", isShowRightBut, "发布");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
//					finish();
				}
			});
		}

		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// finish();
					startActivity(new Intent(BuyInfoListActivity.this, PublishBuyActivity.class));
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
		buyCargoList = (AbPullListView) findViewById(R.id.buyCargoList);
		// 打开关闭下拉刷新加载更多功能
		buyCargoList.setPullRefreshEnable(true);
		buyCargoList.setPullLoadEnable(true);
		mBuyInfoInfoList = new ArrayList<BuyInfo>();

		// 获取构造数据
		List<BuyInfoDetail> buyInfoDetailList = application.buyInfoList;
		try {
			BuyInfo getBuyInfo = null;
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			if (buyInfoDetailList != null && buyInfoDetailList.size() > 0) { // Application中获取数据数据
				String objectItem = gson.toJson(buyInfoDetailList);
				JSONArray array = new JSONArray(objectItem);
				for (int i = 0; i < array.length(); i++) {
					JSONObject item = array.getJSONObject(i);
					getBuyInfo = gson.fromJson(item.toString(), BuyInfo.class);
					mBuyInfoInfoList.add(getBuyInfo);
				}

				// 输出得到的列表Json数据字符
				String getSupplyInfoListStr = gson.toJson(mBuyInfoInfoList);
				System.out.println("getSupplyInfoListStr is ------------>>" + getSupplyInfoListStr);
				Toast.makeText(BuyInfoListActivity.this, "Application中获取数据数据", Toast.LENGTH_SHORT).show();
			} else { // Assets中获取数据数据
				String getInfo = MethodUtil.getLocalInfo(BuyInfoListActivity.this, "buy_list.java");
				JSONArray array = new JSONArray(getInfo);
				for (int i = 0; i < array.length(); i++) {
					JSONObject item = array.getJSONObject(i);
					getBuyInfo = gson.fromJson(item.toString(), BuyInfo.class);
					mBuyInfoInfoList.add(getBuyInfo);
				}
				Toast.makeText(BuyInfoListActivity.this, "Assets中获取数据数据", Toast.LENGTH_SHORT).show();
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		// 使用自定义的Adapter
		myListViewAdapter = new BuyInfoAdapter(BuyInfoListActivity.this, mBuyInfoInfoList);
		buyCargoList.setAdapter(myListViewAdapter);

		buyCargoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				BuyInfo buyInfo = (BuyInfo) myListViewAdapter.getItem(position - 1);
				String infoName = buyInfo.getTitleName();
				String infoContent = buyInfo.getTitleName();
				String infoTime = buyInfo.getCreatTime();
				Intent intent = new Intent(BuyInfoListActivity.this, BuyInfoDetailActivity.class);
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

	/**
	 * 获取assets文件中的Json字符串
	 * 
	 */
	private void getJsonText() {
		try {

			// private InputStream mStream;
			// private String getStr;

			InputStream mStream = this.getAssets().open("jsonTest_1.txt");
			byte[] buffer = new byte[getResources().getAssets().open("jsonTest_1.txt").available()];
			mStream.read(buffer);
			mStream.close();
			String getStr = new String(buffer);
			// mTextView.setText(getStr);
			Log.d("TAG", "getJson is ---------------------->>" + getStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
