package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.task.AbThread;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.adapter.AuctionInfoAdapter;
import com.haohuotong.entity.AuctionInfo;
import com.haohuotong.entity.CargoInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.entity.UserInfo;
import com.haohuotong.entity.VehicleInfo;
import com.haohuotong.global.MyApplication;
import com.haohuotong.other.InfoEntity;
import com.haohuotong.web.PublicInfoWeb;
import com.haohuotong.web.UserInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @name：PublicInfoDetailActivity.java
 * @describe：公共信息详情
 * @author Administrator
 * @date：2013-11-12 上午10:40:16
 * @version v1.0
 */
public class FragmentVehicleInfoDetail extends AbActivity implements OnClickListener {
	private TextView info_contextStr, publicTimeStr, phoneStr1, phoneStr2, phoneStr3;
	private RelativeLayout phoneLayout = null;
	private Button phoneBut, addFriendBut, shareInfo, myFriendBut;
	private InfoEntity entitySet = null;
	private MyApplication application = null;
	private List<String> phoneStr = new ArrayList<String>();
	private boolean isClink = false;
	private int phoneSize = 0;
	private int ADDFRIENDINTENT = 0;
	private int SHOWPHONE = 1;
    private VehicleInfo vehicleInfo = null;
	private AlertDialog dialog;
	private AbPullListView listView = null;
	private AbTaskQueue mAbTaskQueue = null;
    private int vehicleInfoId = 0 ;
    private String money ;
    private AuctionInfoAdapter acAdapter = null;
	private ArrayList<Parameter> configurationParameter = null;
	private List<AuctionInfo> mAuctionInfoList = null;
	private List<AuctionInfo> newAuctionInfoList = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.info_detail);

		// 获取Intent传递的数据
//		entitySet = getIntent().getParcelableExtra("DATA");
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		vehicleInfo = (VehicleInfo)bundle.get("vehicleInfo");
		vehicleInfoId = Integer.parseInt(vehicleInfo.getAdd_id());
		Log.i("tag", "------------>vehicleInfoId="+vehicleInfoId);
		initTitleLayout();
