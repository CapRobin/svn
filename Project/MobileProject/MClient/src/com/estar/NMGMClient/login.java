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
		/* ����mylayout.xml Layout */ 
//	    setContentView(R.layout.login_x); 
//		application = (MyApplication) abApplication;
		setAbContentView(R.layout.login_x);
//		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout("��¼", isShowRightBut);
//		initView();
	    
	    
	    //ȫ�ֱ������
	  	app = (MySuperApplication) getApplication(); //������ǵ�Ӧ�ó���MySuperApplication
	  	loginMsgHander=new Handler();  //�����������̵߳�handler   
	  	myFun=new MyFun();
	    //�õ��������
	    //EditText
	    mUsername=(EditText)findViewById(R.id.username);
	    mPassword=(EditText)findViewById(R.id.password);
	    //Button
	    mloginBtn=(Button)findViewById(R.id.loginBtn);
	    mregBtn=(Button)findViewById(R.id.regBtn);
	    //CheckBox
	    mNopass=(CheckBox)findViewById(R.id.nopass);
	    //======================================================
	    //��ȡ�����ļ����Դ˽�������
	    ReadConfigData();
	    //======================================================
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
	    //���ע�ᰴť
	    mregBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��ʾע�ᴰ��
				Intent intent=new Intent();
				intent.setClass(login.this, reg.class);
				//login.this.startActivity(intent);
				/*newһ��Bundle���󣬲���Ҫ���ݵ����ݴ���*/ 
				Bundle bundle = new Bundle(); 
		        bundle.putString("username",""); 
		        bundle.putString("password",""); 
		        /*��Bundle����assign��Intent*/ 
		        intent.putExtras(bundle); 
				login.this.startActivityForResult(intent,0); 
			}
		});
		
