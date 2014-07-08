package com.estar.comm;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

public class MySuperApplication extends Application{


	private static final String m_ServiceUrl = "http://192.168.0.58/estar/mclient/service.asp";
	private static final String mOldVersion="1.0.0.1";
	private static final String configFileName="nmgclientcfg";
	
	public class USERINFOSTRUCT{
		public int userID;		//��ԱID
		public String name1;	//��ʵ����
		public String sex;		//�Ա�
		public String company;	//��˾����
		public String tel;		//�绰����
		public String sj;		//�ֻ�
		public String company_proxy;//����������
		public String tel_proxy;	//�����̵绰
		public String sj1;		//QQ
		public String tel1;		//�绰��
		public String town;		//��������
		public String city;		//��������
		public String province;	//����ʡ��
		public String url;		//��˾��ַ
		public String email;	//E-mail
		public String address;	//��ϵ��ַ
		public String post;		//��������
		public int usertypes;		//��Ա����(0:������Ա;1:������Ա;2:ú̿��Ա)
		public int wlusertypes;		//������Ա����(0:��;1:��;)
		public int lasttime;		//��������
		public int addfunc;			//�Ƿ��з�������(0:��;1:��)
		public int handwrite;		//�Ƿ������д����(0:��;1:��)
		public int AddTimes;		//����ʱ����(0:��;1:��)
		public int InfoNum;			//������Ϣ����
		public int CharNum;			//������Ϣ����
		public int changeinfo;		//�Ƿ�ɸ���ע����Ϣ(0:��;1:��)
		public int changepwd;		//�Ƿ�ɸ�������(0:��;1:��)
		public String backprovince;	//�س�ʡ
		public String backcity;		//�س���

