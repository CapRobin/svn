package com.steellogistics.activity;

import java.text.SimpleDateFormat;
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorListenerAdapter;
import com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.steellogistics.R;
import com.steellogistics.adapter.MyLocationAdapter;
import com.steellogistics.entity.BuyInfoDetail;
import com.steellogistics.entity.SupplyInfoDetail;
import com.steellogistics.entity.UserInfo;
import com.steellogistics.util.MethodUtil;
import com.steellogistics.view.NoScrollGridView;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PublishActivity.java
 * @Describe：发布求购信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:08:10
 * @Version v1.0
 */
public class PublishBuyActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private TextView sldwEdit = null;
	private View mHolder5 = null;
	private String titlebarName = "发布求购";
	private String[] mItems = { "供货信息", "求购信息" };
	private EditText btEdit, qgyqEdit, slEdit, jgEdit, bzyqEdit, bcnrEdit = null;
	private Button publishBuyBtn = null;
	private List<String> myGridViewList5 = null;
	private NoScrollGridView slGridView = null;
	private static String steelAmount[] = null;
	private String slUnitStr = "吨";
//	unit
	
	public List<BuyInfoDetail> buyListInfo = new ArrayList<BuyInfoDetail>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.publish_buy);
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
		setTitleInfo(titlebarName, isShowLeftBut, "返回", isShowRightBut, "发布供货");
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
					Intent intent = new Intent(PublishBuyActivity.this, PublishSupplyActivity.class);
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
		slEdit = (EditText) findViewById(R.id.slEdit);
		qgyqEdit = (EditText) findViewById(R.id.qgyqEdit);
		bzyqEdit = (EditText) findViewById(R.id.bzyqEdit);
		bcnrEdit = (EditText) findViewById(R.id.bcnrEdit);
		publishBuyBtn = (Button) findViewById(R.id.publishBuyBtn);
		sldwEdit = (TextView) findViewById(R.id.sldwEdit);
		mHolder5 = findViewById(R.id.holder5);
		steelAmount = this.getResources().getStringArray(R.array.gcShuliang);
		slGridView = (NoScrollGridView) findViewById(R.id.slGridView);
		
		publishBuyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (application.isLogin) {
					submitBuyInfo();
				} else {
					startActivity(new Intent(PublishBuyActivity.this, LoginActivity.class));
				}
			}
		});

		sldwEdit.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				MethodUtil.closeInputMethod(PublishBuyActivity.this);
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					popViewisShow();
				}
				return false;
			}
		});
		
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
		// 提前配置隐藏View显示数据
//		myGridViewList1 = new ArrayList<String>();
//		myGridViewList2 = new ArrayList<String>();
//		myGridViewList3 = new ArrayList<String>();
//		myGridViewList4 = new ArrayList<String>();
		myGridViewList5 = new ArrayList<String>();
//		myGridViewList6 = new ArrayList<String>();
//		myGridViewList7 = new ArrayList<String>();

//		int lbArray = steelStyle.length;
//		// int pmArray = gcPinming.length;
//		int ggArray = steelSpecification.length;
//		int cdArray = steelAddress.length;
		int slArray = steelAmount.length;
//		int gbArray = steelGuoBiao.length;
//		int jyfsArray = steelDealType.length;

		// 构造数据
//		for (int i = 0; i < lbArray; i++) {
//			myGridViewList1.add(steelStyle[i]);
//		}
//		// for (int i = 0; i < pmArray; i++) {
//		// myGridViewList2.add(gcPinming[i]);
//		// }
//		for (int i = 0; i < ggArray; i++) {
//			myGridViewList3.add(steelSpecification[i]);
//		}
//		for (int i = 0; i < cdArray; i++) {
//			myGridViewList4.add(steelAddress[i]);
//		}
		for (int i = 0; i < slArray; i++) {
			myGridViewList5.add(steelAmount[i]);
		}
//		for (int i = 0; i < gbArray; i++) {
//			myGridViewList6.add(steelGuoBiao[i]);
//		}
//		for (int i = 0; i < jyfsArray; i++) {
//			myGridViewList7.add(steelDealType[i]);
//		}

//		showView(myGridViewList1, 1);
//		// showView(myGridViewList2, 2);
//		showView(myGridViewList3, 3);
//		showView(myGridViewList4, 4);
		showView(myGridViewList5);
