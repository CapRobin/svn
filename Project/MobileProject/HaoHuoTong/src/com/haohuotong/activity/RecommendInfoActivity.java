package com.haohuotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.ab.activity.AbActivity;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.adapter.CommonalityInfoAdapter;
import com.haohuotong.global.MyApplication;
import com.haohuotong.other.Config;
import com.haohuotong.other.EntitySet;
import com.haohuotong.other.InfoEntity;
import com.haohuotong.other.UserEntity;
import com.haohuotong.web.PublicInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name锛歊ecommendInfoActivity.java
 * @Describe锛氭帹鑽愪俊鎭� * @Author Administrator yfr5734@gmail.com
 * @Date锛�013-11-20 涓嬪崍2:37:41
 * @Version v1.0
 */
public class RecommendInfoActivity extends AbActivity {

	private MyApplication application;
	private EntitySet mList = null;
	private EntitySet mNewList = null;
	private AbPullListView mAbPullListView = null;
	private int currentPage = 1;
	private AbTaskQueue mAbTaskQueue = null;
	private CommonalityInfoAdapter myListViewAdapter = null;
	private PublicInfoWeb infoWeb = null;
	private int getIntentType = 0;

	private String mCity = "";
	private String mKeywords = "";
	private InfoEntity.Type mType = InfoEntity.Type.All;
	private EntitySet mEntitySet = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.pull_list);
		// 鑾峰彇Intent浼犻�鐨勬暟鎹�		getIntentType = getIntent().getIntExtra("intentType", 0);
		initTitleRightLayout();
		initView();
		getListData();

		// AbTitleBar mAbTitleBar = this.getTitleBar();
		// mAbTitleBar.setTitleText("鍏叡淇℃伅");
		// mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		// mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		// mAbTitleBar.setLogoLine(R.drawable.line);

	}

	private void initTitleRightLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();
		if (getIntentType == 0) {
			mAbTitleBar.setTitleText("鍏叡淇℃伅");
		} else {
			mAbTitleBar.setTitleText("鎺ㄨ崘淇℃伅");
		}
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		application = (MyApplication) abApplication;
	}

	/**
	 * 
	 * 鎻忚堪锛氬垵濮嬪寲
	 * 
	 * @throws
	 * @date锛�013-11-18 涓嬪崍5:52:11
	 * @version v1.0
	 */
	private void initView() {
		mAbTaskQueue = AbTaskQueue.getInstance();

		// 鑾峰彇ListView瀵硅薄
		mAbPullListView = (AbPullListView) this.findViewById(R.id.mListView);

		// 鎵撳紑鍏抽棴涓嬫媺鍒锋柊鍔犺浇鏇村鍔熻兘
		mAbPullListView.setPullRefreshEnable(true);
		mAbPullListView.setPullLoadEnable(true);

		mList = new EntitySet();

		// 浣跨敤鑷畾涔夌殑Adapter
//		myListViewAdapter = new CommonalityInfoAdapter(RecommendInfoActivity.this, mList);
		mAbPullListView.setAdapter(myListViewAdapter);
		// item琚偣鍑讳簨浠�
		mAbPullListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				InfoEntity entity = (InfoEntity) mList.get(position - 1);
				// Toast.makeText(CommonalityInfoActivity.this,
				// entity.getMsg_Content(), 5).show();
				Intent intent = new Intent(RecommendInfoActivity.this, FragmentCargoInfoDetail.class);
				// InfoEntity entity = (InfoEntity)
				// application.entitySet.get(position);
				intent.putExtra("DATA", entity);
				startActivity(intent);
			}
		});
		// 璇锋眰鍏ㄩ儴鎺ㄨ崘淇℃伅
		Config.getInstance().setType(InfoEntity.Type.All);
		UserEntity user = Config.getInstance().getUser();
		String city = Config.getInstance().getCity();
		if (null != user) {
			city = user.getMsg_City();
		}

		InfoEntity.Type type = Config.getInstance().getType();
		this.mCity = city;
		this.mKeywords = "";
		this.mType = type;

		if (this.mEntitySet != null)
			this.mEntitySet.clear();
		infoWeb = new PublicInfoWeb();
	}

	/**
	 * 
	 * 鎻忚堪锛氳幏鍙栧垪琛ㄦ暟鎹�	 * 
	 * @throws
	 * @date锛�013-11-18 涓嬪崍5:54:26
	 * @version v1.0
	 */
	private void getListData() {
		showProgressDialog();

		// 瀹氫箟涓ょ鏌ヨ鐨勪簨浠�		
		final AbTaskItem item1 = new AbTaskItem();
		item1.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeProgressDialog();
				mList.clear();
				if (mNewList != null && mNewList.size() > 0) {
					mList.addAll(mNewList);
					myListViewAdapter.notifyDataSetChanged();
					mNewList.clear();
				}
				mAbPullListView.stopRefresh();
			}

			@Override
			public void get() {
				try {
					Thread.sleep(1000);
					currentPage = 1;
					mNewList = new EntitySet();
					mNewList = infoWeb.getPagedInfoListSync2(mCity, mKeywords, mType, currentPage);
				} catch (Exception e) {
				}
			};
		};

		final AbTaskItem item2 = new AbTaskItem();
		item2.listener = new AbTaskListener() {

			@Override
			public void update() {
				if (mNewList != null && mNewList.size() > 0) {
					mList.addAll(mNewList);
					myListViewAdapter.notifyDataSetChanged();
					mNewList.clear();
//					mAbPullListView.stopLoadMore(true);
				} else {
					// 娌℃湁鏂版暟鎹簡
//					mAbPullListView.stopLoadMore(false);
				}

			}

			@Override
			public void get() {
				try {
					currentPage++;
					Thread.sleep(1000);

					mNewList = new EntitySet();
					mNewList = infoWeb.getPagedInfoListSync2(mCity, mKeywords, mType, currentPage);
				} catch (Exception e) {
					currentPage--;
					mNewList.clear();
					showToastInThread(e.getMessage());
				}
			};
		};

		mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {

			@Override
			public void onRefresh() {
				mAbTaskQueue.execute(item1);
			}

			@Override
			public void onLoadMore() {
				mAbTaskQueue.execute(item2);
			}

		});

		// 绗竴娆′笅杞芥暟鎹�		mAbTaskQueue.execute(item1);
	}
}
