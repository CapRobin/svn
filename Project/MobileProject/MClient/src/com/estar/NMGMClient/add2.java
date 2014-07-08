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
		///* ����mylayout.xml Layout */ 
		setContentView(R.layout.add2); 
		//Log.e("-------------------add1","1");
		//����ȫ�ֱ������
		app = (MySuperApplication) getApplication(); //������ǵ�Ӧ�ó���MySuperApplication
		addMsgHander=new Handler();  //�����������̵߳�handler  
		//---------������ʾ��--------------------------------
		//Log.e("-------------------add1","2");
	    mypDialog = new ProgressDialog(this);// ʵ����   
		// ���ý�������񣬷��Ϊ���ν�������STYLE_SPINNERΪԲ��   
		//mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
		//mypDialog.setTitle("��½");// ����ProgressDialog ����   
		//mypDialog.setMessage("������֤���Ժ�...");// ����ProgressDialog ��ʾ��Ϣ   
		mypDialog.setIndeterminate(false);// ����ProgressDialog   
		mypDialog.setCancelable(true);// �Ƿ���԰��˻ذ���ȡ��   
		//mypDialog.setMax(100);// �������ֵ
		//-----------------------------------------------------
		//Log.e("-------------------add1","3");
		//��Ϣ��
		infoContent=(EditText)findViewById(R.id.infoContent);
		//����
		Button backBtn=(Button)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��ʾ������
				//Intent intent = new Intent();
                //intent.setClass(add1.this, MainActivity.class);//Ĭ��add1.class
                //add1.this.startActivity(intent);
				add2.this.finish();
			}
		});
		//��Ϣ����ѡ��
		mTypeBtn=(Button)findViewById(R.id.typeBtn);
		
		//����2(������Ŀ)
		Button add2Btn=(Button)findViewById(R.id.add2Btn);
		add2Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��ʾ����2����
			}
		});
		//
		mstartcity=(Button)findViewById(R.id.startcity);
		mstopcity=(Button)findViewById(R.id.stopcity);
		
		m_strStartCity=app.GetConfigValue("add1startcity");
		m_strStopCity=app.GetConfigValue("add1stopcity");
		//���ð�ť��ʾֵ
		mstartcity.setText(m_strStartCity);
		mstopcity.setText(m_strStopCity);
		//ѡ����
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
		//ѡ��ʡ����
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
		//��������ѡ��
		
		mstartcity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ʾ�Ի���
				if(m_iStartSign==0)//ʡ��
				{
					ShowProvinceDlg(mstartcity,0);
				}else if(m_iStartSign==1)//����
				{
					ShowCityDlg(mstartcity,0);
				}else if(m_iStartSign==2)//����
				{
					ShowTownDlg(mstartcity,0);
				}
			}
		});
		//�������ѡ��
		
		mstopcity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(m_iStopSign==0)//ʡ��
				{
					ShowProvinceDlg(mstopcity,1);
				}else if(m_iStopSign==1)//����
				{
					ShowCityDlg(mstopcity,1);
				}else if(m_iStopSign==2)//����
				{
					ShowTownDlg(mstopcity,1);
				}
			}
		});
		//���
		Button clearBtn=(Button)findViewById(R.id.clearBtn);
		clearBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				infoContent.setText("");
			}
		});
		//�˸�
		Button backspaceBtn=(Button)findViewById(R.id.backspaceBtn);
		backspaceBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BackSpace();
			}
		});
		//������Ϣ
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
					app.SetConfig("add1selectedStartProvinceIndex",String.valueOf(selectedProvinceIndex));
				}else if(iSign==1){//�������
					selectedStopProvinceIndex=selectedProvinceIndex;
					app.SetConfig("add1selectedStopProvinceIndex",String.valueOf(selectedProvinceIndex));
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
	    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	//����ѡ�����
	        	if(iSign==0){//��������
	        		//�����ز�����ѡ��ʡ��
	        		/*if(selectedProvinceIndex>0){
	        			mStringID=R.string.startcitynoprovincetxt;
	        			addMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
		        		return;
	        		}*/
	        		m_iStartSign=0;//ʡ�ݲ��
	        		app.SetConfig("add1startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//�������
					m_iStopSign=0;//ʡ�ݲ��
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
		if(iSign==0){//��������
			selectedProvinceIndex=selectedStartProvinceIndex;
		}else if(iSign==1){//�������
			selectedProvinceIndex=selectedStopProvinceIndex;
		}
		String strProvince=getResources().getStringArray(R.array.province_item)[selectedProvinceIndex];
		cityBtn.setText(strProvince);
		if(iSign==0){//��������
			m_strStartCity=strProvince;
			app.SetConfig("add1startcity",strProvince);
		}else if(iSign==1){//�������
			m_strStopCity=strProvince;
			app.SetConfig("add1stopcity",strProvince);
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
					app.SetConfig("add1selectedStartCityIndex",String.valueOf(selectedCityIndex));
				}else if(iSign==1){//�������
					selectedStopCityIndex=selectedCityIndex;
					app.SetConfig("add1selectedStopCityIndex",String.valueOf(selectedCityIndex));
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
	        	//
	        	ShowTownDlg(cityBtn,iSign);
	        /*	if(iSign!=0)//������
	        	{
		        	//��ʾ��ѡ��Ի���
		        	ShowTownDlg(cityBtn,iSign);
	        	}else//iSign==0
	        	{
	        		m_iStartSign=1;//���в��
	        		app.SetConfig("add1startsign",String.valueOf(m_iStartSign));
	        		//�����ز�����ѡ������
	        		mStringID=R.string.startcitynotowntxt;
	        		addMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
	        	}*/
	        }
	    }).setNegativeButton(getString(R.string.xuanzhe), new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	         // TODO Auto-generated method stub
	        	showCity(cityBtn,iSign);
	        	//����ѡ�����
	        	if(iSign==0){//��������
	        		m_iStartSign=1;//���в��
	        		app.SetConfig("add1startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//�������
					m_iStopSign=1;//���в��
					app.SetConfig("add1stopsign",String.valueOf(m_iStopSign));
				}
	        }
	       }).create();

		alertDialog.show();
	}
	//
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
			app.SetConfig("add1startcity",strCity);
		}else if(iSign==1){//�������
			m_strStopCity=strCity;
			app.SetConfig("add1stopcity",strCity);
		}
	}
	//
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
					app.SetConfig("add1selectedStartTownIndex",String.valueOf(selectedTownIndex));
				}else if(iSign==1){//�������
					selectedStopTownIndex=selectedTownIndex;
					app.SetConfig("add1selectedStopTownIndex",String.valueOf(selectedTownIndex));
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
	        		app.SetConfig("add1startsign",String.valueOf(m_iStartSign));
				}else if(iSign==1){//�������
					m_iStopSign=2;//�������
					app.SetConfig("add1stopsign",String.valueOf(m_iStopSign));
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
			app.SetConfig("add1startcity",strTown);
		}else if(iSign==1){//�������
			m_strStopCity=strTown;
			app.SetConfig("add1stopcity",strTown);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//������Ϣ
	protected void addInfo() {
		// TODO Auto-generated method stub
		//�ж��Ƿ��з���Ȩ��
		int addfunc=app.myinfo.addfunc;//0;//app.myinfo.addfunc
		if(addfunc==0){
			//�Բ�������ʱû��Ȩ��ʹ�ø÷�������
			MyMessage(R.string.addinfoErr1Txt);
			return;
		}
		String strOldValue=infoContent.getText().toString();
		if(strOldValue.equals(""))
		{
			//��Ϣ���ݲ���Ϊ��
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
        //������Ϣ
        sendInfo();
	}
	//������Ϣ
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
		//��Ϣ����
		/*String strType=mTypeBtn.getText().toString();
		if(strType.equals(getString(R.string.typeInfo1Txt)))//��Դ��Ϣ
		{
			msg_type="0";
		}else//��Դ��Ϣ 
		{
			msg_type="1";
		}*/
		//��Ϣ����
		String strStartCity=mstartcity.getText().toString();
		if(strStartCity.equals(getString(R.string.quanguo))){//���Ϊȫ������Ϊ��
			strStartCity="";
		}
		msg_startcity=strStartCity;
		String strStopCity=mstopcity.getText().toString();
		if(strStopCity.equals(getString(R.string.quanguo))){//���Ϊȫ������Ϊ��
			strStopCity="";
		}
		String strContent=infoContent.getText().toString();
		if(!strStartCity.equals("") && !strStopCity.equals(""))
		{
			msg_content=strStartCity+"��"+strStopCity+","+strContent;
		}else
		{
			msg_content=strContent;
		}
		//��ϵ��ʽ
		msg_tel=app.myinfo.tel+" "+app.myinfo.sj+" "+app.myinfo.tel1+" "+app.myinfo.company+" "+app.myinfo.name1;
		//�ύ����
		String InfoSign="1";//�ֲ�ú̿Ϊ1;������ϢΪ0
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
		        //��շ�����
		        //infoContent.setText("");
		        msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
	            msg.what = 5;  
	            addHander.sendMessage(msg);
		        
		        //Log.e("!!!!!!!!!!","set infoContent null");
		        //ȡ����ʾ��
		        msg = new Message();// ��������д����Ȼ�ᱨ����Ϊ   
	            msg.what = 4;  
	            addHander.sendMessage(msg);
			}
		}
	}
	//��ʾ��Ϣ
	Handler addHander = new Handler() {  
		@Override  
		public void handleMessage(Message msg) {  
		switch (msg.what) {  
			case 1:// 
				mypDialog.setMessage(getString(R.string.addinfoMsg1));
				mypDialog.show();// ��ProgressDialog��ʾ   
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
		//�˸��
		protected void BackSpace() {
			// TODO Auto-generated method stub
			//ֱ�� ɾ����Ϣ�����һ���ַ�
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
			
			//���ù��λ�� 
			/*CharSequence text = infoContent.getText();
			//Debug.asserts(text instanceof Spannable);
			if (text instanceof Spannable) {
				Spannable spanText = (Spannable)text;
				Selection.setSelection(spanText, text.length());
			}*/

		}
		//�رնԻ��򣬲�����ʾString	
		private void MyMessage(String string) {
			// TODO Auto-generated method stub
			//ȡ����ʾ��Ϣ
			Message msg = new Message();// 
	        msg.what = 4;  
	        addHander.sendMessage(msg);
			//ȡ����ʾ
	        mString=string;
	        addMsgHander.post(runnableUiString);   //���½���UI(��ʾ)
		}
		//�رնԻ��򣬲�����ʾStringID
		private void MyMessage(int stringid) {
			// TODO Auto-generated method stub
			//ȡ����ʾ��Ϣ
			Message msg = new Message();// 
	        msg.what = 4;  
	        addHander.sendMessage(msg);
			//ȡ����ʾ
	        mStringID=stringid;
	        addMsgHander.post(runnableUiStringID);   //���½���UI(��ʾ)
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
