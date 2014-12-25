package com.haohuotong.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.haohuotong.R;
import com.haohuotong.global.MyApplication;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * @Name：CommonalityInfoActivity_Y.java 
 * @Describe：公共信息数据
 * @Author Administrator  yfr5734@gmail.com
 * @Date：2013-11-19 下午5:53:47
 * @Version v1.0
 */
public class CommonalityInfoActivity extends FragmentActivity implements TabListener, OnClickListener {

	private ViewPager mViewPager;
	public Button backBut, publishInfo = null;
	public static final int MAX_TAB_SIZE = 2;
	public static final String ARGUMENTS_NAME = "args";
	private TabFragmentPagerAdapter mAdapter;
	private MyApplication application = null;
	private int infoType = 0;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(CommonalityInfoActivity.this, msg.getData().getString("Msg"), 5).show();
				break;
			case 1:
				
				break;
			case 2:
				
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		application = (MyApplication) getApplication();
//		findViewById();
		mViewPager = (ViewPager) this.findViewById(R.id.pager);
		
		initView();
	}

//	private void findViewById() {
//		mViewPager = (ViewPager) this.findViewById(R.id.pager);
//	}

	private void initView() {

		final ActionBar mActionBar = getActionBar();
		backBut = (Button) findViewById(R.id.backBut);
		publishInfo = (Button) findViewById(R.id.publishInfo);
		
		backBut.setOnClickListener(this);
		publishInfo.setOnClickListener(this);
		
		View view = LayoutInflater.from(this).inflate(R.layout.title_bar_view, null);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setCustomView(view);
		// 设置ActionBar标题不显示
		// mActionBar.setDisplayShowTitleEnabled(false);
		// 设置ActionBar的背景
		mActionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_bg));
		mActionBar.setDisplayShowTitleEnabled(false);
		// 设置ActionBar左边默认的图标是否可用
		mActionBar.setDisplayUseLogoEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mActionBar.setLogo(R.drawable.button_selector_back);

		mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

				mActionBar.setSelectedNavigationItem(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
//		mActionBar.getDisplayOptions(
		
//		mActionBar.setOnActionBarListener(new OnActionBarListener() {
//
//			@Override
//			public void onActionBarItemClicked(int position) {
//
//				if (position == ActionBar.OnActionBarListener.HOME_ITEM) {
//
//					// 当按左边的主页按钮时所触发的操作
////					Toast.makeText(MainActivity.this, "home or back",
////							Toast.LENGTH_SHORT).show();
//					finish();
//					return;
//
//				}
//			}
//		});

		// ��ʼ�� ActionBar
		for (int i = 0; i < MAX_TAB_SIZE; i++) {
			Tab tab = mActionBar.newTab();
			if (i == 0) {
				tab.setText("货源信息").setTabListener(this);
			} else if (i == 1) {
				tab.setText("车源信息").setTabListener(this);
			}
			// tab.setText(mAdapter.getPageTitle(i)).setTabListener(this);
			mActionBar.addTab(tab);
		}
	}

	public static class TabFragmentPagerAdapter extends FragmentPagerAdapter {

		public TabFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment ft = null;
			switch (arg0) {
			case 0:
				ft = new FragmentCargoInfo();
				break;
			case 1:
				ft = new FragmentVehicleInfo();
				break;

			// default:
			// ft = new CommonUIFragment();
			//
			// Bundle args = new Bundle();
			// args.putString(ARGUMENTS_NAME, "TAB" + (arg0 + 1));
			// ft.setArguments(args);
			//
			// break;
			}
			return ft;
		}

		@Override
		public int getCount() {

			return MAX_TAB_SIZE;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "TAB " + (position + 1);
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
		//tab.getPosition() = 0 货源信息；tab.getPosition() = 1 车源信息
		infoType = tab.getPosition();
		if (infoType == 1) {
//			sendMsgUpdateUI(0, "暂时没有车源信息！");
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case android.R.id.home:
//			Intent intent = new Intent(this, MainActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			return true;
			break;
		case R.drawable.button_selector_back:
			Toast.makeText(CommonalityInfoActivity.this, "公共信息", 5).show();
			break;
		default:
			break;
		}
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			Intent intent = new Intent(this, MainActivity.class);
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
//			return true;
//		}
		Toast.makeText(CommonalityInfoActivity.this, "公共信息", 5).show();
		return false;
//		return super.onOptionsItemSelected(item)
	}
	
	/**
	 * 描述：发送消息刷新UI 第一参数是0
	 * 
	 * @param what
	 * @param toast
	 * @throws
	 * @date：2013-11-19 下午1:28:08
	 * @version v1.0
	 */
	public void sendMsgUpdateUI(int what, String toast) {
		Message msg = handler.obtainMessage(what);
		Bundle bundle = new Bundle();
		bundle.putString("Msg", toast);
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

	public void showDialogMsg(int type) {
		switch (type) {
		case 0:
			showDialog(0);
			break;
		case 1:
			removeDialog(0);
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBut:
			finish();
			break;
		case R.id.publishInfo:
			Intent intent2 = new Intent(CommonalityInfoActivity.this, PublicCargoInfoActivity.class);
			startActivity(intent2);
//			if (application.isLogin) {
//				if (application.userInfo.getUserType() == 1) {
//					Intent intent1 = new Intent(CommonalityInfoActivity.this, PublicVehicleInfoActivity.class);
//					startActivity(intent1);
//				}else {
//					Intent intent2 = new Intent(CommonalityInfoActivity.this, PublicCargoInfoActivity.class);
//					startActivity(intent2);
//				}
//			} else {
//				Toast.makeText(CommonalityInfoActivity.this, "登录后才能发布信息！", 5).show();
//				Intent intent3 = new Intent(CommonalityInfoActivity.this, LoginActivity.class);
//				startActivity(intent3);
//			}
			break;
		}

	}
}
