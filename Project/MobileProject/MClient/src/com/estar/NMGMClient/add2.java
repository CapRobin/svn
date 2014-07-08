package com.estar.NMGMClient;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.estar.comm.MySuperActivity;
import com.estar.comm.MySuperApplication;
import com.estar.data.DataSource;

public class add2 extends MySuperActivity{

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		///* 载入mylayout.xml Layout */ 
		setContentView(R.layout.add2); 
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
				add2.this.finish();
			}
		});
		//信息类型选择
		mTypeBtn=(Button)findViewById(R.id.typeBtn);
		
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
		String msg_type = "0";
		String msg_content = "";
		String msg_tel="";
		String msg_flag=String.valueOf(app.myinfo.userID);
		String msg_startcity="";
		String msg_netphone=app.myinfo.sj1;
		String infonum=String.valueOf(app.myinfo.InfoNum);
		//信息类型
		/*String strType=mTypeBtn.getText().toString();
		if(strType.equals(getString(R.string.typeInfo1Txt)))//货源信息
		{
			msg_type="0";
		}else//车源信息 
		{
			msg_type="1";
		}*/
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
		String InfoSign="1";//钢材煤炭为1;货运信息为0
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
