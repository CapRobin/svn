package com.haohuotong.activity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.ab.util.AbImageUtil;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.entity.DriverCarInfo;
import com.haohuotong.entity.DriverUserInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.entity.UserInfo;
import com.haohuotong.global.MyApplication;
import com.haohuotong.web.UserInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：PersonalDriverInfoActivity.java
 * @Describe：司机信息
 * @Author yufarong_yfr5734@163.com
 * @Date：2013-12-23 下午5:32:36
 * @Version v1.0
 */
public class PersonalDriverInfoActivity extends AbActivity implements OnClickListener {
	private MyApplication application = null;
	private EditText cartypeEdit, carLengthEdit, carHightEdit, volumeEdit, maxLoadEdit, freightStateEdit;
	private EditText engineNumEdit, chejiaNumEdit, operationNumEdit, insuranceNumEdit, guakaodanwEdit;
	private EditText userNameEdit, personalMobileEdit, suicheMobileEdit, identityCardEdit, jinjiMobileEdit;
	private EditText driverSexEdit, familyAddressEdit, qiwangliuxiangEdit, jiashizhizhaoEdit, carNumEdit;
	private ImageView docImage = null;
	private Button updateSubmit = null;
	private List<Parameter> driverUserParameterList = null;
	private List<Parameter> driverCarParameterList = null;
	private DriverUserInfo driverUserInfo = null;
	private DriverCarInfo driverCarInfo = null;
	private boolean isUpDriverdate = false;
	private boolean isUCarpdate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		application = (MyApplication) abApplication;
		setAbContentView(R.layout.user_driver_info);

