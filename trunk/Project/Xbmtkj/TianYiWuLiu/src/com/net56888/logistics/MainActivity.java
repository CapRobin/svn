package com.net56888.logistics;

import android.R.integer;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TabHost;

import com.net56888.logistics.ui.MoveBg;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：MainActivity.java
 * @Describe：程序首页
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午10:09:15
 * @Version v1.0O
 */
/**
 * Copyright (c) 2013 All rights reserved
 * @Name：MainActivity.java 
 * @Describe：TODO
 * @Author:  yfr5734@gmail.com
 * @Date：2014年12月29日 上午11:25:44
 * @Version v1.0
 */

public class MainActivity extends TabActivity {
	private TabHost tabHost;
	private TabHost.TabSpec tabSpec;
	private RadioGroup radioGroup;
	private RelativeLayout bottom_layout;
	private RadioGroup radiogroup;
	private ImageView img;
	private View view;
	private int startLeft;
	private int chekedItem = 0;
	private int itemWidth = 0;
	private RadioButton radio_news,  radio_topic, radio_pic, radio_follow = null;
	

	// private MyApplication application = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		intView();
	}
	

	//初始化View
	private void intView() {
		bottom_layout = (RelativeLayout) findViewById(R.id.layout_bottom);
		radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(checkedChangeListener);
		
		// 获取屏幕的1/4长度值
		WindowManager wm2 = MainActivity.this.getWindowManager();
		int width = wm2.getDefaultDisplay().getWidth();
		itemWidth = width / 4;

		// 设置底部菜单的高度
		android.view.ViewGroup.LayoutParams pp = radiogroup.getLayoutParams();
		radiogroup.getLayoutParams();
		pp.height = (int) (itemWidth * 0.7);
		radiogroup.setLayoutParams(pp);

		// 设置TabHost
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("news").setIndicator("News").setContent(new Intent(this, BrowserActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("topic").setIndicator("Topic").setContent(new Intent(this, SearchActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("picture").setIndicator("Picture").setContent(new Intent(this, PublishActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("follow").setIndicator("Follow").setContent(new Intent(this, MoreActivity.class)));

		radio_news = (RadioButton) findViewById(R.id.radio_news);
		radio_topic = (RadioButton) findViewById(R.id.radio_topic);
		radio_pic = (RadioButton) findViewById(R.id.radio_pic);
		radio_follow = (RadioButton) findViewById(R.id.radio_follow);
//		radio_news.performClick();
		// 设置标签切换动画视图
		view = LayoutInflater.from(this).inflate(R.layout.bgimage, null);
		img = (ImageView) view.findViewById(R.id.bgImage);
		android.view.ViewGroup.LayoutParams pps = img.getLayoutParams();
		img.getLayoutParams();
		pps.width = itemWidth;
		pps.height = (int) (itemWidth * 0.7);
		img.setLayoutParams(pps);

		// 初始写入标签动画视图
//		bottom_layout.addView(view);
		changeTextColor(chekedItem);
	}

	/**
	 * 标签切换事件监听
	 */
	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_news:
				tabHost.setCurrentTabByTag("news");
//				MoveBg.moveFrontBg(view, startLeft, 0, 0, 0);
				startLeft = 0;
				chekedItem = 0;
				break;
			case R.id.radio_topic:
				tabHost.setCurrentTabByTag("topic");
//				MoveBg.moveFrontBg(view, startLeft, itemWidth * 1, 0, 0);
				startLeft = itemWidth * 1;
				chekedItem = 1;
				break;
			case R.id.radio_pic:
				tabHost.setCurrentTabByTag("picture");
//				MoveBg.moveFrontBg(view, startLeft, itemWidth * 2, 0, 0);
				startLeft = itemWidth * 2;
				chekedItem = 2;
				break;
			case R.id.radio_follow:
				tabHost.setCurrentTabByTag("follow");
//				MoveBg.moveFrontBg(view, startLeft, itemWidth * 3, 0, 0);
				startLeft = itemWidth * 3;
				chekedItem = 3;
				break;
			}
			changeTextColor(chekedItem);
		}
	};
	
	
	/**
	 * @Describe：切换字体颜色
	 * @param item
	 * @Throws:  
	 * @Date：2014年12月29日 上午11:25:47
	 * @Version v1.0
	 */
	public void changeTextColor(int item) {
		switch (item) {
		case 0:
//			@drawable/bg_021
//			radio_news.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_021));
			radio_news.setTextColor(getResources().getColor(R.color.textColorChecked));
			radio_topic.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_pic.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_follow.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			break;
		case 1:
			radio_news.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_topic.setTextColor(getResources().getColor(R.color.textColorChecked));
			radio_pic.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_follow.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			break;
		case 2:
			radio_news.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_topic.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_pic.setTextColor(getResources().getColor(R.color.textColorChecked));
			radio_follow.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			break;
		case 3:
			radio_news.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_topic.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_pic.setTextColor(getResources().getColor(R.color.textColorUnChecked));
			radio_follow.setTextColor(getResources().getColor(R.color.textColorChecked));
			break;
		}
	}
	
}