package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import android.widget.Toast;

import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorListenerAdapter;
import com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator;
import com.steellogistics.R;
import com.steellogistics.adapter.MyLocationAdapter;
import com.steellogistics.util.MethodUtil;
import com.steellogistics.view.NoScrollGridView;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SearchActivity.java
 * @Describe：搜索信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:08:59
 * @Version v1.0
 */
public class SearchActivity extends BaseActivity implements OnTouchListener {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private Button sslxBut = null;
	private Button searchBut = null;
	private EditText searchEdit = null;
	private EditText lbEdit = null;
	private EditText pmEdit = null;
	private boolean isGy = true;
	private View mHolder1, mHolder2, mHolder3;
	private List<String> myGridViewList1, myGridViewList2, myGridViewList3 = null;
	private NoScrollGridView lbGridView, pmGridView, ggGridView = null;
	private static String steelStyle[] = null;

	private static String gcPinming[] = null;
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
		setBaseContentView(R.layout.search);
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
		setTitleInfo("搜    索", isShowLeftBut, "返回", isShowRightBut, null);
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年4月29日 上午10:52:17
	 * @Version v1.0
	 * 
	 */
	private void initView() {
		sslxBut = (Button) findViewById(R.id.sslxBut);
		searchBut = (Button) findViewById(R.id.searchBut);
		searchEdit = (EditText) findViewById(R.id.searchEdit);
		lbEdit = (EditText) findViewById(R.id.lbEdit);
		pmEdit = (EditText) findViewById(R.id.pmEdit);

		mHolder1 = findViewById(R.id.holder1);
		mHolder2 = findViewById(R.id.holder2);
		lbGridView = (NoScrollGridView) findViewById(R.id.lbGridView);
		pmGridView = (NoScrollGridView) findViewById(R.id.pmGridView);

		steelStyle = this.getResources().getStringArray(R.array.steelStyle);
		gcPinming_01 = this.getResources().getStringArray(R.array.gcPinming_01);
		gcPinming_02 = this.getResources().getStringArray(R.array.gcPinming_02);
		gcPinming_03 = this.getResources().getStringArray(R.array.gcPinming_03);
		gcPinming_04 = this.getResources().getStringArray(R.array.gcPinming_04);
		gcPinming_05 = this.getResources().getStringArray(R.array.gcPinming_05);
		gcPinming_06 = this.getResources().getStringArray(R.array.gcPinming_06);
		gcPinming_07 = this.getResources().getStringArray(R.array.gcPinming_07);

		lbEdit.setOnTouchListener(this);
		pmEdit.setOnTouchListener(this);

		sslxBut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isGy) {
					sslxBut.setText("求购");
					isGy = false;
					showToast("求购");
				} else {
					sslxBut.setText("供应");
					showToast("供应");
					isGy = true;
				}
			}
		});

		searchBut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				submitSearch();
			}
		});

		setGridViewData();
	}

	/**
	 * 
	 * @Describe：开始搜索
	 * @Throws:
	 * @Date：2014年8月26日 下午5:30:35
	 * @Version v1.0
	 */
	private void submitSearch() {
		showToast("搜索成功！");
		finish();
	}

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

		int lbArray = steelStyle.length;

		// 构造数据
		for (int i = 0; i < lbArray; i++) {
			myGridViewList1.add(steelStyle[i]);
		}

		showView(myGridViewList1, 1);
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

		MyLocationAdapter locationAdapter = new MyLocationAdapter(SearchActivity.this, list);
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
					// popViewisShow(3);
					// ggEdit.setFocusable(true);
					// ggEdit.requestFocus();
					// ggEdit.performClick();
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
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.lbEdit: // 点击触动类别项目
			MethodUtil.closeInputMethod(SearchActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				popViewisShow(1);
			}
			break;
		case R.id.pmEdit:// 点击触动品名项目
			MethodUtil.closeInputMethod(SearchActivity.this);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				if (gcPinming != null) {
					popViewisShow(2);
				} else {
					Toast.makeText(SearchActivity.this, "请先选择类别", 5).show();
				}
			}
			break;
		}
		return false;
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

}
