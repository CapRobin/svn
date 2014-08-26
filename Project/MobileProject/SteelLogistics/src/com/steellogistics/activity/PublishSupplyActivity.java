package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorListenerAdapter;
import com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.steellogistics.R;
import com.steellogistics.adapter.MyLocationAdapter;
import com.steellogistics.entity.SupplyInfoDetail;
import com.steellogistics.util.MethodUtil;
import com.steellogistics.view.CheckSwitchButton;
import com.steellogistics.view.NoScrollGridView;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PublishSupplyActivity.java
 * @Describe：发布供货信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:08:10
 * @Version v1.0
 */
public class PublishSupplyActivity extends BaseActivity implements OnTouchListener {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private String titlebarName = "发布供货";
	private String[] mItems = { "供货信息", "求购信息" };
	private Button publishSupplyBtn = null;
	public List<SupplyInfoDetail> supplyListInfo = new ArrayList<SupplyInfoDetail>();

	private EditText btEdit = null;
	private EditText jgEdit = null;
	private EditText bcnrEdit = null;

	private EditText lbEdit = null;
	private EditText pmEdit = null;
	private EditText ggEdit = null;
	private EditText cdEdit = null;
	private EditText slEdit = null;
	private TextView sldwEdit = null;
//	private EditText gbEdit = null;
	private EditText jyfsEdit = null;
//	private View mHolder6 = null;
	private View mHolder1, mHolder2, mHolder3, mHolder4, mHolder5, mHolder7;
	private List<String> myGridViewList1, myGridViewList2, myGridViewList3, myGridViewList4, myGridViewList5, myGridViewList6, myGridViewList7 = null;
	private NoScrollGridView lbGridView, pmGridView, ggGridView, cdGridView, slGridView, gbGridView, jyfsGridView = null;
	private CheckSwitchButton gbSwithcButton = null;
	private static String gcPinming[] = null;
	private static String steelStyle[] = null;
	private static String steelSpecification[] = null;

	private static String steelAddress[] = null;
	private static String steelAmount[] = null;
	private static String steelGuoBiao[] = null;
	private static String steelDealType[] = null;

	private static String gcPinming_01[] = null;
	private static String gcPinming_02[] = null;
	private static String gcPinming_03[] = null;

	private static String gcPinming_04[] = null;
	private static String gcPinming_05[] = null;
	private static String gcPinming_06[] = null;
	private static String gcPinming_07[] = null;
	private boolean isGb = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.publish_supply);
		titleBarInitView();
		initView();
	}

	/**
	 * 
	 * @Describe：初始化标题栏
	 * @Throws:
	 * @Date：2014年7月24日 上午9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo(titlebarName, isShowLeftBut, "返回", isShowRightBut, "发布求购");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(PublishSupplyActivity.this, PublishBuyActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：初始化
	 * @Throws:
	 * @Date：2014年7月25日 下午4:35:13
	 * @Version v1.0
	 */
	private void initView() {
		btEdit = (EditText) findViewById(R.id.btEdit);
		jgEdit = (EditText) findViewById(R.id.jgEdit);
		bcnrEdit = (EditText) findViewById(R.id.bcnrEdit);
		publishSupplyBtn = (Button) findViewById(R.id.publishSupplyBtn);
		mHolder1 = findViewById(R.id.holder1);
		mHolder2 = findViewById(R.id.holder2);
		mHolder3 = findViewById(R.id.holder3);
		mHolder4 = findViewById(R.id.holder4);
		mHolder5 = findViewById(R.id.holder5);
//		mHolder6 = findViewById(R.id.holder6);
		mHolder7 = findViewById(R.id.holder7);

		lbEdit = (EditText) findViewById(R.id.lbEdit);
		pmEdit = (EditText) findViewById(R.id.pmEdit);
		ggEdit = (EditText) findViewById(R.id.ggEdit);
		cdEdit = (EditText) findViewById(R.id.cdEdit);
		slEdit = (EditText) findViewById(R.id.slEdit);
		sldwEdit = (TextView) findViewById(R.id.sldwEdit);
//		gbEdit = (EditText) findViewById(R.id.gbEdit);
		jyfsEdit = (EditText) findViewById(R.id.jyfsEdit);

		lbGridView = (NoScrollGridView) findViewById(R.id.lbGridView);
		pmGridView = (NoScrollGridView) findViewById(R.id.pmGridView);
		ggGridView = (NoScrollGridView) findViewById(R.id.ggGridView);
		cdGridView = (NoScrollGridView) findViewById(R.id.cdGridView);
		slGridView = (NoScrollGridView) findViewById(R.id.slGridView);
//		gbGridView = (NoScrollGridView) findViewById(R.id.gbGridView);
		jyfsGridView = (NoScrollGridView) findViewById(R.id.jyfsGridView);

		steelStyle = this.getResources().getStringArray(R.array.steelStyle);
		steelSpecification = this.getResources().getStringArray(R.array.gcGuige);
		steelAddress = this.getResources().getStringArray(R.array.gccd);
		steelAmount = this.getResources().getStringArray(R.array.gcShuliang);
		steelGuoBiao = this.getResources().getStringArray(R.array.isGuobiao);
		steelDealType = this.getResources().getStringArray(R.array.jyfs);

		gcPinming_01 = this.getResources().getStringArray(R.array.gcPinming_01);
		gcPinming_02 = this.getResources().getStringArray(R.array.gcPinming_02);
		gcPinming_03 = this.getResources().getStringArray(R.array.gcPinming_03);
		gcPinming_04 = this.getResources().getStringArray(R.array.gcPinming_04);
		gcPinming_05 = this.getResources().getStringArray(R.array.gcPinming_05);
		gcPinming_06 = this.getResources().getStringArray(R.array.gcPinming_06);
		gcPinming_07 = this.getResources().getStringArray(R.array.gcPinming_07);
		
		gbSwithcButton = (CheckSwitchButton)findViewById(R.id.gbSwithcButton);
		gbSwithcButton.setChecked(false);
		gbSwithcButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					isGb = isChecked;
//					Toast.makeText(PublishSupplyActivity.this, String.valueOf(isGb), 5).show();
				}else{
					isGb = isChecked;
//					Toast.makeText(PublishSupplyActivity.this,  String.valueOf(isGb), 5).show();
				}
			}
		});
		
		// 设置隐藏GridView数据
		setGridViewData();

		lbEdit.setOnTouchListener(this);
		pmEdit.setOnTouchListener(this);
		ggEdit.setOnTouchListener(this);
		cdEdit.setOnTouchListener(this);
		sldwEdit.setOnTouchListener(this);
