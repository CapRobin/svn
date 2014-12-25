package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.entity.LogisticsCompanyInfo;
import com.haohuotong.entity.LogisticsUserInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.entity.UserInfo;
import com.haohuotong.global.MyApplication;
import com.haohuotong.web.UserInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：PersonalLogisticsInfoActivity.java
 * @Describe：物流用户信息
 * @Author yufarong_yfr5734@163.com
 * @Date：2013-12-23 下午5:33:15
 * @Version v1.0
 */
public class PersonalLogisticsInfoActivity extends AbActivity implements OnClickListener {
	private MyApplication application = null;
	private EditText userAccountEdit, realNameEdit, userSexEdit, userPostEdit, userMobileEdit, companyNameEdit;
	private EditText companyAddressEdit, specialPlaneEdit, shopCardEdit, enterpriseNumberEdit, enterpriseReputationEdit;
	private ImageView docImage = null;
	private Button updateSubmit = null;
	private List<Parameter> logisticsCcountParameterList = null;
	private List<Parameter> logisticsCompanyParameterList = null;
	private LogisticsUserInfo logisticsCcountInfo = null;
	private LogisticsCompanyInfo logisticsCompanyInfo = null;
	private boolean isUpDriverdate = false;
//	svn://218.95.143.162/svn/work
	private boolean isUCarpdate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		application = (MyApplication) abApplication;
		setAbContentView(R.layout.user_logistics_info);

