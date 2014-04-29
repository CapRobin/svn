package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.TextView;

import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.xibeiwuliu.adapter.VehicleInfoInfoAdapter;
import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.entity.VehicleInfo;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.web.PublicInfoWeb;

/**
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：LorryListActivity.java
 * @Describe：车源信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午4:42:04
 * @Version v1.0 *
 * 
 */
public class VehicleListActivity extends BaseActivity {
	private MyApplication application = null;
	private String getMsg;
	private boolean isShowRightBut = false; // 是否显示右边按钮
	private AbTaskQueue mAbTaskQueue = null;
	private AbPullListView cargoList = null;
	private List<VehicleInfo> mVehicleInfoList = null;
	private VehicleInfoInfoAdapter myListViewAdapter = null;
	private int currentPage = 1;
	private List<Parameter> getParameterList = null;
	private List<VehicleInfo> mNewVehicleInfoList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.lorry_list);
		application = (MyApplication) abApplication;
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
		initView();
		getListData();
	}

	/**
	 * 
	 * 描述：初始化View
	 * 
	 * @throws
	 * @date：2013-11-13 上午10:21:24
	 * @version v1.0
	 */
	private void initView() {
		mAbTaskQueue = AbTaskQueue.getInstance();

		// 获取ListView对象
		cargoList = (AbPullListView) findViewById(R.id.lorryList);
		// 打开关闭下拉刷新加载更多功能
		cargoList.setPullRefreshEnable(true);
		cargoList.setPullLoadEnable(true);

		mVehicleInfoList = new ArrayList<VehicleInfo>();
		// 使用自定义的Adapter
		myListViewAdapter = new VehicleInfoInfoAdapter(VehicleListActivity.this, mVehicleInfoList);
		cargoList.setAdapter(myListViewAdapter);
		// item被点击事件

		// cargoList.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view, int
		// position, long id) {
		// VehicleInfo vehicleInfo = (VehicleInfo)
		// myListViewAdapter.getItem(position-1);
		// Intent intent = new Intent(LorryListActivity.this,
		// FragmentVehicleInfoDetail.class);
		// Bundle bundle = new Bundle();
		// bundle.putSerializable("vehicleInfo", vehicleInfo);
		// intent.putExtra("bundle",bundle);
		// startActivity(intent);
		// }
		// });

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
		showDialog(0);

		// 定义两种查询的事件
		final AbTaskItem item1 = new AbTaskItem();
		item1.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeDialog(0);
				;
				mVehicleInfoList.clear();
				if (mNewVehicleInfoList != null && mNewVehicleInfoList.size() > 0) {
					mVehicleInfoList.addAll(mNewVehicleInfoList);
					myListViewAdapter.notifyDataSetChanged();
					mNewVehicleInfoList.clear();
				}
				cargoList.stopRefresh();

			}

			@Override
			public void get() {
				try {
					currentPage = 1;

					setParameterList("Car_Bway", "");
					setParameterList("Car_Eway", "");
					setParameterList("pageSize", String.valueOf(10));
					setParameterList("page", String.valueOf(currentPage));

					mNewVehicleInfoList = new ArrayList<VehicleInfo>();
					mNewVehicleInfoList = PublicInfoWeb.vehicleInfoList("I_A016", getParameterList);
				} catch (Exception e) {
				}
			};
		};

		final AbTaskItem item2 = new AbTaskItem();
		item2.listener = new AbTaskListener() {

			@Override
			public void update() {
				if (mNewVehicleInfoList != null && mNewVehicleInfoList.size() > 0) {
					mVehicleInfoList.addAll(mNewVehicleInfoList);
					myListViewAdapter.notifyDataSetChanged();
					mNewVehicleInfoList.clear();
				}
				cargoList.stopLoadMore();

			}

			@Override
			public void get() {
				try {
					currentPage++;

					setParameterList("Car_Bway", "");
					setParameterList("Car_Eway", "");
					setParameterList("pageSize", String.valueOf(10));
					setParameterList("page", String.valueOf(currentPage));

					mNewVehicleInfoList = new ArrayList<VehicleInfo>();
					mNewVehicleInfoList = PublicInfoWeb.vehicleInfoList("I_A016", getParameterList);

				} catch (Exception e) {
					currentPage--;
					mNewVehicleInfoList.clear();
					sendMsgUpdateUI(0, e.getMessage());
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
	 * 描述：设置信息参数
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
}
