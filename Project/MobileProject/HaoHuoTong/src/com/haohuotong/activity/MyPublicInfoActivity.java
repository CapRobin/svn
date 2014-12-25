package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.ab.activity.AbActivity;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.adapter.CommonalityInfoAdapter;
import com.haohuotong.entity.CargoInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.global.MyApplication;
import com.haohuotong.web.PublicInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：MyPublicInfoActivity.java
 * @Describe：用户发布的信息列表
 * @Author FaRong——yfr5734@gmail.com
 * @Date：2013-12-13 上午11:12:33
 * @Version v1.0
 */
public class MyPublicInfoActivity extends AbActivity implements OnClickListener {
	

	private MyApplication application = null;
	private AbPullListView cargoList = null;
	private AbTaskQueue mAbTaskQueue = null;

	private List<CargoInfo> mCargoInfoList = null;
	private List<CargoInfo> mNewCargoInfoList = null;
	private CommonalityInfoAdapter myListViewAdapter = null;
	private int currentPage = 1;
	private List<Parameter> getParameterList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.my_cargo_info);
		application = (MyApplication) abApplication;
		initTitleLayout();
		initView();
		
	}
	
	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initTitleLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();

		// 添加左侧布局文件
		mAbTitleBar.setTitleText("我的货源信息");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		/*// 添加右侧布局文件
		View rightViewMore = mInflater.inflate(R.layout.more_btn1, null);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AuctionListActivity.this, DriverAuctionListActivity.class);
				startActivity(intent);
			}
		});*/
	}
	
	/**
	 * 
	 * 描述：初始化
	 * 
	 * @throws
	 * @date：2013-11-18 下午5:52:11
	 * @version v1.0
	 */
	private void initView() {
		mAbTaskQueue = AbTaskQueue.getInstance();
		// 获取ListView对象
		cargoList = (AbPullListView) findViewById(R.id.cargoList);
		// 打开关闭下拉刷新加载更多功能
		cargoList.setPullRefreshEnable(true);
		cargoList.setPullLoadEnable(true);
		mCargoInfoList = new ArrayList<CargoInfo>();
		// 使用自定义的Adapter
		myListViewAdapter = new CommonalityInfoAdapter(MyPublicInfoActivity.this, mCargoInfoList);
		cargoList.setAdapter(myListViewAdapter);
		cargoList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				CargoInfo cargoInfo = (CargoInfo) myListViewAdapter.getItem(position-1);
				Intent intent = new Intent(MyPublicInfoActivity.this, MyPublicInfoDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("cargoInfo", cargoInfo);
				intent.putExtra("bundle",bundle);
				startActivity(intent);
				
			}
		});
		getListData();
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
//				mActivity.showDialogMsg(1);
				mCargoInfoList.clear();
				if (mNewCargoInfoList != null && mNewCargoInfoList.size() > 0) {
					mCargoInfoList.addAll(mNewCargoInfoList);
					myListViewAdapter.notifyDataSetChanged();
					mNewCargoInfoList.clear();
				}
				cargoList.stopRefresh();

			}

			@Override
			public void get() {
				try {
					currentPage = 1;
					setParameterList("Info_Status", "1");
					setParameterList("pageSize", String.valueOf(10));
					setParameterList("page", String.valueOf(currentPage));
//					setParameterList("UserID", application.userInfo.getLogisticsCompanyInfo().getAdd_id());
					setParameterList("UserID", String.valueOf(15));
					mNewCargoInfoList = new ArrayList<CargoInfo>();
					mNewCargoInfoList = PublicInfoWeb.cargoInfoList("I_A014", getParameterList);
				} catch (Exception e) {
				}
			};
		};

		final AbTaskItem item2 = new AbTaskItem();
		item2.listener = new AbTaskListener() {

			@Override
			public void update() {
				if (mNewCargoInfoList != null && mNewCargoInfoList.size() > 0) {
					mCargoInfoList.addAll(mNewCargoInfoList);
					myListViewAdapter.notifyDataSetChanged();
					mNewCargoInfoList.clear();
				}
				cargoList.stopLoadMore();

			}

			@Override
			public void get() {
				try {
					currentPage++;
					setParameterList("Info_Status", "1");
					setParameterList("pageSize", String.valueOf(10));
					setParameterList("page", String.valueOf(currentPage));
//					setParameterList("UserID", application.userInfo.getLogisticsCompanyInfo().getAdd_id());
					setParameterList("UserID", String.valueOf(15));
					mNewCargoInfoList = new ArrayList<CargoInfo>();
					mNewCargoInfoList = PublicInfoWeb.cargoInfoList("I_A014", getParameterList);

				} catch (Exception e) {
					currentPage--;
					mNewCargoInfoList.clear();
				}
			};
		};

		cargoList.setAbOnListViewListener(new AbOnListViewListener() {

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




	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
