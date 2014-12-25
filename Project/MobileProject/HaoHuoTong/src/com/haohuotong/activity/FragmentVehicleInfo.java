package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.haohuotong.adapter.VehicleInfoInfoAdapter;
import com.haohuotong.entity.CargoInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.entity.VehicleInfo;
import com.haohuotong.other.Config;
import com.haohuotong.other.EntitySet;
import com.haohuotong.other.InfoEntity;
import com.haohuotong.other.UserEntity;
import com.haohuotong.web.PublicInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name��FragmentLorryInfo.java
 * @Describe����Դ��Ϣ
 * @Author Administrator yfr5734@gmail.com
 * @Date��2013-11-21 ����5:18:33
 * @Version v1.0
 */
public class FragmentVehicleInfo extends Fragment {
	private AbPullListView cargoList = null;
	private AbTaskQueue mAbTaskQueue = null;
	private CommonalityInfoActivity mActivity = null;
	private EntitySet mNewList = null;
	private VehicleInfoInfoAdapter myListViewAdapter = null;
	private String mCity = "";
	private String mKeywords = "";
	private InfoEntity.Type mType = InfoEntity.Type.All;
	private EntitySet mEntitySet = null;
	private PublicInfoWeb infoWeb = null;
	private int currentPage = 1;
	private List<Parameter> getParameterList = null;
	private List<VehicleInfo> mVehicleInfoList = null;
	private List<VehicleInfo> mNewVehicleInfoList = null;
//	private Button backBut, publishInfo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mActivity = (CommonalityInfoActivity) getActivity();
		View rootView = inflater.inflate(R.layout.fragment_lorry_info, container, false);
		initView(rootView);
		getListData();
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
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
		cargoList = (AbPullListView) view.findViewById(R.id.lorryList);
//		backBut = (Button) view.findViewById(R.id.backBut);
//		publishInfo = (Button) view.findViewById(R.id.publishInfo);

//		mActivity.backBut.setOnClickListener(this);
//		mActivity.publishInfo.setOnClickListener(this);

		// �򿪹ر�����ˢ�¼��ظ��๦��
		cargoList.setPullRefreshEnable(true);
		cargoList.setPullLoadEnable(true);
		
		mVehicleInfoList = new ArrayList<VehicleInfo>();
		// ʹ���Զ����Adapter
		myListViewAdapter = new VehicleInfoInfoAdapter(mActivity, mVehicleInfoList);
		cargoList.setAdapter(myListViewAdapter);
		// item������¼�

		cargoList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					VehicleInfo vehicleInfo = (VehicleInfo) myListViewAdapter.getItem(position-1);
					Intent intent = new Intent(mActivity, FragmentVehicleInfoDetail.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("vehicleInfo", vehicleInfo);
					intent.putExtra("bundle",bundle);
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
	 * ������������Ϣ����
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
