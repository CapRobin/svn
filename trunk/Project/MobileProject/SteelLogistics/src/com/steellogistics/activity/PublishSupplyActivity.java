package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorListenerAdapter;
import com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator;
import com.google.gson.Gson;
import com.steellogistics.R;
import com.steellogistics.adapter.MyLocationAdapter;
import com.steellogistics.entity.SupplyInfoDetail;
import com.steellogistics.util.MethodUtil;
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
public class PublishSupplyActivity extends BaseActivity implements OnClickListener, OnTouchListener {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private String titlebarName = "发布供货";
	private String[] mItems = { "供货信息", "求购信息" };
	private Spinner slSpinner, gbSpinner, jyfsSpinner;
//	private Spinner ggSpinner;
	private EditText btEdit, cdEdit, slEdit, jgEdit, bcnrEdit = null;
	private Button publishSupplyBtn = null;
	private EditText lbEdit = null;
	private EditText pmEdit = null;
	private EditText ggEdit = null;
	private View mHolder1, mHolder2, mHolder3, mHolder4;
	private List<String> myGridViewList1, myGridViewList2, myGridViewList3, myGridViewList4 = null;
	private NoScrollGridView lbGridView, pmGridView, ggGridView = null;

	private static String gcPinming[] = null;
	private static String steelStyle[] = null;
	private static String steelSpecification[] = null;
	private static String gcPinming_01[] = null;
	private static String gcPinming_02[] = null;
	private static String gcPinming_03[] = null;

	private static String gcPinming_04[] = null;
	private static String gcPinming_05[] = null;
	private static String gcPinming_06[] = null;
	private static String gcPinming_07[] = null;

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
		cdEdit = (EditText) findViewById(R.id.cdEdit);
		slEdit = (EditText) findViewById(R.id.slEdit);
		jgEdit = (EditText) findViewById(R.id.jgEdit);
		bcnrEdit = (EditText) findViewById(R.id.bcnrEdit);
		mHolder1 = findViewById(R.id.holder1);
		mHolder2 = findViewById(R.id.holder2);
		mHolder3 = findViewById(R.id.holder3);
		lbEdit = (EditText) findViewById(R.id.lbEdit);
		pmEdit = (EditText) findViewById(R.id.pmEdit);
		ggEdit = (EditText) findViewById(R.id.ggEdit);
		
		lbGridView = (NoScrollGridView) findViewById(R.id.lbGridView);
		pmGridView = (NoScrollGridView) findViewById(R.id.pmGridView);
		ggGridView = (NoScrollGridView) findViewById(R.id.ggGridView);

//		ggSpinner = (Spinner) findViewById(R.id.ggSpinner);
		slSpinner = (Spinner) findViewById(R.id.slSpinner);
		gbSpinner = (Spinner) findViewById(R.id.gbSpinner);
		jyfsSpinner = (Spinner) findViewById(R.id.jyfsSpinner);

		steelStyle = this.getResources().getStringArray(R.array.steelStyle);
		steelSpecification = this.getResources().getStringArray(R.array.gcGuige);
		gcPinming_01 = this.getResources().getStringArray(R.array.gcPinming_01);
		gcPinming_02 = this.getResources().getStringArray(R.array.gcPinming_02);
		gcPinming_03 = this.getResources().getStringArray(R.array.gcPinming_03);

		gcPinming_04 = this.getResources().getStringArray(R.array.gcPinming_04);
		gcPinming_05 = this.getResources().getStringArray(R.array.gcPinming_05);
		gcPinming_06 = this.getResources().getStringArray(R.array.gcPinming_06);
		gcPinming_07 = this.getResources().getStringArray(R.array.gcPinming_07);
		publishSupplyBtn = (Button) findViewById(R.id.publishSupplyBtn);
		publishSupplyBtn.setOnClickListener(this);


