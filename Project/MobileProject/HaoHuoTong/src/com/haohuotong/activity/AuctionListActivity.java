package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.task.AbThread;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.adapter.AuctionInfoAdapter;
import com.haohuotong.entity.AuctionInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.global.MyApplication;
import com.haohuotong.web.PublicInfoWeb;

public class AuctionListActivity extends AbActivity {

	private AbPullListView listView = null;
	private Button comp = null;
	private EditText edt = null;
	private AbTaskQueue mAbTaskQueue = null;
	private AuctionInfoAdapter acAdapter = null;
	private MyApplication application = null;
	private ArrayList<Parameter> driverCarParameterList = null;
	private List<AuctionInfo> mAuctionInfoList = null;
	private List<AuctionInfo> newAuctionInfoList = null;
//	private List<AuctionInfo> auctionInfoList = null;
	private boolean isAuction = false;
	private int carInfoId = 0 ;
    private int money = 0 ;
    private int currentPage = 1;//分页
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.auction_msg);
		initTitleLayout();
		carInfoId = getIntent().getIntExtra("cargoInfoId", 0);
//		money = getIntent().getStringExtra("money");
		application = (MyApplication) abApplication;
		mAbTaskQueue = AbTaskQueue.getInstance();
		listView = (AbPullListView)findViewById(R.id.listView);
		comp = (Button) findViewById(R.id.comp);
		edt = (EditText) findViewById(R.id.edt);
		mAuctionInfoList = new ArrayList<AuctionInfo>();
		acAdapter = new AuctionInfoAdapter(this,mAuctionInfoList);
		listView.setAdapter(acAdapter);
        this.listView.setPullRefreshEnable(true);
        this.listView.setPullLoadEnable(true);
		
		load();
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
		mAbTitleBar.setTitleText("竞价信息");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// 添加右侧布局文件
		View rightViewMore = mInflater.inflate(R.layout.more_btn1, null);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AuctionListActivity.this, DriverAuctionListActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public void compOnClick(View view){
		if(edt.getText().toString().trim() != null && !"".equals(edt.getText().toString().trim())){
			money = Integer.parseInt(edt.getText().toString().trim());
			edt.setText("");
	        showMyDialog();
		}else{
			Toast.makeText(this, "请输入您的运价！",5).show();
		}
		
		
		
		
//		load();
		
	}
	
	public void load(){
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
					driverCarParameterList.clear();
					setDdriverCarParameterList("goods_info_id", carInfoId+"");
					setDdriverCarParameterList("pageSize", String.valueOf(10));
					setDdriverCarParameterList("page", String.valueOf(currentPage));
					newAuctionInfoList = new ArrayList<AuctionInfo>();
					newAuctionInfoList = PublicInfoWeb.driverInfoList("I_A006", driverCarParameterList);

				} catch (AbAppException e) {
					e.printStackTrace();
				}
			};
		};
		
		final AbTaskItem item2 = new AbTaskItem();
		item2.listener = new AbTaskListener() {

			//@Override
			public void update() {
				if (newAuctionInfoList != null && newAuctionInfoList.size() > 0) {
					mAuctionInfoList.clear();
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
					setDdriverCarParameterList("goods_info_id", carInfoId+"");
					setDdriverCarParameterList("pageSize", String.valueOf(10));
					setDdriverCarParameterList("page", String.valueOf(currentPage));
					newAuctionInfoList = new ArrayList<AuctionInfo>();
					newAuctionInfoList = PublicInfoWeb.driverInfoList("I_A006", driverCarParameterList);
					Log.i("tag", "=======================newAuctionInfoList.size()="+newAuctionInfoList.size());
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
	
	public void update(){
		driverCarParameterList = new ArrayList<Parameter>();
		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对象
		final AbTaskItem item3 = new AbTaskItem();
		item3.listener = new AbTaskListener() {
  
			@Override
			public void update() {
				if (isAuction) {
					showToastInThread("竞价信息发送成功！");
				} else {
					showToastInThread("竞价信息发送失败！");
				}
			}

			@Override
			public void get() {
				try {
					setDdriverCarParameterList("goods_info_id",  carInfoId+"");
					setDdriverCarParameterList("User_id", application.userInfo.getDriverUserInfo().getDuser_id()+"");
					setDdriverCarParameterList("Car_ID", 0+"");
					setDdriverCarParameterList("Money_value",String.valueOf(money));
					setDdriverCarParameterList("JP_remark", " ");
					setDdriverCarParameterList("pageSize", String.valueOf(10));
					setDdriverCarParameterList("page", String.valueOf(1));
					isAuction=PublicInfoWeb.sendAuction("I_A008", driverCarParameterList);

				} catch (AbAppException e) {
					e.printStackTrace();
				}
			};
		};
		// 开始执行
		mAbTaskThread.execute(item3);
	}

    private void setDdriverCarParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		driverCarParameterList.add(parameter);
    }
    
    /*
	 * 竞价窗口
	 */
	
	private void showMyDialog() {
		Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog = builder.create();
		View retieve = LayoutInflater.from(this).inflate(R.layout.dialog_show_comp, null);
		dialog.setView(retieve, 0, 0, 0, 0);
		Button acceptBtn = (Button) retieve.findViewById(R.id.acceptBtn);
		Button unAcceptBtn = (Button) retieve.findViewById(R.id.unAcceptBtn);
		TextView dialogTitleText1 = (TextView) retieve.findViewById(R.id.dialogTitleText1);
		TextView cargoodsInfoId = (TextView) retieve.findViewById(R.id.com_infoId);
		TextView AucName = (TextView) retieve.findViewById(R.id.com_name);
		TextView moenyTxt = (TextView) retieve.findViewById(R.id.moeny_value);
		mAbTaskQueue = AbTaskQueue.getInstance();
		dialogTitleText1.setText("请确认运价信息");
		cargoodsInfoId.setText(carInfoId+"");
		AucName.setText(application.userInfo.getDriverUserInfo().getDuser_name());
		moenyTxt.setText(String.valueOf(money));
		acceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				update();
				dialog.dismiss();
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