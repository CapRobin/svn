package com.steellogistics.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.steellogistics.R;
import com.steellogistics.global.MyApplication;
import com.steellogistics.view.MyImgScroll;

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
	private Button ghBut, qgBut, fbBut, ssBut, zxBut, gdBut;
	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private AlertDialog dialog;
	private MyApplication application = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_main);
		application = (MyApplication) getApplication();
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
		setTitleInfo("首    页", isShowLeftBut, "退出", isShowRightBut, "个人");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					showMyDialog();
				}
			});
		}

		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (application.isLogin) {
						startActivity(new Intent(MainActivity.this, PersonalCenterActivity.class));
					} else {
						startActivity(new Intent(MainActivity.this, LoginActivity.class));
					}
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

		ghBut = (Button) findViewById(R.id.ghBut);
		qgBut = (Button) findViewById(R.id.qgBut);
		fbBut = (Button) findViewById(R.id.fbBut);
		ssBut = (Button) findViewById(R.id.ssBut);
		zxBut = (Button) findViewById(R.id.zxBut);
		gdBut = (Button) findViewById(R.id.gdBut);

		titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);

		ghBut.setOnClickListener(this);
		qgBut.setOnClickListener(this);
		fbBut.setOnClickListener(this);
		ssBut.setOnClickListener(this);
		zxBut.setOnClickListener(this);
		gdBut.setOnClickListener(this);

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
		int[] imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
		for (int i = 0; i < imageResId.length; i++) {
			final int imageItem = i;
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			// 设置图片点击事件
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					switch (imageItem) {
					case 0:
						intent.setClass(MainActivity.this, MyWebView.class);
						// intent.putExtra("url", "http://dl.5671.cc/");
						intent.putExtra("url", "http://www.nxgtjt.com");
						startActivity(intent);
						break;
					case 1:
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.baosteel.com/group/index.htm");
						startActivity(intent);
						break;
					case 2:
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.gtxh.com");
						startActivity(intent);
						break;
					case 3:
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.custeel.com");
						startActivity(intent);
						break;
					case 4:
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.csteelnews.com");
						startActivity(intent);
						break;
					}
					Toast.makeText(MainActivity.this, "您点击了第" + (myPager.getCurIndex() + 1) + "个广告位", Toast.LENGTH_SHORT).show();
				}
			});

			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ghBut:
			startActivity(new Intent(MainActivity.this, SupplyInfoListActivity.class));
			break;
		case R.id.qgBut:
			startActivity(new Intent(MainActivity.this, BuyInfoListActivity.class));
			break;
		case R.id.fbBut:
			startActivity(new Intent(MainActivity.this, PublishSupplyActivity.class));
			break;
		case R.id.ssBut:
			startActivity(new Intent(MainActivity.this, SearchActivity.class));
			break;
		case R.id.zxBut:
			startActivity(new Intent(MainActivity.this, RelevantInfoActivity.class));
			break;
		case R.id.gdBut:
			startActivity(new Intent(MainActivity.this, MoreActivity.class));
			break;
		}
	}

	/**
	 * 
	 * 描述：显示提示对话框
	 * 
	 * @throws
	 * @date：2013-11-19 上午10:36:52
	 * @version v1.0
	 */
	private void showMyDialog() {
		String titleInfo = null;
		Builder builder = new AlertDialog.Builder(this);
		dialog = builder.create();
		View retieve = LayoutInflater.from(this).inflate(R.layout.dialog_show, null);
		dialog.setView(retieve, 0, 0, 0, 0);
		Button acceptBtn = (Button) retieve.findViewById(R.id.acceptBtn);
		Button unAcceptBtn = (Button) retieve.findViewById(R.id.unAcceptBtn);
		TextView dialogTitleText1 = (TextView) retieve.findViewById(R.id.dialogTitleText1);
		TextView setMessage = (TextView) retieve.findViewById(R.id.setMessage);
		dialogTitleText1.setText("温馨提示");
		setMessage.setText("		你确定要退出程序吗？");
		acceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
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
}