		USERINFOSTRUCT(){}
	}
	public USERINFOSTRUCT myinfo;
	public String m_strTableType;
	private String mServiceUrl;//��������·��
	private String mUsername;//�û���
	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		setServiceUrl(m_ServiceUrl); //��ʼ��ȫ�ֱ���
		//
		myinfo=new USERINFOSTRUCT();
		//
		m_strTableType="";
		/**��������ر���Ϣ*/
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}

	//ȡ�÷�������·��
	public String GetServiceUrl() {
		return mServiceUrl;
	}
	//���÷�������·��
	public void setServiceUrl(String strValue) {
		this.mServiceUrl=strValue;
	}
	//
	public void Msg(String strValue)
	{
		Toast.makeText(getApplicationContext(), strValue,Toast.LENGTH_SHORT).show();
	}
	//�õ��汾��
	public String GetCurVersion(){
		return mOldVersion;
	}

	public USERINFOSTRUCT getMyinfo() {
		return myinfo;
	}

	/*
	 * "@@@@@"&CStr(userID)&"@@@@@"&rs("name1")&"@@@@@"&rs("sex")&"@@@@@"&rs("company")&"@@@@@"&rs("tel")&"@@@@@"&rs("sj")&"@@@@@"&
	 * CStr(lasttime)&"@@@@@"&CStr(addfunc)&"@@@@@"&CStr(handwrite)&"@@@@@"&company_proxy&"@@@@@"&tel_proxy&"@@@@@"&rs("sj1")&"@@@@@"&
	 * rs("tel1")&"@@@@@"&rs("city")&"@@@@@"&rs("province")&"@@@@@"&rs("url")&"@@@@@"&rs("email")&"@@@@@"&rs("address")&"@@@@@"&rs("post")
	 * &"@@@@@"&CStr(AddTimes)&"@@@@@"&CStr(InfoNum)&"@@@@@"&CStr(CharNum)&"@@@@@"&CStr(rs("changeinfo"))&"@@@@@"&CStr(rs("changepwd"))
	 * &"@@@@@"&rs("backprovince")&"@@@@@"&rs("backcity")&"@@@@@"
	 */
	public void setMyinfo(String strInfo) {
		String[] strInfoArray=strInfo.split("@@@@@"); 
		//Log.e("!!!!!!!!!!!!!!App", String.valueOf(strInfoArray.length));
		int i = 1;
		//Log.e("!!!!!!!!!!!!!!App_i++", String.valueOf(i++));
		myinfo.userID = Integer.parseInt(strInfoArray[i++]);
		myinfo.name1 = strInfoArray[i++];
		myinfo.usertypes = Integer.parseInt(strInfoArray[i++]);//0:������Ա;1:������Ա;2:ú̿��Ա
		myinfo.wlusertypes = Integer.parseInt(strInfoArray[i++]);//0:��;1:��
		myinfo.sex=strInfoArray[i++];
		myinfo.company=strInfoArray[i++];
		myinfo.tel=strInfoArray[i++];
		myinfo.sj=strInfoArray[i++];
		myinfo.lasttime=Integer.parseInt(strInfoArray[i++]);
		myinfo.addfunc=Integer.parseInt(strInfoArray[i++]);
		myinfo.handwrite=Integer.parseInt(strInfoArray[i++]);
		myinfo.company_proxy=strInfoArray[i++];
		myinfo.tel_proxy=strInfoArray[i++];
		myinfo.sj1=strInfoArray[i++];
		myinfo.tel1=strInfoArray[i++];
		myinfo.city=strInfoArray[i++];
		myinfo.province=strInfoArray[i++];
		myinfo.url=strInfoArray[i++];
		myinfo.email=strInfoArray[i++];
		myinfo.address=strInfoArray[i++];
		myinfo.post=strInfoArray[i++];
		myinfo.AddTimes=Integer.parseInt(strInfoArray[i++]);
		myinfo.InfoNum=Integer.parseInt(strInfoArray[i++]);
		myinfo.CharNum=Integer.parseInt(strInfoArray[i++]);
		myinfo.changeinfo=Integer.parseInt(strInfoArray[i++]);
		myinfo.changepwd=Integer.parseInt(strInfoArray[i++]);
		//myinfo.backprovince=strInfoArray[i++];
		//myinfo.backcity=strInfoArray[i++];
        
		//Log.e("!!!!!!!!!!!!!!App", "setMyinfo end");
		
		/*if(strInfoArray.length>0){
			if(strInfoArray.length>=1){
				myinfo.userID=Integer.parseInt(strInfoArray[1]);
			}
			if(strInfoArray.length>=2){
				myinfo.name1=strInfoArray[2];
			}
			if(strInfoArray.length>=3){
				myinfo.sex=strInfoArray[3];
			}
			if(strInfoArray.length>=4){
				myinfo.company=strInfoArray[4];
			}
			if(strInfoArray.length>=5){
				myinfo.tel=strInfoArray[5];
			}
			if(strInfoArray.length>=6){
				myinfo.sj=strInfoArray[6];
			}
			if(strInfoArray.length>=7){
				myinfo.lasttime=Integer.parseInt(strInfoArray[7]);
			}
			if(strInfoArray.length>=8){
				myinfo.addfunc=Integer.parseInt(strInfoArray[8]);
			}
			if(strInfoArray.length>=9){
				myinfo.handwrite=Integer.parseInt(strInfoArray[9]);
			}
			if(strInfoArray.length>=10){
				myinfo.company_proxy=strInfoArray[10];
			}
			if(strInfoArray.length>=11){
				myinfo.tel_proxy=strInfoArray[11];
			}
			if(strInfoArray.length>=12){
				myinfo.sj1=strInfoArray[12];
			}
			if(strInfoArray.length>=13){
				myinfo.tel1=strInfoArray[13];
			}
			if(strInfoArray.length>=14){
				myinfo.city=strInfoArray[14];
			}
			if(strInfoArray.length>=15){
				myinfo.province=strInfoArray[15];
			}
			if(strInfoArray.length>=16){
				myinfo.url=strInfoArray[16];
			}
			if(strInfoArray.length>=17){
				myinfo.email=strInfoArray[17];
			}
			if(strInfoArray.length>=18){
				myinfo.address=strInfoArray[18];
			}
			if(strInfoArray.length>=19){
				myinfo.post=strInfoArray[19];
			}
			if(strInfoArray.length>=20){
				myinfo.AddTimes=Integer.parseInt(strInfoArray[20]);
			}
			if(strInfoArray.length>=21){
				myinfo.InfoNum=Integer.parseInt(strInfoArray[21]);
			}
			if(strInfoArray.length>=22){
				myinfo.CharNum=Integer.parseInt(strInfoArray[22]);
			}
			if(strInfoArray.length>=23){
				myinfo.changeinfo=Integer.parseInt(strInfoArray[23]);
			}
			if(strInfoArray.length>=24){
				myinfo.changepwd=Integer.parseInt(strInfoArray[24]);
			}
			if(strInfoArray.length>=25){
				myinfo.backprovince=strInfoArray[25];
			}
			if(strInfoArray.length>=26){
				myinfo.backcity=strInfoArray[26];
			}
		}*/
	}


	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String mUsername) {
		this.mUsername = mUsername;
	}
	//
	public String getConfigFileName(){
		return configFileName;
	}
	
	//
	public void SetConfig(String items, String strValue) {
		// TODO Auto-generated method stub
		SharedPreferences cfg=getSharedPreferences(getConfigFileName(),MODE_PRIVATE);
		Editor editor=cfg.edit();//ȡ�ñ༭��
		editor.putString(items, strValue);
		editor.commit();
	}
	//
	public String GetConfigValue(String items){
		SharedPreferences cfg=getSharedPreferences(getConfigFileName(),MODE_PRIVATE);
		String strValue=cfg.getString(items, "");
		
		return strValue;
	}
	
}
