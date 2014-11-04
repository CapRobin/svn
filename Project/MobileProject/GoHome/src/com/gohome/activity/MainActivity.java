package com.gohome.activity;

import java.util.ArrayList;
import java.util.List;

import com.gohome.R;
import com.gohome.view.MyImgScroll;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.main);
		titleBarInitView();
		initView();

//		// 构造用户个人信息
//		if (application.userInfo == null) {
//			makeData();
//		}
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
					showToast("设置");
//					if (application.isLogin) {
//						startActivity(new Intent(MainActivity.this, PersonalCenterActivity.class));
//					} else {
//						startActivity(new Intent(MainActivity.this, LoginActivity.class));
//					}
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
//			imageView.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					Intent intent = new Intent();
//					switch (imageItem) {
//					case 0:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						// intent.putExtra("url", "http://dl.5671.cc/");
//						intent.putExtra("url", "http://www.nxgtjt.com");
//						startActivity(intent);
//						break;
//					case 1:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						intent.putExtra("url", "http://www.baosteel.com/group/index.htm");
//						startActivity(intent);
//						break;
//					case 2:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						intent.putExtra("url", "http://www.gtxh.com");
//						startActivity(intent);
//						break;
//					case 3:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						intent.putExtra("url", "http://www.custeel.com");
//						startActivity(intent);
//						break;
//					case 4:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						intent.putExtra("url", "http://www.csteelnews.com");
//						startActivity(intent);
//						break;
//					}
//					Toast.makeText(MainActivity.this, "您点击了第" + (myPager.getCurIndex() + 1) + "个广告位", Toast.LENGTH_SHORT).show();
//				}
//			});

			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ghBut:
			startActivity(new Intent(MainActivity.this, LoginActivity.class));
			showToast("跳转页面_01");
			break;
		case R.id.qgBut:
			startActivity(new Intent(MainActivity.this, RegisterActivity.class));
			showToast("跳转页面_02");
			break;
		case R.id.fbBut:
//			startActivity(new Intent(MainActivity.this, PublishSupplyActivity.class));
			showToast("跳转页面_03");
			break;
		case R.id.ssBut:
//			startActivity(new Intent(MainActivity.this, SearchActivity.class));
			showToast("跳转页面_04");
			break;
		case R.id.zxBut:
//			startActivity(new Intent(MainActivity.this, RelevantInfoActivity.class));
			showToast("跳转页面_05");
			break;
		case R.id.gdBut:
//			startActivity(new Intent(MainActivity.this, MoreActivity.class));
			showToast("跳转页面_06");
			break;
		}
	}

	/**
	 * 
	 * @Describe：构造数据
	 * @Throws:
	 * @Date：2014年8月1日 下午1:33:36
	 * @Version v1.0
	 */
//	private void makeData() {
//		UserInfo userInfo = new UserInfo();
//
//		userInfo.setUserName("yfr5734");
//		userInfo.setGrade("普通会员");
//		userInfo.setLasttime("2014.08.30");
//
//		userInfo.setRealName("罗永康");
//		userInfo.setIdentityCard("640321198603153879");
//		userInfo.setSex("男");
//		userInfo.setAge("26");
//		userInfo.setMobile("13800000001");
//		userInfo.setTelephone("0951-5321564");
//		userInfo.setEmail("1542956428@qq.com");
//		userInfo.setIndustry("螺纹钢");
//		userInfo.setAddress("宁夏银川");
//		userInfo.setCompanyInfo("公司成立于1998年，专注钢铁行业多年，有一定的行业基类！");
//
//		application.userInfo = userInfo;
//	}

}
