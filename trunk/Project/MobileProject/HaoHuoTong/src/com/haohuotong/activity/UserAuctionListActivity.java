package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ab.activity.AbActivity;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.adapter.UserAuctionAdapter;
import com.haohuotong.entity.AuctionInfo;
import com.haohuotong.entity.CargoInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.global.MyApplication;
import com.haohuotong.other.InfoEntity;
import com.haohuotong.web.PublicInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：UserAuctionListActivity.java
 * @Describe：用户竞拍信息列表
 * @Author FaRong——yfr5734@gmail.com
 * @Date：2013-12-13 下午1:27:24
 * @Version v1.0
 */
public class UserAuctionListActivity extends AbActivity implements OnClickListener {
	private AbPullListView compList = null;
	private MyApplication application = null;
	private UserAuctionAdapter adapter = null;
	private AbTaskQueue mAbTaskQueue = null;
	private List<AuctionInfo> mAuctionList = null;
	private List<AuctionInfo> mNewAuctionList = null;
	private int currentPage = 1;
	private List<Parameter> getParameterList = null;
	private int carInfoId = 0 ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.auction_list);
		carInfoId = getIntent().getIntExtra("carInfoId",0);
		initTitleRightLayout();
		initView();

	}

	/**
	 * 
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-11-13 上午11:13:10
	 * @version v1.0
	 */
	private void initTitleRightLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText("竞拍列表");

		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) abApplication;
	}

	/**
	 * 
	 * 描述：初始化
	 * 
	 * @throws
	 * @date：2013-11-13 上午11:11:52
	 * @version v1.0
	 */
	private void initView() {
		mAbTaskQueue = AbTaskQueue.getInstance();
		compList = (AbPullListView) findViewById(R.id.mList);
		// 打开关闭下拉刷新加载更多功能
		compList.setPullRefreshEnable(true);
		compList.setPullLoadEnable(true);
		mAuctionList = new ArrayList<AuctionInfo>();
		adapter = new UserAuctionAdapter(UserAuctionListActivity.this, mAuctionList);
		compList.setAdapter(adapter);

		getListData();
		compList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(UserAuctionListActivity.this, SubmitOrderDetailActivity.class);
				intent.putExtra("User_id", mAuctionList.get(position).getUser_id());
				intent.putExtra("Add_id", mAuctionList.get(position).getAdd_id());
				intent.putExtra("Money_value", Double.parseDouble(mAuctionList.get(position).getMoney_value()));
//				InfoEntity entity = (InfoEntity) application.entitySet.get(position);
//				intent.putExtra("DATA", entity);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * 描述：获取数据
	 * 
	 * @throws
	 * @date：2013-11-13 下午1:47:25
	 * @version v1.0
	 */
	public void getData() {

		List<AuctionInfo> auctionList = new ArrayList<AuctionInfo>();
		AuctionInfo auctionInfo = null;
		for (int i = 0; i < 20; i++) {
			auctionInfo = new AuctionInfo();
			auctionInfo.setDuser_name("宁夏" + (i + 1) + "司机" + (i + 1));
			auctionInfo.setMoney_value("出价：" + String.valueOf((500 + i)));
			auctionInfo.setCar_NOI("车牌：宁A:1204" + i);
			auctionInfo.setJP_time("2013.12.0" + (i + 1));

			auctionList.add(auctionInfo);
		}

		adapter = new UserAuctionAdapter(UserAuctionListActivity.this, auctionList);
		compList.setAdapter(adapter);

	}
	
	/**
	 * 
	 * 描述：获取列表数据
	 * 
	 * @throws
	 * @date：2013-11-18 下午5:54:26
	 * @version v1.0
	 */
	private void getListData() {
		getParameterList = new ArrayList<Parameter>();
		// 定义两种查询的事件
		final AbTaskItem item1 = new AbTaskItem();
		item1.listener = new AbTaskListener() {

			@Override
			public void update() {
				mAuctionList.clear();
				if (mNewAuctionList != null && mNewAuctionList.size() > 0) {
					mAuctionList.addAll(mNewAuctionList);
					adapter.notifyDataSetChanged();
					mNewAuctionList.clear();
				}
				 compList.stopRefresh();
			}

			@Override
			public void get() {
				try {
					currentPage = 1;
					loadDate();
					mNewAuctionList = new ArrayList<AuctionInfo>();
					mNewAuctionList = PublicInfoWeb.driverInfoList("I_A006", getParameterList);
				} catch (Exception e) {
				}
			};
		};

		final AbTaskItem item2 = new AbTaskItem();
		item2.listener = new AbTaskListener() {

			@Override
			public void update() {
				if (mNewAuctionList != null && mNewAuctionList.size() > 0) {
					mAuctionList.addAll(mNewAuctionList);
					adapter.notifyDataSetChanged();
					mNewAuctionList.clear();
				}
				compList.stopLoadMore();

			}

			@Override
			public void get() {
				try {
					currentPage++;
					loadDate();
					mNewAuctionList = new ArrayList<AuctionInfo>();
					mNewAuctionList = PublicInfoWeb.driverInfoList("I_A006", getParameterList);

				} catch (Exception e) {
					currentPage--;
					mNewAuctionList.clear();
				}
			};
		};

		compList.setAbOnListViewListener(new AbOnListViewListener() {

			@Override
			public void onRefresh() {
				mAbTaskQueue.execute(item1);
			}

			@Override
			public void onLoadMore() {
				mAbTaskQueue.execute(item2);
			}

		});

		// 第一次下载数据
		mAbTaskQueue.execute(item1);
	}

	/**
	 * 
	 * 描述：发布货源信息参数
	 * 
	 * @param string
	 * @param lprId
	 * @throws
	 * @date：2013年12月27日 上午3:37:10
	 * @version v1.0
	 */
	private void setParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		getParameterList.add(parameter);
		// 13895479167
	}
	public void loadDate(){
		setParameterList("goods_info_id", this.carInfoId+"");
//		setParameterList("pageSize", String.valueOf(10));
//		setParameterList("page", String.valueOf(currentPage));

    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBut:
			finish();
			break;
//		case R.id.consultlInfo:
//			startActivity(new Intent(UserAuctionListActivity.this, InfoConsultlActivity.class));
//			break;
		case R.id.publishInfo:
			startActivity(new Intent(UserAuctionListActivity.this, PublicVehicleInfoActivity.class));
			break;
		}

	}
}
