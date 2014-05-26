package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.renderscript.ProgramVertexFixedFunction.Constants;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.util.MethodUtil;
import com.xibeiwuliu.web.UserInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：LoginActivity.java
 * @Describe：登陆界面
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-11-13 下午1:03:28
 * @Version v1.0
 */
public class LoginActivity_x extends BaseActivity {
	private Button loginBtn;
	private MyApplication application = null;
	private EditText userName, userPwd;
	private String userNameStr, userPwdStr = null;
	private Spinner userTypeSpin = null;
	private int userType = 0;
	private List<Parameter> driverCarParameterList = null;
	private boolean mIsServiceRunning = false;// 服务是否启动标志
	private String sendUserInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.login);
		application = (MyApplication) abApplication;
		initTitleLayout();
//		initView();

	}

	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initTitleLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();

		// 添加左侧布局文件
		mAbTitleBar.setTitleText("登      陆");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// 添加右侧布局文件
		// View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
		View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity_x.this, RegisterActivity_x.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * 描述：初始化View
	 * 
	 * @throws
	 * @date：2013-11-13 上午10:21:24
	 * @version v1.0
	 */
//	private void initView() {
//		loginBtn = (Button) findViewById(R.id.loginBtn);
//		userName = (EditText) findViewById(R.id.userName);
//		userPwd = (EditText) findViewById(R.id.userPwd);
//		userTypeSpin = (Spinner) findViewById(R.id.userTypeSpin);
//
//		userTypeSpin.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//				String getUserTypeStr = userTypeSpin.getSelectedItem().toString().toString();
//				if ("司机".equals(getUserTypeStr)) {
//					userType = 1;
//				} else {
//					userType = 2;
//				}
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//
//		});
//
//		loginBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startLogin();
//				submit();
//			}
//		});
//	}

	/**
	 * 
	 * 描述：登录
	 * 
	 * @throws
	 * @date：2013-11-27 上午10:53:24
	 * @version v1.0
	 */
	private void startLogin() {
		userNameStr = userName.getText().toString().trim();
		userPwdStr = userPwd.getText().toString().trim();

		// 输入电话验证
		if (TextUtils.isEmpty(userNameStr)) {
			userName.setError(getResources().getString(R.string.phoneNumError));
			userName.setFocusable(true);
			userName.setFocusableInTouchMode(true);
			userName.requestFocus();
			return;
		}
		if (!MethodUtil.isMobileNo(userNameStr)) {
			userName.setError(getResources().getText(R.string.error_phone));
			userName.setFocusable(true);
			userName.setFocusableInTouchMode(true);
			userName.requestFocus();
			return;
		}

		// 输入密码验证
		if (TextUtils.isEmpty(userPwdStr)) {
			userPwd.setError(getResources().getString(R.string.setPwdError));
			userPwd.setFocusable(true);
			userPwd.setFocusableInTouchMode(true);
			userPwd.requestFocus();
			return;
		}

		driverCarParameterList = new ArrayList<Parameter>();
		setDdriverCarParameterList("User_name", userNameStr);
		setDdriverCarParameterList("User_pwd", userPwdStr);
		setDdriverCarParameterList("User_type", String.valueOf(userType));

		// 显示进度框
		showProgressDialog();
		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对象
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeProgressDialog();
				if (application.isLogin) {
//					startActivity(new Intent(LoginActivity.this, MainActivity.class));
//					LoginActivity.this.finish();
				}
			}

			@Override
			public void get() {
//				try {
//					UserInfo userInfo = UserInfoWeb.userLogin("I_A002", driverCarParameterList);
//					if (userInfo != null) {
//						application.isLogin = userInfo.isOk();
//						application.userInfo = userInfo;
//					}
//				} catch (AbAppException e) {
//					e.printStackTrace();
//					showToastInThread(e.getMessage());
//				}
			};
		};
		// 开始执行
		mAbTaskThread.execute(item);
	}

	/**
	 * 
	 * @Describe：设置登录参数
	 * @param key
	 * @param value
	 * @Throws
	 * @Date：2013-12-23 下午5:36:00
	 * @Version v1.0
	 */
	private void setDdriverCarParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		driverCarParameterList.add(parameter);
	}
	
	
	
	
	
//	@Override
//	protected void onResume() {// 在onResume方法里面先判断网络是否可用，再启动服务,这样在打开网络连接之后返回当前Activity时，会重新启动服务联网，
//		// TODO Auto-generated method stub
//		super.onResume();
//		if (!GetMsgService.isStart) {
//			loadData();
//		} else {
////			toast(LoginActivity.this);
//		}
//	}
	
	public void loadData(){
		
		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对象
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
			}

			@Override
			public void get() {
//				Intent service = new Intent(LoginActivity.this, GetMsgService.class);
//				startService(service);
				mIsServiceRunning = true;
				
			};
		};
		// 开始执行
		mAbTaskThread.execute(item);
    }

	/**
	 * 点击登录按钮后，弹出验证对话框
	 */
	private Dialog mDialog = null;

//	private void showRequestDialog() {
//		if (mDialog != null) {
//			mDialog.dismiss();
//			mDialog = null;
//		}
//		mDialog = DialogFactory.creatRequestDialog(this, "正在验证账号...");
//		mDialog.show();
//	}

	/**
	 * 提交账号密码信息到服务器
	 */
	private void submit() {
//		showRequestDialog();
		// 通过Socket验证信息
//		if (GetMsgService.isStart) {
//			loadSubmitData();
//		} 
//		else {
////			if (mDialog.isShowing())
////				mDialog.dismiss();
////			DialogFactory.ToastDialog(LoginActivity.this, "QQ登录", "服务器未响应！");
//			Toast.makeText(this, "服务器未响应！", 5).show();
//		}

	}

