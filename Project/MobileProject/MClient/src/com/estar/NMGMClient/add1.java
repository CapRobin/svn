package com.estar.NMGMClient;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.estar.comm.MySuperActivity;
import com.estar.comm.MySuperApplication;
import com.estar.data.DataSource;

public class add1 extends MySuperActivity{

	EditText infoContent;
	Button mTypeBtn,mstartcity,mstopcity;
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
	private String m_strStartCity;
	private String m_strStopCity;
	//
	private MySuperApplication app;
	private Handler addMsgHander=null;  
	private ProgressDialog mypDialog;
	//
	private int mStringID;
	private String mString;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		///* 载入mylayout.xml Layout */ 
//		setContentView(R.layout.add1); 		
		setAbContentView(R.layout.add1);
//		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout("发布", false);
		//Log.e("-------------------add1","1");
		//设置全局变量相关
		app = (MySuperApplication) getApplication(); //获得我们的应用程序MySuperApplication
		addMsgHander=new Handler();  //创建属于主线程的handler  
		//---------设置提示框--------------------------------
		//Log.e("-------------------add1","2");
	    mypDialog = new ProgressDialog(this);// 实例化   
		// 设置进度条风格，风格为长形进度条，STYLE_SPINNER为圆的   
		//mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
		//mypDialog.setTitle("登陆");// 设置ProgressDialog 标题   
		//mypDialog.setMessage("正在验证请稍后...");// 设置ProgressDialog 提示信息   
		mypDialog.setIndeterminate(false);// 设置ProgressDialog   
		mypDialog.setCancelable(true);// 是否可以按退回按键取消   
		//mypDialog.setMax(100);// 设置最大值
		//-----------------------------------------------------
		//Log.e("-------------------add1","3");
		//信息框
		infoContent=(EditText)findViewById(R.id.infoContent);
		//返回
		Button backBtn=(Button)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//显示主界面
				//Intent intent = new Intent();
                //intent.setClass(add1.this, MainActivity.class);//默认add1.class
                //add1.this.startActivity(intent);
				add1.this.finish();
			}
		});
		//信息类型选择
		mTypeBtn=(Button)findViewById(R.id.typeBtn);
		mTypeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showTypeDlg();
			}
		});
		//发布2(保留项目)
		Button add2Btn=(Button)findViewById(R.id.add2Btn);
		add2Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//显示发布2窗口
			}
		});
		//
		mstartcity=(Button)findViewById(R.id.startcity);
		mstopcity=(Button)findViewById(R.id.stopcity);
		
		m_strStartCity=app.GetConfigValue("add1startcity");
		m_strStopCity=app.GetConfigValue("add1stopcity");
		//设置按钮显示值
		mstartcity.setText(m_strStartCity);
		mstopcity.setText(m_strStopCity);
		//选择层次
		String startsign=app.GetConfigValue("add1startsign");
		if(startsign.equals(""))
		{
			startsign="0";
		}
		String stopsign=app.GetConfigValue("add1stopsign");
		if(stopsign.equals(""))
		{
			stopsign="0";
		}
		
		//Log.e("-------------------add1_startsign",startsign);
		//Log.e("-------------------add1_stopsign",stopsign);
		
		m_iStartSign=Integer.parseInt(startsign);
		m_iStopSign=Integer.parseInt(stopsign);
		//选择省市县
		String strSelectedStartProvinceIndex=app.GetConfigValue("add1selectedStartProvinceIndex");
		if(strSelectedStartProvinceIndex.equals(""))
		{
			strSelectedStartProvinceIndex="0";
		}
		String strSelectedStartCityIndex=app.GetConfigValue("add1selectedStartCityIndex");
		if(strSelectedStartCityIndex.equals(""))
		{
			strSelectedStartCityIndex="0";
		}
		String strSelectedStartTownIndex=app.GetConfigValue("add1selectedStartTownIndex");
		if(strSelectedStartTownIndex.equals(""))
		{
			strSelectedStartTownIndex="0";
		}
		String strSelectedStopProvinceIndex=app.GetConfigValue("add1selectedStopProvinceIndex");
		if(strSelectedStopProvinceIndex.equals(""))
		{
			strSelectedStopProvinceIndex="0";
		}
		String strSelectedStopCityIndex=app.GetConfigValue("add1selectedStopCityIndex");
		if(strSelectedStopCityIndex.equals(""))
		{
			strSelectedStopCityIndex="0";
		}
		String strSelectedStopTownIndex=app.GetConfigValue("add1selectedStopTownIndex");
		if(strSelectedStopTownIndex.equals(""))
		{
			strSelectedStopTownIndex="0";
		}
		//Log.e("-------------------add1_strSelectedStartProvinceIndex",strSelectedStartProvinceIndex);
		//Log.e("-------------------add1_strSelectedStartCityIndex",strSelectedStartCityIndex);
		//Log.e("-------------------add1_strSelectedStartTownIndex",strSelectedStartTownIndex);
		//Log.e("-------------------add1_strSelectedStopProvinceIndex",strSelectedStopProvinceIndex);
		//Log.e("-------------------add1_strSelectedStopCityIndex",strSelectedStopCityIndex);
		//Log.e("-------------------add1_strSelectedStopTownIndex",strSelectedStopTownIndex);
		
		selectedStartProvinceIndex=Integer.parseInt(strSelectedStartProvinceIndex);
		selectedStartCityIndex=Integer.parseInt(strSelectedStartCityIndex);
		selectedStartTownIndex=Integer.parseInt(strSelectedStartTownIndex);
		selectedStopProvinceIndex=Integer.parseInt(strSelectedStopProvinceIndex);
		selectedStopCityIndex=Integer.parseInt(strSelectedStopCityIndex);
		selectedStopTownIndex=Integer.parseInt(strSelectedStopTownIndex);
		
		//Log.e("--------------------add1","buttonPre");
		//出发城市选择
		
		mstartcity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示对话框
				if(m_iStartSign==0)//省份
				{
					ShowProvinceDlg(mstartcity,0);
				}else if(m_iStartSign==1)//城市
				{
					ShowCityDlg(mstartcity,0);
				}else if(m_iStartSign==2)//县区
				{
					ShowTownDlg(mstartcity,0);
				}
			}
		});
		//到达城市选择
		
		mstopcity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(m_iStopSign==0)//省份
				{
					ShowProvinceDlg(mstopcity,1);
				}else if(m_iStopSign==1)//城市
				{
					ShowCityDlg(mstopcity,1);
				}else if(m_iStopSign==2)//县区
				{
					ShowTownDlg(mstopcity,1);
				}
			}
		});
		//清空
		Button clearBtn=(Button)findViewById(R.id.clearBtn);
		clearBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				infoContent.setText("");
			}
		});
		//退格
		Button backspaceBtn=(Button)findViewById(R.id.backspaceBtn);
		backspaceBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BackSpace();
			}
		});
		//发布信息
		Button addinfoBtn=(Button)findViewById(R.id.addinfoBtn);
		addinfoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(){

					/* (non-Javadoc)
					 * @see java.lang.Thread#run()
					 */
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//super.run();
						Message msg = new Message();// 
				        msg.what = 1;  
				        addHander.sendMessage(msg);
				        //
						addInfo();
					}
					
				}.start();
			}
		});
		//Log.e("--------------------add1","buttonPre");
		/*
		 * 数据区 点击按钮
		 */
		/////////////////////////////////////////////////////////////////////////////////
		//0
		Button button00=(Button)findViewById(R.id.button00);
		button00.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button00Txt);
			}
		});
		//1
		Button button01=(Button)findViewById(R.id.button01);
		button01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button01Txt);
			}
		});
		//2
		Button button02=(Button)findViewById(R.id.button02);
		button02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button02Txt);
			}
		});
		//3
		Button button03=(Button)findViewById(R.id.button03);
		button03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button03Txt);
			}
		});
		//4
		Button button04=(Button)findViewById(R.id.button04);
		button04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button04Txt);
			}
		});
		//5
		Button button05=(Button)findViewById(R.id.button05);
		button05.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button05Txt);
			}
		});
		//6
		Button button06=(Button)findViewById(R.id.button06);
		button06.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button06Txt);
			}
		});
		//7
		Button button07=(Button)findViewById(R.id.button07);
		button07.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button07Txt);
			}
		});
		//8
		Button button08=(Button)findViewById(R.id.button08);
		button08.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button08Txt);
			}
		});
		//9
		Button button09=(Button)findViewById(R.id.button09);
		button09.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button09Txt);
			}
		});
		//
		Button button10=(Button)findViewById(R.id.button10);
		button10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button10Txt);
			}
		});
		Button button11=(Button)findViewById(R.id.button11);
		button11.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button11Txt);
			}
		});
		Button button12=(Button)findViewById(R.id.button12);
		button12.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button12Txt);
			}
		});
		Button button13=(Button)findViewById(R.id.button13);
		button13.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button13Txt);
			}
		});
		Button button14=(Button)findViewById(R.id.button14);
		button14.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button14Txt);
			}
		});
		Button button15=(Button)findViewById(R.id.button15);
		button15.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button15Txt);
			}
		});
		Button button16=(Button)findViewById(R.id.button16);
		button16.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button16Txt);
			}
		});
		Button button17=(Button)findViewById(R.id.button17);
		button17.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button17Txt);
			}
		});
		Button button18=(Button)findViewById(R.id.button18);
		button18.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button18Txt);
			}
		});
		Button button19=(Button)findViewById(R.id.button19);
		button19.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button19Txt);
			}
		});
		Button button20=(Button)findViewById(R.id.button20);
		button20.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button20Txt);
			}
		});
		Button button21=(Button)findViewById(R.id.button21);
		button21.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button21Txt);
			}
		});
		Button button22=(Button)findViewById(R.id.button22);
		button22.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button22Txt);
			}
		});
		Button button23=(Button)findViewById(R.id.button23);
		button23.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button23Txt);
			}
		});
		Button button24=(Button)findViewById(R.id.button24);
		button24.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button24Txt);
			}
		});
		Button button25=(Button)findViewById(R.id.button25);
		button25.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button25Txt);
			}
		});
		Button button26=(Button)findViewById(R.id.button26);
		button26.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button26Txt);
			}
		});
		Button button27=(Button)findViewById(R.id.button27);
		button27.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button27Txt);
			}
		});
		Button button28=(Button)findViewById(R.id.button28);
		button28.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button28Txt);
			}
		});
		Button button29=(Button)findViewById(R.id.button29);
		button29.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button29Txt);
			}
		});
		Button button30=(Button)findViewById(R.id.button30);
		button30.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button30Txt);
			}
		});
		Button button31=(Button)findViewById(R.id.button31);
		button31.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button31Txt);
			}
		});
		Button button32=(Button)findViewById(R.id.button32);
		button32.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button32Txt);
			}
		});
		Button button33=(Button)findViewById(R.id.button33);
		button33.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button33Txt);
			}
		});
		Button button34=(Button)findViewById(R.id.button34);
		button34.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button34Txt);
			}
		});
		Button button35=(Button)findViewById(R.id.button35);
		button35.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button35Txt);
			}
		});
		Button button36=(Button)findViewById(R.id.button36);
		button36.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button36Txt);
			}
		});
		Button button37=(Button)findViewById(R.id.button37);
		button37.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button37Txt);
			}
		});
		Button button38=(Button)findViewById(R.id.button38);
		button38.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button38Txt);
			}
		});
		Button button39=(Button)findViewById(R.id.button39);
		button39.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button39Txt);
			}
		});
		Button button40=(Button)findViewById(R.id.button40);
		button40.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button40Txt);
			}
		});
		Button button41=(Button)findViewById(R.id.button41);
		button41.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button41Txt);
			}
		});
		Button button42=(Button)findViewById(R.id.button42);
		button42.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button42Txt);
			}
		});
		Button button43=(Button)findViewById(R.id.button43);
		button43.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button43Txt);
			}
		});
		Button button44=(Button)findViewById(R.id.button44);
		button44.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button44Txt);
			}
		});
		Button button45=(Button)findViewById(R.id.button45);
		button45.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button45Txt);
			}
		});
		Button button46=(Button)findViewById(R.id.button46);
		button46.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button46Txt);
			}
		});
		Button button47=(Button)findViewById(R.id.button47);
		button47.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button47Txt);
			}
		});
		Button button48=(Button)findViewById(R.id.button48);
		button48.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button48Txt);
			}
		});
		Button button49=(Button)findViewById(R.id.button49);
		button49.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button49Txt);
			}
		});
		Button button50=(Button)findViewById(R.id.button50);
		button50.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button50Txt);
			}
		});
		Button button51=(Button)findViewById(R.id.button51);
		button51.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button51Txt);
			}
		});
		Button button52=(Button)findViewById(R.id.button52);
		button52.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button52Txt);
			}
		});
		Button button53=(Button)findViewById(R.id.button53);
		button53.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button53Txt);
			}
		});
		Button button54=(Button)findViewById(R.id.button54);
		button54.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button54Txt);
			}
		});
		Button button55=(Button)findViewById(R.id.button55);
		button55.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button55Txt);
			}
		});
		Button button56=(Button)findViewById(R.id.button56);
		button56.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button56Txt);
			}
		});
		Button button57=(Button)findViewById(R.id.button57);
		button57.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button57Txt);
			}
		});
		Button button58=(Button)findViewById(R.id.button58);
		button58.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button58Txt);
			}
		});
		Button button59=(Button)findViewById(R.id.button59);
		button59.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button59Txt);
			}
		});
		Button button60=(Button)findViewById(R.id.button60);
		button60.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button60Txt);
			}
		});
		Button button61=(Button)findViewById(R.id.button61);
		button61.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button61Txt);
			}
		});
		Button button62=(Button)findViewById(R.id.button62);
		button62.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button62Txt);
			}
		});
		Button button63=(Button)findViewById(R.id.button63);
		button63.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button63Txt);
			}
		});
		Button button64=(Button)findViewById(R.id.button64);
		button64.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button64Txt);
			}
		});
		Button button70=(Button)findViewById(R.id.button70);
		button70.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button70Txt);
			}
		});
		Button button71=(Button)findViewById(R.id.button71);
		button71.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button71Txt);
			}
		});
		Button button72=(Button)findViewById(R.id.button72);
		button72.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button72Txt);
			}
		});
		Button button73=(Button)findViewById(R.id.button73);
		button73.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button73Txt);
			}
		});
		Button button74=(Button)findViewById(R.id.button74);
		button74.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetValues(R.string.button74Txt);
			}
		});
		//////////////////////////////////////////////////////////////
		/*
		 * 弹出菜单的按钮
		 */
		/////////////////////////////////////////////////////////////
		//货物
		Button button80=(Button)findViewById(R.id.button80);
		button80.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowDlgMenuItems(R.array.button80_item);
			}
		});
		//车长
		Button button81=(Button)findViewById(R.id.button81);
		button81.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowDlgMenuItems(R.array.button81_item);
			}
		});
		//车型 
		Button button82=(Button)findViewById(R.id.button82);
		button82.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowDlgMenuItems(R.array.button82_item);
			}
		});
		//运费
		Button button83=(Button)findViewById(R.id.button83);
		button83.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowDlgMenuItems(R.array.button83_item);
			}
		});
		//说明
		Button button84=(Button)findViewById(R.id.button84);
		button84.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowDlgMenuItems(R.array.button84_item);
			}
		});
		/////////////////////////////////////////////////////////////////////////////////
		//Log.e("--------------------add1","end");
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
					app.SetConfig("add1selectedStartProvinceIndex",String.valueOf(selectedProvinceIndex));
				}else if(iSign==1){//到达城市
					selectedStopProvinceIndex=selectedProvinceIndex;
					app.SetConfig("add1selectedStopProvinceIndex",String.valueOf(selectedProvinceIndex));
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
	    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	//设置选择层数
	        	if(iSign==0){//出发城市
	        		//出发地不允许选择省份
	        		/*if(selectedProvinceIndex>0){
	        			mStringID=R.string.startcitynoprovincetxt;
	        			addMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
		        		return;
	        		}*/
	        		m_iStartSign=0;//省份层次
	        		app.SetConfig("add1startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//到达城市
					m_iStopSign=0;//省份层次
					app.SetConfig("add1stopsign",String.valueOf(m_iStopSign));
				}
	        	showProvince(CityBtn,iSign);
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
			app.SetConfig("add1startcity",strProvince);
		}else if(iSign==1){//到达城市
			m_strStopCity=strProvince;
			app.SetConfig("add1stopcity",strProvince);
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
					app.SetConfig("add1selectedStartCityIndex",String.valueOf(selectedCityIndex));
				}else if(iSign==1){//到达城市
					selectedStopCityIndex=selectedCityIndex;
					app.SetConfig("add1selectedStopCityIndex",String.valueOf(selectedCityIndex));
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
	        	//
	        	ShowTownDlg(cityBtn,iSign);
	        /*	if(iSign!=0)//出发地
	        	{
		        	//显示县选择对话框
		        	ShowTownDlg(cityBtn,iSign);
	        	}else//iSign==0
	        	{
	        		m_iStartSign=1;//城市层次
	        		app.SetConfig("add1startsign",String.valueOf(m_iStartSign));
	        		//出发地不允许选择县区
	        		mStringID=R.string.startcitynotowntxt;
	        		addMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
	        	}*/
	        }
	    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	showCity(cityBtn,iSign);
	        	//设置选择层数
	        	if(iSign==0){//出发城市
	        		m_iStartSign=1;//城市层次
	        		app.SetConfig("add1startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//到达城市
					m_iStopSign=1;//城市层次
					app.SetConfig("add1stopsign",String.valueOf(m_iStopSign));
				}
	        }
	       }).create();

		alertDialog.show();
	}
	//
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
			app.SetConfig("add1startcity",strCity);
		}else if(iSign==1){//到达城市
			m_strStopCity=strCity;
			app.SetConfig("add1stopcity",strCity);
		}
	}
	//
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
					app.SetConfig("add1selectedStartTownIndex",String.valueOf(selectedTownIndex));
				}else if(iSign==1){//到达城市
					selectedStopTownIndex=selectedTownIndex;
					app.SetConfig("add1selectedStopTownIndex",String.valueOf(selectedTownIndex));
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
	        		app.SetConfig("add1startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//到达城市
					m_iStopSign=2;//县区层次
					app.SetConfig("add1stopsign",String.valueOf(m_iStopSign));
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
			app.SetConfig("add1startcity",strTown);
		}else if(iSign==1){//到达城市
			m_strStopCity=strTown;
			app.SetConfig("add1stopcity",strTown);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//发布信息
	protected void addInfo() {
		// TODO Auto-generated method stub
		//判断是否有发布权限
		int addfunc=app.myinfo.addfunc;//0;//app.myinfo.addfunc
		if(addfunc==0){
			//对不起，您暂时没有权限使用该发布功能
			MyMessage(R.string.addinfoErr1Txt);
			return;
		}
		String strOldValue=infoContent.getText().toString();
		if(strOldValue.equals(""))
		{
			//信息内容不能为空
			MyMessage(R.string.addinfoDataErrTxt);
			return;
		}
		int limit=app.myinfo.CharNum;
		
		//Log.e("!!!!!!!!!!!!!!add1",String.valueOf(limit));
		
		if(limit!=0){
			if(strOldValue.length()>=limit){
				//
				MyMessage(R.string.addinfoErr2Txt);
				return;
			}
		}
		//
		Message msg = new Message();// 
        msg.what = 2;  
        addHander.sendMessage(msg);
        //发送信息
        sendInfo();
	}
	//发送信息
	protected void sendInfo() {
		// TODO Auto-generated method stub
		String msg_code = app.myinfo.city;
		String msg_type = "1";
		String msg_content = "";
		String msg_tel="";
		String msg_flag=String.valueOf(app.myinfo.userID);
		String msg_startcity="";
		String msg_netphone=app.myinfo.sj1;
		String infonum=String.valueOf(app.myinfo.InfoNum);
		//信息类型
		String strType=mTypeBtn.getText().toString();
		if(strType.equals(getString(R.string.typeBtn1Txt)))//货源信息
		{
			msg_type="0";
		}else//车源信息 
		{
			msg_type="1";
		}
		//信息内容
		String strStartCity=mstartcity.getText().toString();
		if(strStartCity.equals(getString(R.string.quanguo))){//如果为全国，则为空
			strStartCity="";
		}
		msg_startcity=strStartCity;
		String strStopCity=mstopcity.getText().toString();
		if(strStopCity.equals(getString(R.string.quanguo))){//如果为全国，则为空
			strStopCity="";
		}
		String strContent=infoContent.getText().toString();
		if(!strStartCity.equals("") && !strStopCity.equals(""))
		{
			msg_content=strStartCity+"→"+strStopCity+","+strContent;
		}else
		{
			msg_content=strContent;
		}
		//联系方式
		msg_tel=app.myinfo.tel+" "+app.myinfo.sj+" "+app.myinfo.tel1+" "+app.myinfo.company+" "+app.myinfo.name1;
		//提交数据
		String InfoSign="0";//钢材煤炭为1;货运信息为0
		String strInfo=DataSource.getInstance().sendHCInfo(InfoSign,msg_code, msg_type, msg_content, msg_tel, msg_flag, msg_startcity, msg_netphone, infonum);
		int result=strInfo.indexOf("Error");
		if(result>=0){
            MyMessage(R.string.addinfoMsgE1);
            return;
		}else{
			if(strInfo.equals("AddError")){////
				MyMessage(R.string.addinfoMsgE2);
				return ;
			}else if(strInfo.equals("AddFull")){////
				MyMessage(R.string.addinfoMsgE3);
				return ;
			}else if(strInfo.equals("AddOK")){////
				Message msg = new Message();// 
		        msg.what = 3;  
		        addHander.sendMessage(msg);
		        //Log.e("!!!!!!!!!","addok");
		        //清空发布框
		        //infoContent.setText("");
		        msg = new Message();// 必须这样写，不然会报错。因为   
	            msg.what = 5;  
	            addHander.sendMessage(msg);
		        
		        //Log.e("!!!!!!!!!!","set infoContent null");
		        //取消提示框
		        msg = new Message();// 必须这样写，不然会报错。因为   
	            msg.what = 4;  
	            addHander.sendMessage(msg);
			}
		}
	}
	//提示信息
	Handler addHander = new Handler() {  
		@Override  
		public void handleMessage(Message msg) {  
		switch (msg.what) {  
			case 1:// 
				mypDialog.setMessage(getString(R.string.addinfoMsg1));
				mypDialog.show();// 让ProgressDialog显示   
				break;  
			case 2://
				mypDialog.setMessage(getString(R.string.addinfoMsg2));
				mypDialog.show();  
				break;
			case 3://
				mypDialog.setMessage(getString(R.string.addinfoMsg3));
				mypDialog.show();  
				break; 
			case 4://
				//mypDialog.setMessage("");
				mypDialog.cancel();  
				break;
			case 5://
				infoContent.setText("");
				break;
			}  
			}  
		}; 

	//对话框菜单选择
	protected void ShowDlgMenuItems(final int arrayItem) {
		// TODO Auto-generated method stub
		//显示菜单
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		//.setTitle("选择")
		.setItems(arrayItem, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strItems=getResources().getStringArray(arrayItem)[which];
				GetValues(strItems);
			}
		})
		.create();
		alertDialog.show();
	}
	//显示信息类型的对话框菜单 
	protected void showTypeDlg() {
		// TODO Auto-generated method stub
		//显示菜单
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		//.setTitle("选择")
		.setItems(R.array.typeBtn_item, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strType=getResources().getStringArray(R.array.typeBtn_item)[which];
				mTypeBtn.setText(strType);
			}
		})
		.create();
		alertDialog.show();
	}
	//退格键
	protected void BackSpace() {
		// TODO Auto-generated method stub
		//直接 删除信息框最后一个字符
		/*
		String strOldValue=infoContent.getText().toString();
		int  ilength=strOldValue.length();
		if(ilength>0)
		{
			String strNewValue=strOldValue.substring(0,ilength-1);
			infoContent.setText(strNewValue);
		}*/
		String strOldValue=infoContent.getText().toString();
		int  ilength=strOldValue.length();
		if(ilength>0)
		{
			int index = infoContent.getSelectionStart();  
			Editable editable = infoContent.getText();  
			editable.delete(index-1, index);  
		}
		
		//设置光标位置 
		/*CharSequence text = infoContent.getText();
		//Debug.asserts(text instanceof Spannable);
		if (text instanceof Spannable) {
			Spannable spanText = (Spannable)text;
			Selection.setSelection(spanText, text.length());
		}*/

	}
	//点击按钮输入到信息框中
	protected void GetValues(int stringValueID) {
		// TODO Auto-generated method stub
		String strOldValue=infoContent.getText().toString();
		String strValue=getString(stringValueID);
		
		//直接
		//String strNewValue=strOldValue+strValue;
		//infoContent.setText(strNewValue);
		//插入到光标处
		String strNewValue=strValue;
		InsertText(strNewValue);
	}
	
	protected void GetValues(String stringValue) {
		// TODO Auto-generated method stub
		String strOldValue=infoContent.getText().toString();
		
		//直接
		//String strNewValue=strOldValue+stringValue;
		//infoContent.setText(strNewValue);
		//插入到光标处
		String strNewValue=stringValue;
		InsertText(strNewValue);
	}
	private void InsertText(String strValue) {
		// TODO Auto-generated method stub
		//直接
		//infoContent.setText(strNewValue);
		//在光标处插入
		int index = infoContent.getSelectionStart();  
		Editable editable = infoContent.getText();  
		editable.insert(index, strValue);  
		//设置光标位置 
	/*	CharSequence text = infoContent.getText();
		//Debug.asserts(text instanceof Spannable);
		if (text instanceof Spannable) {
		     Spannable spanText = (Spannable)text;
		     Selection.setSelection(spanText, text.length());
		}*/
	}
	//关闭对话框，并且提示String
		private void MyMessage(String string) {
			// TODO Auto-generated method stub
			//取消提示消息
			Message msg = new Message();// 
	        msg.what = 4;  
	        addHander.sendMessage(msg);
			//取得提示
	        mString=string;
	        addMsgHander.post(runnableUiString);   //更新界面UI(提示)
		}
		//关闭对话框，并且提示StringID
		private void MyMessage(int stringid) {
			// TODO Auto-generated method stub
			//取消提示消息
			Message msg = new Message();// 
	        msg.what = 4;  
	        addHander.sendMessage(msg);
			//取得提示
	        mStringID=stringid;
	        addMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
		}
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

	
}
