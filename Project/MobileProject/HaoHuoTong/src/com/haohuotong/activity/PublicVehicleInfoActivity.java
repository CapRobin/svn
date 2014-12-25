package com.haohuotong.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.global.AbConstant;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.ab.view.titlebar.AbTitleBar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haohuotong.R;
import com.haohuotong.entity.Parameter;
import com.haohuotong.global.MyApplication;
import com.haohuotong.other.DataSource;
import com.haohuotong.other.Entity;
import com.haohuotong.other.InfoEntity;
import com.haohuotong.other.LocationSelector;
import com.haohuotong.other.UserEntity;
import com.haohuotong.web.PublicInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 名称：PublicLorryInfoActivity.java 
 * 描述：发布车源信息
 * @author Yu Farong - yfr5734@gmail.com
 * @date：2013年12月27日 上午4:34:58
 * @version v1.0
 */
public class PublicVehicleInfoActivity extends AbActivity implements DataSource.Callback {

	private MyApplication application = null;
	private LocationSelector mLocationStartArea, mLocationStopArea = null;
	private InfoEntity.Type mType;
	private Spinner mCargoType;
	private Spinner mCargoWeightUnit;
	private Spinner pub_truck_type;
	private Spinner pub_cargo_type;
	private EditText mCargoWeight;
	private EditText mTruckLength;
	private EditText mMemo;
	private EditText mTruckCapacity;
	private EditText mTruckPlate;
	private String cargoType = null;
	

	private EditText ccslEdit, chEdit, clzzEdit, hyjgEdit;
	private Button publicInfoBut = null;
	private List<Parameter> cargoParameterList = null;
	private boolean isPublic = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setAbContentView(R.layout.public_vehicle_info);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//		initTitleRightLayout();
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
		mAbTitleBar.setTitleText("发布车源信息");
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
				Intent intent = new Intent(PublicVehicleInfoActivity.this, MyPublicInfoActivity.class);
				startActivity(intent);
			}
		});
		

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

		application = (MyApplication) abApplication;
		FragmentManager fm = this.getSupportFragmentManager();
		mLocationStartArea = (LocationSelector) fm.findFragmentById(R.id.fragment_start_area);
		mLocationStopArea = (LocationSelector) fm.findFragmentById(R.id.fragment_stop_area);

		// String startArea = this.mLocationStartArea.getLocationString();
		// String stopArea = this.mLocationStopArea.getLocationString();
		// System.out.println("startArea is "+startArea);
		// System.out.println("stopArea is "+stopArea);

//		this.mCargoType = (Spinner) this.findViewById(R.id.pub_cargo_type);
//		this.mCargoWeightUnit = (Spinner) this.findViewById(R.id.pub_cargo_weight_unit_list);
//		this.mCargoWeight = (EditText) this.findViewById(R.id.pub_cargo_weight);
//		this.mTruckLength = (EditText) this.findViewById(R.id.pub_truck_length);
//		this.mMemo = (EditText) this.findViewById(R.id.pub_memo);
//		this.mTruckCapacity = (EditText) this.findViewById(R.id.pub_truck_capacity);
//		this.mTruckPlate = (EditText) this.findViewById(R.id.pub_truck_plate);
		

		pub_cargo_type = (Spinner) findViewById(R.id.pub_cargo_type);
		pub_truck_type = (Spinner) this.findViewById(R.id.pub_truck_type);
		publicInfoBut = (Button) findViewById(R.id.publicInfoBut);
		ccslEdit = (EditText) findViewById(R.id.ccslEdit);
		chEdit = (EditText) findViewById(R.id.chEdit);
		clzzEdit = (EditText) findViewById(R.id.clzzEdit);
		hyjgEdit = (EditText) findViewById(R.id.hyjgEdit);