		lbEdit.setOnTouchListener(this);
		pmEdit.setOnTouchListener(this);
		ggEdit.setOnTouchListener(this);
		
//		lbEdit.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				switch (event.getAction()) {
//				case KeyEvent.ACTION_DOWN:
//					MethodUtil.closeInputMethod(PublishSupplyActivity.this);
//					popViewisShow(1);
//					break;
//				}
//				return false;
//			}
//		});
//
//		pmEdit.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				switch (event.getAction()) {
//				case KeyEvent.ACTION_DOWN:
//					if (gcPinming != null) {
//						MethodUtil.closeInputMethod(PublishSupplyActivity.this);
//						popViewisShow(2);
//					} else {
//						Toast.makeText(PublishSupplyActivity.this, "请先选择类别", 5).show();
//					}
//					break;
//				}
//				return false;
//			}
//		});
		
		//设置隐藏GridView数据
		setGridViewData();

	};

	/**
	 * 
	 * @Describe：构造View的数据
	 * @Throws:
	 * @Date：2014年8月19日 上午10:09:09
	 * @Version v1.0
	 */
	private void setGridViewData() {
		// 创建类别隐藏数据
		myGridViewList1 = new ArrayList<String>();
		// 创建品名隐藏数据
		myGridViewList2 = new ArrayList<String>();
		// 创建规格隐藏数据
		myGridViewList3 = new ArrayList<String>();

		int lbArray = steelStyle.length;
		// int pmArray = gcPinming.length;
		int ggArray = steelSpecification.length;

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

		showView(myGridViewList1, 1);
		// showView(myGridViewList2, 2);
		 showView(myGridViewList3, 3);

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
		case 1:			//显示类别隐藏View
			lbGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			lbGridView.setAdapter(locationAdapter);
			lbGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String string = myGridViewList1.get(position);
					//设置类别数据
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
		case 2:		//显示品名隐藏View
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
					cdEdit.setFocusable(true);
					cdEdit.requestFocus();
					cdEdit.performClick();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.publishSupplyBtn:

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

			String lbSpinnerStr = lbEdit.toString();
			String pmSpinnerStr = pmEdit.toString();
//			String ggSpinnerStr = ggSpinner.getSelectedItem().toString();
			String slSpinnerStr = slSpinner.getSelectedItem().toString();
			String gbSpinnerStr = gbSpinner.getSelectedItem().toString();
			String jyfsSpinnerStr = jyfsSpinner.getSelectedItem().toString();

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
			supplyInfoDetail.setCreatTime("2014_07_30");

			supplyInfoDetail.setType(lbSpinnerStr);
//			supplyInfoDetail.setSpecification(ggSpinnerStr);
			supplyInfoDetail.setMakeAddress(cdEditStr);
			supplyInfoDetail.setAmount(slEditStr + slSpinnerStr);
			supplyInfoDetail.setIsGb(gbSpinnerStr);
			supplyInfoDetail.setContacts(contacts);
			supplyInfoDetail.setEmail(email);
			supplyInfoDetail.setDealType(jyfsSpinnerStr);

			publicJk(userId, supplyInfoDetail);
			break;
		}
	}

	// 模拟调用接口
	private void publicJk(int userId, SupplyInfoDetail supplyInfo) {
		application.supplyInfoList.add(supplyInfo);

		// supplyInfoDetail对象打包成Json字符串
		Gson gson = new Gson();
		String getSupplyInfoStr = gson.toJson(supplyInfo);
		System.out.println("getSupplyInfoStr is ----------->>" + getSupplyInfoStr);
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
		// animator.setDuration(DURATION);
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
		case R.id.lbEdit:		//点击触动类别项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				popViewisShow(1);
			}
			//监听触动事件
//			switch (event.getAction()) {
//			case KeyEvent.ACTION_DOWN:
//				MethodUtil.closeInputMethod(PublishSupplyActivity.this);
//				popViewisShow(1);
//				break;
//			}
			break;
		case R.id.pmEdit://点击触动品名项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				if (gcPinming != null) {
					popViewisShow(2);
				} else {
					Toast.makeText(PublishSupplyActivity.this, "请先选择类别", 5).show();
				}
			}
			break;
		case R.id.ggEdit://点击触动规格项目
			MethodUtil.closeInputMethod(PublishSupplyActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				popViewisShow(3);
			}
			break;
		}
		return false;
	}
}
