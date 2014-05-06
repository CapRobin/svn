package com.xibeiwuliu.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.other.LocationSelector;

/**
 * 
 * Copyright (c) 2012 All rights reserved 名称：PublicCargoInfoActivity.java
 * 描述：发布货源信息
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date：2013年12月27日 上午4:36:12
 * @version v1.0
 */
public class PublicActivity extends AbActivity implements OnItemSelectedListener {

	private MyApplication application = null;
	private LocationSelector mLocationStartArea, mLocationStopArea = null;
//	private InfoEntity.Type mType;
	// private EditText hwmcEdit, hzxmEdit, zcsjEdit, bzxsEdit, sfqhEdit,
	// sfpdEdit, sfyyEdit, lxrEdit, zhEdit, khhEdit, lxdhEdit ;
	private EditText zltjEdit, ysjlEdit, ysfyEdit, qcslEdit;
	private EditText jsfsEdit, fwfyEdit, bcsmEdit;
	private EditText zxsjEdit, ddsjEdit;
	private List<Parameter> cargoParameterList = null;

	// private CargoInfo cargoInfo = null;
	private boolean isPublic = false;
	private Spinner hwmcSpinner, hwlxSpinner, zltjSpinner, qclxSpinner;
	private String cargoName = null;
	private String cargoType = null;
	private String volumeUnit = null;
	private String carType = null;

	private Spinner mCargoType;
	private Spinner mCargoWeightUnit;
	private Spinner mTruckType;
	private EditText mCargoWeight;
	private EditText mTruckLength;
	private EditText mMemo;
	// SimpleDateFormat formatter = new SimpleDateFormat
	// ("yyyy年MM月dd日   HH:mm:ss     ");
	private EditText mTruckCapacity;
	private EditText mTruckPlate;
	private Button publicInfoBut = null;
	private ImageView zhdzImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setAbContentView(R.layout.public_cargo_info_x);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
		mAbTitleBar.setTitleText("发布货源信息");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
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
//				Intent intent = new Intent(PublicCargoInfoActivity.this, MyPublicInfoActivity.class);
//				startActivity(intent);
			}
		});

		application = (MyApplication) abApplication;

	}

	/**
	 * 
	 * 描述：初始化View
	 * 
	 * @throws
	 * @date：2013-11-13 上午11:16:27
	 * @version v1.0
	 */
	private void initView() {
		FragmentManager fm = this.getSupportFragmentManager();
		mLocationStartArea = (LocationSelector) fm.findFragmentById(R.id.fragment_start_area);
		mLocationStopArea = (LocationSelector) fm.findFragmentById(R.id.fragment_stop_area);
		zhdzImage = (ImageView) findViewById(R.id.zhdzImage);

		// String startArea = this.mLocationStartArea.getLocationString();
		// String stopArea = this.mLocationStopArea.getLocationString();
		// System.out.println("startArea is "+startArea);
		// System.out.println("stopArea is "+stopArea);

		publicInfoBut = (Button) findViewById(R.id.publicInfoBut);

		// hwmcEdit = (EditText) findViewById(R.id.hwmcEdit);
		// hzxmEdit = (EditText) findViewById(R.id.hzxmEdit);
		zltjEdit = (EditText) findViewById(R.id.zltjEdit);
		ysjlEdit = (EditText) findViewById(R.id.ysjlEdit);
		ysfyEdit = (EditText) findViewById(R.id.ysfyEdit);
		qcslEdit = (EditText) findViewById(R.id.qcslEdit);

		hwmcSpinner = (Spinner) findViewById(R.id.hwmcSpinner);
		hwlxSpinner = (Spinner) findViewById(R.id.hwlxSpinner);
		zltjSpinner = (Spinner) findViewById(R.id.zltjSpinner);
		qclxSpinner = (Spinner) findViewById(R.id.qclxSpinner);
		// zcsjEdit = (EditText) findViewById(R.id.zcsjEdit);

		jsfsEdit = (EditText) findViewById(R.id.jsfsEdit);
		fwfyEdit = (EditText) findViewById(R.id.fwfyEdit);
		// bzxsEdit = (EditText) findViewById(R.id.bzxsEdit);
		// sfqhEdit = (EditText) findViewById(R.id.sfqhEdit);
		// sfpdEdit = (EditText) findViewById(R.id.sfpdEdit);
		bcsmEdit = (EditText) findViewById(R.id.bcsmEdit);

		zxsjEdit = (EditText) findViewById(R.id.zxsjEdit);
		ddsjEdit = (EditText) findViewById(R.id.ddsjEdit);
		// sfyyEdit = (EditText) findViewById(R.id.sfyyEdit);
		// lxdhEdit = (EditText) findViewById(R.id.lxdhEdit);
		// lxrEdit = (EditText) findViewById(R.id.lxrEdit);
		// zhEdit = (EditText) findViewById(R.id.zhEdit);
		// khhEdit = (EditText) findViewById(R.id.khhEdit);

//		publicInfoBut.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startSubmit();
//
//				showProgressDialog();
//				AbThread mAbTaskThread = new AbThread();
//				// 定义异步执行的对象
//				final AbTaskItem item = new AbTaskItem();
//				item.listener = new AbTaskListener() {
//
//					@Override
//					public void update() {
//						removeProgressDialog();
//						if (isPublic) {
//							Toast.makeText(PublicCargoInfoActivity.this, "信息发布成功成功！", 5).show();
//							finish();
//						}
//					}
//
//					@Override
//					public void get() {
//						try {
//							isPublic = PublicInfoWeb.publicCargoInfo("I_A003", cargoParameterList);
//						} catch (AbAppException e) {
//							e.printStackTrace();
//							showToastInThread(e.getMessage());
//						}
//					};
//				};
//				mAbTaskThread.execute(item);
//
//			}
//		});
		
//		zhdzImage.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(PublicCargoInfoActivity.this, MapActivity.class));
//			}
//		});

		// 为Spinner赋值
		// cargoName = hwmcSpinner.getSelectedItem().toString().toString();
		// cargoType = hwlxSpinner.getSelectedItem().toString().toString();
		// volumeUnit = zltjSpinner.getSelectedItem().toString().toString();
		// carType = qclxSpinner.getSelectedItem().toString().toString();

		hwmcSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				cargoName = hwmcSpinner.getSelectedItem().toString().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		hwlxSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				cargoType = hwlxSpinner.getSelectedItem().toString().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		zltjSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				volumeUnit = zltjSpinner.getSelectedItem().toString().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		qclxSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				carType = qclxSpinner.getSelectedItem().toString().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

	}