		initTitleRightLayout();
		initView();
	}

	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initTitleRightLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText("个人信息");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
	}

	/**
	 * 
	 * 描述：初始化View
	 * 
	 * @throws
	 * @date：2013-11-13 上午10:21:24
	 * @version v1.0
	 */
	private void initView() {
		docImage = (ImageView) findViewById(R.id.docImage);
		updateSubmit = (Button) findViewById(R.id.updateSubmit);

		userNameEdit = (EditText) findViewById(R.id.userNameEdit);
		personalMobileEdit = (EditText) findViewById(R.id.personalMobileEdit);
		suicheMobileEdit = (EditText) findViewById(R.id.suicheMobileEdit);
		identityCardEdit = (EditText) findViewById(R.id.identityCardEdit);
		jinjiMobileEdit = (EditText) findViewById(R.id.jinjiMobileEdit);
		driverSexEdit = (EditText) findViewById(R.id.driverSexEdit);
		familyAddressEdit = (EditText) findViewById(R.id.familyAddressEdit);
		qiwangliuxiangEdit = (EditText) findViewById(R.id.qiwangliuxiangEdit);//期望了流向
		jiashizhizhaoEdit = (EditText) findViewById(R.id.jiashizhizhaoEdit);
		carNumEdit = (EditText) findViewById(R.id.carNumEdit);
		cartypeEdit = (EditText) findViewById(R.id.cartypeEdit);
		carLengthEdit = (EditText) findViewById(R.id.carLengthEdit);
		carHightEdit = (EditText) findViewById(R.id.carHightEdit);
		volumeEdit = (EditText) findViewById(R.id.volumeEdit);
		maxLoadEdit = (EditText) findViewById(R.id.maxLoadEdit);
		freightStateEdit = (EditText) findViewById(R.id.freightStateEdit);
		engineNumEdit = (EditText) findViewById(R.id.engineNumEdit);
		chejiaNumEdit = (EditText) findViewById(R.id.chejiaNumEdit);
		operationNumEdit = (EditText) findViewById(R.id.operationNumEdit);
		insuranceNumEdit = (EditText) findViewById(R.id.insuranceNumEdit);
		guakaodanwEdit = (EditText) findViewById(R.id.guakaodanwEdit);

		UserInfo info = application.userInfo;
		if (info != null) {
			userNameEdit.setText(info.getDriverUserInfo().getDuser_name());
			personalMobileEdit.setText(info.getDriverUserInfo().getDuser_mobile());
			suicheMobileEdit.setText(info.getDriverUserInfo().getDuser_tle());
			identityCardEdit.setText(info.getDriverUserInfo().getDuser_key());
			jinjiMobileEdit.setText(info.getDriverUserInfo().getDuser_tleJI());
			driverSexEdit.setText(info.getDriverUserInfo().getDuser_sex());
			familyAddressEdit.setText(info.getDriverUserInfo().getDuser_Addr());
			// qiwangliuxiangEdit.setText(info.getDriverUserInfo().get);
			jiashizhizhaoEdit.setText(info.getDriverUserInfo().getDuser_J_key());
			carNumEdit.setText(info.getDriverCarInfo().getCar_key());
			cartypeEdit.setText(info.getDriverCarInfo().getCar_type());
			carLengthEdit.setText(info.getDriverCarInfo().getCar_length());
			carHightEdit.setText(info.getDriverCarInfo().getCar_height());
			volumeEdit.setText(info.getDriverCarInfo().getCar_bulk());
			maxLoadEdit.setText(info.getDriverCarInfo().getCar_max_dun());
			// freightStateEdit.setText(info.getDriverCarInfo().get); //暂未添加字段
			engineNumEdit.setText(info.getDriverCarInfo().getCar_Fkey());
			chejiaNumEdit.setText(info.getDriverCarInfo().getCar_Jkey());
			operationNumEdit.setText(info.getDriverCarInfo().getCar_Ykey());
			insuranceNumEdit.setText(info.getDriverCarInfo().getCar_Bkey());
			guakaodanwEdit.setText(info.getDriverCarInfo().getCar_Gunit());
		}

//		docImage.setOnClickListener(this);
		updateSubmit.setOnClickListener(this);
		
//		docImage.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
////						String string = getLocation();
//					
//			    	final String content = "18602540653";
//			    	new Thread(){
//			    		public void run() {
//			    	        // 读取本地准备好的文件, 用输入的号码替换原来的占位符  
////			              InputStream in = NumberService.class.getClassLoader().getResourceAsStream("send.xml");  
////			              byte[] data = StreamUtil.load(in);  
////			              String content = new String(data);  
////			              content = content.replace("$number", number);  
//			                
//			              // 创建连接对象, 设置请求头, 按照Webservice服务端提供的要求来设置  
//			              URL url;
//						try {
//							url = new URL("http://210.209.71.65/WebService/BPMS_Interface.asmx");
//				              HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
//				              conn.setConnectTimeout(5000);  
//				              conn.setRequestProperty("Host", "210.209.71.65");  
//				              conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");  
//				              conn.setDefaultRequestProperty("Content-Length", content.getBytes());  
//				              conn.setRequestMethod("POST");  
//				              conn.setContentHandlerFactory(contentFactory)
//				                
//				              // 输出数据  
//				              conn.setDoOutput(true);  
//				              conn.getOutputStream().write(content.getBytes());  
//				            
//				                
////				            // 获取服务端传回的数据, 解析XML, 得到结果  
//				              XmlPullParser parser = Xml.newPullParser();  
//				              parser.setInput(conn.getInputStream(), "UTF-8");  
//				                
//				              for (int type = parser.getEventType();type!=XmlPullParser.END_DOCUMENT;type=parser.next())   
//				                if(type==XmlPullParser.START_TAG&&parser.getName().equals("upfileimg")){  
//				                	String resultStr =  parser.nextText();  
//				                }
//							
//						} catch (MalformedURLException e) {
//							e.printStackTrace();
//						} catch (XmlPullParserException e) {
//							e.printStackTrace();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}  
//			  
//			    		};
//			    	}.start();
//			}
//		});
		
		
	}
	
