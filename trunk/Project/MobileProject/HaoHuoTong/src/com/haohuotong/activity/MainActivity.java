package com.haohuotong.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.util.AbImageUtil;
import com.ab.view.titlebar.AbTitleBar;
import com.chat.activity.FriendListActivity;
import com.chat.activity.GetMsgService;
import com.haohuotong.R;
import com.haohuotong.database.SqliteDaoArea;
import com.haohuotong.global.MyApplication;
import com.haohuotong.web.UserInfoWeb;
import com.way.chat.common.util.Constants;



/**
 * 
 * Copyright (c) 2013 All rights reserved
 * @Name：MainActivity.java 
 * @Describe：首页
 * @Author:  yfr5734@gmail.com
 * @Date：2014年4月28日 上午11:06:35
 * @Version v1.0
 */
public class MainActivity extends AbActivity {

	private boolean isClink = true;
	private MyApplication application = null;
	private AlertDialog dialog;
	private double mLongitude = 0;
	private double mLatitude = 0;
	private String resultStr = null;
	private  LinearLayout main_layout, dialogLayout_1,dialogLayout_2, dialogLayout_3;
	private SqliteDaoArea daoArea = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.main);
		mLongitude = getIntent().getDoubleExtra("longitude",0);
		mLatitude = getIntent().getDoubleExtra("latitude",0);
		
		initTitleRightLayout();
		initView();
//		initMyView();
		

	}


	private void initMyView(){
		main_layout = (LinearLayout) findViewById(R.id.main_layout);
		dialogLayout_1 = (LinearLayout) findViewById(R.id.dialogLayout_1);
		dialogLayout_2 = (LinearLayout) findViewById(R.id.dialogLayout_2);
		dialogLayout_3 = (LinearLayout) findViewById(R.id.dialogLayout_3);
		
		//得到当前屏幕的分辨率
		DisplayMetrics myMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(myMetrics);
		
		int widthPixels = myMetrics.widthPixels;
		int heightPixels = myMetrics.heightPixels;
//		int setHeight = (heightPixels - 110)/9;
		
		int main_layout_width = widthPixels-20;
		int main_layout_height = heightPixels-100;
		
		main_layout.getLayoutParams().width = main_layout_width;
		main_layout.getLayoutParams().height = main_layout_height;
		int setHeight =(main_layout_height - 30)/9;
		
		dialogLayout_1.getLayoutParams().height = setHeight;
		dialogLayout_2.getLayoutParams().height = 4*setHeight;
		dialogLayout_3.getLayoutParams().height = 4*setHeight;
		
//		List<LinearLayout> imageViews  = new ArrayList<LinearLayout>();
//		imageViews.add(dialogLayout_1);
//		imageViews.add(dialogLayout_2);
//		imageViews.add(dialogLayout_3);
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
		mAbTitleBar.setTitleText("首页");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		mAbTitleBar.getLogoView().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showMyDialog();
			}
		});
		application = (MyApplication) abApplication;
	}

	/**
	 * 
	 * 描述：初始化数据
	 * 
	 * @throws
	 * @date：2013-11-14 下午5:26:43
	 * @version v1.0
	 */
	private void initView() {
		daoArea = SqliteDaoArea.getInstance(MainActivity.this);
		ImageView itemImage01 = (ImageView) findViewById(R.id.but1);
		ImageView itemImage02 = (ImageView) findViewById(R.id.but2);
		ImageView itemImage03 = (ImageView) findViewById(R.id.but3);
		ImageView itemImage04 = (ImageView) findViewById(R.id.but4);
		ImageView itemImage05 = (ImageView) findViewById(R.id.but5);
		ImageView itemImage06 = (ImageView) findViewById(R.id.but6);
		ImageView itemImage07 = (ImageView) findViewById(R.id.but7);
		ImageView itemImage08 = (ImageView) findViewById(R.id.but8);
		ImageView itemImage09 = (ImageView) findViewById(R.id.but9);
		ImageView itemImage10 = (ImageView) findViewById(R.id.but10);
		ImageView itemImage11 = (ImageView) findViewById(R.id.but11);
		ImageView itemImage12 = (ImageView) findViewById(R.id.but12);

		itemImage01.setOnClickListener(new MyClick());
		itemImage02.setOnClickListener(new MyClick());
		itemImage03.setOnClickListener(new MyClick());
		itemImage04.setOnClickListener(new MyClick());
		itemImage05.setOnClickListener(new MyClick());
		itemImage06.setOnClickListener(new MyClick());
		itemImage07.setOnClickListener(new MyClick());
		itemImage08.setOnClickListener(new MyClick());
		itemImage09.setOnClickListener(new MyClick());
//		itemImage10.setOnClickListener(new MyClick());
		itemImage11.setOnClickListener(new MyClick());
		itemImage12.setOnClickListener(new MyClick());
		
		//得到当前屏幕的分辨率
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int setWidth = (metrics.widthPixels - 100)/3;
		
		List<ImageView> imageViews  = new ArrayList<ImageView>();
		imageViews.add(itemImage01);
		imageViews.add(itemImage02);
		imageViews.add(itemImage03);
		imageViews.add(itemImage04);
		imageViews.add(itemImage05);
		imageViews.add(itemImage06);
		imageViews.add(itemImage07);
		imageViews.add(itemImage08);
		imageViews.add(itemImage09);
		imageViews.add(itemImage10);
		imageViews.add(itemImage11);
		imageViews.add(itemImage12);
		
		int listSize = imageViews.size();
		for (int i = 0; i < listSize; i++) {
			imageViews.get(i).getLayoutParams().width = setWidth;
			imageViews.get(i).getLayoutParams().height = setWidth;
			imageViews.get(i).setLayoutParams(imageViews.get(i).getLayoutParams());
		}
		
		itemImage10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Drawable bitmap = getResources().getDrawable(R.drawable.item_01);
				BitmapDrawable bd = (BitmapDrawable) bitmap;
				Bitmap bm = bd.getBitmap();
				final byte[] headStr = AbImageUtil.bitmap2Bytes(bm, Bitmap.CompressFormat.PNG,false);
				new Thread(){
					public void run() {
						String boolea;
						try {
							boolea = UserInfoWeb.getImageInfo(headStr, "兰哥哥");
							System.out.println(boolea);
						} catch (AbAppException e) {
							e.printStackTrace();
						}
					};
				}.start();
//				byte[] headStr = AbImageUtil.bitmap2Bytes(bitmap, mCompressFormat, needRecycle);
			}
		});
		
		
		
		
