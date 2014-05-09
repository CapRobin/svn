package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ab.task.AbTaskQueue;
import com.ab.view.pullview.AbPullListView;
import com.xibeiwuliu.adapter.RelevantInfoAdapter;
import com.xibeiwuliu.database.SqliteDaoArea;
import com.xibeiwuliu.entity.RelevantInfo;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.view.MyImgScroll;

/**
 * 
  * Copyright (c) 2013 All rights reserved
  * @Name：RelevantInfoActivity.java 
  * @Describe：TODO
  * @Author:  yfr5734@gmail.com
  * @Date：2014年5月8日 下午3:21:49
  * @Version v1.0 *
  *
 */
public class RelevantInfoActivity extends BaseActivity implements OnClickListener {

	private MyApplication application = null;
	private MyImgScroll myPager; // 图片容器
	private LinearLayout ovalLayout; // 圆点容器
	private List<View> listViews; // 图片组
	private boolean isShowRightBut = false; // 是否显示右边按钮
	private SqliteDaoArea daoArea = null;
	private AbPullListView cargoList = null;
	private AbTaskQueue mAbTaskQueue = null;
	private RelevantInfoAdapter myListViewAdapter = null;
	private List<RelevantInfo> mRelevantInfoList = null;
	private String getMsg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.relevant_info);
		application = (MyApplication) abApplication;
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
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
		daoArea = SqliteDaoArea.getInstance(RelevantInfoActivity.this);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);
//		cargoList = (AbPullListView) findViewById(R.id.cargoList);
		// 初始化图片
		InitViewPager();
		// 开始滚动
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v, R.drawable.dot_focused, R.drawable.dot_normal);
		
		mAbTaskQueue = AbTaskQueue.getInstance();

		// 获取ListView对象
		cargoList = (AbPullListView) findViewById(R.id.cargoList);
		// 打开关闭下拉刷新加载更多功能
		cargoList.setPullRefreshEnable(true);
		cargoList.setPullLoadEnable(true);

		mRelevantInfoList = new ArrayList<RelevantInfo>();
		
		//构造数据
		RelevantInfo info = null;
		for (int i = 0; i < 50; i++) {
			info = new RelevantInfo();
			info.setTitleName("资讯"+ i);
			info.setContent(getResources().getString(R.string.contentText)+i);
			info.setTime(String.valueOf(2001+i)+"_05_08");
			mRelevantInfoList.add(info);
		}
		

		// 使用自定义的Adapter
		myListViewAdapter = new RelevantInfoAdapter(RelevantInfoActivity.this, mRelevantInfoList);
		cargoList.setAdapter(myListViewAdapter);
		
		cargoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				RelevantInfo cargoInfo = (RelevantInfo) myListViewAdapter.getItem(position - 1);
				String infoName = cargoInfo.getTitleName();
				String infoContent = cargoInfo.getContent();
				String infoTime = cargoInfo.getTime();
//				Toast.makeText(RelevantInfoActivity.this, infoContent, 5).show();
				Intent intent = new Intent(RelevantInfoActivity.this, RelevantInfoDetailActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putSerializable("cargoInfo", cargoInfo);
//				intent.putExtra("bundle", bundle);
				intent.putExtra("infoName", infoName);
				intent.putExtra("infoContent", infoContent);
				intent.putExtra("infoTime", infoTime);
				startActivity(intent);
			}
		});
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
		int[] imageResId = new int[] { R.drawable.hyzx_01, R.drawable.hyzx_02, R.drawable.hyzx_03, R.drawable.hyzx_04, R.drawable.hyzx_05, R.drawable.hyzx_06 };
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
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://dl.5671.cc/");
						startActivity(intent);
						break;
					case 1:
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://qy.58.com/17195629821703/");
						startActivity(intent);
						break;
					case 2:
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.cnpc.com.cn/cn/");
						startActivity(intent);
						break;
					case 3:
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://csl.chinawuliu.com.cn/");
						startActivity(intent);
						break;
					case 4:
						intent.setClass(RelevantInfoActivity.this, MyWebView.class);
						intent.putExtra("url", "http://www.189.cn/");
						startActivity(intent);
						break;
					}
					Toast.makeText(RelevantInfoActivity.this, "您点击了第" + (myPager.getCurIndex() + 1) + "个广告位", Toast.LENGTH_SHORT).show();
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
			// Toast.makeText(MainActivity.this, Msg, 5).show();
			intent.setClass(RelevantInfoActivity.this, CargoListActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem02:
			Msg = "车源信息";
			intent.setClass(RelevantInfoActivity.this, VehicleListActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem03:
			Msg = "物流专线";
			intent.setClass(RelevantInfoActivity.this, SpecialLineActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem04:
			Msg = "预约查询";
			intent.setClass(RelevantInfoActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem05:
			Msg = "发布货源";
			intent.setClass(RelevantInfoActivity.this, PublishCargoActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem06:
			Msg = "发布车源";
			intent.setClass(RelevantInfoActivity.this, PublishVehicleActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem07:
			Msg = "行业资讯";
			intent.setClass(RelevantInfoActivity.this, TestActivity.class);
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.layoutItem08:
			Msg = "更多";
			intent.setClass(RelevantInfoActivity.this, TestActivity.class);
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