//		showView(myGridViewList6, 6);
//		showView(myGridViewList7, 7);
	}
	
	public void showView(List<String> list) {
		MyLocationAdapter locationAdapter = new MyLocationAdapter(PublishBuyActivity.this, list);
		slGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		slGridView.setAdapter(locationAdapter);
		slGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				slUnitStr = myGridViewList5.get(position);
				sldwEdit.setText(slUnitStr);
				popViewisShow();
//				// popViewisShow(6);
				qgyqEdit.setFocusable(true);
				qgyqEdit.requestFocus();
				qgyqEdit.performClick();
			}
		});
		
	}
	
	private void popViewisShow() {
		if (View.GONE == mHolder5.findViewById(R.id.hiddenview5).getVisibility()) {
			animateExpanding(mHolder5.findViewById(R.id.hiddenview5));
		} else {
			animateCollapsing(mHolder5.findViewById(R.id.hiddenview5));
		}
	}
	

	/**
	 * 
	 * @Describe：发布求购信息
	 * @Throws:  
	 * @Date：2014年8月22日 上午11:12:53
	 * @Version v1.0
	 */
	private void submitBuyInfo() {
		UserInfo info = application.userInfo;
		String btEditStr = btEdit.getText().toString();
		String jgEditStr = jgEdit.getText().toString();
		String slEditStr = slEdit.getText().toString();
		String qgyqEditStr = qgyqEdit.getText().toString();
		String bzyqEditStr = bzyqEdit.getText().toString();
		String bcnrEditStr = bcnrEdit.getText().toString();

		if (info != null) {
			int userId = info.getId();
			String buyerName = info.getRealName();
			String mobile = info.getMobile();
			String buyerAddress = info.getAddress();
			String contacts = info.getContacts();

			// String dealType = "线下交易";
			// 输入标题验证
			if (TextUtils.isEmpty(btEditStr)) {
				btEdit.setError("请输入标题");
				btEdit.setFocusable(true);
				btEdit.setFocusableInTouchMode(true);
				btEdit.requestFocus();
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
			// 输入数量验证
			if (TextUtils.isEmpty(slEditStr)) {
				slEdit.setError("请输入数量");
				slEdit.setFocusable(true);
				slEdit.setFocusableInTouchMode(true);
				slEdit.requestFocus();
				return;
			}
			BuyInfoDetail buyInfoDetail = new BuyInfoDetail();

			buyInfoDetail.setId(001);
			buyInfoDetail.setTitleName(btEditStr);
			buyInfoDetail.setImageUrl("http://img3.cache.netease.com/catchimg/20100808/85U286RU_0.jpg");
			buyInfoDetail.setOtherInfo(bcnrEditStr);
			buyInfoDetail.setBuyAmount(slEditStr+slUnitStr);
			buyInfoDetail.setBuyPrice(jgEditStr);
			buyInfoDetail.setUserAddress(buyerAddress);

			buyInfoDetail.setContactNumber(mobile);
			buyInfoDetail.setCreatTime(dateFormat.format(new   java.util.Date()));
			buyInfoDetail.setBuyRequire(qgyqEditStr);
			buyInfoDetail.setPackRequire(bzyqEditStr);
			buyInfoDetail.setUserRealName(buyerName);
			buyInfoDetail.setContacts(contacts);
			
			saveBuyInfo(userId, buyInfoDetail);
			application.isPublishSupply = false;
			finish();
		}else {
			showToast("请完善个人信息");
		}
	}
	
	/**
	 * 
	 * @Describe：模拟调用接口
	 * @param userId
	 * @param supplyInfo
	 * @Throws:
	 * @Date：2014年8月6日 下午3:01:37
	 * @Version v1.0
	 */
	private void saveBuyInfo(int userId, BuyInfoDetail buyInfo) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		BuyInfoDetail buyinfodetail = null;
		try { // 获取本地构造数据
			String getInfo = MethodUtil.getSharedPreferences(this, "AppData", "buyInfo");
			if (!TextUtils.isEmpty(getInfo)) {
				JSONArray array = new JSONArray(getInfo);
				for (int i = 0; i < array.length(); i++) {
					JSONObject item = array.getJSONObject(i);
					buyinfodetail = gson.fromJson(item.toString(), BuyInfoDetail.class);
					buyListInfo.add(buyinfodetail);
				}
			}
			buyListInfo.add(buyInfo);
			String buyInfoStr = gson.toJson(buyListInfo);
			MethodUtil.setSharedPreferences(this, "AppData", "buyInfo", buyInfoStr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		application.buyInfoList = buyListInfo;
		Toast.makeText(PublishBuyActivity.this, "发布求购信息成功", 5).show();
		
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

}
