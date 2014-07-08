package com.estar.NMGMClient;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.estar.comm.AlwaysMarqueeTextView;
import com.estar.comm.AutoScrollTextView;
import com.estar.comm.MsgInfos;
import com.estar.comm.MsgInfosSet;
import com.estar.comm.MyBaseAdapter;
import com.estar.comm.MyListView;
import com.estar.comm.MySuperActivity;
import com.estar.comm.MySuperApplication;
import com.estar.data.DataSource;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends MySuperActivity implements MyListView.IOnRefreshListener,MyListView.IOnLoadMoreListener{
	//手机通知栏相关参数
	//============================================================
	//声明通知（消息）管理器   
	private NotificationManager m_NotificationManager;  
	private Intent  m_Intent;  
	private PendingIntent m_PendingIntent;  
	//声明Notification对象  
	private Notification  m_Notification; 
	//============================================================
	//底部滚动条相关参数
	AutoScrollTextView EndAdv;
	//相关按钮
	Button mStartCity,mStopCity,mSearchBtn,mAddBtn;
	CheckBox mScrollCheckBox;
	//
	private int selectedStartProvinceIndex = 0;
	private int selectedStartCityIndex = 0;
	private int selectedStartTownIndex = 0;
	private int selectedStopProvinceIndex = 0;
	private int selectedStopCityIndex = 0;
	private int selectedStopTownIndex = 0;
	private int m_iStartSign=0;
	private int m_iStopSign=0;
	private int selectedProvinceIndex = 0;
	private int selectedCityIndex = 0;
	private int selectedTownIndex = 0;
	//
	private String m_strStartCity;
	private String m_strStopCity;
	private String m_strType,m_strTableType;
	private MySuperApplication app;
	private Handler mainMsgHander=null;  
	//
	private int mStringID;
	private String mString;
	//
	private long mMaxID=0;
	private boolean showBottomView=true;
	private int mCurPageNum=0;
	//==============================================================
	//信息显示栏目相关参数
	/** Called when the activity is first created. */
	private MyListView mListView;
	private MyBaseAdapter adapter;
	private RefreshDataAsynTask mRefreshAsynTask;
	private LoadMoreDataAsynTask mLoadMoreAsynTask;

	boolean bRefresh=true;
	Timer timer=null;
	TimerTask task=null;
	Handler handler=null;
	//==============================================================
	private ProgressDialog mypDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//设置全局变量相关
		app = (MySuperApplication) getApplication(); //获得我们的应用程序MySuperApplication
		mainMsgHander=new Handler();  //创建属于主线程的handler   
		//设置底部滚动条
		EndAdv=(AutoScrollTextView)findViewById(R.id.endadv);
		Handler advHander=new Handler();  //创建属于主线程的handler   
		advHander.post(runnableSetEndAdvText);   //更新界面UI(提示)
		//
		//---------设置提示框--------------------------------
	    mypDialog = new ProgressDialog(this);// 实例化   
		// 设置进度条风格，风格为长形进度条，STYLE_SPINNER为圆的   
		//mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
		//mypDialog.setTitle("登陆");// 设置ProgressDialog 标题   
		//mypDialog.setMessage("正在验证请稍后...");// 设置ProgressDialog 提示信息   
		mypDialog.setIndeterminate(false);// 设置ProgressDialog   
		mypDialog.setCancelable(true);// 是否可以按退回按键取消   
		//mypDialog.setMax(100);// 设置最大值
		//-----------------------------------------------------
		//相关Button
		mStartCity=(Button)findViewById(R.id.startCityBtn);
		mStopCity=(Button)findViewById(R.id.stopCityBtn);
		mSearchBtn=(Button)findViewById(R.id.searchBtn);
		mAddBtn=(Button)findViewById(R.id.addBtn);
		mScrollCheckBox=(CheckBox)findViewById(R.id.scrollCheckBox);
		//设置按钮显示值
		m_strStartCity=app.GetConfigValue("startcity");
		m_strStopCity=app.GetConfigValue("stopcity");
		m_strType="8";//信息搜索类型，默认为8（8：全部；0：货源；1：车源）
		if(app.myinfo.usertypes==1){//钢材会员
			m_strTableType=getString(R.string.typeInfo1Txt);
		}else if(app.myinfo.usertypes==2){//煤炭会员
			m_strTableType=getString(R.string.typeInfo2Txt);
		}
		app.m_strTableType=m_strTableType;
		//
		mStartCity.setText(m_strStartCity);
		mStopCity.setText(m_strStopCity);
		//选择层次
		String startsign=app.GetConfigValue("startsign");
		if(startsign.equals(""))
		{
			startsign="0";
		}
		String stopsign=app.GetConfigValue("stopsign");
		if(stopsign.equals(""))
		{
			stopsign="0";
		}
		m_iStartSign=Integer.parseInt(startsign);
		m_iStopSign=Integer.parseInt(stopsign);
		//选择省市县
		String strSelectedStartProvinceIndex=app.GetConfigValue("selectedStartProvinceIndex");
		if(strSelectedStartProvinceIndex.equals(""))
		{
			strSelectedStartProvinceIndex="0";
		}
		String strSelectedStartCityIndex=app.GetConfigValue("selectedStartCityIndex");
		if(strSelectedStartCityIndex.equals(""))
		{
			strSelectedStartCityIndex="0";
		}
		String strSelectedStopProvinceIndex=app.GetConfigValue("selectedStopProvinceIndex");
		if(strSelectedStopProvinceIndex.equals(""))
		{
			strSelectedStopProvinceIndex="0";
		}
		String strSelectedStopCityIndex=app.GetConfigValue("selectedStopCityIndex");
		if(strSelectedStopCityIndex.equals(""))
		{
			strSelectedStopCityIndex="0";
		}
		String strSelectedStopTownIndex=app.GetConfigValue("selectedStopTownIndex");
		if(strSelectedStopTownIndex.equals(""))
		{
			strSelectedStopTownIndex="0";
		}
		selectedStartProvinceIndex=Integer.parseInt(strSelectedStartProvinceIndex);
		selectedStartCityIndex=Integer.parseInt(strSelectedStartCityIndex);
		selectedStopProvinceIndex=Integer.parseInt(strSelectedStopProvinceIndex);
		selectedStopCityIndex=Integer.parseInt(strSelectedStopCityIndex);
		selectedStopTownIndex=Integer.parseInt(strSelectedStopTownIndex);
		//点击开始城市
		mStartCity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示对话框
				if(m_iStartSign==0)//省份
				{
					ShowProvinceDlg(mStartCity,0);
				}else if(m_iStartSign==1)//城市
				{
					ShowCityDlg(mStartCity,0);
				}else if(m_iStartSign==2)//县区
				{
					ShowTownDlg(mStartCity,0);
				}
			}
		});
		//点击到达城市
		mStopCity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示对话框
				if(m_iStopSign==0)//省份
				{
					ShowProvinceDlg(mStopCity,1);
				}else if(m_iStopSign==1)//城市
				{
					ShowCityDlg(mStopCity,1);
				}else if(m_iStopSign==2)//县区
				{
					ShowTownDlg(mStopCity,1);
				}
			}
		});
		//点击查询按钮
		mSearchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//显示查询菜单
				if(app.myinfo.usertypes==1){//钢材
					ShowGCSearchMenuDlg();
				}else if(app.myinfo.usertypes==2){//煤炭
					ShowMTSearchMenuDlg();
				}
				
				//Search();
				
			}
		});
		//点击发布按钮
		mAddBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//显示发布菜单
				if(app.myinfo.usertypes==1){//钢材
					ShowGCAddMenuDlg();
				}else if(app.myinfo.usertypes==2){//煤炭
					ShowMTAddMenuDlg();
				}
			}
		});
		//初始化NotificationManager对象   	
		m_NotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); 
		//显示通知栏
		showNotification(MainActivity.this);
		//====================================================
		mCurPageNum=1;
		//信息栏目显示
		initView(); 
        //initData();
		//initData(m_strStartCity,m_strStopCity,m_strType,String.valueOf(mCurPageNum));
        //mListView.setAdapter(adapter);
		handler = new Handler(){    
	        public void handleMessage(Message msg) {    
	            switch (msg.what) {        
	            case 1:        
	                OnRefresh();//刷新显示新信息   
	                break;
	            case 2://正在读取
	    			mypDialog.setMessage(getString(R.string.string04));
	    			mypDialog.show();  
	    			break;
	            case 3://
	            	//Log.e("!!!!!!!!!!showBottomView",String.valueOf(showBottomView));
	            	//是否显示底部界面
	            	if(showBottomView)
	            		mListView.onLoadMoreComplete(false);//显示
	            	else
	            		mListView.onLoadMoreComplete(true);//隐藏
	            	//刷新显示界面
	            	mListView.setAdapter(adapter);
	            	//
	    			mypDialog.setMessage(getString(R.string.string06));
	    			mypDialog.show();  
	    			break; 
	            case 4://
	    			//mypDialog.setMessage("");
	    			mypDialog.cancel();  
	    			break;
	            case 5://
	            	//是否显示底部界面
	            	if(showBottomView)
	            		mListView.onLoadMoreComplete(false);//显示
	            	else
	            		mListView.onLoadMoreComplete(true);//隐藏
	            	
	    			break;
	            }        
	            //super.handleMessage(msg);    
	        }    
	            
	    };
		
		mListView.setOnRefreshListener(this);
		mListView.setOnLoadMoreListener(this);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
				// TODO Auto-generated method stub
				MyListView myListView=(MyListView)parent;
				MsgInfos item=(MsgInfos)myListView.getItemAtPosition(position);
				//item.setTableType(m_strTableType);
				//显示
				//String str=item.getID()+item.getMsg_Content();
				//Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();  
				//详情（通过putExtra传递参数）    
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, Detail.class);
				intent.putExtra("MSGINFODATA", item);
				MainActivity.this.startActivity(intent);
			}
		});
    	
		
		//--------------------------------------------------------------------  
		//5秒钟读取一次信息（自动刷新读取）
		//点击滚动复选框CheckBox
		mScrollCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(mScrollCheckBox.isChecked())//滚动
				{
					//开始自动滚动信息
					mStringID=R.string.startscrollTxt;
					mainMsgHander.post(runnableUiStringID);
					//
					bRefresh=true;
					//开始刷新timer
					startTimer();
				}else//
				{
					//停止滚动信息
					mStringID=R.string.stopscrollTxt;
					mainMsgHander.post(runnableUiStringID);
					//
					bRefresh=false;
					//停止 timer
					stopTimer();
				}
			}
		});
		//
		//-----------------------------------------------------------------------------------------
        //====================================================
		//线程进行查询
				new Thread(){

					/* (non-Javadoc)
					 * @see java.lang.Thread#run()
					 */
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//正在加载中...
						Message msg = new Message();  
				        msg.what = 2;// 开始下载标志1   
				        handler.sendMessage(msg);  
				        //进行搜索
				        initData(m_strTableType,m_strStartCity,m_strStopCity,m_strType,String.valueOf(mCurPageNum));
						//
						msg = new Message();  
				        msg.what = 3;// 加载完毕 
				        handler.sendMessage(msg);  
				        //
						msg = new Message();  
				        msg.what = 4;// 结束
				        handler.sendMessage(msg); 
				        //自动刷新
				        startTimer();
					}
					
				}.start();
	}
	//钢材信息发布框
	protected void ShowAddGCDlg() {
		// TODO Auto-generated method stub
		//显示窗体
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, add2.class);
		MainActivity.this.startActivity(intent);
	}
	//煤炭信息发布框
	protected void ShowAddMCDlg() {
		// TODO Auto-generated method stub
		//显示窗体
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, add3.class);
		MainActivity.this.startActivity(intent);
	}
	//物流信息发布框
	protected void ShowAddWLDlg() {
		// TODO Auto-generated method stub
		//显示窗体
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, add1.class);
		MainActivity.this.startActivity(intent);
	}
	//煤炭
	protected void ShowMTAddMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		.setTitle("选择需要发布")
		.setItems(R.array.mtBtn_item, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strType=getResources().getStringArray(R.array.mtBtn_item)[which];
				if(strType.equals(getString(R.string.typeInfo3Txt))){
					ShowAddWLDlg();
				}else{
					ShowAddMCDlg();
				}
			}
		})
		.create();
		alertDialog.show();
	}
	
	//钢材
	protected void ShowGCAddMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		.setTitle("选择需要发布")
		.setItems(R.array.gcBtn_item, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strType=getResources().getStringArray(R.array.gcBtn_item)[which];
				if(strType.equals(getString(R.string.typeInfo3Txt))){
					ShowAddWLDlg();
				}else{
					ShowAddGCDlg();
				}
			}
		})
		.create();
		alertDialog.show();
	}
	
	//煤炭搜索菜单选项
	protected void ShowMTSearchMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		//.setTitle("选择")
		.setItems(R.array.mtBtn_item, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strType=getResources().getStringArray(R.array.mtBtn_item)[which];
				//mTypeBtn.setText(strType);
				m_strTableType=strType;
				app.m_strTableType=m_strTableType;
				//
				Search();
			}
		})
		.create();
		alertDialog.show();
	}
	//钢材搜索菜单选项
	protected void ShowGCSearchMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		//.setTitle("选择")
		.setItems(R.array.gcBtn_item, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strType=getResources().getStringArray(R.array.gcBtn_item)[which];
				//mTypeBtn.setText(strType);
				m_strTableType=strType;
				app.m_strTableType=m_strTableType;
				//
				Search();
			}
		})
		.create();
		alertDialog.show();
	}
	//搜索函数
	protected void Search(){
		//出发地
		m_strStartCity=mStartCity.getText().toString();
		//到达地
		m_strStopCity=mStopCity.getText().toString();
		//
		mCurPageNum=1;
        showBottomView=true;
		//
		///if(m_strStartCity.equals(m_strStopCity) && (m_strStartCity.equals("") || m_strStartCity.equals(getString(R.string.quanguo)))){
		///	mStringID=R.string.searchCheckTxt1;
    	///	mainMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
		///}else
		///{
			//线程进行查询
			new Thread(){

				/* (non-Javadoc)
				 * @see java.lang.Thread#run()
				 */
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//super.run();
					//重写下 到达地
					app.SetConfig("stopcity",m_strStopCity);
					//正在加载中...
					Message msg = new Message();  
			        msg.what = 2;// 开始下载标志1   
			        handler.sendMessage(msg);  
			        //进行搜索
					initData(m_strTableType,m_strStartCity,m_strStopCity,m_strType,String.valueOf(mCurPageNum));
					//
					msg = new Message();  
			        msg.what = 3;// 加载完毕 
			        handler.sendMessage(msg);  
			        //
					msg = new Message();  
			        msg.what = 4;// 结束
			        handler.sendMessage(msg); 
				}
				
			}.start();
		///}
	}
	/*
	 * 选择省份城市县区相关函数
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected void ShowProvinceDlg(final Button CityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//出发城市
			selectedProvinceIndex=selectedStartProvinceIndex;
		}else if(iSign==1){//到达城市
			selectedProvinceIndex=selectedStopProvinceIndex;
		}
		AlertDialog alertDialog = new AlertDialog.Builder(this)
	    .setTitle(getString(R.string.xuanzhe))
	    //.setIcon(R.drawable.ic_launcher)
	    .setSingleChoiceItems(R.array.province_item, selectedProvinceIndex, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				selectedProvinceIndex = which;
				if(iSign==0){//出发城市
					selectedStartProvinceIndex=selectedProvinceIndex;
					app.SetConfig("selectedStartProvinceIndex",String.valueOf(selectedProvinceIndex));
				}else if(iSign==1){//到达城市
					selectedStopProvinceIndex=selectedProvinceIndex;
					app.SetConfig("selectedStopProvinceIndex",String.valueOf(selectedProvinceIndex));
				}
			}
	    	
	    }).setPositiveButton(getString(R.string.xiaji), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	//显示省选择
	        	showProvince(CityBtn,iSign);
	        	if(selectedProvinceIndex>0)
	        	{
		        	//显示市选择对话框 
		        	ShowCityDlg(CityBtn,iSign);
	        	}
	        }
	    }).setNeutralButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	//设置选择层数
	        	if(iSign==0){//出发城市
	        		//出发地不允许选择省份
	        		if(selectedProvinceIndex>0){
	        			mStringID=R.string.startcitynoprovincetxt;
		        		mainMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
		        		return;
	        		}
	        		m_iStartSign=0;//省份层次
	        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//到达城市
					m_iStopSign=0;//省份层次
					app.SetConfig("stopsign",String.valueOf(m_iStopSign));
				}
	        	showProvince(CityBtn,iSign);
	        }
	       }).setNegativeButton(getString(R.string.quanxuan), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		         // TODO Auto-generated method stub
		        	//设置选择层数
		        	if(iSign==0){//出发城市
		        		//出发地不允许选择省份
		        		if(selectedProvinceIndex>0){
		        			mStringID=R.string.startcitynoallquanxuantxt;
			        		mainMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
			        		return;
		        		}
		        		m_iStartSign=0;//省份层次
		        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
					}else if(iSign==1){//到达城市
						m_iStopSign=0;//省份层次
						app.SetConfig("stopsign",String.valueOf(m_iStopSign));
					}
		        	showAllProvince(CityBtn,iSign);
		        }
		       }).create();

		alertDialog.show();
	}
	//
	protected void showProvince(Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//出发城市
			selectedProvinceIndex=selectedStartProvinceIndex;
		}else if(iSign==1){//到达城市
			selectedProvinceIndex=selectedStopProvinceIndex;
		}
		String strProvince=getResources().getStringArray(R.array.province_item)[selectedProvinceIndex];
		cityBtn.setText(strProvince);
		if(iSign==0){//出发城市
			m_strStartCity=strProvince;
			app.SetConfig("startcity",strProvince);
		}else if(iSign==1){//到达城市
			m_strStopCity=strProvince;
			app.SetConfig("stopcity",strProvince);
		}
	}
	//
	protected void showAllProvince(Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//出发城市
			selectedProvinceIndex=selectedStartProvinceIndex;
		}else if(iSign==1){//到达城市
			selectedProvinceIndex=selectedStopProvinceIndex;
		}
		if(selectedProvinceIndex>0){
			int arryLen=getResources().getStringArray(city[selectedProvinceIndex]).length;
			String strProvinces="";
			for(int i=0;i<arryLen;i++)
			{
				String strProvince=getResources().getStringArray(city[selectedProvinceIndex])[i];
				if(strProvinces.equals(""))
				{
					strProvinces=strProvince;
				}else
				{
					strProvinces+=","+strProvince;
				}
			}
			cityBtn.setText(strProvinces);
		}
	}
	
	//
	protected void ShowCityDlg(final Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//出发城市
			selectedProvinceIndex=selectedStartProvinceIndex;
			selectedCityIndex=selectedStartCityIndex;
		}else if(iSign==1){//到达城市
			selectedProvinceIndex=selectedStopProvinceIndex;
			selectedCityIndex=selectedStopCityIndex;
		}
		AlertDialog alertDialog = new AlertDialog.Builder(this)
	    .setTitle(getString(R.string.xuanzhe))
	    //.setIcon(R.drawable.ic_launcher)
	    .setSingleChoiceItems(city[selectedProvinceIndex], selectedCityIndex, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				selectedCityIndex = which;
				if(iSign==0){//出发城市
					selectedStartCityIndex=selectedCityIndex;
					app.SetConfig("selectedStartCityIndex",String.valueOf(selectedCityIndex));
				}else if(iSign==1){//到达城市
					selectedStopCityIndex=selectedCityIndex;
					app.SetConfig("selectedStopCityIndex",String.valueOf(selectedCityIndex));
				}
			}
	    	
	    }).setPositiveButton(getString(R.string.shangji), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         
	        	//显示市选择
	        	showCity(cityBtn,iSign);
	        	//显示省选择对话框
	        	ShowProvinceDlg(cityBtn,iSign);
	        }
	    }).setNeutralButton(getString(R.string.xiaji), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	//显示市选择
	        	showCity(cityBtn,iSign);
	        	if(iSign!=0)//出发地
	        	{
		        	//显示县选择对话框
		        	ShowTownDlg(cityBtn,iSign);
	        	}else//iSign==0
	        	{
	        		m_iStartSign=1;//城市层次
	        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
	        		//出发地不允许选择县区
	        		mStringID=R.string.startcitynotowntxt;
	        		mainMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
	        	}
	        }
	    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	showCity(cityBtn,iSign);
	        	//设置选择层数
	        	if(iSign==0){//出发城市
	        		m_iStartSign=1;//城市层次
	        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//到达城市
					m_iStopSign=1;//城市层次
					app.SetConfig("stopsign",String.valueOf(m_iStopSign));
				}
	        }
	       }).create();

		alertDialog.show();
	}
	
	protected void showCity(Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//出发城市
			selectedProvinceIndex=selectedStartProvinceIndex;
			selectedCityIndex=selectedStartCityIndex;
		}else if(iSign==1){//到达城市
			selectedProvinceIndex=selectedStopProvinceIndex;
			selectedCityIndex=selectedStopCityIndex;
		}
		String strCity=getResources().getStringArray(city[selectedProvinceIndex])[selectedCityIndex];
		cityBtn.setText(strCity);
		if(iSign==0){//出发城市
			m_strStartCity=strCity;
			app.SetConfig("startcity",strCity);
		}else if(iSign==1){//到达城市
			m_strStopCity=strCity;
			app.SetConfig("stopcity",strCity);
		}
	}
	
	protected void ShowTownDlg(final Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//出发城市
			selectedProvinceIndex=selectedStartProvinceIndex;
			selectedCityIndex=selectedStartCityIndex;
			selectedTownIndex=selectedStartTownIndex;
		}else if(iSign==1){//到达城市
			selectedProvinceIndex=selectedStopProvinceIndex;
			selectedCityIndex=selectedStopCityIndex;
			selectedTownIndex=selectedStopTownIndex;
		}
		AlertDialog alertDialog = new AlertDialog.Builder(this)
	    .setTitle(getString(R.string.xuanzhe))
	    //.setIcon(R.drawable.ic_launcher)
	    .setSingleChoiceItems(GetTownItems(selectedProvinceIndex,selectedCityIndex), selectedTownIndex, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				selectedTownIndex = which;
				if(iSign==0){//出发城市
					selectedStartTownIndex=selectedTownIndex;
				}else if(iSign==1){//到达城市
					selectedStopTownIndex=selectedTownIndex;
					app.SetConfig("selectedStopTownIndex",String.valueOf(selectedTownIndex));
				}
			}
	    	
	    }).setPositiveButton(getString(R.string.shangji), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	//显示县选择
	        	showTown(cityBtn,iSign);
	        	//显示市选择对话框
	        	ShowCityDlg(cityBtn,iSign);
	        }
	    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	showTown(cityBtn,iSign);
	        	//设置选择层数
	        	if(iSign==0){//出发城市
	        		m_iStartSign=2;//县区层次
	        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//到达城市
					m_iStopSign=2;//县区层次
					app.SetConfig("stopsign",String.valueOf(m_iStopSign));
				}
	        }
	       }).create();

		alertDialog.show();
	}
	
	protected void showTown(Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//出发城市
			selectedProvinceIndex=selectedStartProvinceIndex;
			selectedCityIndex=selectedStartCityIndex;
			selectedTownIndex=selectedStartTownIndex;
		}else if(iSign==1){//到达城市
			selectedProvinceIndex=selectedStopProvinceIndex;
			selectedCityIndex=selectedStopCityIndex;
			selectedTownIndex=selectedStopTownIndex;
		}
		String strTown=getResources().getStringArray(GetTownItems(selectedProvinceIndex,selectedCityIndex))[selectedTownIndex];
		cityBtn.setText(strTown);
		if(iSign==0){//出发城市
			m_strStartCity=strTown;
			app.SetConfig("startcity",strTown);
		}else if(iSign==1){//到达城市
			m_strStopCity=strTown;
			app.SetConfig("stopcity",strTown);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 构建Runnable对象，在runnable中更新界面   
		 Runnable   runnableUiStringID=new  Runnable(){  
		 	     @Override  
		 	     public void run() {  
		 	    	 //更新界面   
		 	    	Msg(mStringID);
		 	     }  
		 	          
		 };
		 Runnable   runnableUiString=new  Runnable(){  
	 	     @Override  
	 	     public void run() {  
	 	    	 //更新界面   
	 	    	Msg(mString);
	 	     }  
	 	          
		 }; 
	//String
		private void Msg(String strValue) {
			// TODO Auto-generated method stub
			app.Msg(strValue);
		}
		//int
		private void Msg(int iValue) {
			// TODO Auto-generated method stub
			app.Msg(getString(iValue));
		}


	//设置广告消息
	Runnable   runnableSetEndAdvText=new  Runnable(){  
	     @Override  
	     public void run() { 
	    	 //取得公告内容
	    	 Map<String, String> params = new HashMap<String, String>();
	    	 params=DataSource.getInstance().getAdv(app.myinfo.city);
	    	 //Log.e("!!!!!!!!!!!adv_city",app.myinfo.city);
	    	 //
	    	 String key, val,strResult="";
	         Iterator it = params.entrySet().iterator();
	         try {
				while (it.hasNext()) {
				     Map.Entry pairs = (Map.Entry) it.next();
				     key = pairs.getKey().toString();
				     val = pairs.getValue().toString();
				     strResult+=key+":"+val+"   ";
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				strResult="Error:"+e.getMessage().toString();
				e.printStackTrace();
			}
	         //Log.e("!!!!!!!!!!!adv_strResult",strResult);
	    	 //更新界面   
	    	 EndAdv.setText(strResult);
	    	//启动公告滚动条	可点击停止
	    	 EndAdv.init(getWindowManager());
	    	 EndAdv.startScroll();
	     }  
	          
	 }; 
	
	/** 设置 */ 
	public void showNotification(Context context){   
		//点击通知时转移内容   
		//Intent intent = new Intent(this, this.getClass());  
		//intent.addCategory(WINDOW_SERVICE);  
		//主要是设置点击通知时显示内容的类   
		m_PendingIntent = PendingIntent.getActivity(context, 0, getIntent(), 0); //如果转移内容则用m_Intent();  
		//构造Notification对象   
		m_Notification = new Notification();   
		//点击clear按钮，如何让通知不被清除？或者点击通知后，通知不消失
		m_Notification.flags |= Notification.FLAG_ONGOING_EVENT;////将此通知放到通知栏的"Ongoing"即"正在运行"组中
		//m_Notification.flags |= Notification.FLAG_NO_CLEAR;////将此通知放到通知栏的"Ongoing"即"正在运行"组中
		//设置通知在状态栏显示的图标   
		m_Notification.icon = R.drawable.ic_launcher;  
		//当我们点击通知时显示的内容   
		m_Notification.tickerText = getString(R.string.app_name);   
		//通知产生的时间，会在通知信息里显示
		//long when = System.currentTimeMillis(); 
		//m_Notification.when=when;
		//通知时发出默认的声音   
		m_Notification.defaults |= Notification.DEFAULT_SOUND;   
		//添加LED灯提醒
		m_Notification.defaults |= Notification.DEFAULT_LIGHTS;
		m_Notification.ledARGB = 0xff00ff00; 
		m_Notification.ledOnMS = 300; 
		m_Notification.ledOffMS = 1000; 
		m_Notification.flags |= Notification.FLAG_SHOW_LIGHTS; 
		//添加振动  <users-permission android:name="android.permission.VIBRATE"/>
		m_Notification.defaults |= Notification.DEFAULT_VIBRATE;
		long[] vibrate = {0,100,200,300}; 
		m_Notification.vibrate = vibrate ;
		
		//设置通知显示的参数   
		m_Notification.setLatestEventInfo(context, getString(R.string.app_name), getString(R.string.app_tel), m_PendingIntent);   
		//可以理解为执行这个通知   
		m_NotificationManager.notify(0, m_Notification);   
	} 
	/** 取消 */ 
	public void cancelNotification(){  
		m_NotificationManager.cancelAll();
	}
	//============================================================================================
	//信息显示相关
	private void initData(String tableType,String msg_code,String keywords,String msg_type,String page) {
		// TODO Auto-generated method stub
		if(msg_code.equals(getString(R.string.quanguo))){
			msg_code="";
		}
		if(keywords.equals(getString(R.string.quanguo))){
			keywords="";
		}
		//
		MsgInfosSet mis=new MsgInfosSet();
		mis=DataSource.getInstance().GetDatas(tableType,msg_code, keywords, msg_type, page);
		//取得最大ID
		mMaxID=mis.getmMaxID();
		//判断是否显示底部加载更多
		showBottomView=mis.showBottomView();
		//根据当前查看页判断是否已经加载到最后一页
		int PageNum=mis.getmTotalPage();
		if(PageNum>1) showBottomView=true;
		if(PageNum==Integer.parseInt(page) || PageNum==0){
			showBottomView=false;
		}else
		{
			showBottomView=true;
		}
		//Log.e("!!!!!initData_page",String.valueOf(page));
		/*
		 * 
		 */
		//
		adapter = new MyBaseAdapter(this,mis); 
		adapter.SetCitys(tableType,msg_code,keywords);
	}
	
	//
	private void SetListViewDataNull(){
		MsgInfosSet ms=null;
		adapter = new MyBaseAdapter(this,ms);
		adapter.notifyDataSetChanged();
	}
	//
	private void initView() {
		// TODO Auto-generated method stub
		mListView = (MyListView) findViewById(R.id.listView); 
	}
	
	@Override
	public void OnLoadMore() {
		// TODO Auto-generated method stub
		mLoadMoreAsynTask = new LoadMoreDataAsynTask();
		mLoadMoreAsynTask.execute(null);
	}

	@Override
	public void OnRefresh() {
		// TODO Auto-generated method stub
		mRefreshAsynTask = new RefreshDataAsynTask();
		mRefreshAsynTask.execute(null);
	}
	//----------------------------------------------------------------------------
		class RefreshDataAsynTask extends AsyncTask<Void , Void, Void>
		{
			//int index = adapter.getCount();
			//MsgInfos items;
			MsgInfosSet mis2=new MsgInfosSet();
			//刷新数据
			@Override
			protected Void doInBackground(Void... arg0) {

				String tableType=m_strTableType;
				String msg_code=m_strStartCity;
				if(msg_code.equals(getString(R.string.quanguo))){
					msg_code="";
				}
				String keywords=m_strStopCity;
				if(keywords.equals(getString(R.string.quanguo))){
					keywords="";
				}
				String msg_type=m_strType;
				String MaxID=String.valueOf(mMaxID);
				//Log.e("111111111RefreshDataAsynTask_MaxID",MaxID);
				//Log.e("111111111RefreshDataAsynTask_tableType",tableType);
				//
				mis2=DataSource.getInstance().GetNewDatas(tableType,msg_code, keywords, msg_type, MaxID);
				//取得最大ID
				mMaxID=mis2.getmMaxID();
				//
				//Log.e("22222RefreshDataAsynTask_MaxID",String.valueOf(mMaxID));
				/*
				 * 
				 */
	            //adapter.addNewsItem(items);

				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				adapter.addNewItems(mis2);
				mListView.onRefreshComplete();
			}
			
		}
		//------------------------------------------------------------------------------------
		class LoadMoreDataAsynTask extends AsyncTask<Void , Void, Void>
		{
			//int pos = adapter.getCount();
			//MsgInfos items;
			MsgInfosSet mis3=new MsgInfosSet();
			//点击更多
			@Override
			protected Void doInBackground(Void... arg0) {
				
				String tableType=m_strTableType;
				String msg_code=m_strStartCity;
				if(msg_code.equals(getString(R.string.quanguo))){
					msg_code="";
				}
				String keywords=m_strStopCity;
				if(keywords.equals(getString(R.string.quanguo))){
					keywords="";
				}
				String msg_type=m_strType;
				String page=String.valueOf(++mCurPageNum);
				//Log.e("!!!!!!!!!!!!mCurPageNum",page);
				mis3=DataSource.getInstance().GetDatas(tableType,msg_code, keywords, msg_type, page);
				/*
				 * 
				 */
	            //adapter.appendNewsItem(items);
	            
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				adapter.appendNewItems(mis3);
				int iTotalPageNum=mis3.getmTotalPage();
				
				if(mCurPageNum==iTotalPageNum){//隐藏
					showBottomView=false;
					mListView.onLoadMoreComplete(true);
				}else
				{
					showBottomView=true;
					mListView.onLoadMoreComplete(false);
				}
			}
		}
		//--------------------------------------------------------------------------------------------------
		protected void startTimer() {
			// TODO Auto-generated method stub
			if(timer==null){
				timer=new Timer();
			}
			if(task==null){
				//开始刷新
				task = new TimerTask(){
			        public void run() {
			            Message message = new Message();        
			            message.what = 1;        
			            handler.sendMessage(message);      
			        }    
			            
			    }; 
			}
			if(timer!=null && task!=null)
	    		timer.schedule(task,1000, 5000); 
		}
		protected void stopTimer() {
			// TODO Auto-generated method stub
			if(timer!=null){
				timer.cancel();
				timer=null;
			}
			if(task!=null){
				task.cancel();
				task=null;
			}
		}
		//-----------------------------------------------------------------------------------------------------
	//============================================================================================
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//点击back或home键时，提示是否退出
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		 if (keyCode == KeyEvent.KEYCODE_BACK || keyCode ==KeyEvent.KEYCODE_HOME) {
			Dialog alertDialog = new AlertDialog.Builder(this). 
                setTitle(R.string.SMTxt). 
                setMessage(R.string.ExitTxt). 
                setIcon(R.drawable.ic_launcher). 
                setPositiveButton(R.string.OKTxt, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        // TODO Auto-generated method stub  
                    	//System.exit(0);//退出整个Application
                    	closeAllActivity();//广播退出整个Application 中的所有 activity
                    } 
                }). 
                setNegativeButton(R.string.CancelTxt, new DialogInterface.OnClickListener() { 
                     
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        // TODO Auto-generated method stub  
                    	dialog.cancel();//取消
                    } 
                }). 
                create(); 
			alertDialog.show(); 
		 }
		return super.onKeyDown(keyCode, event);
	}

	//广播退出整个Application 中的所有 activity
	protected void closeAllActivity() {
		// TODO Auto-generated method stub
		cancelNotification();//关闭通知栏
		//停止刷新
		bRefresh=false;
		//停止 timer
		stopTimer();
		//关闭所有窗口
		Intent intent = new Intent(); 
	    intent.setAction("ExitApp"); 
	    this.sendBroadcast(intent); 
	    super.finish();
	}
	
}
