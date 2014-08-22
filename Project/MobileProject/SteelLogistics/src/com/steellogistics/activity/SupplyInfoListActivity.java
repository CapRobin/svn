package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.steellogistics.R;
import com.steellogistics.adapter.SupplyInfoAdapter;
import com.steellogistics.database.SqliteDaoArea;
import com.steellogistics.entity.SupplyInfo;
import com.steellogistics.entity.SupplyInfoDetail;
import com.steellogistics.util.MethodUtil;
import com.steellogistics.view.MyImgScroll;

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
	private AlertDialog dialog;
	private SqliteDaoArea daoArea = null;
	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.supply);
		titleBarInitView();
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setData();
	}

	/**
	 * 
	 * @Describe：初始化标题栏
	 * @Throws:
	 * @Date：2014年7月24日 上午9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo("首    页", isShowLeftBut, "退出", isShowRightBut, "搜索");
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
					startActivity(new Intent(SupplyInfoListActivity.this, SearchActivity.class));
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
		daoArea = SqliteDaoArea.getInstance(SupplyInfoListActivity.this);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);

		// 初始化图片
		InitViewPager();
		// 开始滚动
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v, R.drawable.dot_focused, R.drawable.dot_normal);

		// 获取ListView对象
		supplyCargoList = (AbPullListView) findViewById(R.id.supplyCargoList);
		// 打开关闭下拉刷新加载更多功能
		supplyCargoList.setPullRefreshEnable(true);
		supplyCargoList.setPullLoadEnable(true);
		// // 构造数据
		// String objectItem = null;
		// try {
		// SupplyInfo getSupplyInfo = null;
		// GsonBuilder builder = new GsonBuilder();
		// Gson gson = builder.create();
		// String getSupplyInfoStr = MethodUtil.getSharedPreferences(this,
		// "AppData", "supplyInfo");
		// if (!TextUtils.isEmpty(getSupplyInfoStr)) { // 本地获取数据
		// objectItem = getSupplyInfoStr;
		// } else { // Application中获取数据数据
		// List<SupplyInfoDetail> supplyInfoDetailList =
		// application.supplyInfoList;
		// objectItem = gson.toJson(supplyInfoDetailList);
		// }
		// JSONArray array = new JSONArray(objectItem);
		// for (int i = 0; i < array.length(); i++) {
		// JSONObject item = array.getJSONObject(i);
		// getSupplyInfo = gson.fromJson(item.toString(), SupplyInfo.class);
		// mSupplyInfoList.add(getSupplyInfo);
		// }
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }

		// //排序
		// List<SupplyInfo> getSupplyInfoList =
		// MethodUtil.sortSupplyList(mSupplyInfoList, formatStr);
		// // 使用自定义的Adapter
		// myListViewAdapter = new
		// SupplyInfoAdapter(SupplyInfoListActivity.this, getSupplyInfoList);
		// supplyCargoList.setAdapter(myListViewAdapter);

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
		// setData();
	}

	/**
	 * 
	 * @Describe：装在数据
	 * @Throws:
	 * @Date：2014年8月22日 下午3:13:07
	 * @Version v1.0
	 */
	private void setData() {
		mSupplyInfoList = makeSupplyListData();
		// 排序
		List<SupplyInfo> getSupplyInfoList = MethodUtil.sortSupplyList(mSupplyInfoList, formatStr);
		// 使用自定义的Adapter
		myListViewAdapter = new SupplyInfoAdapter(SupplyInfoListActivity.this, getSupplyInfoList);
		supplyCargoList.setAdapter(myListViewAdapter);
	}

	/**
	 * @Describe：初始化广告图片
	 * @Throws:
	 * @Date：2014年4月28日 下午3:29:10
	 * @Version v1.0
	 */
	private void InitViewPager() {
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.g };
		for (int i = 0; i < imageResId.length; i++) {
			final int imageItem = i;
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			// 设置图片点击事件
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					switch (imageItem) {
					case 0:
						intent.setClass(SupplyInfoListActivity.this, MyWebView.class);
						intent.putExtra("url", "http://dl.5671.cc/");
						startActivity(intent);
						break;
					case 1:
						intent.setClass(SupplyInfoListActivity.this, MyWebView.class);
						intent.putExtra("url", "http://qy.58.com/17195629821703/");
						startActivity(intent);
						break;
					case 2:
						intent.setClass(SupplyInfoListActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.cnpc.com.cn/cn/");
						startActivity(intent);
						break;
					case 3:
						intent.setClass(SupplyInfoListActivity.this, MyWebView.class);
						intent.putExtra("url", "http://csl.chinawuliu.com.cn/");
						startActivity(intent);
						break;
					case 4:
						intent.setClass(SupplyInfoListActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.189.cn/");
						startActivity(intent);
						break;
					}
					Toast.makeText(SupplyInfoListActivity.this, "您点击了第" + (myPager.getCurIndex() + 1) + "个广告位", Toast.LENGTH_SHORT).show();
				}
			});

			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
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
				if (supplyInfoDetailList.size() > 0 && supplyInfoDetailList != null) {
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
			} else {
				Toast.makeText(SupplyInfoListActivity.this, "暂时没有数据", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return mSupplyInfoList;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showMyDialog();
		}
		return super.onKeyDown(keyCode, event);
	}
}