//
//		pub_cargo_type.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//				cargoType = pub_cargo_type.getSelectedItem().toString().toString();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
		
		publicInfoBut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				submit();
				submitVehicleInfo();
			}
		});
	}

	/**
	 * 
	 * @Describe：发布车辆信息
	 * @Throws 
	 * @Date：2014-1-2 下午4:22:36
	 * @Version v1.0
	 */
	private void submitVehicleInfo() {
		cargoParameterList = new ArrayList<Parameter>();
		String startArea = mLocationStartArea.getLocationString();
		String stopArea = mLocationStopArea.getLocationString();
		

//		private EditText ccslEdit, chEdit, clzzEdit, hyjgEdit;
		String ccslEditStr = ccslEdit.getText().toString();
		String chEditStr = chEdit.getText().toString();
		String clzzEditStr = clzzEdit.getText().toString();
		String hyjgEditStr = hyjgEdit.getText().toString();
		String cargoType = pub_cargo_type.getSelectedItem().toString().toString();
		String truckType = pub_truck_type.getSelectedItem().toString().toString();
//		<>车主ID</Duser_id>
//		<>路线起始地</Car_Bway>
//		<>路线目地地 </Car_Eway>
		
//		<>有车辆数</Car_Count>
//		<>车长</Car_length>
//		<>车辆最大承载吨位</Car_max_dun>
//		<>货物类形</Car_Huo_Type>
//		<>货运价格</Huo_price>
//		<>价格单位</Huo_price_unit>
		
//		<Duser_id>车主ID</Duser_id>
//		<Car_Bway>路线起始地</Car_Bway>
//		<Car_Eway>路线目地地 </Car_Eway>
//		<Car_Count>有车辆数</Car_Count>
//		<Car_length>车长</Car_length>
//		<Car_max_dun>车辆最大承载吨位</Car_max_dun>
//		<Car_Huo_Type>货物类形</Car_Huo_Type>
//		<Huo_price>货运价格</Huo_price>
//		<Huo_price_unit>价格单位</Huo_price_unit>
		
		setPublicVehicleParameterList("Duser_id", application.userInfo.getDriverUserInfo().getDuser_id());
		setPublicVehicleParameterList("Car_Bway", startArea);
		setPublicVehicleParameterList("Car_Eway", stopArea);

		setPublicVehicleParameterList("Car_Count", ccslEditStr);
		setPublicVehicleParameterList("Car_length", chEditStr);
		setPublicVehicleParameterList("Car_max_dun", clzzEditStr);
		setPublicVehicleParameterList("Car_Huo_Type", cargoType);
		setPublicVehicleParameterList("Huo_price", hyjgEditStr);
		setPublicVehicleParameterList("Huo_price_unit", "吨");
		setPublicVehicleParameterList("Car_Type", truckType);
		
//		sap rfc3261 
		
		
		showProgressDialog();
		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对象
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeProgressDialog();
				if (isPublic) {
					Toast.makeText(PublicVehicleInfoActivity.this, "信息发布成功!", 5).show();
					finish();
				}
			}

			@Override
			public void get() {
				try {
					isPublic = PublicInfoWeb.publicCargoInfo("I_A015", cargoParameterList);
				} catch (AbAppException e) {
					e.printStackTrace();
					showToastInThread(e.getMessage());
				}
			};
		};
		mAbTaskThread.execute(item);

	}
	
	
	/**
	 * 
	 * 描述：发布车源信息参数
	 * 
	 * @param string
	 * @param lprId
	 * @throws
	 * @date：2013年12月27日 上午3:37:10
	 * @version v1.0
	 */
	private void setPublicVehicleParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		cargoParameterList.add(parameter);
	}
	
	
	/**
	 * 
	 * 描述：发布车源信息
	 * 
	 * @throws
	 * @date：2013-11-20 下午2:43:55
	 * @version v1.0
	 */
