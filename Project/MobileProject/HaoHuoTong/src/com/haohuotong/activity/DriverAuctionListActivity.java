package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.adapter.AuctionInfoAdapter;
import com.haohuotong.entity.AuctionInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.global.MyApplication;
import com.haohuotong.web.PublicInfoWeb;

public class DriverAuctionListActivity extends AbActivity {

	private AbPullListView listView = null;
	private AbTaskQueue mAbTaskQueue = null;
	private AuctionInfoAdapter acAdapter = null;
	private MyApplication application = null;
	private ArrayList<Parameter> driverCarParameterList = null;
	private List<AuctionInfo> mAuctionInfoList = null;
	private List<AuctionInfo> newAuctionInfoList = null;
    private int currentPage = 1;//分页
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.driver_auction_msg);
		initTitleLayout();
		application = (MyApplication) abApplication;
		mAbTaskQueue = AbTaskQueue.getInstance();
		listView = (AbPullListView)findViewById(R.id.listView);
		mAuctionInfoList = new ArrayList<AuctionInfo>();
		acAdapter = new AuctionInfoAdapter(this,mAuctionInfoList);
		listView.setAdapter(acAdapter);
        this.listView.setPullRefreshEnable(true);
        this.listView.setPullLoadEnable(true);
		
		upload();
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
		mAbTitleBar.setTitleText("我的竞价详情");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// 添加右侧布局文件
		/*View rightViewMore = mInflater.inflate(R.layout.more_btn1, null);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DriverAuctionListActivity.this, ConsultActivity.class);
				startActivity(intent);
			}
		});*/
	}
	
	public void upload(){
		driverCarParameterList = new ArrayList<Parameter>();
		// 定义异步执行的对象
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			//@Override
			public void update() {
				mAuctionInfoList.clear();
				if (newAuctionInfoList != null && newAuctionInfoList.size() > 0) {
					mAuctionInfoList.addAll(newAuctionInfoList);
					acAdapter.notifyDataSetChanged();
					newAuctionInfoList.clear();
				}
				listView.stopRefresh();
			}

			@Override
			public void get() {
				try {
					currentPage = 1;
					loadDate();
					newAuctionInfoList = new ArrayList<AuctionInfo>();
					newAuctionInfoList = PublicInfoWeb.driverInfoList("I_A008", driverCarParameterList);
				} catch (AbAppException e) {
					e.printStackTrace();
				}
			};
		};
		
		final AbTaskItem item2 = new AbTaskItem();
		item2.listener = new AbTaskListener() {

			//@Override
			public void update() {
				mAuctionInfoList.clear();
				if (newAuctionInfoList != null && newAuctionInfoList.size() > 0) {
					mAuctionInfoList.addAll(newAuctionInfoList);
					acAdapter.notifyDataSetChanged();
					newAuctionInfoList.clear();
				}
				listView.stopRefresh();
			}

			@Override
			public void get() {
				try {
					currentPage++;
					loadDate();
					newAuctionInfoList = new ArrayList<AuctionInfo>();
					newAuctionInfoList = PublicInfoWeb.driverInfoList("I_A008", driverCarParameterList);
					
				} catch (AbAppException e) {
					currentPage--;
					newAuctionInfoList.clear();
					e.printStackTrace();
				}
			};
		};
		listView.setAbOnListViewListener(new AbOnListViewListener() {

			@Override
			public void onRefresh() {
				mAbTaskQueue.execute(item);
			}

			@Override
			public void onLoadMore() {
				mAbTaskQueue.execute(item2);
			}

		});
		// 开始执行
		mAbTaskQueue.execute(item);
		
	}

    private void setDdriverCarParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		driverCarParameterList.add(parameter);
    }
    
    public void loadDate(){
		setDdriverCarParameterList("goods_info_id","");
		setDdriverCarParameterList("User_id", application.userInfo.getDriverUserInfo().getDuser_id()+"");
		setDdriverCarParameterList("Car_ID", application.userInfo.getDriverCarInfo().getAdd_id());
		setDdriverCarParameterList("Money_value",String.valueOf(0));
		setDdriverCarParameterList("JP_remark", " ");
		setDdriverCarParameterList("pageSize", String.valueOf(10));
		setDdriverCarParameterList("page", String.valueOf(currentPage));

    }
}