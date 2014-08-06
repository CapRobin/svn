package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ab.task.AbTaskQueue;
import com.ab.view.pullview.AbPullListView;
import com.steellogistics.R;
import com.steellogistics.adapter.RelevantInfoAdapter;
import com.steellogistics.database.SqliteDaoArea;
import com.steellogistics.entity.RelevantInfo;
import com.steellogistics.view.MyImgScroll;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：RelevantInfoActivity.java
 * @Describe：行业资讯
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:08:33
 * @Version v1.0
 */
public class RelevantInfoActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;

	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组
	private SqliteDaoArea daoArea = null;
	private AbPullListView cargoList = null;
	private AbTaskQueue mAbTaskQueue = null;
	private RelevantInfoAdapter myListViewAdapter = null;
	private List<RelevantInfo> mRelevantInfoList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.relevant_info);
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
		setTitleInfo("行业资讯", isShowLeftBut, "返回", isShowRightBut, null);
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年4月28日 上午11:07:32
	 * @Version v1.0
	 */
	private void initView() {
		daoArea = SqliteDaoArea.getInstance(RelevantInfoActivity.this);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);
		// cargoList = (AbPullListView) findViewById(R.id.cargoList);
		// 初始化图片
		InitViewPager();
		// 开始滚动
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v, R.drawable.dot_focused, R.drawable.dot_normal);

		mAbTaskQueue = AbTaskQueue.getInstance();

		// 获取ListView对象
		cargoList = (AbPullListView) findViewById(R.id.cargoList);
		// 打开关闭下拉刷新加载更多功能
		cargoList.setPullRefreshEnable(true);
		cargoList.setPullLoadEnable(true);

		mRelevantInfoList = new ArrayList<RelevantInfo>();

		// 构造数据
		RelevantInfo info = null;
		for (int i = 0; i < 50; i++) {
			info = new RelevantInfo();
			info.setTitleName("资讯" + i);
			info.setContent(getResources().getString(R.string.contentText) + i);
			info.setTime(String.valueOf(2001 + i) + "_05_08");
			mRelevantInfoList.add(info);
		}

		// 使用自定义的Adapter
		myListViewAdapter = new RelevantInfoAdapter(RelevantInfoActivity.this, mRelevantInfoList);
		cargoList.setAdapter(myListViewAdapter);

		cargoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				RelevantInfo cargoInfo = (RelevantInfo) myListViewAdapter.getItem(position - 1);
				String infoName = cargoInfo.getTitleName();
				String infoContent = cargoInfo.getContent();
				String infoTime = cargoInfo.getTime();
				Intent intent = new Intent(RelevantInfoActivity.this, RelevantInfoDetailActivity.class);
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
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://dl.5671.cc/");
						startActivity(intent);
						break;
					case 1:
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://qy.58.com/17195629821703/");
						startActivity(intent);
						break;
					case 2:
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.cnpc.com.cn/cn/");
						startActivity(intent);
						break;
					case 3:
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://csl.chinawuliu.com.cn/");
						startActivity(intent);
						break;
					case 4:
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.189.cn/");
						startActivity(intent);
						break;
					}
					Toast.makeText(RelevantInfoActivity.this, "您点击了第" + (myPager.getCurIndex() + 1) + "个广告位", Toast.LENGTH_SHORT).show();
				}
			});

			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}

	@Override
	protected void onRestart() {
		myPager.startTimer();
		super.onRestart();
	}

	@Override
	protected void onStop() {
		myPager.stopTimer();
		super.onStop();
	}

	public void stop(View v) {
		myPager.stopTimer();
	}
}
