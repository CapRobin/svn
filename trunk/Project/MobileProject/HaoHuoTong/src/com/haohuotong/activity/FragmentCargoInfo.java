package com.haohuotong.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.haohuotong.R;
import com.haohuotong.adapter.CommonalityInfoAdapter;
import com.haohuotong.entity.CargoInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.web.PublicInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：FragmentCargoInfo.java
 * @Describe：货源信息
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-11-19 下午7:01:46
 * @Version v1.0
 */
public class FragmentCargoInfo extends Fragment {
	private AbPullListView cargoList = null;
	private AbTaskQueue mAbTaskQueue = null;

	private List<CargoInfo> mCargoInfoList = null;
	private List<CargoInfo> mNewCargoInfoList = null;
	private CommonalityInfoActivity mActivity = null;
	private CommonalityInfoAdapter myListViewAdapter = null;
	private int currentPage = 1;
	private List<Parameter> getParameterList = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mActivity = (CommonalityInfoActivity) getActivity();
		View rootView = inflater.inflate(R.layout.fragment_cargo_info, container, false);
		initView(rootView);

		getListData();
		return rootView;
	}

	/**
	 * 
	 * 描述：初始化
	 * 
	 * @throws
	 * @date：2013-11-18 下午5:52:11
	 * @version v1.0
	 */
	private void initView(View view) {
		mAbTaskQueue = AbTaskQueue.getInstance();

		// 获取ListView对象
		cargoList = (AbPullListView) view.findViewById(R.id.cargoList);

		// 打开关闭下拉刷新加载更多功能
		cargoList.setPullRefreshEnable(true);
		cargoList.setPullLoadEnable(true);

		mCargoInfoList = new ArrayList<CargoInfo>();

		// 使用自定义的Adapter
		myListViewAdapter = new CommonalityInfoAdapter(mActivity, mCargoInfoList);
		cargoList.setAdapter(myListViewAdapter);
		cargoList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				CargoInfo cargoInfo = (CargoInfo) myListViewAdapter.getItem(position-1);
				Intent intent = new Intent(mActivity, FragmentCargoInfoDetail.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("cargoInfo", cargoInfo);
				intent.putExtra("bundle",bundle);
//				int cargoInfoId= Integer.valueOf(mCargoInfoList.get(position).getAdd_id());
//				intent.putExtra("cargoInfoId", cargoInfoId);
				startActivity(intent);
				
			}
		});
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
		mActivity.showDialogMsg(0);

		// 定义两种查询的事件
		final AbTaskItem item1 = new AbTaskItem();
		item1.listener = new AbTaskListener() {

			@Override
			public void update() {
				mActivity.showDialogMsg(1);
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
					setParameterList("UserID", String.valueOf(0));
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
					setParameterList("UserID", String.valueOf(0));
					mNewCargoInfoList = new ArrayList<CargoInfo>();
					mNewCargoInfoList = PublicInfoWeb.cargoInfoList("I_A014", getParameterList);

				} catch (Exception e) {
					currentPage--;
					mNewCargoInfoList.clear();
					mActivity.sendMsgUpdateUI(0, e.getMessage());
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
}
