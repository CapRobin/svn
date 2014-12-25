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
 * @Name��MyPublicInfoActivity.java
 * @Describe���û���������Ϣ�б�
 * @Author FaRong����yfr5734@gmail.com
 * @Date��2013-12-13 ����11:12:33
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
	 * ��������ʼ��������
	 * 
	 * @throws
	 * @date��2013-4-25 ����10:21:18
	 * @version v1.0
	 */
	private void initTitleLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();

		// �����಼���ļ�
		mAbTitleBar.setTitleText("�ҵĻ�Դ��Ϣ");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		/*// ����Ҳ಼���ļ�
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
	 * ��������ʼ��
	 * 
	 * @throws
	 * @date��2013-11-18 ����5:52:11
	 * @version v1.0
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
	 * ��������ȡ�б�����
	 * 
	 * @throws
	 * @date��2013-11-18 ����5:54:26
	 * @version v1.0
	 */
	private void getListData() {
		getParameterList = new ArrayList<Parameter>();

		// �������ֲ�ѯ���¼�
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




	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