//		but10.setOnClickListener(new OnClickListener() {
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
//							url = new URL("http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx");
//				              HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
//				              conn.setConnectTimeout(5000);  
//				              conn.setRequestProperty("Host", "webservice.webxml.com.cn");  
//				              conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");  
//				              conn.setRequestProperty("Content-Length", content.getBytes().length + "");  
//				              conn.setRequestMethod("POST");  
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
//				                if(type==XmlPullParser.START_TAG&&parser.getName().equals("getMobileCodeInfoResult")){  
//				                	resultStr =  parser.nextText();  
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
		
		
//		but11.setOnClickListener(new Button.OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				new Thread() {
//					public void run() {
//						String content = "<Request><data code='I_A001'><no><User_name>ceshi02</User_name><User_pwd>02</User_pwd><User_type>02</User_type><registerResult>-1</registerResult></no></data></Request>";
//						URL url;
//						try {
//							// url = new
//							// URL("http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx");
//							url = new URL("http://210.209.71.65/WebService/BPMS_Interface.asmx");
//							// String uriAPI =
//							// "http://210.209.71.65/WebService/BPMS_Interface.asmx/Invoke";
//							HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//							conn.setConnectTimeout(5000);
//							conn.setRequestProperty("Host", "210.209.71.65");
//							conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
//							conn.setRequestProperty("Content-Length", content.getBytes().length + "");
//							conn.setRequestMethod("POST");
//
//							// 输出数据
////							conn.setDoOutput(true);
//							conn.getOutputStream().write(content.getBytes());
//
////			              // 获取服务端传回的数据, 解析XML, 得到结果  
//			                XmlPullParser parser = Xml.newPullParser();  
//			                parser.setInput(conn.getInputStream(), "UTF-8");  
//			                  
//			                for (int type = parser.getEventType();type!=XmlPullParser.END_DOCUMENT;type=parser.next())   
//			                  if(type==XmlPullParser.START_TAG&&parser.getName().equals("getMobileCodeInfoResult")){  
//			                      String string = parser.nextText();  
//			                  }  
//							
////							InputStream in = conn.getInputStream();
////							String string = inputStream2String(in);
////							Toast.makeText(MainActivity.this, string, 5).show();
//
//							// byte[] data = Stre;
//							// String content = new String(data);
//							// content = content.replace("$number", number);
//
//						} catch (MalformedURLException e1) {
//							e1.printStackTrace();
//						} catch (ProtocolException e) {
//							e.printStackTrace();
//						} catch (IOException e) {
//							e.printStackTrace();
//						} catch (XmlPullParserException e) {
//							e.printStackTrace();
//						}
//
//					};
//				}.start();
//
//			}
//
//		});
		
		
		
		
		
		
		
		
		
		
//		but12.setOnClickListener(new Button.OnClickListener(){           
//			   @Override  
//			   public void onClick(View arg0) {  
//			    // TODO Auto-generated method stub  
//			    //URLַ  
//				   String string = "<Request><data code='I_A001'><no><User_name>ceshi02</User_name><User_pwd>02</User_pwd><User_type>02</User_type><registerResult>-1</registerResult></no></data></Request>";
////			     String uriAPI = "http://www.dubblogs.cc:8751/Android/Test/API/Post/index.php";  
////			     String uriAPI = "http://210.209.71.65/WebService/BPMS_Interface.asmx?op=Invoke";  
//			     String uriAPI = "http://210.209.71.65/WebService/BPMS_Interface.asmx/Invoke";  
//			    /*建立HTTP Post连线*/  
//			    HttpPost httpRequest =new HttpPost(uriAPI);  
//			    //Post运作传送变数必须用NameValuePair[]阵列储存  
//			    //传参数 服务端获取的方法为request.getParameter("name")  
//			    List <NameValuePair> params=new ArrayList<NameValuePair>();  
//			    params.add(new BasicNameValuePair("PROC_register_LPR_UserInfo",string));  
//			    try{  
//			       
//			       
//			     //发出HTTP request  
//			     httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
//			     //取得HTTP response  
//			     HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);  
//			       
//			     //若状态码为200 ok   
//			     if(httpResponse.getStatusLine().getStatusCode()==200){  
//			      //取出回应字串  
//			      String strResult=EntityUtils.toString(httpResponse.getEntity());
//			      Toast.makeText(MainActivity.this, strResult, 5).show();
//			     }
//			    }catch (Exception e) {  
//			     e.printStackTrace();  
//			    }  
//			   }  
//			           
//			        }); 
		
		
		List<String> strList = daoArea.getAreaInfo(100);
		Toast.makeText(MainActivity.this, strList.get(0), 5).show();
		
		
		
	}
	
    public String getLocation() throws Exception { 
    	final String content = "18602540653";
    	new Thread(){
    		public void run() {
    	        // 读取本地准备好的文件, 用输入的号码替换原来的占位符  
//              InputStream in = NumberService.class.getClassLoader().getResourceAsStream("send.xml");  
//              byte[] data = StreamUtil.load(in);  
//              String content = new String(data);  
//              content = content.replace("$number", number);  
                
              // 创建连接对象, 设置请求头, 按照Webservice服务端提供的要求来设置  
              URL url;
			try {
				url = new URL("http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx");
	              HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	              conn.setConnectTimeout(5000);  
	              conn.setRequestProperty("Host", "webservice.webxml.com.cn");  
	              conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");  
	              conn.setRequestProperty("Content-Length", content.getBytes().length + "");  
	              conn.setRequestMethod("POST");  
	                
	              // 输出数据  
	              conn.setDoOutput(true);  
	              conn.getOutputStream().write(content.getBytes());  
	            
	                
//	            // 获取服务端传回的数据, 解析XML, 得到结果  
	              XmlPullParser parser = Xml.newPullParser();  
	              parser.setInput(conn.getInputStream(), "UTF-8");  
	                
	              for (int type = parser.getEventType();type!=XmlPullParser.END_DOCUMENT;type=parser.next())   
	                if(type==XmlPullParser.START_TAG&&parser.getName().equals("getMobileCodeInfoResult")){  
	                	resultStr =  parser.nextText();  
	                }
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
  
    		};
    	}.start();
		return resultStr;

    }  
  
	
	public String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	

	/**
	 * 
	 * Copyright (c) 2012 All rights reserved
	 * 
	 * @Name：MainActivity.java
	 * @Describe：触发点击事件
	 * @Author Administrator yfr5734@gmail.com
	 * @Date：2013-11-18 下午2:18:55
	 * @Version v1.0
	 */
	class MyClick implements OnClickListener {

		public void onClick(View v) {
			AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			if (isClink) {
				switch (v.getId()) {
				case R.id.but1: // 专线查询
//					toastStr();
					startActivity(new Intent(MainActivity.this, MapActivity.class));
					break;
				case R.id.but2: // 高价急走
					toastStr();
//					startActivity(new Intent(MainActivity.this, OverlayDemo.class));
//					Intent intent4 = new Intent(MainActivity.this,OverlayDemo.class);
//					intent4.putExtra("longitude", mLongitude);
//					intent4.putExtra("latitude", mLatitude);
//					startActivity(intent4);
					break;
				case R.id.but3: // 公共资源
					Intent intent = new Intent(MainActivity.this, CommonalityInfoActivity.class);
//					Intent intent = new Intent(MainActivity.this, CommonalityActivity.class);
					intent.putExtra("intentType", 0);
					startActivity(intent);
					break;
				case R.id.but4: // 专线资源
					toastStr();
					break;
				case R.id.but5: // 运价行情
					toastStr();
					break;
				case R.id.but6: // 交通路况
					toastStr();
					break;
				case R.id.but7: // 业务洽谈
					toastStr();
					break;
				case R.id.but8: // 发布车市
					toastStr();
					break;
				case R.id.but9: // 移动结算

					// 启动卡乐付
					Intent intent1 = new Intent();
					intent1.setComponent(new ComponentName("com.unionpay.kalefu", "com.unionpay.kalefu.MainMenu"));
					startActivity(intent1);
					break;
				case R.id.but10: // 货物投保
					toastStr();
					// Intent intent4 = new Intent(MainActivity.this,
					// LoginActivity.class);
					// startActivity(intent4);

					break;
				case R.id.but11: // 更多功能
					toastStr();
					// Intent intent3 = new Intent(MainActivity.this,
					// ContactListActivity.class);
					// startActivity(intent3);
					break;
				case R.id.but12: // 设置中心
					// toastStr();
					Intent intent2 = new Intent(MainActivity.this, SettingCenterActivity.class);
					startActivity(intent2);
					break;
				}
			}
		}
	}

	/**
	 * 
	 * 描述：显示提示对话框
	 * 
	 * @throws
	 * @date：2013-11-19 上午10:36:52
	 * @version v1.0
	 */
	private void showMyDialog() {
		String titleInfo = null;
		Builder builder = new AlertDialog.Builder(this);
		dialog = builder.create();
		View retieve = LayoutInflater.from(this).inflate(R.layout.dialog_show, null);
		dialog.setView(retieve, 0, 0, 0, 0);
		Button acceptBtn = (Button) retieve.findViewById(R.id.acceptBtn);
		Button unAcceptBtn = (Button) retieve.findViewById(R.id.unAcceptBtn);
		TextView dialogTitleText1 = (TextView) retieve.findViewById(R.id.dialogTitleText1);
		TextView setMessage = (TextView) retieve.findViewById(R.id.setMessage);
		dialogTitleText1.setText("温馨提示");
		setMessage.setText("		你确定要退出程序吗？");
		// if (connectType == 1) {
		// titleInfo = "您还未开启网络，请设置网络！";
		// action = Settings.ACTION_WIRELESS_SETTINGS;
		// } else if (connectType == 2) {
		// titleInfo = "本软件需要开启定位功能，以便为您找车或找货，不开启GPS功能您将无法使用本软件，祝您生意兴隆！";
		// action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
		// }
		// setMessage.setText(titleInfo);
		acceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		unAcceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});
		dialog.show();
	}

	/**
	 * 
	 * 描述：提示信息
	 * 
	 * @throws
	 * @date：2013-11-18 下午2:19:22
	 * @version v1.0
	 */
	private void toastStr() {
		Toast.makeText(MainActivity.this, "温馨提示：您目前不是收费用户，暂时不能使用该功能", 5).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "退出应用程序");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	// 菜单选项添加事件处理
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			exitDialog(MainActivity.this, "QQ提示", "您真的要退出吗？");
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// 完全退出提示窗
		private void exitDialog(Context context, String title, String msg) {
			new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@SuppressLint("NewApi")
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// 关闭服务
							Intent service = new Intent(MainActivity.this,
									GetMsgService.class);
							stopService(service);

							// 发送广播关闭所有activity
							Intent i = new Intent();
							i.setAction(Constants.ACTION);
							sendBroadcast(i);
							Log.i("tag", "==================="+GetMsgService.isStart);
							GetMsgService.isStart = false;
							finish();//
							android.os.Process.killProcess(android.os.Process.myPid());
							ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);       
							manager.killBackgroundProcesses(getPackageName());
						}
					}).setNegativeButton("取消", null).create().show();
		}
		
		
	
}
