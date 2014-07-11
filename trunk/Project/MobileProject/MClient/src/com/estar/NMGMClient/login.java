package com.estar.NMGMClient;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.estar.comm.AutoInstall;
import com.estar.comm.MySuperActivity;
import com.estar.comm.MySuperApplication;
import com.estar.data.DataSource;
import com.estar.net.HttpDownloader;

public class login extends MySuperActivity{

	private MySuperApplication app;
	private Handler loginMsgHander=null;  
	private MyFun myFun=null;
	
	private Button mloginBtn,mregBtn;
	private EditText mUsername,mPassword;
	private CheckBox mNopass;
	private ProgressDialog mypDialog;
	private int mStringID=0;
	private String mString=null;
	private String mUpdateUrl=null;
	private boolean isShowRightBut = false;
	//
	String m_strUsername,m_strPassword,m_strNopass;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/* 载入mylayout.xml Layout */ 
//	    setContentView(R.layout.login_x); 
//		application = (MyApplication) abApplication;
		setAbContentView(R.layout.login_x);
//		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout("登录", isShowRightBut);
//		initView();
	    
	    
	    //全局变量相关
	  	app = (MySuperApplication) getApplication(); //获得我们的应用程序MySuperApplication
	  	loginMsgHander=new Handler();  //创建属于主线程的handler   
	  	myFun=new MyFun();
	    //得到相关资料
	    //EditText
	    mUsername=(EditText)findViewById(R.id.username);
	    mPassword=(EditText)findViewById(R.id.password);
	    //Button
	    mloginBtn=(Button)findViewById(R.id.loginBtn);
	    mregBtn=(Button)findViewById(R.id.regBtn);
	    //CheckBox
	    mNopass=(CheckBox)findViewById(R.id.nopass);
	    //======================================================
	    //读取配置文件，对此进行配置
	    ReadConfigData();
	    //======================================================
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
	    //点击注册按钮
	    mregBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//显示注册窗体
				Intent intent=new Intent();
				intent.setClass(login.this, reg.class);
				//login.this.startActivity(intent);
				/*new一个Bundle对象，并将要传递的数据传入*/ 
				Bundle bundle = new Bundle(); 
		        bundle.putString("username",""); 
		        bundle.putString("password",""); 
		        /*将Bundle对象assign给Intent*/ 
		        intent.putExtras(bundle); 
				login.this.startActivityForResult(intent,0); 
			}
		});
		