		initTitleRightLayout();
		initView();

	}

	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initTitleRightLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText("物流公司信息");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
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

		docImage = (ImageView) findViewById(R.id.docImage);
		updateSubmit = (Button) findViewById(R.id.updateSubmit);

		userAccountEdit = (EditText) findViewById(R.id.userAccountEdit);
		realNameEdit = (EditText) findViewById(R.id.realNameEdit);
		userSexEdit = (EditText) findViewById(R.id.userSexEdit);
		userPostEdit = (EditText) findViewById(R.id.userPostEdit);
		userMobileEdit = (EditText) findViewById(R.id.userMobileEdit);
		companyNameEdit = (EditText) findViewById(R.id.companyNameEdit);
		companyAddressEdit = (EditText) findViewById(R.id.companyAddressEdit);
		specialPlaneEdit = (EditText) findViewById(R.id.specialPlaneEdit);
		shopCardEdit = (EditText) findViewById(R.id.shopCardEdit);
		enterpriseNumberEdit = (EditText) findViewById(R.id.enterpriseNumberEdit);
		enterpriseReputationEdit = (EditText) findViewById(R.id.enterpriseReputationEdit);

		UserInfo info = application.userInfo;
		if (info != null) {
			userAccountEdit.setText(info.getLogisticsUserInfo().getUserloginname());
			realNameEdit.setText(info.getLogisticsUserInfo().getUserTname());
			userSexEdit.setText(info.getLogisticsUserInfo().getUserSex());
			userPostEdit.setText(info.getLogisticsUserInfo().getUserPost());
			userMobileEdit.setText(info.getLogisticsUserInfo().getUserMoble());
			companyNameEdit.setText(info.getLogisticsCompanyInfo().getLPR_name());
			companyAddressEdit.setText(info.getLogisticsCompanyInfo().getLPR_addr());
			specialPlaneEdit.setText(info.getLogisticsCompanyInfo().getLPR_tle());
			shopCardEdit.setText(info.getLogisticsCompanyInfo().getLPR_YID());
			enterpriseNumberEdit.setText(info.getLogisticsCompanyInfo().getLPR_ID());
			enterpriseReputationEdit.setText(info.getLogisticsCompanyInfo().getLPR_Level());

		}

		docImage.setOnClickListener(this);
		updateSubmit.setOnClickListener(this);
	}

	/**
	 * 
	 * @Describe：设置物流公司账户参数
	 * @param key
	 * @param value
	 * @Throws
	 * @Date：2013-12-24 下午5:11:54
	 * @Version v1.0
	 */
	private void setLogisticsCcountParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		logisticsCcountParameterList.add(parameter);
	}

	/**
	 * 
	 * @Describe：设置物流公司参数
	 * @param key
	 * @param value
	 * @Throws
	 * @Date：2013-12-24 下午5:11:21
	 * @Version v1.0
	 */
	private void setLogisticsCompanyParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		logisticsCompanyParameterList.add(parameter);
	}

	/**
	 * 
	 * @Describe：修改物流公司账户信息
	 * @Throws
	 * @Date：2013-12-23 下午1:53:08
	 * @Version v1.0
	 */
	private void updateLogisticsacCcountInfo() {
		logisticsCcountParameterList = new ArrayList<Parameter>();

		String userAccountStr = userAccountEdit.getText().toString();
		String realNameStr = realNameEdit.getText().toString();
		String userSexStr = userSexEdit.getText().toString();
		String userPostStr = userPostEdit.getText().toString();
		String userMobileStr = userMobileEdit.getText().toString();

		logisticsCcountInfo = new LogisticsUserInfo();
		LogisticsUserInfo logisticsUserInfoApp = new LogisticsUserInfo();
		logisticsUserInfoApp = application.userInfo.getLogisticsUserInfo();

		logisticsCcountInfo.setAdd_id(logisticsUserInfoApp.getAdd_id());
		logisticsCcountInfo.setLPRId(logisticsUserInfoApp.getLPRId());
		logisticsCcountInfo.setUserlogin_id(logisticsUserInfoApp.getUserlogin_id());
		logisticsCcountInfo.setUserloginname(userAccountStr);
		logisticsCcountInfo.setUserloginPwd(logisticsUserInfoApp.getUserloginPwd());
		logisticsCcountInfo.setUserTname(realNameStr);
		logisticsCcountInfo.setUserSex(userSexStr);
		logisticsCcountInfo.setUserPost(userPostStr);
		logisticsCcountInfo.setUserMoble(userMobileStr);
		logisticsCcountInfo.setUserPhoto(logisticsUserInfoApp.getUserPhoto());

		setLogisticsCcountParameterList("userlogin_id", logisticsCcountInfo.getUserlogin_id());
		setLogisticsCcountParameterList("userTname", logisticsCcountInfo.getUserTname());
		setLogisticsCcountParameterList("userSex", logisticsCcountInfo.getUserSex());
		setLogisticsCcountParameterList("userPost", logisticsCcountInfo.getUserPost());
		setLogisticsCcountParameterList("UserMoble", logisticsCcountInfo.getUserMoble());
		setLogisticsCcountParameterList("UserPhoto", logisticsCcountInfo.getUserPhoto());
	}

	/**
	 * 
	 * @Describe：修改物流公司信息
	 * @Throws
	 * @Date：2013-12-23 下午1:57:20
	 * @Version v1.0
	 */
	private void updateLogisticsCompanyInfo() {
		logisticsCompanyParameterList = new ArrayList<Parameter>();

		String companyNameStr = companyNameEdit.getText().toString();
		String companyAddressStr = companyAddressEdit.getText().toString();
		String specialPlaneStr = specialPlaneEdit.getText().toString();
		String shopCardStr = shopCardEdit.getText().toString();
		String enterpriseNumberStr = enterpriseNumberEdit.getText().toString();
		String enterpriseReputationStr = enterpriseReputationEdit.getText().toString();

		logisticsCompanyInfo = new LogisticsCompanyInfo();
		LogisticsCompanyInfo logisticsCompanyInfoApp = new LogisticsCompanyInfo();
		logisticsCompanyInfoApp = application.userInfo.getLogisticsCompanyInfo();

		logisticsCompanyInfo.setAdd_id(logisticsCompanyInfoApp.getAdd_id());
		logisticsCompanyInfo.setLPR_name(companyNameStr);
		logisticsCompanyInfo.setLPR_addr(companyAddressStr);
		logisticsCompanyInfo.setLPR_tle(specialPlaneStr);
		logisticsCompanyInfo.setLPR_YID(shopCardStr);
		logisticsCompanyInfo.setLPR_ID(enterpriseNumberStr);
		logisticsCompanyInfo.setLPR_Level(enterpriseReputationStr);
		logisticsCompanyInfo.setAdd_time(logisticsCompanyInfoApp.getAdd_time());

		setLogisticsCompanyParameterList("Add_id", logisticsCompanyInfo.getAdd_id());
		setLogisticsCompanyParameterList("LPR_name", logisticsCompanyInfo.getLPR_name());
		setLogisticsCompanyParameterList("LPR_addr", logisticsCompanyInfo.getLPR_addr());
		setLogisticsCompanyParameterList("LPR_tle", logisticsCompanyInfo.getLPR_tle());
		setLogisticsCompanyParameterList("LPR_YID", logisticsCompanyInfo.getLPR_YID());
		setLogisticsCompanyParameterList("LPR_ID", logisticsCompanyInfo.getLPR_ID());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.docImage:
			Intent intent = new Intent(PersonalLogisticsInfoActivity.this, PhotoActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.updateSubmit:
			// 修改物流公司账号信息
			updateLogisticsacCcountInfo();
			// 修改物流公司用户信息信息
			updateLogisticsCompanyInfo();

			showProgressDialog();
			AbThread mAbTaskThread = new AbThread();
			// 定义异步执行的对象
			final AbTaskItem item = new AbTaskItem();
			item.listener = new AbTaskListener() {

				@Override
				public void update() {
					removeProgressDialog();
					if (isUpDriverdate && isUCarpdate) {
						if (logisticsCcountInfo != null && logisticsCompanyInfo != null) {
							application.userInfo.setLogisticsUserInfo(logisticsCcountInfo);
							application.userInfo.setLogisticsCompanyInfo(logisticsCompanyInfo);
							Toast.makeText(PersonalLogisticsInfoActivity.this, "修改成功！", 5).show();
							finish();
						}
					}
				}

				@Override
				public void get() {
					try {
						isUpDriverdate = UserInfoWeb.updateLogisticsCcountInfo("I_A013", logisticsCcountParameterList);
						isUCarpdate = UserInfoWeb.updateLogisticsCompanyInfo("I_A012", logisticsCompanyParameterList);
					} catch (AbAppException e) {
						e.printStackTrace();
						showToastInThread(e.getMessage());
					}
				};
			};
			// 开始执行
			mAbTaskThread.execute(item);
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
