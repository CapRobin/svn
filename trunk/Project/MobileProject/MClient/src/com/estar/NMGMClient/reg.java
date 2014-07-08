package com.estar.NMGMClient;

import com.estar.comm.MySuperActivity;
import com.estar.comm.MySuperApplication;
import com.estar.comm.PCT;
import com.estar.data.DataSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class reg extends MySuperActivity{

	private MySuperApplication app;
	Bundle bunde; 
	Intent intents; 
	
	private Handler regMsgHander=null;  
	private MyFun myFun;
	private ProgressDialog mypDialog;
	private int mStringID=0;
	private String mString=null;

	Button mAgreeBtn,mNoAgreeBtn,mServiceBtn,mUsertypeBtn;
	EditText mSj,mName1,mCompany,mTelnum,mTel,mQQ,mPostCard;
	EditText mPwd,mPwd2;
	RadioGroup mSexGroup1;
	RadioButton mMan,mWoman;
	Button mProvince,mCity,mTown;
	//定义变量
	String m_strSj,m_strName1,m_strCompany,m_strTelnum,m_strTel,m_strSex,m_strUserType;
	String m_strPwd,m_strPwd2,m_strPostCard;
	
	String m_strQQ="";
	String m_strProvince="";
	String m_strCity="";
	String m_strTown="";
	
	private int selectedProvinceIndex = 0;
	private int selectedCityIndex = 0;
	private int selectedTownIndex = 0;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reg); 
		
		//全局变量相关
		app = (MySuperApplication) getApplication(); //获得我们的应用程序MySuperApplication
		//取得相关值，注册完成后，将用户名密码返回给登录框并显示
		intents=this.getIntent(); 
	    bunde = intents.getExtras();
	    //
	    myFun=new MyFun();
	    regMsgHander=new Handler();  //创建属于主线程的handler   
		//Button
		mAgreeBtn=(Button)findViewById(R.id.agreeBtn);
		mNoAgreeBtn=(Button)findViewById(R.id.noAgreeBtn);
		mServiceBtn=(Button)findViewById(R.id.serviceBtn);
		mProvince=(Button)findViewById(R.id.province);
		mCity=(Button)findViewById(R.id.city);
		mTown=(Button)findViewById(R.id.town);
		mUsertypeBtn=(Button)findViewById(R.id.usertype);
		//EditText
		mSj=(EditText)findViewById(R.id.sj);
		mPwd=(EditText)findViewById(R.id.pwdreg);
		mPwd2=(EditText)findViewById(R.id.pwdreg2);
		mName1=(EditText)findViewById(R.id.name1);
		mCompany=(EditText)findViewById(R.id.company);
		mPostCard=(EditText)findViewById(R.id.postcard);
		mTelnum=(EditText)findViewById(R.id.telnum);
		mTel=(EditText)findViewById(R.id.tel);
		mQQ=(EditText)findViewById(R.id.qq);
		//RadioGroup
		mSexGroup1=(RadioGroup)findViewById(R.id.sexGroup1);
		mMan=(RadioButton)findViewById(R.id.man);
		mWoman=(RadioButton)findViewById(R.id.woman);
		
		m_strSex=getString(R.string.manTxt);
		
		mSexGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==mMan.getId()){
					m_strSex=getString(R.string.manTxt);
				}else if(checkedId==mWoman.getId()){
					m_strSex=getString(R.string.womanTxt);
				}
			}
		});
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

		//点击 不同意按钮
		mNoAgreeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reg.this.finish();
			}
		});
		//点击阅读服务条款按钮
		mServiceBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//显示服务条款窗口
				//使用默认浏览器打开网页 
				/*Intent intent = new Intent();        
				intent.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse(app.GetServiceUrl()); //利用全局变量取得服务条款路径或getString(R.string.serviceUrl)
				intent.setData(content_url);  
				startActivity(intent);*/
				//在窗体中显示网页
				Intent intent=new Intent();
				intent.setClass(reg.this, serviceWnd.class);
				reg.this.startActivity(intent);
			}
		});
		//点击注册按钮
		mAgreeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onAgreeReg();
			}
		});
		//点击省份
		mProvince.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示对话框
                ShowProvinceDlg();
			}
		});
		//点击城市
		mCity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示对话框
                ShowCityDlg();
			}
		});
		//点击县区
		mTown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示对话框
                ShowTownDlg();
			}
		});
		//点击会员类型
		mUsertypeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示对话框
                ShowMemTypeDlg();
			}
		});
	}
		
		//显示会员类型的对话框菜单 
		protected void ShowMemTypeDlg() {
			// TODO Auto-generated method stub
			//显示菜单
			AlertDialog alertDialog = new AlertDialog.Builder(this)
			//.setTitle("选择")
			.setItems(R.array.regMem_item, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String strType=getResources().getStringArray(R.array.regMem_item)[which];
					mUsertypeBtn.setText(strType);
				}
			})
			.create();
			alertDialog.show();
		}
		
		//显示省份
		protected void ShowProvinceDlg() {
			// TODO Auto-generated method stub
			AlertDialog alertDialog = new AlertDialog.Builder(this)
		    .setTitle(getString(R.string.xuanzhe))
		    //.setIcon(R.drawable.ic_launcher)
		    .setSingleChoiceItems(R.array.province_item, selectedProvinceIndex, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					selectedProvinceIndex = which;
				}
		    	
		    }).setPositiveButton(getString(R.string.xiaji), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	//显示省选择
		        	showProvince();
		        	//显示市选择对话框 
		        	ShowCityDlg();
		        }
		    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		         // TODO Auto-generated method stub
		        	showProvince();
		        }
		       }).create();

			alertDialog.show();
		}
		//
		protected void showProvince() {
			// TODO Auto-generated method stub
			m_strProvince=getResources().getStringArray(R.array.province_item)[selectedProvinceIndex];
	    	mProvince.setText(m_strProvince);
	    	//
	    	selectedCityIndex=0;
	    	selectedTownIndex=0;
	    	mCity.setText(getString(R.string.cityTxt));
	    	mTown.setText(getString(R.string.townTxt));
		}
		//显示城市
		protected void ShowCityDlg() {
			// TODO Auto-generated method stub
			//取得省份（如果为全国，则不弹出）
			m_strProvince=mProvince.getText().toString();
			if(m_strProvince.equals(getString(R.string.quanguo)) || m_strProvince.equals(""))
			{
				return;
			}
			AlertDialog alertDialog = new AlertDialog.Builder(this)
		    .setTitle(getString(R.string.xuanzhe))
		    //.setIcon(R.drawable.ic_launcher)
		    .setSingleChoiceItems(city[selectedProvinceIndex], selectedCityIndex, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					selectedCityIndex = which;
				}
		    	
		    }).setPositiveButton(getString(R.string.shangji), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		         
		        	//显示市选择
		        	showCity();
		        	//显示省选择对话框
		        	ShowProvinceDlg();
		        }
		    })/*.setNeutralButton(getString(R.string.xiaji), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	//显示市选择
		        	showCity();
		        	//显示县选择对话框
		        	ShowTownDlg();
		        }
		    })*/.setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		         // TODO Auto-generated method stub
		        	showCity();
		        }
		       }).create();

			alertDialog.show();
		}
		protected void showCity() {
			// TODO Auto-generated method stub
			m_strCity=getResources().getStringArray(city[selectedProvinceIndex])[selectedCityIndex];
	    	mCity.setText(m_strCity);
	    	//
	    	selectedTownIndex=0;
	    	mTown.setText(getString(R.string.townTxt));
	    	//==============================================
	    	//设置选择城市的区号 mTelnum
	    	PCT pct=new PCT();
	    	String strArea=pct.GetCityArea(m_strCity);
	    	if(!strArea.equals("")){
	    		mTelnum.setText(strArea);
	    		SetNoReadOnly(false);//设置为只读
	    	}else
	    	{
	    		SetNoReadOnly(true);
	    	}
	    	//==============================================
		}
		//设置区号只读状态
		private void SetNoReadOnly(boolean b) {
			// TODO Auto-generated method stub
			mTelnum.setCursorVisible(b);      
			mTelnum.setFocusable(b);         
			mTelnum.setFocusableInTouchMode(b);    
		}
		//显示县区
		protected void ShowTownDlg() {
			// TODO Auto-generated method stub
			//取得城市（如果为城市，则不弹出）
			if(m_strCity.equals(getString(R.string.cityTxt)) || m_strCity.equals(""))
			{
				return;
			}
					
			AlertDialog alertDialog = new AlertDialog.Builder(this)
		    .setTitle(getString(R.string.xuanzhe))
		    //.setIcon(R.drawable.ic_launcher)
		    .setSingleChoiceItems(GetTownItems(selectedProvinceIndex,selectedCityIndex), selectedTownIndex, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					selectedTownIndex = which;
				}
		    	
		    }).setPositiveButton(getString(R.string.shangji), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		         
		        	//显示县选择
		        	showTown();
		        	//显示市选择对话框
		        	ShowCityDlg();
		        }
		    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		         // TODO Auto-generated method stub
		        	showTown();
		        }
		       }).create();

			alertDialog.show();
		}

		protected void showTown() {
			// TODO Auto-generated method stub
			m_strTown=getResources().getStringArray(GetTownItems(selectedProvinceIndex,selectedCityIndex))[selectedTownIndex];
	    	mTown.setText(m_strTown);
		}
		
	//注册
	protected void onAgreeReg() {
		// TODO Auto-generated method stub
		//取得各项值
		if(!GetItemValue())
		{
			return;
		}
		//进行注册 线程
		new Thread(){
			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//super.run();
				//正在检测网络提示
				Message msg = new Message();  
	            msg.what = 1;// 正在检测网络
	            regHander.sendMessage(msg);  
	            //检测网络代码
	            String strAnswer=DataSource.getInstance().checkConnection();
	            //mString=strAnswer;
	            //regMsgHander.post(runnableUiString);
	            if(strAnswer.equals("TestOK"))
	            {
	            	//成功联网
	            }else
	            {
	            	//取消提示消息
	            	msg = new Message();// 必须这样写，不然会报错。因为   
		            msg.what = 4;  
		            regHander.sendMessage(msg);
		            //取得提示
		            mStringID=R.string.netChkTxt;
		            regMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
		            //打开网络配置页面
		            
	            	return;
	            }
	            //正在提交数据
				msg = new Message();  
	            msg.what = 2;// 正在提交数据
	            regHander.sendMessage(msg);  
	            //进行注册
	            if(Reg()){
	            	//取消提示消息
	            	msg = new Message();// 必须这样写，不然会报错。因为   
		            msg.what = 4;  
		            regHander.sendMessage(msg);
	            	//关闭本对话框
		            CloseWnd();
	            }
			}
			
		}.start();
	}
	//
	Handler regHander = new Handler() {  
	@Override  
	public void handleMessage(Message msg) {  
	switch (msg.what) {  
		case 1:// 开始下载标志   
			mypDialog.setMessage(getString(R.string.loginMsgW1));
			mypDialog.show();// 让ProgressDialog显示   
			break;  
		case 2://
			mypDialog.setMessage(getString(R.string.regsumbitTxt));
			mypDialog.show();  
			break;
		case 4://
			//mypDialog.setMessage("");
			mypDialog.cancel();  
			break;
		}  
		}  
	}; 
	//
	private boolean Reg(){
		
		String Imei = "A0000022DAC845";
//		String diskinfo=myFun.getMobileIMEI()+myFun.getMobileIESI();//IMEI号+IESI号
		String diskinfo=Imei+myFun.getMobileIESI();//IMEI号+IESI号
		String macaddr=myFun.getSimSerialNumber();//SIM卡卡号 
		String strDeviceInfo=myFun.getMobileDeviceDataString();//手机相关资料（备注）
		//Log.e("!!!!!!!!reg_post",post);
		String strInfo=DataSource.getInstance().regUser(
				m_strSj,
				m_strPwd,
				m_strName1,
				m_strSex,
				m_strCompany,
				m_strProvince,
				m_strCity,
				"",
				"",
				m_strTel,
				"",
				m_strQQ,
				"",
				m_strPostCard,
				strDeviceInfo,
				diskinfo,
				macaddr,
				m_strTown,
				m_strUserType);
		//MyMessage(strInfo);
		
		//Log.e("!!!!!!!!!!!!!!reg", strInfo);
		//MyMessage(strInfo);
		
		int result=strInfo.indexOf("Error");
		if(result>=0){
            MyMessage(R.string.loginMsgE1);
            return false;
		}else{
			String[] strInfoArray=strInfo.split(":"); 
			String strInfo1=strInfoArray[0];
			String strInfo2="";
			String strInfo3="";
			if(strInfoArray.length>=2)//modify in 2013-12-20
			{
				strInfo2=strInfoArray[1];
			}
			//
			if(strInfo1.equals("HasReged")){////普通会员
				MyMessage(R.string.regMsgE1);
				return false;
			}else if(strInfo1.equals("HasRegedBefore")){
				strInfo3=getString(R.string.regMsgE2)+strInfo2+getString(R.string.regMsgE21);
				MyMessage(strInfo3);
				return false;
			}else if(strInfo1.equals("RegNoGo")){
				MyMessage(R.string.regMsgE3);
				return false;
			}else if(strInfo1.equals("RegSuccess")){
				strInfo3=getString(R.string.regMsgS1)+strInfo2+getString(R.string.regMsgS11);
				MyMessage(strInfo3);
				return true;
			}
		}
		return true;
	}
	//关闭此对话框
	protected void CloseWnd(){
		//将需要返回的数据增加到Bundle中
	    // Bundle bundle = new Bundle(); 
	    bunde.putString("username",m_strSj); 
	    bunde.putString("password",m_strPwd); 
	    /*将Bundle对象assign给Intent*/ 
	    intents.putExtras(bunde);  
	    /* 回传result回上一个activity */ 
	    reg.this.setResult(RESULT_OK, intents); 
	    /* 关闭activity */ 
	    reg.this.finish(); 
	}
	//取得各项值
	private boolean GetItemValue() {
		// TODO Auto-generated method stub
		m_strSj = mSj.getText().toString();//手机
		m_strPwd=mPwd.getText().toString();//密码
		m_strPwd2=mPwd2.getText().toString();//密码2
		m_strName1 = mName1.getText().toString(); //真实姓名
		m_strCompany = mCompany.getText().toString(); //公司名称
		m_strPostCard=mPostCard.getText().toString();//身份证号码
		m_strTelnum = mTelnum.getText().toString(); //区号
		m_strTel = mTel.getText().toString();//电话号码
		m_strQQ = mQQ.getText().toString(); //QQ号
		
		m_strProvince = mProvince.getText().toString();//省份 
		m_strCity = mCity.getText().toString(); //城市
		m_strTown = mTown.getText().toString(); //县区
		m_strUserType=mUsertypeBtn.getText().toString();//会员类型
		//取得性别
		if(mMan.isChecked()){
			m_strSex=getString(R.string.manTxt);
		}
		if(mWoman.isChecked()){
			m_strSex=getString(R.string.womanTxt);
		}
		//--------------判断检测------------------------------
		//手机号
		if(m_strSj.equals(""))
		{
			mSj.requestFocus();
			Msg(R.string.sjChkTxt);
			return false;
		}
		//密码
		if(m_strPwd.equals(""))
		{
			mPwd.requestFocus();
			Msg(R.string.pwdregChkTxt);
			return false;
		}
		//确认密码
		if(!m_strPwd.equals(m_strPwd2))
		{
			mPwd.requestFocus();
			Msg(R.string.pwdreg2ChkTxt);
			return false;
		}
		//真实姓名
		if(m_strName1.equals(""))
		{
			mName1.requestFocus();
			Msg(R.string.name1ChkTxt);
			return false;
		}
		//身份证号码
		if(m_strPostCard.equals(""))
		{
			mPostCard.requestFocus();
			Msg(R.string.postcardChkTxt);
			return false;
		}
		//验证身份证是否正确合法
		if(!myFun.sfzCheck(m_strPostCard))
		{
			mPostCard.requestFocus();
			Msg(R.string.postcardErrChkTxt);
			return false;
		}
		//省份
		if(m_strProvince.equals("") || m_strProvince.equals(getString(R.string.provinceTxt)) || m_strProvince.equals(getString(R.string.quanguo)))
		{
			mProvince.requestFocus();
			Msg(R.string.proChkTxt);
			return false;
		}
		//城市
		if(m_strCity.equals("") || m_strCity.equals(getString(R.string.cityTxt)))
		{
			mCity.requestFocus();
			Msg(R.string.cityChkTxt);
			return false;
		}
		//县区
		/*if(m_strTown.equals("") || m_strTown.equals(getString(R.string.townTxt)))
		{
			mTown.requestFocus();
			Msg(R.string.townChkTxt);
			return false;
		}*/
		//区号
		if(m_strTelnum.equals(""))
		{
			mTelnum.requestFocus();
			Msg(R.string.telnumChkTxt);
			return false;
		}
		//电话号码
		if(m_strTel.equals(""))
		{
			mTel.requestFocus();
			Msg(R.string.telChkTxt);
			return false;
		}
		m_strTel=m_strTelnum+"-"+m_strTel;
		//----------------------------------------------------
		return true;
	}
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

	//关闭对话框，并且提示String
		private void MyMessage(String string) {
			// TODO Auto-generated method stub
			//取消提示消息
			Message msg = new Message();// 
	        msg.what = 4;  
	        regHander.sendMessage(msg);
			//取得提示
	        mString=string;
	        regMsgHander.post(runnableUiString);   //更新界面UI(提示)
		}
		//关闭对话框，并且提示StringID
		private void MyMessage(int stringid) {
			// TODO Auto-generated method stub
			//取消提示消息
			Message msg = new Message();// 
	        msg.what = 4;  
	        regHander.sendMessage(msg);
			//取得提示
	        mStringID=stringid;
	        regMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
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
		 
}
