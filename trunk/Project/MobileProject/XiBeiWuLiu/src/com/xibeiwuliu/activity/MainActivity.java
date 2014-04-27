package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.view.MyImgScroll;

public class MainActivity extends AbActivity implements OnClickListener{

	private MyApplication application = null;
	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private LinearLayout layoutItem01, layoutItem02, layoutItem03, layoutItem04, layoutItem05, layoutItem06,layoutItem07,layoutItem08; 
	private List<View> listViews; // 图片组

	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_main);
//		application = (MyApplication) abApplication;
		initTitleLayout();
		initView();

	}

	/**
	 * 描述：初始化View
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initView() {
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
		
		InitViewPager();// 初始化图片
		// 开始滚动
		myPager.start(this, listViews, 4000, ovalLayout,
				R.layout.ad_bottom_item, R.id.ad_item_v,
				R.drawable.dot_focused, R.drawable.dot_normal);

		layoutItem01.setOnClickListener(this);
		layoutItem02.setOnClickListener(this);
		layoutItem03.setOnClickListener(this);
		layoutItem04.setOnClickListener(this);
		layoutItem05.setOnClickListener(this);
		layoutItem06.setOnClickListener(this);
		layoutItem07.setOnClickListener(this);
		layoutItem08.setOnClickListener(this);
	}

	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initTitleLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();

		// 添加左侧布局文件
		mAbTitleBar.setTitleText("首      页");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// 添加右侧布局文件
		// View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
		View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		about.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Intent intent = new Intent(MainActivity.this,
				// RegisterActivity.class);
				// startActivity(intent);
			}
		});
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

	/**
	 * 初始化图片
	 */
	private void InitViewPager() {
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.a, R.drawable.b,
				R.drawable.c, R.drawable.d, R.drawable.e };
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {// 设置图片点击事件
					Toast.makeText(MainActivity.this,
							"点击了:" + myPager.getCurIndex(), Toast.LENGTH_SHORT)
							.show();
				}
			});
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}

	public void onClick(View v) {
		Intent intent = new Intent();
		String Msg = null;
		switch (v.getId()) {
		case R.id.layoutItem01:
			Msg = "货源信息";
//			Toast.makeText(MainActivity.this, Msg, 5).show();
			intent.setClass(MainActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem02:
			Msg = "车源信息";
//			Toast.makeText(MainActivity.this, Msg, 5).show();
			intent.setClass(MainActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem03:
			Msg = "信息发布";
//			Toast.makeText(MainActivity.this, Msg, 5).show();
			intent.setClass(MainActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem04:
			Msg = "物流专线";
//			Toast.makeText(MainActivity.this, Msg, 5).show();
			intent.setClass(MainActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem05:
			Msg = "预约查询";
//			Toast.makeText(MainActivity.this, Msg, 5).show();
			intent.setClass(MainActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem06:
			Msg = "行业资讯";
//			Toast.makeText(MainActivity.this, Msg, 5).show();
			intent.setClass(MainActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem07:
//			Toast.makeText(MainActivity.this, "个人中心", 5).show();
			startActivity(new Intent(MainActivity.this, SettingCenterActivity.class));
			break;
		case R.id.layoutItem08:
			Msg = "更多";
//			Toast.makeText(MainActivity.this, Msg, 5).show();
			intent.setClass(MainActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		}
	}

}