//    public void loadSubmitData(){
//		
//		AbThread mAbTaskThread = new AbThread();
//		// 定义异步执行的对象
//		final AbTaskItem item = new AbTaskItem();
//		item.listener = new AbTaskListener() {
//
//			@Override
//			public void update() {
//			}
//
//			@Override
//			public void get() {
//				
//				//Java后台版
////				Client client = GetMsgService.client;
////				ClientOutputThread out = client.getClientOutputThread();
////				TranObject<User> o = new TranObject<User>(TranObjectType.LOGIN);
////				User u = new User();
////				u.setId(Integer.parseInt("2017"));
////				u.setPassword(Encode.getEncode("MD5", "123"));
////				o.setObject(u);
////				out.setMsg(o);
////				Log.i("rag", "--------success");
//				
//				Client client = GetMsgService.client;
//				ClientOutputThread out = client.getClientOutputThread();
//				TranObject<User> o = new TranObject<User>(TranObjectType.LOGIN);
//				User u = new User();
//				u.setId(Integer.parseInt("2017"));
//				u.setPassword(Encode.getEncode("MD5", "123"));
//				o.setObject(u);
//				String string = application.firstGetMsg;
//				
//				try {
//					JSONObject jsonObject1 = new JSONObject(string);
////					String msgType = jsonObject1.getString("MsgTypeValue");
//				String jsonStr1 = jsonObject1.getString("Chat");
//				JSONObject jsonObject2 = new JSONObject(jsonStr1);
//				String jsonStr2 = jsonObject2.getString("MsgConnect");
//				JSONObject msgConnectObject = new JSONObject(jsonStr2);
//				UserInfo info = application.userInfo;
//				msgConnectObject.put("UserType", info.getUserType());
//				if (info.getUserType() == 1) {
//					msgConnectObject.put("Userid", info.getDriverUserInfo().getDuser_id());
//					msgConnectObject.put("UserName", info.getDriverUserInfo().getDuser_name());
//				}else {
//					msgConnectObject.put("Userid", info.getLogisticsUserInfo().getUserlogin_id());
//					msgConnectObject.put("UserName", info.getLogisticsUserInfo().getUserloginname());
//				}
//				msgConnectObject.put("UserHandle", "");
//				msgConnectObject.put("UserGUID", application.socketRegisterCode);
//				jsonObject2.put("MsgConnect", msgConnectObject);
//				jsonObject1.put("Chat", jsonObject2);
//				System.out.println("Get UserGUID is --------------->>" + jsonObject1);
//				sendUserInfo = jsonObject1.toString();
//				if (!sendUserInfo.isEmpty()) {
//					out.setMsg(sendUserInfo);
//				}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			};
//		};
//		// 开始执行
//		mAbTaskThread.execute(item);
//    }
	

	private void toast(Context context) {
		new AlertDialog.Builder(context)
				.setTitle("提示")
				.setMessage("网络连接未打开")
				.setPositiveButton("前往打开",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(
										android.provider.Settings.ACTION_WIRELESS_SETTINGS);
								startActivity(intent);
							}
						}).setNegativeButton("取消", null).create().show();
	}
	
	
	// 依据自己需求处理父类广播接收者收取到的消息
//	public void getMessage(TranObject msg) {
//		if (msg != null) {
//			// System.out.println("Login:" + msg);
//			switch (msg.getType()) {
//			case LOGIN:// LoginActivity只处理登录的消息
//				List<User> list = (List<User>) msg.getObject();
//				if (list.size() > 0) {
//					// 保存用户信息
//					SharePreferenceUtil util = new SharePreferenceUtil(
//							this, Constants.SAVE_USER);
//					util.setId(list.get(0).getId()+"");
//					util.setPasswd(list.get(0).getPassword());
//					util.setEmail(list.get(0).getEmail());
//					util.setName(list.get(0).getName());
//					util.setImg(list.get(0).getImg());
//
//					UserDB db = new UserDB(this);
//					db.addUser(list);
//					
//					Intent i = new Intent(LoginActivity.this,
//							SettingCenterActivity.class);
//					i.putExtra(Constants.MSGKEY, msg);
//					startActivity(i);
//					finish();
//					Toast.makeText(getApplicationContext(), "登录成功", 0).show();
//				}
//				break;
//			default:
//				break;
//			}
//		}
//	}

	
	/**
	 * 广播接收者，接收GetMsgService发�?过来的消�?
	 */
//	private BroadcastReceiver MsgReceiver = new BroadcastReceiver() {
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			TranObject msg = (TranObject) intent
//					.getSerializableExtra(Constants.MSGKEY);
//			if (msg != null) {//如果不是空，说明是消息广�?
//				Log.i("tag","---------------------MsgReceiver.msg="+msg);
//				// System.out.println("MyActivity:" + msg);
//				getMessage(msg);// 把收到的消息传�?给子�?
//			} else {//如果是空消息，说明是关闭应用的广�?
//				close();
//			}
//			
//		}
//	};
	
//	public void close() {
//		Intent i = new Intent();
//		i.setAction(Constants.ACTION);
//		sendBroadcast(i);
//		finish();
//	}

//	@Override
//	public void onStart() {// 在start方法中注册广播接收�?
//		super.onStart();
//		IntentFilter intentFilter = new IntentFilter();
//		intentFilter.addAction(Constants.ACTION);
//		registerReceiver(MsgReceiver, intentFilter);// 注册接受消息广播
//
//	}

//	@Override
//	protected void onStop() {// 在stop方法中注�?��播接收�?
//		super.onStop();
//		unregisterReceiver(MsgReceiver);// 注销接受消息广播
//	}
//	
//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
////		unregisterReceiver(MsgReceiver);// 注销接受消息广播
//	}
}
