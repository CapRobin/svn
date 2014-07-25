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
import com.steellogistics.adapter.BuyInfoAdapter;
import com.steellogistics.entity.BuyInfo;
import com.steellogistics.entity.RelevantInfo;

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
	
	private List<BuyInfo> mBuylyInfoList = null;
	private BuyInfoAdapter myListViewAdapter = null;
	private AbPullListView buyCargoList = null;

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
					finish();
				}
			});
		}
		

		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
//					finish();
					startActivity(new Intent(BuyInfoListActivity.this,PublishSupplyActivity.class));
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

		mBuylyInfoList = new ArrayList<BuyInfo>();

		// 制作假数据
		for (int i = 0; i < 20; i++) {
			BuyInfo buyInfo = new BuyInfo();
			buyInfo.setId(i);
			buyInfo.setTitleName("求购信息" + (i + 1));
			buyInfo.setImageUrl("http://i.steelcn.cn/member/cg/buyadd.aspx");
			buyInfo.setContent((i + 1) + "求购信息内容求购信息内容求购信息内容求购信息内容求购信息内容求购信息内容求购信息内容");
			buyInfo.setCreatTime("2014_07_25");
			mBuylyInfoList.add(buyInfo);
		}

		// 使用自定义的Adapter
		myListViewAdapter = new BuyInfoAdapter(BuyInfoListActivity.this, mBuylyInfoList);
		buyCargoList.setAdapter(myListViewAdapter);
		
		buyCargoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				BuyInfo buyInfo = (BuyInfo) myListViewAdapter.getItem(position - 1);
				String infoName = buyInfo.getTitleName();
				String infoContent = buyInfo.getContent();
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
}