//		//����ActivityView
//		mregBtn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(login.this,TestActivity.class));
//			}
//		});
		
	    //�����¼��ť
	    mloginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onLogin();
			}
		});
	    
	    
	    
	    
		// �Ҳఴť�ĵ���¼�_����
		if (isShowRightBut) {
			this.rightTitleBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					startActivity(new Intent(login.this,TestActivity.class));
				}
			});
		}
	    
	    
	}
	/* ��д onActivityResult()*/ 
	//��������ע�ᴰ�ڵķ���ֵ
	  @Override 
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
	    switch (resultCode) { 
	      case RESULT_OK: 
	        /* ȡ�����ݣ�����ʾ�ڻ����� */ 
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
	//��ȡ�����ļ���Ϣ
	private void ReadConfigData() {
		// TODO Auto-generated method stub
		SharedPreferences cfg=getSharedPreferences(app.getConfigFileName(),MODE_PRIVATE);
		String username=cfg.getString("username", "");
		String password=cfg.getString("password", "");
		String nopass=cfg.getString("nopass", "1");
		//��ֵ
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
	//��¼
	protected void onLogin() {
		// TODO Auto-generated method stub
		//ȡ�����ITEMֵ
		if(!GetItemValue())
		{
			return ;
		}
		//�洢��ز���
		SaveConfigValue();
		//�߳̽��е�¼
		LoginIn();
	}
	//���е�¼ �߳�
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
				//���ڼ��������ʾ
				Message msg = new Message();  
	            msg.what = 1;// ��ʼ���ر�־1   
	            loginHander.sendMessage(msg);  
	            //����������
	            String strAnswer=DataSource.getInstance().checkConnection();
	            //mString=strAnswer;
	            //loginMsgHander.post(runnableUiString);
	            //Log.e("!!!!!!!!!!!!!!Login_run", strAnswer);
	            if(strAnswer.equals("TestOK"))
	            {
	            	//�ɹ�����
	            }else
	            {
	            	//ȡ����ʾ��Ϣ
	            	msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
		            msg.what = 4;  
		            loginHander.sendMessage(msg);
		            //ȡ����ʾ
		            mStringID=R.string.netChkTxt;
		            loginMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
		            //����������ҳ��
	            	return;
	            }
	            //Log.e("!!!!!!!!!!!!!!Login_checkCurVer", "checkCurVer");
	            //���ڼ���°汾��ʾ
	            msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
	            msg.what = 2;  
	            loginHander.sendMessage(msg);
	            //����°汾
	            if(checkCurVer())
	            {
	            	//��������
					msg = new Message();// 
		            msg.what = 5;  
		            loginHander.sendMessage(msg);
		            //�����µĳ��򣬲���װ
		            downloadUpdateFile(mUpdateUrl);
		            //ȡ����ʾ��Ϣ
		            msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
		            msg.what = 4;  
		            loginHander.sendMessage(msg);
	            }else
	            {
			        //���ڵ�¼��ʾ
			        msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
			        msg.what = 3;  
			        loginHander.sendMessage(msg);
			        //Log.e("!!!!!!!!!!!!!!Login_checkUser", "checkUser");
			        //���ڵ�¼
			        CheckUser();
		            //ȡ����ʾ��Ϣ
		            msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
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
		String diskinfo=myFun.getMobileIMEI()+myFun.getMobileIESI();//IMEI��+IESI��
		String macaddr=myFun.getSimSerialNumber();//SIM������ 
		String post=myFun.getMobileDeviceDataString();//�ֻ�������ϣ���¼�Աȣ�
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
			if(strInfo.equals("NotGoInSign")){////��ͨ��Ա
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
			
			//����¼��Ϣת����ȫ�ֱ�����
			app.setMyinfo(strInfo);
			if(app.myinfo.lasttime<=15){
				String str;
				str=getString(R.string.loginMsgE7)+app.myinfo.lasttime+getString(R.string.loginMsgE71);
				MyMessage(str);
			}
			//���û�Ա��Ϊȫ�ֱ���
			app.setUsername(m_strUsername);
			
			//Log.e("!!!!!!!!!!!!!!Login_setUsername", m_strUsername);
			
			//��ʾ������
			Intent intent=new Intent();
			intent.setClass(login.this, MainActivity.class);
			/*newһ��Bundle���󣬲���Ҫ���ݵ����ݴ���*/ 
			Bundle bundle = new Bundle(); 
	        bundle.putString("city",app.myinfo.city);
	        bundle.putInt("userid", app.myinfo.userID);
	        /*��Bundle����assign��Intent*/ 
	        intent.putExtras(bundle); 
	        //=================================================================
	        
	      //һЩĬ�ϲ���������
	        //========��������ز���===========================================
	        //���������ļ�
	        String strCity=app.myinfo.city;
	        app.SetConfig("startcity",getString(R.string.quanguo));//***������	strCity
	        app.SetConfig("stopcity",getString(R.string.quanguo));//�����(ȫ��)
	        app.SetConfig("userid",String.valueOf(app.myinfo.userID));//��ԱID
	        
	        app.SetConfig("startsign","0");//***���м���(������) 1
	        app.SetConfig("stopsign","0");
	        //ʡ��
	        String strProvince=app.myinfo.province;
	        //
	        //Log.e("!!!!!!!!!!!strProvince",strProvince);
	        //Log.e("!!!!!!!!!!!strCity",strCity);
	        int iSelectedStartProvinceIndex=GetIndexByProvinceName(strProvince);
	        app.SetConfig("selectedStartProvinceIndex",String.valueOf(0));//***iSelectedStartProvinceIndex
	        //Log.e("!!!!!!!!!!!selectedStartProvinceIndex",String.valueOf(iSelectedStartProvinceIndex));
	        //����
	        int iSelectedStartCityIndex=GetIndexByCityName(iSelectedStartProvinceIndex,strCity);
	        //app.SetConfig("selectedStartCityIndex",String.valueOf(iSelectedStartCityIndex));//***//
	        //Log.e("!!!!!!!!!!!selectedStartCityIndex",String.valueOf(iSelectedStartCityIndex));
	        //�������
	        app.SetConfig("selectedStopProvinceIndex",String.valueOf(0));
	        //==========����1������ز���====================================
	        app.SetConfig("add1startcity",strCity);//������
	        app.SetConfig("add1stopcity",getString(R.string.quanguo));//�����(ȫ��)
	        
	        app.SetConfig("add1startsign","1");//���м���(������)
	        app.SetConfig("add1stopsign","0");
	        
	        app.SetConfig("add1selectedStartProvinceIndex",String.valueOf(iSelectedStartProvinceIndex));
	        app.SetConfig("add1selectedStartCityIndex",String.valueOf(iSelectedStartCityIndex));
	        //�������
	        app.SetConfig("add1selectedStopProvinceIndex",String.valueOf(0));
	        //===================================================================
	        
			login.this.startActivity(intent);
		}
	}
	//�رնԻ��򣬲�����ʾString
	private void MyMessage(String string) {
		// TODO Auto-generated method stub
		//ȡ����ʾ��Ϣ
		Message msg = new Message();// 
        msg.what = 4;  
        loginHander.sendMessage(msg);
		//ȡ����ʾ
        mString=string;
        loginMsgHander.post(runnableUiString);   //���½���UI(��ʾ)
	}
	//�رնԻ��򣬲�����ʾStringID
	private void MyMessage(int stringid) {
		// TODO Auto-generated method stub
		//ȡ����ʾ��Ϣ
		Message msg = new Message();// 
        msg.what = 4;  
        loginHander.sendMessage(msg);
		//ȡ����ʾ
        mStringID=stringid;
        loginMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
	}
	//����°汾
	protected boolean checkCurVer() {
		// TODO Auto-generated method stub
		boolean bNewVersion=false;
		String strInfo=DataSource.getInstance().checkCurVer();
		int result=strInfo.indexOf("Error");
		if(result>=0){
			//���ڴ���
			//ȡ����ʾ��Ϣ
			//Message msg = new Message();// 
            //msg.what = 4;  
            //loginHander.sendMessage(msg);
			//ȡ����ʾ
            //mString=strInfo;
            //loginMsgHander.post(runnableUiString);   //���½���UI(��ʾ)
			bNewVersion= false;
		}else
		{
			String strNewVersion,strUpdateUrl;
			String[] strInfoArray=strInfo.split("#"); 
			strNewVersion=strInfoArray[1];
			strUpdateUrl=strInfoArray[2];
			//�Ƚϰ汾��
			if(myFun.TestVersion(app.GetCurVersion(), strNewVersion)){
				//������°汾
				bNewVersion= true;
				mUpdateUrl=strUpdateUrl;
			}else
			{
				bNewVersion= false;
			}
		}
		
		return bNewVersion;
	}
	//�����µĳ��򣬲���װ
	private void downloadUpdateFile(String strUpdateUrl) {
		// TODO Auto-generated method stub
		String fileTmpPath="nmgclient/";
		String fileTmpName="crs.apk";
		HttpDownloader httpDownloader=new HttpDownloader();  
		//����httpDownloader��������ط���download����apk�ļ�  
		int result=httpDownloader.download(strUpdateUrl,fileTmpPath,fileTmpName);
		if(result==0){
			//�����ɹ�
			Message msg = new Message();// 
            msg.what = 6;  
            loginHander.sendMessage(msg);
			//�����ص��ļ�����װ
			String filepath=httpDownloader.getFilePath(fileTmpPath, fileTmpName);
			//login.this.finish();
			//ȡ����ʾ��Ϣ
	        msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
	        msg.what = 4;  
	        loginHander.sendMessage(msg);
            //
			closeAllActivity();
			//��װ 
			AutoInstall.setUrl(filepath);
			AutoInstall.install(login.this);  
		}else
		{
			//����ʧ��
			Message msg = new Message();// 
            msg.what = 7;  
            loginHander.sendMessage(msg);
            //
            //ȡ����ʾ��Ϣ
            msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
            msg.what = 4;  
            loginHander.sendMessage(msg);
		}
		
	}
	//
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
	//
	Handler loginHander = new Handler() {  
	@Override  
	public void handleMessage(Message msg) {  
	switch (msg.what) {  
		case 1:// ��ʼ���ر�־   
			mypDialog.setMessage(getString(R.string.loginMsgW1));
			mypDialog.show();// ��ProgressDialog��ʾ   
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
		case 6://�����ɹ�
			mypDialog.setMessage(getString(R.string.loginMsgW6));
			mypDialog.show();  
			break;
		case 7://����ʧ��
			mypDialog.setMessage(getString(R.string.loginMsgW7));
			mypDialog.show();  
			break; 
		}  
		}  
	}; 
		 
	//�洢��ز���
	private void SaveConfigValue() {
		// TODO Auto-generated method stub
		SharedPreferences cfg=getSharedPreferences(app.getConfigFileName(),MODE_PRIVATE);
		Editor editor=cfg.edit();//ȡ�ñ༭��
		editor.putString("username", m_strUsername);
		editor.putString("password", m_strPassword);
		editor.putString("nopass", m_strNopass);
		editor.commit();
	}
	//
	private boolean GetItemValue() {
		// TODO Auto-generated method stub
		m_strUsername = mUsername.getText().toString();//�û������ֻ�
		m_strPassword=mPassword.getText().toString();//����
		if(mNopass.isChecked())
		{
			m_strNopass="1";
		}else
		{
			m_strNopass="0";
		}
		
		//--------------�жϼ��------------------------------
		//�û������ֻ���
		if(m_strUsername.equals(""))
		{
			mUsername.requestFocus();
			Msg(R.string.userChkTxt);
			return false;
		}
		//����
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
			Intent intent = new Intent(); 
		    intent.setAction("ExitApp"); 
		    this.sendBroadcast(intent); 
		    super.finish();
		}
}
