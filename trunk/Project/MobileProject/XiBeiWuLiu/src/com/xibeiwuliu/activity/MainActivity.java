package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xibeiwuliu.database.SqliteDaoArea;
import com.xibeiwuliu.entity.AreaInfo;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.view.MyImgScroll;

/**
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：MainActivity.java
 * @Describe：物流首页
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午4:32:30
 * @Version v1.0 *
 * 
 */
public class MainActivity extends BaseActivity implements OnClickListener {

	private MyApplication application = null;
	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组
	private LinearLayout layoutItem01, layoutItem02, layoutItem03, layoutItem04, layoutItem05, layoutItem06, layoutItem07, layoutItem08;
	private boolean isShowRightBut = true; // 是否显示右边按钮
	private SqliteDaoArea daoArea = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.main);
		application = (MyApplication) abApplication;
		initTitleLayout("首页", isShowRightBut);
		initView();

	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年4月28日 上午11:07:32
	 * @Version v1.0
	 */
	private void initView() {
		daoArea = SqliteDaoArea.getInstance(MainActivity.this);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);
		layoutItem01 = (LinearLayout) findViewById(R.id.layoutItem01);
		layoutItem02 = (LinearLayout) findViewById(R.id.layoutItem02);
		layoutItem03 = (LinearLayout) findViewById(R.id.layoutItem03);
		layoutItem04 = (LinearLayout) findViewById(R.id.layoutItem04);
		layoutItem05 = (LinearLayout) findViewById(R.id.layoutItem05);
		layoutItem06 = (LinearLayout) findViewById(R.id.layoutItem06);
		layoutItem07 = (LinearLayout) findViewById(R.id.layoutItem07);
		layoutItem08 = (LinearLayout) findViewById(R.id.layoutItem08);
		// TextView wlzxText = (TextView) findViewById(R.id.wlzxText);
		layoutItem01.setOnClickListener(this);
		layoutItem02.setOnClickListener(this);
		layoutItem03.setOnClickListener(this);
		layoutItem04.setOnClickListener(this);
		layoutItem05.setOnClickListener(this);
		layoutItem06.setOnClickListener(this);
		layoutItem07.setOnClickListener(this);
		layoutItem08.setOnClickListener(this);
		// wlzxText.setText("物\/流\/专\/线");

		if (isShowRightBut) {
			this.rightTitleBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String Msg = "个人中心";
					Intent intent = new Intent(MainActivity.this, PersonalCenterActivity.class);
					intent.putExtra("msg", Msg);
					startActivity(intent);
				}
			});
		}

		// 初始化图片
		InitViewPager();
		// 开始滚动
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v, R.drawable.dot_focused, R.drawable.dot_normal);

		List<AreaInfo> strList = daoArea.getAreaInfo(0);
		// Toast.makeText(MainActivity.this, strList.get(0), 5).show();
		Toast.makeText(MainActivity.this, strList.get(0).getCcityName(), 5).show();
	}

	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	// private void initTitleLayout() {
	// AbTitleBar mAbTitleBar = this.getTitleBar();
	//
	// // 添加左侧布局文件
	// mAbTitleBar.setTitleText("首      页");
	// mAbTitleBar.setLogo(R.drawable.button_selector_back);
	// // mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
	// mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
	// mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
	// mAbTitleBar.setLogoLine(R.drawable.line);
	//
	// // 添加右侧布局文件
	// // View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
	// View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
	// mAbTitleBar.addRightView(rightViewMore);
	// Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
	// about.setOnClickListener(new View.OnClickListener() {
	//
	// public void onClick(View v) {
	// // Intent intent = new Intent(MainActivity.this,
	// // RegisterActivity.class);
	// // startActivity(intent);
	// }
	// });
	// }

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
						// intent.setData(Uri.parse("http://dl.5671.cc/"));
						// intent = Intent.createChooser(intent, null);
						// startActivity(intent);
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://dl.5671.cc/");
						startActivity(intent);
						break;
					case 1:
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://qy.58.com/17195629821703/");
						startActivity(intent);
						break;
					case 2:
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.cnpc.com.cn/cn/");
						startActivity(intent);
						break;
					case 3:
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://csl.chinawuliu.com.cn/");
						startActivity(intent);
						break;
					case 4:
						intent.setClass(MainActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.189.cn/");
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

	/**
	 * @Describe：项目点击事件
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * @Author: zhanglei
	 * @Date：2014年4月28日 下午4:21:50
	 * @Version v1.0
	 */
	public void onClick(View v) {
		Intent intent = new Intent();
		String Msg = null;
		switch (v.getId()) {
		case R.id.layoutItem01:
			Msg = "货源信息";
			intent.setClass(MainActivity.this, CargoListActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem02:
			Msg = "车源信息";
			intent.setClass(MainActivity.this, VehicleListActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem03:
			Msg = "物流专线";
			intent.setClass(MainActivity.this, SpecialLineActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem04:
			Msg = "预约查询";
			intent.setClass(MainActivity.this, OrderSearchActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem05:
			Msg = "发布货源";
			intent.setClass(MainActivity.this, PublishCargoActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem06:
			Msg = "发布车源";
			intent.setClass(MainActivity.this, PublishVehicleActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem07:
			Msg = "行业资讯";
			intent.setClass(MainActivity.this, RelevantInfoActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem08:
			Msg = "更多";
			intent.setClass(MainActivity.this, MoreActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		}
	}

	@Override
	protected void onRestart() {
		myPager.startTimer();
		super.onRestart();
	}

	@Override
	protected void onStop() {
		myPager.stopTimer();
		super.onStop();
	}

	public void stop(View v) {
		myPager.stopTimer();
	}

}