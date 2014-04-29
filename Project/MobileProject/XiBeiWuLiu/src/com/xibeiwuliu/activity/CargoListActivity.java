package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.xibeiwuliu.adapter.CommonalityInfoAdapter;
import com.xibeiwuliu.entity.CargoInfo;
import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.web.PublicInfoWeb;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name��CargoListActivity.java
 * @Describe����Դ��Ϣ
 * @Author: yfr5734@gmail.com
 * @Date��2014��4��28�� ����4:40:30
 * @Version v1.0 *
 * 
 */
public class CargoListActivity extends BaseActivity {
	private MyApplication application = null;
	private AbTaskQueue mAbTaskQueue = null;
	private AbPullListView cargoList = null;
	private List<CargoInfo> mCargoInfoList = null;
	private CommonalityInfoAdapter myListViewAdapter = null;
	private List<Parameter> getParameterList = null;
	private List<CargoInfo> mNewCargoInfoList = null;
	private int currentPage = 1;
	private String getMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.cargo_list);
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, false);
		initView();
		getListData();

	}

	/**
	 * 
	 * @Describe����ʼ��View
	 * @Throws:
	 * @Date��2014��4��29�� ����10:52:17
	 * @Version v1.0
	 * 
	 */
	private void initView() {
		mAbTaskQueue = AbTaskQueue.getInstance();

		// ��ȡListView����
		cargoList = (AbPullListView) findViewById(R.id.cargoList);
		// �򿪹ر�����ˢ�¼��ظ��๦��
		cargoList.setPullRefreshEnable(true);
		cargoList.setPullLoadEnable(true);

		mCargoInfoList = new ArrayList<CargoInfo>();

		// ʹ���Զ����Adapter
		myListViewAdapter = new CommonalityInfoAdapter(CargoListActivity.this, mCargoInfoList);
		cargoList.setAdapter(myListViewAdapter);
		// cargoList.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view, int
		// position, long id) {
		// CargoInfo cargoInfo = (CargoInfo)
		// myListViewAdapter.getItem(position-1);
		// Intent intent = new Intent(CargoListActivity.this,
		// FragmentCargoInfoDetail.class);
		// Bundle bundle = new Bundle();
		// bundle.putSerializable("cargoInfo", cargoInfo);
		// intent.putExtra("bundle",bundle);
		// // int cargoInfoId=
		// Integer.valueOf(mCargoInfoList.get(position).getAdd_id());
		// // intent.putExtra("cargoInfoId", cargoInfoId);
		// startActivity(intent);
		//
		// }
		// });

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
		showDialog(0);

		// �������ֲ�ѯ���¼�
		final AbTaskItem item1 = new AbTaskItem();
		item1.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeDialog(0);
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
	}
}
