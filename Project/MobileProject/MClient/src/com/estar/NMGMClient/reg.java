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
	//�������
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
		
		//ȫ�ֱ������
		app = (MySuperApplication) getApplication(); //������ǵ�Ӧ�ó���MySuperApplication
		//ȡ�����ֵ��ע����ɺ󣬽��û������뷵�ظ���¼����ʾ
		intents=this.getIntent(); 
	    bunde = intents.getExtras();
	    //
	    myFun=new MyFun();
	    regMsgHander=new Handler();  //�����������̵߳�handler   
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

		//��� ��ͬ�ⰴť
		mNoAgreeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reg.this.finish();
			}
		});
		//����Ķ��������ť
		mServiceBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��ʾ���������
				//ʹ��Ĭ�����������ҳ 
				/*Intent intent = new Intent();        
				intent.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse(app.GetServiceUrl()); //����ȫ�ֱ���ȡ�÷�������·����getString(R.string.serviceUrl)
				intent.setData(content_url);  
				startActivity(intent);*/
				//�ڴ�������ʾ��ҳ
				Intent intent=new Intent();
				intent.setClass(reg.this, serviceWnd.class);
				reg.this.startActivity(intent);
			}
		});
		//���ע�ᰴť
		mAgreeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onAgreeReg();
			}
		});
		//���ʡ��
		mProvince.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ʾ�Ի���
                ShowProvinceDlg();
			}
		});
		//�������
		mCity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ʾ�Ի���
                ShowCityDlg();
			}
		});
		//�������
		mTown.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ʾ�Ի���
                ShowTownDlg();
			}
		});
		//�����Ա����
		mUsertypeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ʾ�Ի���
                ShowMemTypeDlg();
			}
		});
	}
		
		//��ʾ��Ա���͵ĶԻ���˵� 
		protected void ShowMemTypeDlg() {
			// TODO Auto-generated method stub
			//��ʾ�˵�
			AlertDialog alertDialog = new AlertDialog.Builder(this)
			//.setTitle("ѡ��")
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
		
		//��ʾʡ��
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
		        	//��ʾʡѡ��
		        	showProvince();
		        	//��ʾ��ѡ��Ի��� 
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
		//��ʾ����
		protected void ShowCityDlg() {
			// TODO Auto-generated method stub
			//ȡ��ʡ�ݣ����Ϊȫ�����򲻵�����
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
		         
		        	//��ʾ��ѡ��
		        	showCity();
		        	//��ʾʡѡ��Ի���
		        	ShowProvinceDlg();
		        }
		    })/*.setNeutralButton(getString(R.string.xiaji), new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	//��ʾ��ѡ��
		        	showCity();
		        	//��ʾ��ѡ��Ի���
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
	    	//����ѡ����е����� mTelnum
	    	PCT pct=new PCT();
	    	String strArea=pct.GetCityArea(m_strCity);
	    	if(!strArea.equals("")){
	    		mTelnum.setText(strArea);
	    		SetNoReadOnly(false);//����Ϊֻ��
	    	}else
	    	{
	    		SetNoReadOnly(true);
	    	}
	    	//==============================================
		}
		//��������ֻ��״̬
		private void SetNoReadOnly(boolean b) {
			// TODO Auto-generated method stub
			mTelnum.setCursorVisible(b);      
			mTelnum.setFocusable(b);         
			mTelnum.setFocusableInTouchMode(b);    
		}
		//��ʾ����
		protected void ShowTownDlg() {
			// TODO Auto-generated method stub
			//ȡ�ó��У����Ϊ���У��򲻵�����
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
		         
		        	//��ʾ��ѡ��
		        	showTown();
		        	//��ʾ��ѡ��Ի���
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
		
	//ע��
	protected void onAgreeReg() {
		// TODO Auto-generated method stub
		//ȡ�ø���ֵ
		if(!GetItemValue())
		{
			return;
		}
		//����ע�� �߳�
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
	            msg.what = 1;// ���ڼ������
	            regHander.sendMessage(msg);  
	            //����������
	            String strAnswer=DataSource.getInstance().checkConnection();
	            //mString=strAnswer;
	            //regMsgHander.post(runnableUiString);
	            if(strAnswer.equals("TestOK"))
	            {
	            	//�ɹ�����
	            }else
	            {
	            	//ȡ����ʾ��Ϣ
	            	msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
		            msg.what = 4;  
		            regHander.sendMessage(msg);
		            //ȡ����ʾ
		            mStringID=R.string.netChkTxt;
		            regMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
		            //����������ҳ��
		            
	            	return;
	            }
	            //�����ύ����
				msg = new Message();  
	            msg.what = 2;// �����ύ����
	            regHander.sendMessage(msg);  
	            //����ע��
	            if(Reg()){
	            	//ȡ����ʾ��Ϣ
	            	msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
		            msg.what = 4;  
		            regHander.sendMessage(msg);
	            	//�رձ��Ի���
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
		case 1:// ��ʼ���ر�־   
			mypDialog.setMessage(getString(R.string.loginMsgW1));
			mypDialog.show();// ��ProgressDialog��ʾ   
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
//		String diskinfo=myFun.getMobileIMEI()+myFun.getMobileIESI();//IMEI��+IESI��
		String diskinfo=Imei+myFun.getMobileIESI();//IMEI��+IESI��
		String macaddr=myFun.getSimSerialNumber();//SIM������ 
		String strDeviceInfo=myFun.getMobileDeviceDataString();//�ֻ�������ϣ���ע��
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
			if(strInfo1.equals("HasReged")){////��ͨ��Ա
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
	//�رմ˶Ի���
	protected void CloseWnd(){
		//����Ҫ���ص��������ӵ�Bundle��
	    // Bundle bundle = new Bundle(); 
	    bunde.putString("username",m_strSj); 
	    bunde.putString("password",m_strPwd); 
	    /*��Bundle����assign��Intent*/ 
	    intents.putExtras(bunde);  
	    /* �ش�result����һ��activity */ 
	    reg.this.setResult(RESULT_OK, intents); 
	    /* �ر�activity */ 
	    reg.this.finish(); 
	}
	//ȡ�ø���ֵ
	private boolean GetItemValue() {
		// TODO Auto-generated method stub
		m_strSj = mSj.getText().toString();//�ֻ�
		m_strPwd=mPwd.getText().toString();//����
		m_strPwd2=mPwd2.getText().toString();//����2
		m_strName1 = mName1.getText().toString(); //��ʵ����
		m_strCompany = mCompany.getText().toString(); //��˾����
		m_strPostCard=mPostCard.getText().toString();//���֤����
		m_strTelnum = mTelnum.getText().toString(); //����
		m_strTel = mTel.getText().toString();//�绰����
		m_strQQ = mQQ.getText().toString(); //QQ��
		
		m_strProvince = mProvince.getText().toString();//ʡ�� 
		m_strCity = mCity.getText().toString(); //����
		m_strTown = mTown.getText().toString(); //����
		m_strUserType=mUsertypeBtn.getText().toString();//��Ա����
		//ȡ���Ա�
		if(mMan.isChecked()){
			m_strSex=getString(R.string.manTxt);
		}
		if(mWoman.isChecked()){
			m_strSex=getString(R.string.womanTxt);
		}
		//--------------�жϼ��------------------------------
		//�ֻ���
		if(m_strSj.equals(""))
		{
			mSj.requestFocus();
			Msg(R.string.sjChkTxt);
			return false;
		}
		//����
		if(m_strPwd.equals(""))
		{
			mPwd.requestFocus();
			Msg(R.string.pwdregChkTxt);
			return false;
		}
		//ȷ������
		if(!m_strPwd.equals(m_strPwd2))
		{
			mPwd.requestFocus();
			Msg(R.string.pwdreg2ChkTxt);
			return false;
		}
		//��ʵ����
		if(m_strName1.equals(""))
		{
			mName1.requestFocus();
			Msg(R.string.name1ChkTxt);
			return false;
		}
		//���֤����
		if(m_strPostCard.equals(""))
		{
			mPostCard.requestFocus();
			Msg(R.string.postcardChkTxt);
			return false;
		}
		//��֤���֤�Ƿ���ȷ�Ϸ�
		if(!myFun.sfzCheck(m_strPostCard))
		{
			mPostCard.requestFocus();
			Msg(R.string.postcardErrChkTxt);
			return false;
		}
		//ʡ��
		if(m_strProvince.equals("") || m_strProvince.equals(getString(R.string.provinceTxt)) || m_strProvince.equals(getString(R.string.quanguo)))
		{
			mProvince.requestFocus();
			Msg(R.string.proChkTxt);
			return false;
		}
		//����
		if(m_strCity.equals("") || m_strCity.equals(getString(R.string.cityTxt)))
		{
			mCity.requestFocus();
			Msg(R.string.cityChkTxt);
			return false;
		}
		//����
		/*if(m_strTown.equals("") || m_strTown.equals(getString(R.string.townTxt)))
		{
			mTown.requestFocus();
			Msg(R.string.townChkTxt);
			return false;
		}*/
		//����
		if(m_strTelnum.equals(""))
		{
			mTelnum.requestFocus();
			Msg(R.string.telnumChkTxt);
			return false;
		}
		//�绰����
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

	//�رնԻ��򣬲�����ʾString
		private void MyMessage(String string) {
			// TODO Auto-generated method stub
			//ȡ����ʾ��Ϣ
			Message msg = new Message();// 
	        msg.what = 4;  
	        regHander.sendMessage(msg);
			//ȡ����ʾ
	        mString=string;
	        regMsgHander.post(runnableUiString);   //���½���UI(��ʾ)
		}
		//�رնԻ��򣬲�����ʾStringID
		private void MyMessage(int stringid) {
			// TODO Auto-generated method stub
			//ȡ����ʾ��Ϣ
			Message msg = new Message();// 
	        msg.what = 4;  
	        regHander.sendMessage(msg);
			//ȡ����ʾ
	        mStringID=stringid;
	        regMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
		}
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
		 
}