//	private void submit() {
//		// UserEntity user = Config.getInstance().getUser();
//		UserEntity user = null;
//		GsonBuilder builder = new GsonBuilder();
//		Gson gson = builder.create();
//
//		String fileName = "user.java"; // 文件名字
//		String getString = "";
//		try {
//			InputStream in = getResources().getAssets().open(fileName);
//			// \Test\assets\yan.txt这里有这样的文件存在
//			int length = in.available();
//			byte[] buffer = new byte[length];
//			in.read(buffer);
//			getString = EncodingUtils.getString(buffer, "UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		user = gson.fromJson(getString, UserEntity.class);
//		// if (null == user) {
//		// Toast.makeText(PublicInfolActivity.this, "请先登录",
//		// Toast.LENGTH_SHORT).show();
//		// // return;
//		// }
//		//
//		// if ("0" == user.getMsg_Publish_Enabled()) {
//		// Toast.makeText(PublicInfolActivity.this, "您没有发布信息的权限",
//		// Toast.LENGTH_SHORT)
//		// .show();
//		// // return;
//		// }
//
//		StringBuilder sb = new StringBuilder();
//		String startArea = this.mLocationStartArea.getLocationString();
//		String stopArea = this.mLocationStopArea.getLocationString();
//
//		sb.append(startArea);
//		sb.append(" → ");
//		sb.append(stopArea);
//
//		if (InfoEntity.Type.CargoInfo == mType) {
//
//			sb.append(", 有");
//			sb.append(this.mCargoType.getSelectedItem().toString());
//			if (this.mCargoWeight.getText().length() > 0) {
//				sb.append(this.mCargoWeight.getText());
//				sb.append(this.mCargoWeightUnit.getSelectedItem().toString());
//			}
//
//			sb.append(", 求");
//			if (this.mTruckLength.getText().length() > 0) {
//				sb.append(this.mTruckLength.getText());
//				sb.append("米");
//			}
//			if (this.mTruckType.getSelectedItemPosition() == 0) {
//				sb.append("车型"); // make it “车型不限”
//			}
//			sb.append(this.mTruckType.getSelectedItem().toString());
//
//			if (this.mMemo.getText().length() > 0) {
//				sb.append(",");
//				sb.append(this.mMemo.getText());
//			}
//
//		}
//		if (InfoEntity.Type.TruckInfo == mType) {
//			sb.append(", 有");
//			if (this.mTruckLength.getText().length() > 0) {
//				sb.append(this.mTruckLength.getText());
//				sb.append("米");
//			}
//			sb.append(this.mTruckType.getSelectedItem().toString());
//
//			if (this.mTruckCapacity.getText().length() > 0) {
//				sb.append(",");
//				sb.append("载重");
//				sb.append(this.mTruckCapacity.getText());
//				sb.append("吨");
//			}
//
//			if (this.mTruckPlate.getText().length() > 0) {
//				sb.append(",");
//				sb.append("车牌");
//				sb.append(this.mTruckPlate.getText());
//			}
//
//			if (this.mMemo.getText().length() > 0) {
//				sb.append(",");
//				sb.append(this.mMemo.getText());
//			}
//		}
//
//		Log.i("TAG", sb.toString());
//
//		InfoEntity info = new InfoEntity();
//		info.setMsg_Type(InfoEntity.Type.toString(this.mType));
//		info.setMsg_Content(sb.toString());
//		info.setMsg_StartCity(startArea);
//
//		DataSource.getInstance().publishInfoAsync(PublicVehicleInfoActivity.this, info, user);
//	}

	@Override
	public void onLogin(Entity entity) {
	}

	@Override
	public void onPublish(String inResult) {
		final String result = inResult;
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(PublicVehicleInfoActivity.this, result, Toast.LENGTH_SHORT).show();
				finish();
			}

		});
	}

	@Override
	public void onCheckConnection(String result, int statusCode) {
	}

	@Override
	public void onRegisterUser(String result) {
	}

	@Override
	public void onCheckUpdate(String result) {
	}
}
