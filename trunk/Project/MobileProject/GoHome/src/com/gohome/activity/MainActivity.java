package com.gohome.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gohome.R;
import com.gohome.view.MyImageView;
import com.gohome.view.MyImgScroll;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：MainActivity.java
 * @Describe：程序首页
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午10:09:15
 * @Version v1.0
 */
public class MainActivity extends BaseActivity implements OnClickListener {
	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private AlertDialog dialog;
	private MyImageView wyzc, wyzr, fbcy, fbry;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.main);
		titleBarInitView();
		initView();

		// // 构造用户个人信息
		// if (application.userInfo == null) {
		// makeData();
		// }
	}

	/**
	 * 
	 * @Describe：初始化标题栏
	 * @Throws:
	 * @Date：2014年7月24日 上午9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo("首    页", isShowLeftBut, "退出", isShowRightBut, "个人");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					showMyDialog();
					showToast("退出");
				}
			});
		}

		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					startActivity(new Intent(MainActivity.this, PersonalCenterActivity.class));
					// if (application.isLogin) {
					// startActivity(new Intent(MainActivity.this,
					// PersonalCenterActivity.class));
					// } else {
					// startActivity(new Intent(MainActivity.this,
					// LoginActivity.class));
					// }
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年4月28日 上午11:07:32
	 * @Version v1.0
	 */
	private void initView() {
		titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);

		wyzc = (MyImageView) findViewById(R.id.wyzc);
		wyzr = (MyImageView) findViewById(R.id.wyzr);
		fbcy = (MyImageView) findViewById(R.id.fbcy);
		fbry = (MyImageView) findViewById(R.id.fbry);
		wyzc.setOnClickListener(this);
		wyzr.setOnClickListener(this);
		fbcy.setOnClickListener(this);
		fbry.setOnClickListener(this);

		// 初始化图片
		InitViewPager();
		// 开始滚动
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v, R.drawable.dot_focused, R.drawable.dot_normal);

	}

	/**
	 * @Describe：初始化广告图片
	 * @Throws:
	 * @Date：2014年4月28日 下午3:29:10
	 * @Version v1.0
	 */
	private void InitViewPager() {
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.k, R.drawable.l, R.drawable.m,
				R.drawable.n, R.drawable.o };
		for (int i = 0; i < imageResId.length; i++) {
			final int imageItem = i;
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			// 设置图片点击事件
			// imageView.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			// Intent intent = new Intent();
			// switch (imageItem) {
			// case 0:
			// intent.setClass(MainActivity.this, MyWebView.class);
			// // intent.putExtra("url", "http://dl.5671.cc/");
			// intent.putExtra("url", "http://www.nxgtjt.com");
			// startActivity(intent);
			// break;
			// case 1:
			// intent.setClass(MainActivity.this, MyWebView.class);
			// intent.putExtra("url",
			// "http://www.baosteel.com/group/index.htm");
			// startActivity(intent);
			// break;
			// case 2:
			// intent.setClass(MainActivity.this, MyWebView.class);
			// intent.putExtra("url", "http://www.gtxh.com");
			// startActivity(intent);
			// break;
			// case 3:
			// intent.setClass(MainActivity.this, MyWebView.class);
			// intent.putExtra("url", "http://www.custeel.com");
			// startActivity(intent);
			// break;
			// case 4:
			// intent.setClass(MainActivity.this, MyWebView.class);
			// intent.putExtra("url", "http://www.csteelnews.com");
			// startActivity(intent);
			// break;
			// }
			// Toast.makeText(MainActivity.this, "您点击了第" +
			// (myPager.getCurIndex() + 1) + "个广告位", Toast.LENGTH_SHORT).show();
			// }
			// });

			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.wyzc:
			startActivity(new Intent(MainActivity.this, WyzcActivity.class));
			break;
		case R.id.wyzr:
			startActivity(new Intent(MainActivity.this, WyzrActivity.class));
			break;
		case R.id.fbcy:
			startActivity(new Intent(MainActivity.this, FbcyActivity.class));
			break;
		case R.id.fbry:
			startActivity(new Intent(MainActivity.this, FbryActivity.class));
			break;
		}
	}
}