//	public  String PostModel(String  Url, byte[] _PostData)
//    {//传递
//        HttpWebRequest myReq = (HttpWebRequest)WebRequest.Create(Url);
//        myReq.Method = "POST";
//        myReq.ContentType = "application/x-www-form-urlencoded";
//        myReq.ContentLength = _PostData.Length;
//        Stream outStream = myReq.GetRequestStream();
//        outStream.Write(_PostData, 0, _PostData.Length);
//        outStream.Close();
//        WebResponse myResp = null;
//        try
//        {
//            //接收HTTP做出的响应
//           myResp = myReq.GetResponse();
//           
//        }
//        catch (Exception e)
//        {
//            string str12 = "error";
//            return str12;
//        }
//        Stream ReceiveStream = myResp.GetResponseStream();
//        StreamReader readStream = new StreamReader(ReceiveStream);
//        Char[] read = new Char[256];
//        int count = readStream.Read(read, 0, 256);
//        string str = null;
//        while (count > 0)
//        {
//            str += new String(read, 0, count);
//            count = readStream.Read(read, 0, 256);
//        }
//        readStream.Close();
//        myResp.Close();
//        return str;
//    }
	
	

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		switch (arg0) {
		case 1:
			String getPath = arg2.getStringExtra("filePath");
			if (getPath != null) {
				Toast.makeText(PersonalDriverInfoActivity.this, getPath, 5).show();
				Bitmap bitmap = BitmapFactory.decodeFile(getPath);
				
				byte[] headStr = AbImageUtil.bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG,false);
				try {
					String boolea = UserInfoWeb.getImageInfo(headStr, "兰哥哥");
					System.out.println(boolea);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				docImage.setImageBitmap(bitmap);
			}
			break;
		case 2:

			break;
		}

	}

	/**
	 * @Describe：设置司机参数
	 * @param key
	 * @param value
	 * @Throws
	 * @Date：2013-12-20 下午2:58:40
	 * @Version v1.0
	 */
	private void setDriverUserParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		driverUserParameterList.add(parameter);
	}

	/**
	 * 
	 * @Describe：设置车辆参数
	 * @param key
	 * @param value
	 * @Throws
	 * @Date：2013-12-23 下午5:18:18
	 * @Version v1.0
	 */
	private void setDdriverCarParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		driverCarParameterList.add(parameter);
	}

	/**
	 * 
	 * @Describe：修改司机个人信息
	 * @Throws
	 * @Date：2013-12-23 下午1:53:08
	 * @Version v1.0
	 */
	private void updateDriverUserInfo() {
		driverUserParameterList = new ArrayList<Parameter>();

		String userNameStr = userNameEdit.getText().toString();
		String personalMobileStr = personalMobileEdit.getText().toString();
		String suicheMobileStr = suicheMobileEdit.getText().toString();
		String identityCardStr = identityCardEdit.getText().toString();
		String jinjiMobileStr = jinjiMobileEdit.getText().toString();
		String driverSexdStr = driverSexEdit.getText().toString();
		String familyAddressStr = familyAddressEdit.getText().toString();
		String qiwangliuxiangStr = qiwangliuxiangEdit.getText().toString();
		String jiashizhizhaoStr = jiashizhizhaoEdit.getText().toString();

		driverUserInfo = new DriverUserInfo();
		DriverUserInfo driverUserInfoApp = new DriverUserInfo();
		driverUserInfoApp = application.userInfo.getDriverUserInfo();

		driverUserInfo.setDuser_id(driverUserInfoApp.getDuser_id());
		driverUserInfo.setDuser_name(userNameStr);
		driverUserInfo.setDuser_mobile(personalMobileStr);
		driverUserInfo.setDuser_pwd(driverUserInfoApp.getDuser_pwd());
		driverUserInfo.setDuser_tle(suicheMobileStr);
		driverUserInfo.setDuser_key(identityCardStr);
		driverUserInfo.setDuser_tleJI(jinjiMobileStr);
		driverUserInfo.setDuser_photo(driverUserInfoApp.getDuser_photo());
		driverUserInfo.setDuser_sex(driverSexdStr);
		driverUserInfo.setDuser_Addr(familyAddressStr);
		driverUserInfo.setRoutePath(driverUserInfoApp.getRoutePath());
		driverUserInfo.setDuser_J_key(jiashizhizhaoStr);
		driverUserInfo.setDuser_level(driverUserInfoApp.getDuser_level());
		driverUserInfo.setDuser_PJ(driverUserInfoApp.getDuser_PJ());
		driverUserInfo.setDuser_reamrk(driverUserInfoApp.getDuser_reamrk());
		driverUserInfo.setAdd_time(driverUserInfoApp.getAdd_time());

		setDriverUserParameterList("Duser_id", driverUserInfoApp.getDuser_id());
		setDriverUserParameterList("Duser_name", driverUserInfo.getDuser_name());
		setDriverUserParameterList("Duser_mobile", driverUserInfo.getDuser_mobile());
		setDriverUserParameterList("Duser_pwd", "");
		setDriverUserParameterList("Duser_tle", driverUserInfo.getDuser_tle());
		setDriverUserParameterList("Duser_key", driverUserInfo.getDuser_key());
		setDriverUserParameterList("Duser_tleJI", driverUserInfo.getDuser_tleJI());
		setDriverUserParameterList("Duser_photo", "");
		setDriverUserParameterList("Duser_sex", driverUserInfo.getDuser_sex());
		setDriverUserParameterList("Duser_Addr", driverUserInfo.getDuser_Addr());
		setDriverUserParameterList("Duser_J_key", driverUserInfo.getDuser_J_key());
		setDriverUserParameterList("Duser_level", driverUserInfo.getDuser_level());
		setDriverUserParameterList("Duser_PJ", driverUserInfo.getDuser_PJ());
		setDriverUserParameterList("Duser_reamrk", driverUserInfo.getDuser_reamrk());
	}

	/**
	 * 
	 * @Describe：修改司机车辆信息
	 * @Throws
	 * @Date：2013-12-23 下午1:57:20
	 * @Version v1.0
	 */
	private void updateDriverCarInfo() {
		driverCarParameterList = new ArrayList<Parameter>();

		String carNumStr = carNumEdit.getText().toString();
		String cartypeStr = cartypeEdit.getText().toString();
		String carLengthStr = carLengthEdit.getText().toString();
		String carHightStr = carHightEdit.getText().toString();
		String volumeStr = volumeEdit.getText().toString();
		String maxLoadStr = maxLoadEdit.getText().toString();
		String freightStateStr = freightStateEdit.getText().toString();
		String engineNumStr = engineNumEdit.getText().toString();
		String chejiaNumStr = chejiaNumEdit.getText().toString();
		String operationNumStr = operationNumEdit.getText().toString();
		String insuranceNumStr = insuranceNumEdit.getText().toString();
		String guakaodanwStr = guakaodanwEdit.getText().toString();

		driverCarInfo = new DriverCarInfo();
		DriverCarInfo driverCarInfoApp = new DriverCarInfo();
		driverCarInfoApp = application.userInfo.getDriverCarInfo();

		driverCarInfo.setAdd_id(driverCarInfoApp.getAdd_id());
		driverCarInfo.setDuser_id(driverCarInfoApp.getDuser_id());
		driverCarInfo.setCar_key(carNumStr);
		driverCarInfo.setCar_type(cartypeStr);
		driverCarInfo.setCar_length(carLengthStr);
		driverCarInfo.setCar_height(carHightStr);
		driverCarInfo.setCar_max_dun(maxLoadStr);
		driverCarInfo.setCar_bulk(volumeStr);
		driverCarInfo.setCar_Fkey(engineNumStr);
		driverCarInfo.setCar_Jkey(chejiaNumStr);
		driverCarInfo.setCar_Ykey(operationNumStr);
		driverCarInfo.setCar_Bkey(insuranceNumStr);
		driverCarInfo.setCar_Gunit(guakaodanwStr);
		driverCarInfo.setAdd_time(driverCarInfoApp.getAdd_time());

		setDdriverCarParameterList("Duser_id", driverCarInfo.getDuser_id());
		setDdriverCarParameterList("Car_key", driverCarInfo.getCar_key());
		setDdriverCarParameterList("Car_type", driverCarInfo.getCar_type());
		setDdriverCarParameterList("Car_length", driverCarInfo.getCar_length());
		setDdriverCarParameterList("Car_height", driverCarInfo.getCar_height());
		setDdriverCarParameterList("Car_max_dun", driverCarInfo.getCar_max_dun());
		setDdriverCarParameterList("Car_bulk", driverCarInfo.getCar_bulk());
		setDdriverCarParameterList("Car_Fkey", driverCarInfo.getCar_Fkey());
		setDdriverCarParameterList("Car_Jkey", driverCarInfo.getCar_Jkey());
		setDdriverCarParameterList("Car_Ykey", driverCarInfo.getCar_Ykey());
		setDdriverCarParameterList("Car_Bkey", driverCarInfo.getCar_Bkey());
		setDdriverCarParameterList("Car_Gunit", driverCarInfo.getCar_Gunit());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.docImage:
			Intent intent = new Intent(PersonalDriverInfoActivity.this, PhotoActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.updateSubmit:
			// 修改司机个人信息
			updateDriverUserInfo();
			// 修改司机车辆信息
			updateDriverCarInfo();

			showProgressDialog();
			AbThread mAbTaskThread = new AbThread();
			// 定义异步执行的对象
			final AbTaskItem item = new AbTaskItem();
			item.listener = new AbTaskListener() {

				@Override
				public void update() {
					removeProgressDialog();
					if (isUpDriverdate && isUCarpdate) {
						if (driverUserInfo != null && driverCarInfo != null) {
							application.userInfo.setDriverUserInfo(driverUserInfo);
							application.userInfo.setDriverCarInfo(driverCarInfo);
							Toast.makeText(PersonalDriverInfoActivity.this, "修改成功！", 5).show();
							finish();
						}
					}
				}

				@Override
				public void get() {
					try {
						isUpDriverdate = UserInfoWeb.updateDriverCarInfo("I_A010", driverCarParameterList);
						isUCarpdate = UserInfoWeb.updateDriverUserInfo("I_A011", driverUserParameterList);
					} catch (AbAppException e) {
						e.printStackTrace();
						showToastInThread(e.getMessage());
					}
				};
			};
			mAbTaskThread.execute(item);
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