//		gbEdit.setOnTouchListener(this);
		jyfsEdit.setOnTouchListener(this);
		publishSupplyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (application.isLogin) {
					if (baseUserInfo != null) {
						submitPublishInfo();
					}else {
						showToast("请完善个人信息");
					}
				} else {
					startActivity(new Intent(PublishSupplyActivity.this, LoginActivity.class));
				}
			}
		});
	};

	/**
	 * 
	 * @Describe：构造View的数据
	 * @Throws:
	 * @Date：2014年8月19日 上午10:09:09
	 * @Version v1.0
	 */
	private void setGridViewData() {
		// 提前配置隐藏View显示数据
		myGridViewList1 = new ArrayList<String>();
		myGridViewList2 = new ArrayList<String>();
		myGridViewList3 = new ArrayList<String>();
		myGridViewList4 = new ArrayList<String>();
		myGridViewList5 = new ArrayList<String>();
		myGridViewList6 = new ArrayList<String>();
		myGridViewList7 = new ArrayList<String>();

		int lbArray = steelStyle.length;
		// int pmArray = gcPinming.length;
		int ggArray = steelSpecification.length;
		int cdArray = steelAddress.length;
		int slArray = steelAmount.length;
		int gbArray = steelGuoBiao.length;
		int jyfsArray = steelDealType.length;

		// 构造数据
		for (int i = 0; i < lbArray; i++) {
			myGridViewList1.add(steelStyle[i]);
		}
		// for (int i = 0; i < pmArray; i++) {
		// myGridViewList2.add(gcPinming[i]);
		// }
		for (int i = 0; i < ggArray; i++) {
			myGridViewList3.add(steelSpecification[i]);
		}
		for (int i = 0; i < cdArray; i++) {
			myGridViewList4.add(steelAddress[i]);
		}
		for (int i = 0; i < slArray; i++) {
			myGridViewList5.add(steelAmount[i]);
		}
		for (int i = 0; i < gbArray; i++) {
			myGridViewList6.add(steelGuoBiao[i]);
		}
		for (int i = 0; i < jyfsArray; i++) {
			myGridViewList7.add(steelDealType[i]);
		}

		showView(myGridViewList1, 1);
		// showView(myGridViewList2, 2);
		showView(myGridViewList3, 3);
		showView(myGridViewList4, 4);
		showView(myGridViewList5, 5);
//		showView(myGridViewList6, 6);
		showView(myGridViewList7, 7);
	}

	/**
	 * 
	 * @Describe：打开显示隐藏View
	 * @param list
	 * @Throws:
	 * @Date：2014年8月20日 上午10:31:19
	 * @Version v1.0
	 */
	public void showView(List<String> list, int viewId) {

		MyLocationAdapter locationAdapter = new MyLocationAdapter(PublishSupplyActivity.this, list);
		switch (viewId) {
		case 1: // 显示类别隐藏View
			lbGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			lbGridView.setAdapter(locationAdapter);
			lbGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String string = myGridViewList1.get(position);
					// 设置类别数据
					setPmData(position);
					lbEdit.setText(string);
					popViewisShow(1);
					popViewisShow(2);
					pmEdit.setFocusable(true);
					pmEdit.requestFocus();
					pmEdit.performClick();
				}
			});
			break;
		case 2: // 显示品名隐藏View
			pmGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			pmGridView.setAdapter(locationAdapter);
			pmGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					String string = myGridViewList2.get(position);
					pmEdit.setText(string);
					popViewisShow(2);
					popViewisShow(3);
					ggEdit.setFocusable(true);
					ggEdit.requestFocus();
					ggEdit.performClick();
				}
			});
			break;
		case 3:
			ggGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			ggGridView.setAdapter(locationAdapter);
			ggGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					String itemStr = myGridViewList3.get(position);
					ggEdit.setText(itemStr);
					popViewisShow(3);
					popViewisShow(4);
					cdEdit.setFocusable(true);
					cdEdit.requestFocus();
					cdEdit.performClick();
				}
			});
			break;
		case 4:
			cdGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			cdGridView.setAdapter(locationAdapter);
			cdGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					String itemStr = myGridViewList4.get(position);
					cdEdit.setText(itemStr);
					popViewisShow(4);
					// popViewisShow(5);
					slEdit.setFocusable(true);
					slEdit.requestFocus();
					slEdit.performClick();
				}
			});
			break;
		case 5:
			slGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			slGridView.setAdapter(locationAdapter);
			slGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					String itemStr = myGridViewList5.get(position);
					sldwEdit.setText(itemStr);
					popViewisShow(5);
					// popViewisShow(6);
					jgEdit.setFocusable(true);
					jgEdit.requestFocus();
					jgEdit.performClick();
				}
			});
			break;
		case 6:
