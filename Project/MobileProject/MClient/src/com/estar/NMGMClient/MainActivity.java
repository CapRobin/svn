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
	//�ֻ�֪ͨ����ز���
	//============================================================
	//����֪ͨ����Ϣ��������   
	private NotificationManager m_NotificationManager;  
	private Intent  m_Intent;  
	private PendingIntent m_PendingIntent;  
	//����Notification����  
	private Notification  m_Notification; 
	//============================================================
	//�ײ���������ز���
	AutoScrollTextView EndAdv;
	//��ذ�ť
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
	//��Ϣ��ʾ��Ŀ��ز���
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
		//����ȫ�ֱ������
		app = (MySuperApplication) getApplication(); //������ǵ�Ӧ�ó���MySuperApplication
		mainMsgHander=new Handler();  //�����������̵߳�handler   
		//���õײ�������
		EndAdv=(AutoScrollTextView)findViewById(R.id.endadv);
		Handler advHander=new Handler();  //�����������̵߳�handler   
		advHander.post(runnableSetEndAdvText);   //���½���UI(��ʾ)
		//
		//---------������ʾ��--------------------------------
	    mypDialog = new ProgressDialog(this);// ʵ����   
		// ���ý�������񣬷��Ϊ���ν�������STYLE_SPINNERΪԲ��   
		//mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
		//mypDialog.setTitle("��½");// ����ProgressDialog ����   
		//mypDialog.setMessage("������֤���Ժ�...");// ����ProgressDialog ��ʾ��Ϣ   
		mypDialog.setIndeterminate(false);// ����ProgressDialog   
		mypDialog.setCancelable(true);// �Ƿ���԰��˻ذ���ȡ��   
		//mypDialog.setMax(100);// �������ֵ
		//-----------------------------------------------------
		//���Button
		mStartCity=(Button)findViewById(R.id.startCityBtn);
		mStopCity=(Button)findViewById(R.id.stopCityBtn);
		mSearchBtn=(Button)findViewById(R.id.searchBtn);
		mAddBtn=(Button)findViewById(R.id.addBtn);
		mScrollCheckBox=(CheckBox)findViewById(R.id.scrollCheckBox);
		//���ð�ť��ʾֵ
		m_strStartCity=app.GetConfigValue("startcity");
		m_strStopCity=app.GetConfigValue("stopcity");
		m_strType="8";//��Ϣ�������ͣ�Ĭ��Ϊ8��8��ȫ����0����Դ��1����Դ��
		if(app.myinfo.usertypes==1){//�ֲĻ�Ա
			m_strTableType=getString(R.string.typeInfo1Txt);
		}else if(app.myinfo.usertypes==2){//ú̿��Ա
			m_strTableType=getString(R.string.typeInfo2Txt);
		}
		app.m_strTableType=m_strTableType;
		//
		mStartCity.setText(m_strStartCity);
		mStopCity.setText(m_strStopCity);
		//ѡ����
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
		//ѡ��ʡ����
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
		//�����ʼ����
		mStartCity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ʾ�Ի���
				if(m_iStartSign==0)//ʡ��
				{
					ShowProvinceDlg(mStartCity,0);
				}else if(m_iStartSign==1)//����
				{
					ShowCityDlg(mStartCity,0);
				}else if(m_iStartSign==2)//����
				{
					ShowTownDlg(mStartCity,0);
				}
			}
		});
		//����������
		mStopCity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ʾ�Ի���
				if(m_iStopSign==0)//ʡ��
				{
					ShowProvinceDlg(mStopCity,1);
				}else if(m_iStopSign==1)//����
				{
					ShowCityDlg(mStopCity,1);
				}else if(m_iStopSign==2)//����
				{
					ShowTownDlg(mStopCity,1);
				}
			}
		});
		//�����ѯ��ť
		mSearchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��ʾ��ѯ�˵�
				if(app.myinfo.usertypes==1){//�ֲ�
					ShowGCSearchMenuDlg();
				}else if(app.myinfo.usertypes==2){//ú̿
					ShowMTSearchMenuDlg();
				}
				
				//Search();
				
			}
		});
		//���������ť
		mAddBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��ʾ�����˵�
				if(app.myinfo.usertypes==1){//�ֲ�
					ShowGCAddMenuDlg();
				}else if(app.myinfo.usertypes==2){//ú̿
					ShowMTAddMenuDlg();
				}
			}
		});
		//��ʼ��NotificationManager����   	
		m_NotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); 
		//��ʾ֪ͨ��
		showNotification(MainActivity.this);
		//====================================================
		mCurPageNum=1;
		//��Ϣ��Ŀ��ʾ
		initView(); 
        //initData();
		//initData(m_strStartCity,m_strStopCity,m_strType,String.valueOf(mCurPageNum));
        //mListView.setAdapter(adapter);
		handler = new Handler(){    
	        public void handleMessage(Message msg) {    
	            switch (msg.what) {        
	            case 1:        
	                OnRefresh();//ˢ����ʾ����Ϣ   
	                break;
	            case 2://���ڶ�ȡ
	    			mypDialog.setMessage(getString(R.string.string04));
	    			mypDialog.show();  
	    			break;
	            case 3://
	            	//Log.e("!!!!!!!!!!showBottomView",String.valueOf(showBottomView));
	            	//�Ƿ���ʾ�ײ�����
	            	if(showBottomView)
	            		mListView.onLoadMoreComplete(false);//��ʾ
	            	else
	            		mListView.onLoadMoreComplete(true);//����
	            	//ˢ����ʾ����
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
	            	//�Ƿ���ʾ�ײ�����
	            	if(showBottomView)
	            		mListView.onLoadMoreComplete(false);//��ʾ
	            	else
	            		mListView.onLoadMoreComplete(true);//����
	            	
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
				//��ʾ
				//String str=item.getID()+item.getMsg_Content();
				//Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();  
				//���飨ͨ��putExtra���ݲ�����    
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, Detail.class);
				intent.putExtra("MSGINFODATA", item);
				MainActivity.this.startActivity(intent);
			}
		});
    	
		
		//--------------------------------------------------------------------  
		//5���Ӷ�ȡһ����Ϣ���Զ�ˢ�¶�ȡ��
		//���������ѡ��CheckBox
		mScrollCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(mScrollCheckBox.isChecked())//����
				{
					//��ʼ�Զ�������Ϣ
					mStringID=R.string.startscrollTxt;
					mainMsgHander.post(runnableUiStringID);
					//
					bRefresh=true;
					//��ʼˢ��timer
					startTimer();
				}else//
				{
					//ֹͣ������Ϣ
					mStringID=R.string.stopscrollTxt;
					mainMsgHander.post(runnableUiStringID);
					//
					bRefresh=false;
					//ֹͣ timer
					stopTimer();
				}
			}
		});
		//
		//-----------------------------------------------------------------------------------------
        //====================================================
		//�߳̽��в�ѯ
				new Thread(){

					/* (non-Javadoc)
					 * @see java.lang.Thread#run()
					 */
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//���ڼ�����...
						Message msg = new Message();  
				        msg.what = 2;// ��ʼ���ر�־1   
				        handler.sendMessage(msg);  
				        //��������
				        initData(m_strTableType,m_strStartCity,m_strStopCity,m_strType,String.valueOf(mCurPageNum));
						//
						msg = new Message();  
				        msg.what = 3;// ������� 
				        handler.sendMessage(msg);  
				        //
						msg = new Message();  
				        msg.what = 4;// ����
				        handler.sendMessage(msg); 
				        //�Զ�ˢ��
				        startTimer();
					}
					
				}.start();
	}
	//�ֲ���Ϣ������
	protected void ShowAddGCDlg() {
		// TODO Auto-generated method stub
		//��ʾ����
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, add2.class);
		MainActivity.this.startActivity(intent);
	}
	//ú̿��Ϣ������
	protected void ShowAddMCDlg() {
		// TODO Auto-generated method stub
		//��ʾ����
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, add3.class);
		MainActivity.this.startActivity(intent);
	}
	//������Ϣ������
	protected void ShowAddWLDlg() {
		// TODO Auto-generated method stub
		//��ʾ����
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, add1.class);
		MainActivity.this.startActivity(intent);
	}
	//ú̿
	protected void ShowMTAddMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		.setTitle("ѡ����Ҫ����")
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
	
	//�ֲ�
	protected void ShowGCAddMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		.setTitle("ѡ����Ҫ����")
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
	
	//ú̿�����˵�ѡ��
	protected void ShowMTSearchMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		//.setTitle("ѡ��")
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
	//�ֲ������˵�ѡ��
	protected void ShowGCSearchMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		//.setTitle("ѡ��")
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
	//��������
	protected void Search(){
		//������
		m_strStartCity=mStartCity.getText().toString();
		//�����
		m_strStopCity=mStopCity.getText().toString();
		//
		mCurPageNum=1;
        showBottomView=true;
		//
		///if(m_strStartCity.equals(m_strStopCity) && (m_strStartCity.equals("") || m_strStartCity.equals(getString(R.string.quanguo)))){
		///	mStringID=R.string.searchCheckTxt1;
    	///	mainMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
		///}else
		///{
			//�߳̽��в�ѯ
			new Thread(){

				/* (non-Javadoc)
				 * @see java.lang.Thread#run()
				 */
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//super.run();
					//��д�� �����
					app.SetConfig("stopcity",m_strStopCity);
					//���ڼ�����...
					Message msg = new Message();  
			        msg.what = 2;// ��ʼ���ر�־1   
			        handler.sendMessage(msg);  
			        //��������
					initData(m_strTableType,m_strStartCity,m_strStopCity,m_strType,String.valueOf(mCurPageNum));
					//
					msg = new Message();  
			        msg.what = 3;// ������� 
			        handler.sendMessage(msg);  
			        //
					msg = new Message();  
			        msg.what = 4;// ����
			        handler.sendMessage(msg); 
				}
				
			}.start();
		///}
	}
	/*
	 * ѡ��ʡ�ݳ���������غ���
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected void ShowProvinceDlg(final Button CityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//��������
			selectedProvinceIndex=selectedStartProvinceIndex;
		}else if(iSign==1){//�������
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
				if(iSign==0){//��������
					selectedStartProvinceIndex=selectedProvinceIndex;
					app.SetConfig("selectedStartProvinceIndex",String.valueOf(selectedProvinceIndex));
				}else if(iSign==1){//�������
					selectedStopProvinceIndex=selectedProvinceIndex;
					app.SetConfig("selectedStopProvinceIndex",String.valueOf(selectedProvinceIndex));
				}
			}
	    	
	    }).setPositiveButton(getString(R.string.xiaji), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	//��ʾʡѡ��
	        	showProvince(CityBtn,iSign);
	        	if(selectedProvinceIndex>0)
	        	{
		        	//��ʾ��ѡ��Ի��� 
		        	ShowCityDlg(CityBtn,iSign);
	        	}
	        }
	    }).setNeutralButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	//����ѡ�����
	        	if(iSign==0){//��������
	        		//�����ز�����ѡ��ʡ��
	        		if(selectedProvinceIndex>0){
	        			mStringID=R.string.startcitynoprovincetxt;
		        		mainMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
		        		return;
	        		}
	        		m_iStartSign=0;//ʡ�ݲ��
	        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//�������
					m_iStopSign=0;//ʡ�ݲ��
					app.SetConfig("stopsign",String.valueOf(m_iStopSign));
				}
	        	showProvince(CityBtn,iSign);
	        }
	       }).setNegativeButton(getString(R.string.quanxuan), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		         // TODO Auto-generated method stub
		        	//����ѡ�����
		        	if(iSign==0){//��������
		        		//�����ز�����ѡ��ʡ��
		        		if(selectedProvinceIndex>0){
		        			mStringID=R.string.startcitynoallquanxuantxt;
			        		mainMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
			        		return;
		        		}
		        		m_iStartSign=0;//ʡ�ݲ��
		        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
					}else if(iSign==1){//�������
						m_iStopSign=0;//ʡ�ݲ��
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
		if(iSign==0){//��������
			selectedProvinceIndex=selectedStartProvinceIndex;
		}else if(iSign==1){//�������
			selectedProvinceIndex=selectedStopProvinceIndex;
		}
		String strProvince=getResources().getStringArray(R.array.province_item)[selectedProvinceIndex];
		cityBtn.setText(strProvince);
		if(iSign==0){//��������
			m_strStartCity=strProvince;
			app.SetConfig("startcity",strProvince);
		}else if(iSign==1){//�������
			m_strStopCity=strProvince;
			app.SetConfig("stopcity",strProvince);
		}
	}
	//
	protected void showAllProvince(Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//��������
			selectedProvinceIndex=selectedStartProvinceIndex;
		}else if(iSign==1){//�������
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
		if(iSign==0){//��������
			selectedProvinceIndex=selectedStartProvinceIndex;
			selectedCityIndex=selectedStartCityIndex;
		}else if(iSign==1){//�������
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
				if(iSign==0){//��������
					selectedStartCityIndex=selectedCityIndex;
					app.SetConfig("selectedStartCityIndex",String.valueOf(selectedCityIndex));
				}else if(iSign==1){//�������
					selectedStopCityIndex=selectedCityIndex;
					app.SetConfig("selectedStopCityIndex",String.valueOf(selectedCityIndex));
				}
			}
	    	
	    }).setPositiveButton(getString(R.string.shangji), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         
	        	//��ʾ��ѡ��
	        	showCity(cityBtn,iSign);
	        	//��ʾʡѡ��Ի���
	        	ShowProvinceDlg(cityBtn,iSign);
	        }
	    }).setNeutralButton(getString(R.string.xiaji), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	//��ʾ��ѡ��
	        	showCity(cityBtn,iSign);
	        	if(iSign!=0)//������
	        	{
		        	//��ʾ��ѡ��Ի���
		        	ShowTownDlg(cityBtn,iSign);
	        	}else//iSign==0
	        	{
	        		m_iStartSign=1;//���в��
	        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
	        		//�����ز�����ѡ������
	        		mStringID=R.string.startcitynotowntxt;
	        		mainMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
	        	}
	        }
	    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	showCity(cityBtn,iSign);
	        	//����ѡ�����
	        	if(iSign==0){//��������
	        		m_iStartSign=1;//���в��
	        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//�������
					m_iStopSign=1;//���в��
					app.SetConfig("stopsign",String.valueOf(m_iStopSign));
				}
	        }
	       }).create();

		alertDialog.show();
	}
	
	protected void showCity(Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//��������
			selectedProvinceIndex=selectedStartProvinceIndex;
			selectedCityIndex=selectedStartCityIndex;
		}else if(iSign==1){//�������
			selectedProvinceIndex=selectedStopProvinceIndex;
			selectedCityIndex=selectedStopCityIndex;
		}
		String strCity=getResources().getStringArray(city[selectedProvinceIndex])[selectedCityIndex];
		cityBtn.setText(strCity);
		if(iSign==0){//��������
			m_strStartCity=strCity;
			app.SetConfig("startcity",strCity);
		}else if(iSign==1){//�������
			m_strStopCity=strCity;
			app.SetConfig("stopcity",strCity);
		}
	}
	
	protected void ShowTownDlg(final Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//��������
			selectedProvinceIndex=selectedStartProvinceIndex;
			selectedCityIndex=selectedStartCityIndex;
			selectedTownIndex=selectedStartTownIndex;
		}else if(iSign==1){//�������
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
				if(iSign==0){//��������
					selectedStartTownIndex=selectedTownIndex;
				}else if(iSign==1){//�������
					selectedStopTownIndex=selectedTownIndex;
					app.SetConfig("selectedStopTownIndex",String.valueOf(selectedTownIndex));
				}
			}
	    	
	    }).setPositiveButton(getString(R.string.shangji), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	//��ʾ��ѡ��
	        	showTown(cityBtn,iSign);
	        	//��ʾ��ѡ��Ի���
	        	ShowCityDlg(cityBtn,iSign);
	        }
	    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	showTown(cityBtn,iSign);
	        	//����ѡ�����
	        	if(iSign==0){//��������
	        		m_iStartSign=2;//�������
	        		app.SetConfig("startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//�������
					m_iStopSign=2;//�������
					app.SetConfig("stopsign",String.valueOf(m_iStopSign));
				}
	        }
	       }).create();

		alertDialog.show();
	}
	
	protected void showTown(Button cityBtn,final int iSign) {
		// TODO Auto-generated method stub
		if(iSign==0){//��������
			selectedProvinceIndex=selectedStartProvinceIndex;
			selectedCityIndex=selectedStartCityIndex;
			selectedTownIndex=selectedStartTownIndex;
		}else if(iSign==1){//�������
			selectedProvinceIndex=selectedStopProvinceIndex;
			selectedCityIndex=selectedStopCityIndex;
			selectedTownIndex=selectedStopTownIndex;
		}
		String strTown=getResources().getStringArray(GetTownItems(selectedProvinceIndex,selectedCityIndex))[selectedTownIndex];
		cityBtn.setText(strTown);
		if(iSign==0){//��������
			m_strStartCity=strTown;
			app.SetConfig("startcity",strTown);
		}else if(iSign==1){//�������
			m_strStopCity=strTown;
			app.SetConfig("stopcity",strTown);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ����Runnable������runnable�и��½���   
		 Runnable   runnableUiStringID=new  Runnable(){  
		 	     @Override  
		 	     public void run() {  
		 	    	 //���½���   
		 	    	Msg(mStringID);
		 	     }  
		 	          
		 };
		 Runnable   runnableUiString=new  Runnable(){  
	 	     @Override  
	 	     public void run() {  
	 	    	 //���½���   
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


	//���ù����Ϣ
	Runnable   runnableSetEndAdvText=new  Runnable(){  
	     @Override  
	     public void run() { 
	    	 //ȡ�ù�������
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
	    	 //���½���   
	    	 EndAdv.setText(strResult);
	    	//�������������	�ɵ��ֹͣ
	    	 EndAdv.init(getWindowManager());
	    	 EndAdv.startScroll();
	     }  
	          
	 }; 
	
	/** ���� */ 
	public void showNotification(Context context){   
		//���֪ͨʱת������   
		//Intent intent = new Intent(this, this.getClass());  
		//intent.addCategory(WINDOW_SERVICE);  
		//��Ҫ�����õ��֪ͨʱ��ʾ���ݵ���   
		m_PendingIntent = PendingIntent.getActivity(context, 0, getIntent(), 0); //���ת����������m_Intent();  
		//����Notification����   
		m_Notification = new Notification();   
		//���clear��ť�������֪ͨ������������ߵ��֪ͨ��֪ͨ����ʧ
		m_Notification.flags |= Notification.FLAG_ONGOING_EVENT;////����֪ͨ�ŵ�֪ͨ����"Ongoing"��"��������"����
		//m_Notification.flags |= Notification.FLAG_NO_CLEAR;////����֪ͨ�ŵ�֪ͨ����"Ongoing"��"��������"����
		//����֪ͨ��״̬����ʾ��ͼ��   
		m_Notification.icon = R.drawable.ic_launcher;  
		//�����ǵ��֪ͨʱ��ʾ������   
		m_Notification.tickerText = getString(R.string.app_name);   
		//֪ͨ������ʱ�䣬����֪ͨ��Ϣ����ʾ
		//long when = System.currentTimeMillis(); 
		//m_Notification.when=when;
		//֪ͨʱ����Ĭ�ϵ�����   
		m_Notification.defaults |= Notification.DEFAULT_SOUND;   
		//���LED������
		m_Notification.defaults |= Notification.DEFAULT_LIGHTS;
		m_Notification.ledARGB = 0xff00ff00; 
		m_Notification.ledOnMS = 300; 
		m_Notification.ledOffMS = 1000; 
		m_Notification.flags |= Notification.FLAG_SHOW_LIGHTS; 
		//�����  <users-permission android:name="android.permission.VIBRATE"/>
		m_Notification.defaults |= Notification.DEFAULT_VIBRATE;
		long[] vibrate = {0,100,200,300}; 
		m_Notification.vibrate = vibrate ;
		
		//����֪ͨ��ʾ�Ĳ���   
		m_Notification.setLatestEventInfo(context, getString(R.string.app_name), getString(R.string.app_tel), m_PendingIntent);   
		//�������Ϊִ�����֪ͨ   
		m_NotificationManager.notify(0, m_Notification);   
	} 
	/** ȡ�� */ 
	public void cancelNotification(){  
		m_NotificationManager.cancelAll();
	}
	//============================================================================================
	//��Ϣ��ʾ���
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
		//ȡ�����ID
		mMaxID=mis.getmMaxID();
		//�ж��Ƿ���ʾ�ײ����ظ���
		showBottomView=mis.showBottomView();
		//���ݵ�ǰ�鿴ҳ�ж��Ƿ��Ѿ����ص����һҳ
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
			//ˢ������
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
				//ȡ�����ID
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
			//�������
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
				
				if(mCurPageNum==iTotalPageNum){//����
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
				//��ʼˢ��
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

	//���back��home��ʱ����ʾ�Ƿ��˳�
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
                    	//System.exit(0);//�˳�����Application
                    	closeAllActivity();//�㲥�˳�����Application �е����� activity
                    } 
                }). 
                setNegativeButton(R.string.CancelTxt, new DialogInterface.OnClickListener() { 
                     
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        // TODO Auto-generated method stub  
                    	dialog.cancel();//ȡ��
                    } 
                }). 
                create(); 
			alertDialog.show(); 
		 }
		return super.onKeyDown(keyCode, event);
	}

	//�㲥�˳�����Application �е����� activity
	protected void closeAllActivity() {
		// TODO Auto-generated method stub
		cancelNotification();//�ر�֪ͨ��
		//ֹͣˢ��
		bRefresh=false;
		//ֹͣ timer
		stopTimer();
		//�ر����д���
		Intent intent = new Intent(); 
	    intent.setAction("ExitApp"); 
	    this.sendBroadcast(intent); 
	    super.finish();
	}
	
}