//	private void startSubmit() {
//		cargoParameterList = new ArrayList<Parameter>();
//
//		StringBuilder infoMsg = new StringBuilder();
//
//		// String hwmcEditStr = hwmcEdit.getText().toString();
//		// String hzxmEditStr = hzxmEdit.getText().toString();
//		String zltjEditStr = zltjEdit.getText().toString();
//		String ysjlEditStr = ysjlEdit.getText().toString();
//		String ysfyEditStr = ysfyEdit.getText().toString();
//		String qcslEditStr = qcslEdit.getText().toString();
//		// String zcsjEditStr = zcsjEdit.getText().toString();
//		String jsfsEditStr = jsfsEdit.getText().toString();
//		String fwfyEditStr = fwfyEdit.getText().toString();
//		// String bzxsEditStr = bzxsEdit.getText().toString();
//		// String sfqhEditStr = sfqhEdit.getText().toString();
//		// String sfpdEditStr = sfpdEdit.getText().toString();
//		String bcsmEditStr = bcsmEdit.getText().toString();
//		String zxsjEditStr = zxsjEdit.getText().toString();
//		String ddsjEditStr = ddsjEdit.getText().toString();
//		// String sfyyEditStr = sfyyEdit.getText().toString();
//		// String lxdhEditStr = lxdhEdit.getText().toString();
//		// String lxrEditStr = lxrEdit.getText().toString();
//		// String zhEditStr = zhEdit.getText().toString();
//		// String khhEditStr = khhEdit.getText().toString();
//
//		// cargoInfo = new CargoInfo();
//		// UserInfo userinfo = new UserInfo();
////		UserInfo userinfo = application.userInfo;
//
//		// cargoInfo.setLPR_ID(userinfo.getAdd_id());
//		// cargoInfo.setInfo_connect(userinfo.getDuser_id());
//		// cargoInfo.setInfo_AddErID(carNumStr);
//		// cargoInfo.setInfo_AddErname(cartypeStr);
//		// cargoInfo.setInfo_AddErname(cartypeStr);
//
//		String startArea = mLocationStartArea.getLocationString();
//		String stopArea = mLocationStopArea.getLocationString();
//
//		// String string = startArea + "到" + stopArea + "you";
//
//		infoMsg.append(startArea);
//		infoMsg.append("到");
//		infoMsg.append(stopArea + "  ");
//		infoMsg.append("有");
//		infoMsg.append(cargoType);
//		infoMsg.append(cargoName);
//		infoMsg.append(zltjEditStr);
//		infoMsg.append(volumeUnit + "  ");
//
//		infoMsg.append("求");
//		infoMsg.append(qcslEditStr);
//		infoMsg.append("辆");
//		infoMsg.append(carType);
//		infoMsg.append("车");
//
//		setPublicCargoParameterList("LPR_ID", userinfo.getLogisticsUserInfo().getLPRId());
//		setPublicCargoParameterList("Info_connect", infoMsg.toString());
//		setPublicCargoParameterList("Info_AddErID", userinfo.getLogisticsUserInfo().getUserlogin_id());
//		setPublicCargoParameterList("Info_AddErname", cargoName);
//		setPublicCargoParameterList("Info_price", ysfyEditStr);
//		setPublicCargoParameterList("Info_BAddTime", zxsjEditStr);
//		setPublicCargoParameterList("Info_BArea", startArea);
//		setPublicCargoParameterList("Info_BAddr", startArea + "详细地址");
//
//		setPublicCargoParameterList("Info_BLinkEr", userinfo.getLogisticsUserInfo().getUserTname());
//		setPublicCargoParameterList("Info_BLinkTle", userinfo.getLogisticsUserInfo().getUserMoble());
//		setPublicCargoParameterList("Info_EArea", stopArea);
//		setPublicCargoParameterList("Info_EAddr", stopArea + "详细地址");
//		setPublicCargoParameterList("Info_ELinkEr", "");
//		setPublicCargoParameterList("Info_ELinkTle", "");
//		setPublicCargoParameterList("Info_Etime", ddsjEditStr);
//		setPublicCargoParameterList("Info_BAreaX", "");
//
//		setPublicCargoParameterList("Info_BAreaY", "");
//		setPublicCargoParameterList("Info_EAreaX", "");
//		setPublicCargoParameterList("Info_EAreaY", "");
//		setPublicCargoParameterList("Car_type", cargoType);
//		setPublicCargoParameterList("Clearing_type", jsfsEditStr);
//		setPublicCargoParameterList("Transport_DIS", ysjlEditStr);
//		setPublicCargoParameterList("InfoXX_price", fwfyEditStr);
//		setPublicCargoParameterList("Info_ReMark", bcsmEditStr);
//		setPublicCargoParameterList("Car_count", qcslEditStr);
//
//	}

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
	private void setPublicCargoParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		cargoParameterList.add(parameter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		switch (view.getId()) {
		case R.id.hwmcSpinner:
			cargoName = hwmcSpinner.getSelectedItem().toString().toString();
			break;
		case R.id.hwlxSpinner:
			cargoType = hwlxSpinner.getSelectedItem().toString().toString();
			break;
		case R.id.zltjSpinner:
			volumeUnit = zltjSpinner.getSelectedItem().toString().toString();
			break;
		case R.id.qclxSpinner:
			carType = qclxSpinner.getSelectedItem().toString().toString();

			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}