//			gbGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
//			gbGridView.setAdapter(locationAdapter);
//			gbGridView.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//					String itemStr = myGridViewList6.get(position);
////					gbEdit.setText(itemStr);
//					popViewisShow(6);
//					popViewisShow(7);
//					jyfsEdit.setFocusable(true);
//					jyfsEdit.requestFocus();
//					jyfsEdit.performClick();
//				}
//			});
			break;
		case 7:
			jyfsGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			jyfsGridView.setAdapter(locationAdapter);
			jyfsGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String itemStr = myGridViewList7.get(position);
					jyfsEdit.setText(itemStr);
					popViewisShow(7);
				}
			});
			break;
		}
	}

	/**
	 * 
	 * @Describe：设置钢材类别名称
	 * @param selectId
	 * @Throws:
	 * @Date：2014年8月2日 下午4:15:02
	 * @Version v1.0
	 */
	private void setPmData(int selectId) {
		switch (selectId) {
		case 0:
			gcPinming = gcPinming_01;
			break;
		case 1:
			gcPinming = gcPinming_02;
			break;
		case 2:
			gcPinming = gcPinming_03;
			break;
		case 3:
			gcPinming = gcPinming_04;
			break;
		case 4:
			gcPinming = gcPinming_05;
			break;
		case 5:
			gcPinming = gcPinming_06;
			break;
		case 6:
			gcPinming = gcPinming_07;
			break;
		}

		int pmArray = gcPinming.length;
		myGridViewList2.clear();
		for (int i = 0; i < pmArray; i++) {
			myGridViewList2.add(gcPinming[i]);
		}
		showView(myGridViewList2, 2);
		pmEdit.setText(gcPinming[0]);
	}

	/**
	 * 
	 * @Describe：发布供货信息
	 * @Throws:
	 * @Date：2014年8月22日 上午9:38:53
	 * @Version v1.0
	 */
	private void submitPublishInfo() {
		String btEditStr = btEdit.getText().toString();
		String cdEditStr = cdEdit.getText().toString();
		String slEditStr = slEdit.getText().toString();
		String jgEditStr = jgEdit.getText().toString();
		String bcnrEditStr = bcnrEdit.getText().toString();
		String jyfwEditStr = "所有钢材";
		// String dealType = "线下交易";
		// 输入标题验证
		if (TextUtils.isEmpty(btEditStr)) {
			btEdit.setError("请输入标题");
			btEdit.setFocusable(true);
			btEdit.setFocusableInTouchMode(true);
			btEdit.requestFocus();
			return;
		}
		// 输入产地验证
		if (TextUtils.isEmpty(cdEditStr)) {
			cdEdit.setError("请输入产地");
			cdEdit.setFocusable(true);
			cdEdit.setFocusableInTouchMode(true);
			cdEdit.requestFocus();
			return;
		}
		// 输入数量验证
		if (TextUtils.isEmpty(slEditStr)) {
			slEdit.setError("请输入数量");
			slEdit.setFocusable(true);
			slEdit.setFocusableInTouchMode(true);
			slEdit.requestFocus();
			return;
		}
		// 输入价格验证
		if (TextUtils.isEmpty(jgEditStr)) {
			jgEdit.setError("请输入价格");
			jgEdit.setFocusable(true);
			jgEdit.setFocusableInTouchMode(true);
			jgEdit.requestFocus();
			return;
		}

		String lbSpinnerStr = lbEdit.getText().toString();
		String pmSpinnerStr = pmEdit.getText().toString();
		String ggSpinnerStr = ggEdit.getText().toString();
		String slSpinnerStr = slEdit.getText().toString();
//		String gbSpinnerStr = gbEdit.getText().toString();
		String jyfsSpinnerStr = jyfsEdit.getText().toString();

		String companyName = application.userInfo.getRealName();
		int userId = application.userInfo.getId();
		String mobile = application.userInfo.getMobile();
		String email = application.userInfo.getEmail();
		String contacts = application.userInfo.getRealName();

		SupplyInfoDetail supplyInfoDetail = new SupplyInfoDetail();
		supplyInfoDetail.setId(01);
		supplyInfoDetail.setTitleName(btEditStr);
		supplyInfoDetail.setImageUrl("http://pic4.jiancai.com/2013/04/19/20130419010145823.jpg");
		supplyInfoDetail.setSellScope(jyfwEditStr);
		supplyInfoDetail.setPrice(jgEditStr);
		supplyInfoDetail.setProductName(pmSpinnerStr);
		supplyInfoDetail.setOtherInfo(bcnrEditStr);
		supplyInfoDetail.setUserRealName(companyName);
		supplyInfoDetail.setUserAddress(cdEditStr);
		supplyInfoDetail.setContactNumber(mobile);
		supplyInfoDetail.setCreatTime(dateFormat.format(new   java.util.Date()));

		supplyInfoDetail.setType(lbSpinnerStr);
		supplyInfoDetail.setSpecification(ggSpinnerStr);
		supplyInfoDetail.setMakeAddress(cdEditStr);
		supplyInfoDetail.setAmount(slEditStr + slSpinnerStr);
		supplyInfoDetail.setIsGb(String.valueOf(isGb));
		supplyInfoDetail.setContacts(contacts);
		supplyInfoDetail.setEmail(email);
		supplyInfoDetail.setDealType(jyfsSpinnerStr);

		saveSupplyInfo(userId, supplyInfoDetail);
		application.isPublishSupply = true;
		finish();
	}

	/**
	 * 
	 * @Describe：存储数据到本地
	 * @param userId
	 * @param supplyInfo
	 * @Throws:
	 * @Date：2014年8月22日 上午10:14:45
	 * @Version v1.0
	 */
	private void saveSupplyInfo(int userId, SupplyInfoDetail supplyInfo) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		SupplyInfoDetail supplyinfodetail = null;
		try { // 获取本地构造数据
			String getInfo = MethodUtil.getSharedPreferences(this, "AppData", "supplyInfo");
			if (!TextUtils.isEmpty(getInfo)) {
				JSONArray array = new JSONArray(getInfo);
				for (int i = 0; i < array.length(); i++) {
					JSONObject item = array.getJSONObject(i);
					supplyinfodetail = gson.fromJson(item.toString(), SupplyInfoDetail.class);
					supplyListInfo.add(supplyinfodetail);
				}
			}
			supplyListInfo.add(supplyInfo);
			String supplyInfoStr = gson.toJson(supplyListInfo);
			MethodUtil.setSharedPreferences(this, "AppData", "supplyInfo", supplyInfoStr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		application.supplyInfoList = supplyListInfo;
		Toast.makeText(PublishSupplyActivity.this, "发布供货信息成功", 5).show();
	}

	/**
	 * 
	 * @Describe：控制视图是否显示
	 * @Throws:
	 * @Date：2014年8月18日 上午10:39:33
	 * @Version v1.0
	 */
	private void popViewisShow(int id) {

		switch (id) {
		case 1:
			if (View.GONE == mHolder1.findViewById(R.id.hiddenview1).getVisibility()) {
				animateExpanding(mHolder1.findViewById(R.id.hiddenview1));
			} else {
				animateCollapsing(mHolder1.findViewById(R.id.hiddenview1));
			}
			break;
		case 2:
			if (View.GONE == mHolder2.findViewById(R.id.hiddenview2).getVisibility()) {
				animateExpanding(mHolder2.findViewById(R.id.hiddenview2));
			} else {
				animateCollapsing(mHolder2.findViewById(R.id.hiddenview2));
			}
			break;
		case 3:
			if (View.GONE == mHolder3.findViewById(R.id.hiddenview3).getVisibility()) {
				animateExpanding(mHolder3.findViewById(R.id.hiddenview3));
			} else {
				animateCollapsing(mHolder3.findViewById(R.id.hiddenview3));
			}
			break;
		case 4:
			if (View.GONE == mHolder4.findViewById(R.id.hiddenview4).getVisibility()) {
				animateExpanding(mHolder4.findViewById(R.id.hiddenview4));
			} else {
				animateCollapsing(mHolder4.findViewById(R.id.hiddenview4));
			}
			break;
		case 5:
			if (View.GONE == mHolder5.findViewById(R.id.hiddenview5).getVisibility()) {
				animateExpanding(mHolder5.findViewById(R.id.hiddenview5));
			} else {
				animateCollapsing(mHolder5.findViewById(R.id.hiddenview5));
			}
			break;
		case 6:
//			if (View.GONE == mHolder6.findViewById(R.id.hiddenview6).getVisibility()) {
//				animateExpanding(mHolder6.findViewById(R.id.hiddenview6));
//			} else {
//				animateCollapsing(mHolder6.findViewById(R.id.hiddenview6));
//			}
			break;
		case 7:
			if (View.GONE == mHolder7.findViewById(R.id.hiddenview7).getVisibility()) {
				animateExpanding(mHolder7.findViewById(R.id.hiddenview7));
			} else {
				animateCollapsing(mHolder7.findViewById(R.id.hiddenview7));
			}
			break;
		}
	}

	public static void animateExpanding(final View view) {
		view.setVisibility(View.VISIBLE);

		final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(widthSpec, heightSpec);

		ValueAnimator animator = createHeightAnimator(view, 0, view.getMeasuredHeight());
		animator.start();
	}

	public static ValueAnimator createHeightAnimator(final View view, int start, int end) {
		ValueAnimator animator = ValueAnimator.ofInt(start, end);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();

				ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
				layoutParams.height = value;
				view.setLayoutParams(layoutParams);
			}
		});
		return animator;
	}

	public static void animateCollapsing(final View view) {
		int origHeight = view.getHeight();

		ValueAnimator animator = createHeightAnimator(view, origHeight, 0);
		animator.addListener(new AnimatorListenerAdapter() {
			public void onAnimationEnd(Animator animation) {
				view.setVisibility(View.GONE);
			};
		});
		animator.start();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.lbEdit: // 点击触动类别项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				popViewisShow(1);
			}
			// 监听触动事件
			// switch (event.getAction()) {
			// case KeyEvent.ACTION_DOWN:
			// MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			// popViewisShow(1);
			// break;
			// }
			break;
		case R.id.pmEdit:// 点击触动品名项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				if (gcPinming != null) {
					popViewisShow(2);
				} else {
					Toast.makeText(PublishSupplyActivity.this, "请先选择类别", 5).show();
				}
			}
			break;
		case R.id.ggEdit:// 点击触动规格项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				popViewisShow(3);
			}
			break;
		case R.id.cdEdit:// 点击触动规格项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				popViewisShow(4);
			}
			break;
		case R.id.sldwEdit:// 点击触动规格项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				popViewisShow(5);
			}
			break;
//		case R.id.gbEdit:// 点击触动规格项目
//			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
//			if (event.getAction() == KeyEvent.ACTION_DOWN) {
//				popViewisShow(6);
//			}
//			break;
		case R.id.jyfsEdit:// 点击触动规格项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				popViewisShow(7);
			}
			break;
		}
		return false;
	}
}