//		//测试ActivityView
//		mregBtn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(login.this,TestActivity.class));
//			}
//		});
		
	    //点击登录按钮
	    mloginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onLogin();
			}
		});
	    
	    
	    
	    
		// 右侧按钮的点击事件_测试
		if (isShowRightBut) {
			this.rightTitleBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					startActivity(new Intent(login.this,TestActivity.class));
				}
			});
		}
	    
	    
	}
	/* 重写 onActivityResult()*/ 
	//接收来自注册窗口的返回值
	  @Override 
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
	    switch (resultCode) { 
	      case RESULT_OK: 
	        /* 取得数据，并显示于画面上 */ 
	        Bundle bunde = data.getExtras(); 
	        String username = bunde.getString("username");
	        String password = bunde.getString("password"); 
	        mUsername.setText(username);
			mPassword.setText(password);
	        break; 
	       default: 
	          break; 
	          
	    } 
	    
	  } 
	//读取配置文件信息
	private void ReadConfigData() {
		// TODO Auto-generated method stub
		SharedPreferences cfg=getSharedPreferences(app.getConfigFileName(),MODE_PRIVATE);
		String username=cfg.getString("username", "");
		String password=cfg.getString("password", "");
		String nopass=cfg.getString("nopass", "1");
		//赋值
		if(nopass.equals("1"))
		{
			mNopass.setChecked(true);
			mUsername.setText(username);
			mPassword.setText(password);
		}else
		{
			mNopass.setChecked(false);
		}
	}
	//登录
	protected void onLogin() {
		// TODO Auto-generated method stub
		//取得相关ITEM值
		if(!GetItemValue())
		{
			return ;
		}
		//存储相关参数
		SaveConfigValue();
		//线程进行登录
		LoginIn();
	}
	//进行登录 线程
	private void LoginIn() {
		// TODO Auto-generated method stub
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
	            msg.what = 1;// 开始下载标志1   
	            loginHander.sendMessage(msg);  
	            //检测网络代码
	            String strAnswer=DataSource.getInstance().checkConnection();
	            //mString=strAnswer;
	            //loginMsgHander.post(runnableUiString);
	            //Log.e("!!!!!!!!!!!!!!Login_run", strAnswer);
	            if(strAnswer.equals("TestOK"))
	            {
	            	//成功联网
	            }else
	            {
	            	//取消提示消息
	            	msg = new Message();// 必须这样写，不然会报错。因为   
		            msg.what = 4;  
		            loginHander.sendMessage(msg);
		            //取得提示
		            mStringID=R.string.netChkTxt;
		            loginMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
		            //打开网络配置页面
	            	return;
	            }
	            //Log.e("!!!!!!!!!!!!!!Login_checkCurVer", "checkCurVer");
	            //正在检测新版本提示
	            msg = new Message();// 必须这样写，不然会报错。因为   
	            msg.what = 2;  
	            loginHander.sendMessage(msg);
	            //检测新版本
	            if(checkCurVer())
	            {
	            	//正在升级
					msg = new Message();// 
		            msg.what = 5;  
		            loginHander.sendMessage(msg);
		            //下载新的程序，并安装
		            downloadUpdateFile(mUpdateUrl);
		            //取消提示消息
		            msg = new Message();// 必须这样写，不然会报错。因为   
		            msg.what = 4;  
		            loginHander.sendMessage(msg);
	            }else
	            {
			        //正在登录提示
			        msg = new Message();// 必须这样写，不然会报错。因为   
			        msg.what = 3;  
			        loginHander.sendMessage(msg);
			        //Log.e("!!!!!!!!!!!!!!Login_checkUser", "checkUser");
			        //正在登录
			        CheckUser();
		            //取消提示消息
		            msg = new Message();// 必须这样写，不然会报错。因为   
		            msg.what = 4;  
		            loginHander.sendMessage(msg);
	            }
			}
			
		}.start();
	}
	//
	protected void CheckUser() {
		// TODO Auto-generated method stub
		//Log.e("!!!!!!!!!!!!!!CheckUser", "CheckUser_begin");
		//Log.e("!!!!!!!!!!!!!!Login_diskinfo", myFun.getMobileIMEI());
		String diskinfo=myFun.getMobileIMEI()+myFun.getMobileIESI();//IMEI号+IESI号
		String macaddr=myFun.getSimSerialNumber();//SIM卡卡号 
		String post=myFun.getMobileDeviceDataString();//手机相关资料（登录对比）
		//Log.e("!!!!!!!!!!!!!!Login_User", m_strUsername);
		//Log.e("!!!!!!!!!!!!!!Login_pwd", m_strPassword);
		//Log.e("!!!!!!!!!!!!!!Login_diskinfo", diskinfo);
		//Log.e("!!!!!!!!!!!!!!Login_macaddr", macaddr);
		
		String strInfo=DataSource.getInstance().checkUser(m_strUsername, m_strPassword, diskinfo, macaddr,post);
		
		//Log.e("!!!!!!!!!!!!!!Login_Answer", strInfo);
		
		//int result=strInfo.indexOf("Error");
		if(strInfo.equals("Error")){
            MyMessage(R.string.loginMsgE1);
            return;
		}else{
			if(strInfo.equals("NotGoInSign")){////普通会员
				MyMessage(R.string.loginMsgE2);
				return ;
			}else if(strInfo.equals("UserOrPassError")){
				MyMessage(R.string.loginMsgE3);
				return ;
			}else if(strInfo.equals("PassError")){
				MyMessage(R.string.loginMsgE4);
				return ;
			}else if(strInfo.equals("HaveNoTime")){
				MyMessage(R.string.loginMsgE5);
				return ;
			}else if(strInfo.equals("NotTheMachine")){
				MyMessage(R.string.loginMsgE6);
				return ;
			}
			
			//Log.e("!!!!!!!!!!!!!!Login_setMyinfo", strInfo);
			
			//将登录信息转移至全局变量中
			app.setMyinfo(strInfo);
			if(app.myinfo.lasttime<=15){
				String str;
				str=getString(R.string.loginMsgE7)+app.myinfo.lasttime+getString(R.string.loginMsgE71);
				MyMessage(str);
			}
			//设置会员名为全局变量
			app.setUsername(m_strUsername);
			
			//Log.e("!!!!!!!!!!!!!!Login_setUsername", m_strUsername);
			
			//显示主界面
			Intent intent=new Intent();
			intent.setClass(login.this, MainActivity.class);
			/*new一个Bundle对象，并将要传递的数据传入*/ 
			Bundle bundle = new Bundle(); 
	        bundle.putString("city",app.myinfo.city);
	        bundle.putInt("userid", app.myinfo.userID);
	        /*将Bundle对象assign给Intent*/ 
	        intent.putExtras(bundle); 
	        //=================================================================
	        
	      //一些默认参数的设置
	        //========主界面相关参数===========================================
	        //设置配置文件
	        String strCity=app.myinfo.city;
	        app.SetConfig("startcity",getString(R.string.quanguo));//***出发地	strCity
	        app.SetConfig("stopcity",getString(R.string.quanguo));//到达地(全国)
	        app.SetConfig("userid",String.valueOf(app.myinfo.userID));//会员ID
	        
	        app.SetConfig("startsign","0");//***城市级别(出发地) 1
	        app.SetConfig("stopsign","0");
	        //省份
	        String strProvince=app.myinfo.province;
	        //
	        //Log.e("!!!!!!!!!!!strProvince",strProvince);
	        //Log.e("!!!!!!!!!!!strCity",strCity);
	        int iSelectedStartProvinceIndex=GetIndexByProvinceName(strProvince);
	        app.SetConfig("selectedStartProvinceIndex",String.valueOf(0));//***iSelectedStartProvinceIndex
	        //Log.e("!!!!!!!!!!!selectedStartProvinceIndex",String.valueOf(iSelectedStartProvinceIndex));
	        //城市
	        int iSelectedStartCityIndex=GetIndexByCityName(iSelectedStartProvinceIndex,strCity);
	        //app.SetConfig("selectedStartCityIndex",String.valueOf(iSelectedStartCityIndex));//***//
	        //Log.e("!!!!!!!!!!!selectedStartCityIndex",String.valueOf(iSelectedStartCityIndex));
	        //到达城市
	        app.SetConfig("selectedStopProvinceIndex",String.valueOf(0));
	        //==========发布1界面相关参数====================================
	        app.SetConfig("add1startcity",strCity);//出发地
	        app.SetConfig("add1stopcity",getString(R.string.quanguo));//到达地(全国)
	        
	        app.SetConfig("add1startsign","1");//城市级别(出发地)
	        app.SetConfig("add1stopsign","0");
	        
	        app.SetConfig("add1selectedStartProvinceIndex",String.valueOf(iSelectedStartProvinceIndex));
	        app.SetConfig("add1selectedStartCityIndex",String.valueOf(iSelectedStartCityIndex));
	        //到达城市
	        app.SetConfig("add1selectedStopProvinceIndex",String.valueOf(0));
	        //===================================================================
	        
			login.this.startActivity(intent);
		}
	}
	//关闭对话框，并且提示String
	private void MyMessage(String string) {
		// TODO Auto-generated method stub
		//取消提示消息
		Message msg = new Message();// 
        msg.what = 4;  
        loginHander.sendMessage(msg);
		//取得提示
        mString=string;
        loginMsgHander.post(runnableUiString);   //更新界面UI(提示)
	}
	//关闭对话框，并且提示StringID
	private void MyMessage(int stringid) {
		// TODO Auto-generated method stub
		//取消提示消息
		Message msg = new Message();// 
        msg.what = 4;  
        loginHander.sendMessage(msg);
		//取得提示
        mStringID=stringid;
        loginMsgHander.post(runnableUiStringID);   //更新界面UI(提示)
	}
	//检测新版本
	protected boolean checkCurVer() {
		// TODO Auto-generated method stub
		boolean bNewVersion=false;
		String strInfo=DataSource.getInstance().checkCurVer();
		int result=strInfo.indexOf("Error");
		if(result>=0){
			//存在错误
			//取消提示消息
			//Message msg = new Message();// 
            //msg.what = 4;  
            //loginHander.sendMessage(msg);
			//取得提示
            //mString=strInfo;
            //loginMsgHander.post(runnableUiString);   //更新界面UI(提示)
			bNewVersion= false;
		}else
		{
			String strNewVersion,strUpdateUrl;
			String[] strInfoArray=strInfo.split("#"); 
			strNewVersion=strInfoArray[1];
			strUpdateUrl=strInfoArray[2];
			//比较版本号
			if(myFun.TestVersion(app.GetCurVersion(), strNewVersion)){
				//检测有新版本
				bNewVersion= true;
				mUpdateUrl=strUpdateUrl;
			}else
			{
				bNewVersion= false;
			}
		}
		
		return bNewVersion;
	}
	//下载新的程序，并安装
	private void downloadUpdateFile(String strUpdateUrl) {
		// TODO Auto-generated method stub
		String fileTmpPath="nmgclient/";
		String fileTmpName="crs.apk";
		HttpDownloader httpDownloader=new HttpDownloader();  
		//调用httpDownloader对象的重载方法download下载apk文件  
		int result=httpDownloader.download(strUpdateUrl,fileTmpPath,fileTmpName);
		if(result==0){
			//升级成功
			Message msg = new Message();// 
            msg.what = 6;  
            loginHander.sendMessage(msg);
			//打开下载的文件并安装
			String filepath=httpDownloader.getFilePath(fileTmpPath, fileTmpName);
			//login.this.finish();
			//取消提示消息
	        msg = new Message();// 必须这样写，不然会报错。因为   
	        msg.what = 4;  
	        loginHander.sendMessage(msg);
            //
			closeAllActivity();
			//安装 
			AutoInstall.setUrl(filepath);
			AutoInstall.install(login.this);  
		}else
		{
			//升级失败
			Message msg = new Message();// 
            msg.what = 7;  
            loginHander.sendMessage(msg);
            //
            //取消提示消息
            msg = new Message();// 必须这样写，不然会报错。因为   
            msg.what = 4;  
            loginHander.sendMessage(msg);
		}
		
	}
	//
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
	//
	Handler loginHander = new Handler() {  
	@Override  
	public void handleMessage(Message msg) {  
	switch (msg.what) {  
		case 1:// 开始下载标志   
			mypDialog.setMessage(getString(R.string.loginMsgW1));
			mypDialog.show();// 让ProgressDialog显示   
			break;  
		case 2://
			mypDialog.setMessage(getString(R.string.loginMsgW2));
			mypDialog.show();  
			break;
		case 3://
			mypDialog.setMessage(getString(R.string.loginMsgW3));
			mypDialog.show();  
			break; 
		case 4://
			//mypDialog.setMessage("");
			mypDialog.cancel();  
			break;
		case 5://
			mypDialog.setMessage(getString(R.string.loginMsgW4));
			mypDialog.show();  
			break; 
		case 6://升级成功
			mypDialog.setMessage(getString(R.string.loginMsgW6));
			mypDialog.show();  
			break;
		case 7://升级失败
			mypDialog.setMessage(getString(R.string.loginMsgW7));
			mypDialog.show();  
			break; 
		}  
		}  
	}; 
		 
	//存储相关参数
	private void SaveConfigValue() {
		// TODO Auto-generated method stub
		SharedPreferences cfg=getSharedPreferences(app.getConfigFileName(),MODE_PRIVATE);
		Editor editor=cfg.edit();//取得编辑器
		editor.putString("username", m_strUsername);
		editor.putString("password", m_strPassword);
		editor.putString("nopass", m_strNopass);
		editor.commit();
	}
	//
	private boolean GetItemValue() {
		// TODO Auto-generated method stub
		m_strUsername = mUsername.getText().toString();//用户名或手机
		m_strPassword=mPassword.getText().toString();//密码
		if(mNopass.isChecked())
		{
			m_strNopass="1";
		}else
		{
			m_strNopass="0";
		}
		
		//--------------判断检测------------------------------
		//用户名或手机号
		if(m_strUsername.equals(""))
		{
			mUsername.requestFocus();
			Msg(R.string.userChkTxt);
			return false;
		}
		//密码
		if(m_strPassword.equals(""))
		{
			mPassword.requestFocus();
			Msg(R.string.pwdChkTxt);
			return false;
		}
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
			Intent intent = new Intent(); 
		    intent.setAction("ExitApp"); 
		    this.sendBroadcast(intent); 
		    super.finish();
		}
}