//		getPhoneList(entitySet.getMsg_Tel());
		
		//获取数据
		initView();

	}

	
	/**
	 *
	 * @Describe： 获取数据
	 * @Throws 
	 * @Date：2014-1-7 下午3:47:32
	 * @Version v1.0
	 */
	private void getData() {
		configurationParameter = new ArrayList<Parameter>();
		setDdriverCarParameterList("CarResourceID", vehicleInfoId+"");
		
		// 显示进度框
		showProgressDialog();
		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对象
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeProgressDialog();
				if (application.isLogin) {
					startActivity(new Intent(FragmentVehicleInfoDetail.this, MainActivity.class));
				}
				
				configurationParameter = null;
				
			}

			@Override
			public void get() {
				try {
					String userInfo = PublicInfoWeb.vehicleInfoDetail("I_A017", configurationParameter);
					System.out.println(userInfo);
//					if (userInfo != null) {
//						application.isLogin = userInfo.isOk();
//						application.userInfo = userInfo;
//					}
				} catch (AbAppException e) {
					e.printStackTrace();
//					showToastInThread(e.getMessage());
				}
			};
		};
		// 开始执行
		mAbTaskThread.execute(item);
		
	}
	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	// private void initTitleRightLayout() {
	// AbTitleBar mAbTitleBar = this.getTitleBar();
	// mAbTitleBar.setTitleText("信息详情");
	// mAbTitleBar.setLogo(R.drawable.button_selector_back);
	// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
	// mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
	// mAbTitleBar.setLogoLine(R.drawable.line);
	//
	// application = (MyApplication) abApplication;
	//
	// }

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
		mAbTitleBar.setTitleText("信息详情");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// 添加右侧布局文件
		// View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
		View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
		// mAbTitleBar.addRightView(rightViewApp);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		// Button appBtn = (Button)rightViewApp.findViewById(R.id.appBtn);

		about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FragmentVehicleInfoDetail.this, ConsultActivity.class);
				startActivity(intent);
			}
		});
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

		application = (MyApplication) abApplication;
		phoneLayout = (RelativeLayout) findViewById(R.id.layout_3);
		info_contextStr = (TextView) findViewById(R.id.info_contextStr);
		publicTimeStr = (TextView) findViewById(R.id.publicTimeStr);
		phoneStr1 = (TextView) findViewById(R.id.phoneStr1);
		phoneStr2 = (TextView) findViewById(R.id.phoneStr2);
		phoneStr3 = (TextView) findViewById(R.id.phoneStr3);
		phoneBut = (Button) findViewById(R.id.phoneBut);
		addFriendBut = (Button) findViewById(R.id.addFriendBut);
		shareInfo = (Button) findViewById(R.id.shareInfo);
		myFriendBut = (Button) findViewById(R.id.myFriendBut);
		info_contextStr.setText(vehicleInfo.getInfo_connect());
		publicTimeStr.setText(vehicleInfo.getCar_InfoAddTime());

		phoneSize = phoneStr.size();
		if (phoneStr != null && phoneSize > 0) {
			switch (phoneStr.size()) {
			case 1:
				phoneStr1.setText(phoneStr.get(0));
				phoneStr2.setVisibility(View.GONE);
				phoneStr3.setVisibility(View.GONE);
				break;
			case 2:
				phoneStr1.setText(phoneStr.get(0));
				phoneStr2.setText(phoneStr.get(1));
				phoneStr3.setVisibility(View.GONE);
				break;
			case 3:
				phoneStr1.setText(phoneStr.get(0));
				phoneStr2.setText(phoneStr.get(1));
				phoneStr3.setText(phoneStr.get(2));
				break;
			}
		}

		phoneBut.setOnClickListener(this);
		addFriendBut.setOnClickListener(this);
		shareInfo.setOnClickListener(this);
		myFriendBut.setOnClickListener(this);
	}

	/**
	 * 
	 * 描述：获取电话列表
	 * 
	 * @throws
	 * @date：2013-11-18 上午10:25:44
	 * @version v1.0
	 */
	private void getPhoneList(String getStr1) {
		String getStr2 = getStr1 + " ";
		String[] textPhone = getStr2.split(" ");
		// int m = getStr2.length();
		int n = 0;
		for (int j = 0; j < textPhone.length; j++) {
			String itmePhone = textPhone[j];
			phoneStr.add(formatPhone(itmePhone.trim()));

			// if (getStr2.charAt(j) == ' ') {
			// phoneText = getStr2.substring(n, (j + 1)).trim();
			// if (!phoneText.isEmpty()) {
			// phoneStr.add(formatPhone(itmePhone.trim()));
			// n = j;
			// }
			// }
		}
	}

	/**
	 * 
	 * 描述：格式化电话
	 * 
	 * @param phoneStr
	 * @return
	 * @throws
	 * @date：2013-11-18 上午11:56:19
	 * @version v1.0
	 */
	private String formatPhone(String phoneStr) {
		String phoneStrText = null;
		for (int i = 0; i < phoneStr.length(); i++) {
			if (!Character.isDigit(phoneStr.charAt(i))) {
				phoneStrText = phoneStr.substring(0, i) + phoneStr.substring(i + 1);
			}
		}
		if (phoneStrText == null) {
			phoneStrText = phoneStr;
		}
		return phoneStrText;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0:
			if (application.isLogin) {
				Toast.makeText(FragmentVehicleInfoDetail.this, "该模块正在开发中，请稍后...", 5).show();
			}
			break;
		case 1:
			// Toast.makeText(CommonalityInfoDetailActivity.this,
			// "该模块正在开发中，请稍后...", 5).show();

			if (application.isLogin) {
				if (phoneSize > 0) {
					if (isClink) {
						phoneLayout.setVisibility(View.GONE);
						isClink = false;
					} else {
						phoneLayout.setVisibility(View.VISIBLE);
						isClink = true;
					}
				} else {
					Toast.makeText(FragmentVehicleInfoDetail.this, "该订单暂时没有联系电话！", 5).show();
				}
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.phoneBut:
			getData();
			if (application.isLogin) {
				if (phoneSize > 0) {
					if (isClink) {
						phoneLayout.setVisibility(View.GONE);
						isClink = false;
					} else {
						phoneLayout.setVisibility(View.VISIBLE);
						isClink = true;
					}
				} else {
					Toast.makeText(FragmentVehicleInfoDetail.this, "该订单暂时没有联系电话！", 5).show();
				}
			} else {
				Toast.makeText(FragmentVehicleInfoDetail.this, "登录后才能查看联系方式！", 5).show();
				startActivityForResult(new Intent(FragmentVehicleInfoDetail.this, LoginActivity.class), SHOWPHONE);
			}
			break;
		case R.id.addFriendBut:
			// boolean isLogin = application.isLogin;
			if (application.isLogin) {
				Toast.makeText(FragmentVehicleInfoDetail.this, "该模块正在开发中，请稍后...", 5).show();
			} else {
				Toast.makeText(FragmentVehicleInfoDetail.this, "登录后才能添加好友！", 5).show();
				startActivityForResult(new Intent(FragmentVehicleInfoDetail.this, LoginActivity.class), ADDFRIENDINTENT);
			}
			break;
		case R.id.shareInfo:
			startActivity(new Intent(FragmentVehicleInfoDetail.this, ContactListActivity.class));
			break;
		case R.id.myFriendBut:
			
//			Toast.makeText(CommonalityInfoDetailActivity.this, "我的好友列表", 5).show();
			if (application.isLogin) {
//			       showMyDialog();
			}else{
				Toast.makeText(FragmentVehicleInfoDetail.this, "请先登录！", 5).show();
			}
			break;
		}
	}
	
	/*
	 * 竞价窗口
	 
	
	private void showMyDialog() {
//		String titleInfo = null;
		Builder builder = new AlertDialog.Builder(this);
		dialog = builder.create();
		View retieve = LayoutInflater.from(this).inflate(R.layout.dialog_show_comp, null);
		dialog.setView(retieve, 0, 0, 0, 0);
		Button acceptBtn = (Button) retieve.findViewById(R.id.acceptBtn);
		Button unAcceptBtn = (Button) retieve.findViewById(R.id.unAcceptBtn);
		TextView dialogTitleText1 = (TextView) retieve.findViewById(R.id.dialogTitleText1);
		final EditText comp = (EditText) retieve.findViewById(R.id.comptete);
		mAbTaskQueue = AbTaskQueue.getInstance();
		listView = (AbPullListView) retieve.findViewById(R.id.listView);
		comp.setFocusable(true);
		dialogTitleText1.setText("请输入运价");
		((InputMethodManager)comp.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(comp, 0);;  
//		inManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  


//		final AuctionInfo info = new AuctionInfo();
		acceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				money = comp.getText().toString().trim();
				viewList();
				upload();
				comp.setText("");
				//隐藏输入法
				final InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);       
				imm.hideSoftInputFromWindow(comp.getWindowToken(), 0);
			}
		});

		unAcceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	*/
	/*
	 * 显示竞价列表
	 */
	public void viewList(){
		mAuctionInfoList = new ArrayList<AuctionInfo>();
		acAdapter = new AuctionInfoAdapter(this,mAuctionInfoList);
		listView.setAdapter(acAdapter);
        this.listView.setPullRefreshEnable(true);
        this.listView.setPullLoadEnable(true);
	}
	
	
	/*
	 * 加载竞价信息
	 */
	
	
	public void upload(){
		application = (MyApplication) abApplication;
		configurationParameter = new ArrayList<Parameter>();
		setDdriverCarParameterList("vehicle_info_id", vehicleInfoId+"");
		setDdriverCarParameterList("User_id", application.userInfo.getDriverUserInfo().getDuser_id()+"");
		setDdriverCarParameterList("Car_ID", application.userInfo.getDriverCarInfo().getAdd_id());
		setDdriverCarParameterList("Money_value",money );
		setDdriverCarParameterList("JP_remark", " ");

		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对象
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			//@Override
			public void update() {
				mAuctionInfoList.clear();
				if (newAuctionInfoList != null && newAuctionInfoList.size() > 0) {
					mAuctionInfoList.addAll(newAuctionInfoList);
					acAdapter.notifyDataSetChanged();
					newAuctionInfoList.clear();
				}
				listView.stopRefresh();
			}

			@Override
			public void get() {
				try {
					newAuctionInfoList = new ArrayList<AuctionInfo>();
					newAuctionInfoList = PublicInfoWeb.driverInfoList("I_A008", configurationParameter);

				} catch (AbAppException e) {
					e.printStackTrace();
				}
			};
		};
		listView.setAbOnListViewListener(new AbOnListViewListener() {

			@Override
			public void onRefresh() {
				mAbTaskQueue.execute(item);
			}

			@Override
			public void onLoadMore() {
				mAbTaskQueue.execute(item);
			}

		});
		// 开始执行
		mAbTaskThread.execute(item);
		
	}

    private void setDdriverCarParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		configurationParameter.add(parameter);
    }
}
