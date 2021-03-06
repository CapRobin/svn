package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.steellogistics.R;
import com.steellogistics.adapter.BuyInfoAdapter;
import com.steellogistics.adapter.SupplyInfoAdapter;
import com.steellogistics.entity.BuyInfo;
import com.steellogistics.entity.BuyInfoDetail;
import com.steellogistics.entity.SupplyInfo;
import com.steellogistics.entity.SupplyInfoDetail;
import com.steellogistics.util.MethodUtil;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：GongQiuActivity.java
 * @Describe：供求信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:14:05
 * @Version v1.0
 */
public class GongQiuActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private boolean isSupplyInfoList = true;
	private Button gyBut = null;
	private Button qgBut = null;
	private AbPullListView supplyList, buyList = null;
	private BuyInfoAdapter buyInfoAdapter = null;
	private SupplyInfoAdapter supplyInfoAdapter = null;
	private List<BuyInfo> mBuyInfoInfoList = null;
	private List<SupplyInfo> mSupplyInfoList = null;
	private List<SupplyInfo> supplyInfoList = null;
	private List<BuyInfo> buyInfoList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.gongqiu);
		titleBarInitView();
		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//工具之前发布的信息类型(发布供货或求购)来选择供求显示信息
		isSupplyInfoList = application.isPublishSupply;
		if (isSupplyInfoList) {
			setTitleInfo("供货信息", isShowLeftBut, "返回", isShowRightBut, "求购");
		} else {
			setTitleInfo("求购信息", isShowLeftBut, "返回", isShowRightBut, "供应");
		}
		setGongQiuInfoView(isSupplyInfoList, false);
	}

	/**
	 * 
	 * @Describe：初始化标题栏
	 * @Throws:
	 * @Date：2014年7月24日 上午9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo("供货信息", isShowLeftBut, "返回", isShowRightBut, "求购");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					showMyDialog();
				}
			});
		}

		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (isSupplyInfoList) {
						setTitleInfo("供货信息", isShowLeftBut, "返回", isShowRightBut, "求购");
					} else {
						setTitleInfo("求购信息", isShowLeftBut, "返回", isShowRightBut, "供应");
					}
					setGongQiuInfoView(isSupplyInfoList, true);
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
		// 默认显示数据
		setGongQiuInfoView(isSupplyInfoList, true);
	}

	/**
	 * 
	 * @Describe：设置显示View数据
	 * @param isSupplyList
	 * @Throws:
	 * @Date：2014年8月21日 下午5:39:06
	 * @Version v1.0
	 */
	private void setGongQiuInfoView(boolean isSupplyList,boolean flag) {
		if (isSupplyInfoList) {
			supplyList = null;
			buyList = null;
			// 获取ListView对象
			supplyList = (AbPullListView) findViewById(R.id.gqList);
			supplyList.setPullRefreshEnable(true);
			supplyList.setPullLoadEnable(true);

			supplyInfoList = makeSupplyListData();
			if (supplyInfoList != null && supplyInfoList.size() > 0) {

				//排序
				List<SupplyInfo> getSupplyInfoList = MethodUtil.sortSupplyList(mSupplyInfoList, formatStr);
				supplyInfoAdapter = new SupplyInfoAdapter(GongQiuActivity.this, getSupplyInfoList);
				supplyList.setAdapter(supplyInfoAdapter);
				supplyList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						SupplyInfo supplyInfo = (SupplyInfo) supplyInfoAdapter.getItem(position - 1);
						String infoName = supplyInfo.getTitleName();
						String infoContent = supplyInfo.getTitleName();
						String infoTime = supplyInfo.getCreatTime();
						Intent intent = new Intent(GongQiuActivity.this, SupplyInfoDetailActivity.class);
						intent.putExtra("infoName", infoName);
						intent.putExtra("infoContent", infoContent);
						intent.putExtra("infoTime", infoTime);
						startActivity(intent);
					}
				});
				supplyInfoAdapter.notifyDataSetChanged();
			}
			if (flag) {
				isSupplyInfoList = false;
			}
		} else {
			supplyList = null;
			buyList = null;

			// 获取ListView对象
			buyList = (AbPullListView) findViewById(R.id.gqList);
			// 打开关闭下拉刷新加载更多功能
			buyList.setPullRefreshEnable(true);
			buyList.setPullLoadEnable(true);

			buyInfoList = makeBuyListData();
			if (buyInfoList != null && buyInfoList.size() > 0) {

				//排序
				List<BuyInfo> getBuyInfoInfoList = MethodUtil.sortBuyList(mBuyInfoInfoList, formatStr);
				buyInfoAdapter = new BuyInfoAdapter(GongQiuActivity.this, getBuyInfoInfoList);
				buyList.setAdapter(buyInfoAdapter);
				buyList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						BuyInfo buyInfo = (BuyInfo) buyInfoAdapter.getItem(position - 1);
						String infoName = buyInfo.getTitleName();
						String infoContent = buyInfo.getTitleName();
						String infoTime = buyInfo.getCreatTime();
						Intent intent = new Intent(GongQiuActivity.this, BuyInfoDetailActivity.class);
						intent.putExtra("infoName", infoName);
						intent.putExtra("infoContent", infoContent);
						intent.putExtra("infoTime", infoTime);
						startActivity(intent);
					}
				});
				buyInfoAdapter.notifyDataSetChanged();
			}
			if (flag) {
				isSupplyInfoList = true;
			}
		}
	}

	/**
	 * 
	 * @Describe：获取供货数据
	 * @return
	 * @Throws:
	 * @Date：2014年8月21日 下午4:37:43
	 * @Version v1.0
	 */
	private List<SupplyInfo> makeSupplyListData() {
		mSupplyInfoList = new ArrayList<SupplyInfo>();
		// 构造数据
		String objectItem = null;
		try {
			SupplyInfo getSupplyInfo = null;
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String getSupplyInfoStr = MethodUtil.getSharedPreferences(this, "AppData", "supplyInfo");
			if (!TextUtils.isEmpty(getSupplyInfoStr)) { // 本地获取数据
				objectItem = getSupplyInfoStr;
			} else { // Application中获取数据数据
				List<SupplyInfoDetail> supplyInfoDetailList = application.supplyInfoList;
				if (supplyInfoDetailList.size() > 0 && supplyInfoDetailList !=null) {
					objectItem = gson.toJson(supplyInfoDetailList);
				}
			}
			
			if (!TextUtils.isEmpty(objectItem)) {
				JSONArray array = new JSONArray(objectItem);
				for (int i = 0; i < array.length(); i++) {
					JSONObject item = array.getJSONObject(i);
					getSupplyInfo = gson.fromJson(item.toString(), SupplyInfo.class);
					mSupplyInfoList.add(getSupplyInfo);
				}
			}else {
				Toast.makeText(GongQiuActivity.this, "暂时没有数据", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return mSupplyInfoList;
	}

	/**
	 * 
	 * @Describe：获取求购信息
	 * @return
	 * @Throws:
	 * @Date：2014年8月21日 下午4:38:32
	 * @Version v1.0
	 */
	private List<BuyInfo> makeBuyListData() {
		mBuyInfoInfoList = new ArrayList<BuyInfo>();
		String objectItem = null;
		try {
			BuyInfo getBuyInfo = null;
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String getBuyInfoStr = MethodUtil.getSharedPreferences(this, "AppData", "buyInfo");
			if (!TextUtils.isEmpty(getBuyInfoStr)) { 		// 本地获取数据
				objectItem = getBuyInfoStr;
			} else { 														// Application中获取数据数据
				List<BuyInfoDetail> buyInfoDetailList = application.buyInfoList;
				objectItem = gson.toJson(buyInfoDetailList);
			}
			JSONArray array = new JSONArray(objectItem);
			for (int i = 0; i < array.length(); i++) {
				JSONObject item = array.getJSONObject(i);
				getBuyInfo = gson.fromJson(item.toString(), BuyInfo.class);
				mBuyInfoInfoList.add(getBuyInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return mBuyInfoInfoList;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showMyDialog();
		}
		return super.onKeyDown(keyCode, event);
	}
}
