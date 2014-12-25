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
 * @Name��FragmentCargoInfo.java
 * @Describe����Դ��Ϣ
 * @Author Administrator yfr5734@gmail.com
 * @Date��2013-11-19 ����7:01:46
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
	 * ��������ʼ��
	 * 
	 * @throws
	 * @date��2013-11-18 ����5:52:11
	 * @version v1.0
	 */
	private void initView(View view) {
		mAbTaskQueue = AbTaskQueue.getInstance();

		// ��ȡListView����
		cargoList = (AbPullListView) view.findViewById(R.id.cargoList);

		// �򿪹ر�����ˢ�¼��ظ��๦��
		cargoList.setPullRefreshEnable(true);
		cargoList.setPullLoadEnable(true);

		mCargoInfoList = new ArrayList<CargoInfo>();

		// ʹ���Զ����Adapter
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
	 * ��������ȡ�б�����
	 * 
	 * @throws
	 * @date��2013-11-18 ����5:54:26
	 * @version v1.0
	 */
	private void getListData() {
		getParameterList = new ArrayList<Parameter>();
		mActivity.showDialogMsg(0);

		// �������ֲ�ѯ���¼�
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

		// ��һ����������
		mAbTaskQueue.execute(item1);
	}

	/**
	 * 
	 * ������������Դ��Ϣ����
	 * 
	 * @param string
	 * @param lprId
	 * @throws
	 * @date��2013��12��27�� ����3:37:10
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
