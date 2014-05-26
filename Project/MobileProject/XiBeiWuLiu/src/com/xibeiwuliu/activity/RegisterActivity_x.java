package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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

import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.global.Constant;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.util.MethodUtil;
import com.xibeiwuliu.web.UserInfoWeb;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：RegisterActivity.java
 * @Describe：注册界面
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-11-13 下午1:05:30
 * @Version v1.0
 */
public class RegisterActivity_x extends BaseActivity {
	private Button registerBtn;
	private EditText realName, phoneNum, setPwd, affirmPwd;
	private String realNameStr, phoneNumStr, setPwdStr, affirmPwdStr = null;
	private MyApplication application = null;
	private String Imei = null;
	private Spinner userTypeSpin = null;
	private int userType = 0;
	private Boolean isRegister = false;
	private List<Parameter> parameterList = new ArrayList<Parameter>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setAbContentView(R.layout.register);

		initTitleRightLayout();
//		initView();

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
		mAbTitleBar.setTitleText("注      册");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) abApplication;
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
//		registerBtn = (Button) findViewById(R.id.registerBtn);
//		realName = (EditText) findViewById(R.id.realName);
//		phoneNum = (EditText) findViewById(R.id.phoneNum);
//		setPwd = (EditText) findViewById(R.id.setPwd);
//		affirmPwd = (EditText) findViewById(R.id.affirmPwd);
//
//		userTypeSpin = (Spinner) findViewById(R.id.userTypeSpin);
//		userTypeSpin.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//
//				String getUserTypeStr = userTypeSpin.getSelectedItem()
//						.toString().toString();
//				if ("司机".equals(getUserTypeStr)) {
//					userType = 1;
//				} else {
//					userType = 2;
//				}
//
//				Toast.makeText(RegisterActivity_x.this, getUserTypeStr, 5).show();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//
//		registerBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// startRegister();
//			}
//		});
//
//	}

	/**
	 * 注册
	 */
	// private void startRegister() {
	//
	// MyAsycnTask mTask = new MyAsycnTask();
	// realNameStr = realName.getText().toString().trim();
	// phoneNumStr = phoneNum.getText().toString().trim();
	// setPwdStr = setPwd.getText().toString().trim();
	// affirmPwdStr = affirmPwd.getText().toString().trim();
	//
	// // 输入姓名验证
	// if (TextUtils.isEmpty(realNameStr)) {
	// realName.setError(getResources().getString(R.string.realName));
	// realName.setFocusable(true);
	// realName.setFocusableInTouchMode(true);
	// realName.requestFocus();
	// return;
	// }
	// if (MethodUtil.chineseLength(realNameStr) < 3 ||
	// MethodUtil.chineseLength(realNameStr) > 16) {
	// realName.setError(getResources().getText(R.string.really_name_on));
	// realName.setFocusable(true);
	// realName.setFocusableInTouchMode(true);
	// realName.requestFocus();
	// return;
	// }
	//
	// // 输入电话验证
	// if (TextUtils.isEmpty(phoneNumStr)) {
	// phoneNum.setError(getResources().getString(R.string.phoneNumError));
	// phoneNum.setFocusable(true);
	// phoneNum.setFocusableInTouchMode(true);
	// phoneNum.requestFocus();
	// return;
	// }
	// if (!MethodUtil.isMobileNo(phoneNumStr)) {
	// phoneNum.setError(getResources().getText(R.string.error_phone));
	// phoneNum.setFocusable(true);
	// phoneNum.setFocusableInTouchMode(true);
	// phoneNum.requestFocus();
	// return;
	// }
	//
	// // 输入密码验证
	// if (TextUtils.isEmpty(setPwdStr)) {
	// setPwd.setError(getResources().getString(R.string.setPwdError));
	// setPwd.setFocusable(true);
	// setPwd.setFocusableInTouchMode(true);
	// setPwd.requestFocus();
	// return;
	// }
	//
	// // if (!MethodUtil.isMobileNo(setPwdStr)) {
	// // setPwd.setError(getResources().getText(R.string.pwdNotPhoneError));
	// // setPwd.setFocusable(true);
	// // setPwd.setFocusableInTouchMode(true);
	// // setPwd.requestFocus();
	// // return;
	// // }
	//
	// // 确认密码验证
	// if (!setPwdStr.equals(affirmPwdStr)) {
	// affirmPwd.setError(getResources().getString(R.string.affirmPwdError));
	// affirmPwd.setFocusable(true);
	// affirmPwd.setFocusableInTouchMode(true);
	// affirmPwd.requestFocus();
	// return;
	// } else {
	// setPwdStr = affirmPwdStr;
	// }
	//
	// Imei = ((TelephonyManager)
	// getSystemService(TELEPHONY_SERVICE)).getDeviceId();
	// setParameterList("User_name", phoneNumStr);
	// setParameterList("User_pwd", setPwdStr);
	// setParameterList("User_type", String.valueOf(userType));
	// setParameterList("User_truename", realNameStr);
	// setParameterList("DeviceMark", Imei);
	//
	// // 显示进度框
	// showProgressDialog();
	// AbThread mAbTaskThread = new AbThread();
	// // 定义异步执行的对象
	// final AbTaskItem item = new AbTaskItem();
	// item.listener = new AbTaskListener() {
	//
	// @Override
	// public void update() {
	// removeProgressDialog();
	// if (isRegister) {
	// showToastInThread("注册成功！");
	// finish();
	// } else {
	// showToastInThread("注册失败！");
	// }
	// }
	//
	// @Override
	// public void get() {
	// try {
	// isRegister = UserInfoWeb.userRegister(parameterList);
	// }catch (AbAppException e) {
	// e.printStackTrace();
	// showToastInThread(e.getMessage());
	// }
	// };
	// };
	// // 开始执行
	// mAbTaskThread.execute(item);
	//
	//
	// // new Thread() {
	// // public void run() {
	// // try {
	// // Boolean isRegister = UserInfoWeb.UserRegister(parameterList);
	// // System.out.println(isRegister);
	// // if (isRegister) {
	// // showToastInThread("注册成功！");
	// // finish();
	// // } else {
	// // showToastInThread("注册失败！");
	// // }
	// // } catch (Exception e) {
	// // e.printStackTrace();
	// // }
	// // };
	// // }.start();
	// }

	/**
	 * 
	 * @Describe：设置参数
	 * @param key
	 * @param value
	 * @Throws
	 * @Date：2013-12-20 下午2:58:40
	 * @Version v1.0
	 */
	private void setParameterList(String key, String value) {
		//
		// List<Parameter> list = new ArrayList<Parameter>();
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		parameterList.add(parameter);
	}

	/**
	 * 
	 * Copyright (c) 2012 All rights reserved
	 * 
	 * @Name：RegisterActivity.java
	 * @Describe：异步加载数据
	 * @Author Administrator yfr5734@gmail.com
	 * @Date：2013-11-27 上午8:49:43
	 * @Version v1.0
	 */
	// private class MyAsycnTask extends AsyncTask<Integer, Integer, String> {
	//
	// // 1、准备工作
	// @Override
	// protected void onPreExecute() {
	// System.out.println("第一步：准备工作！");
	// super.onPreExecute();
	// }
	//
	// // 2、异步任务执行阶段
	// @Override
	// protected String doInBackground(Integer... params) {
	// System.out.println("第二步：异步任务执行阶段！");
	//
	// String resultData = null;
	// final Map<Object, Object> params1 = new HashMap<Object, Object>();
	// HttpRequestEx mHttp = new HttpRequestEx();
	// final String url = Constant.BASEURL + "estar/mclient2/Register.asp";
	// params1.put("action", "Reg");
	// params1.put("username", phoneNumStr);
	// params1.put("password", setPwdStr);
	// params1.put("name1", realNameStr);
	// params1.put("diskinfo", "Android");
	// params1.put("macaddr", Imei);
	// HttpResultEx res = mHttp.getHttpData(url, params1);
	// if (res.getStatusCode() == 200) {
	// resultData = res.getData();
	// }
	// return resultData; // 返回结果
	// }
	//
	// // 3、任务执行进度控制
	// @Override
	// protected void onProgressUpdate(Integer... values) {
	// Log.d("Tag", "随着doInBackground方法中的publishProgress方法的执行而执行!");
	// // mView.setText(values[0]+"%");
	// super.onProgressUpdate(values);
	// }
	//
	// // 4、处理结果
	// @Override
	// protected void onPostExecute(String result) {
	// System.out.println("第四步：对返回的结果进行处理！");
	// if (!(result.indexOf("RegSuccess") == -1)) {
	// Toast.makeText(RegisterActivity.this, "注册成功", 5).show();
	// finish();
	// } else if (!(result.indexOf("RegNoGo") == -1)) {
	// Toast.makeText(RegisterActivity.this, "提交信息成功,等待审核，不能登录软件", 5).show();
	// } else if (!(result.indexOf("HasRegedBefore") == -1)) {
	// Toast.makeText(RegisterActivity.this, "此机器已经注册过一个会员名", 5).show();
	// } else if (!(result.indexOf("HasReged") == -1)) {
	// Toast.makeText(RegisterActivity.this, "用户名已经被注册", 5).show();
	// } else if (!(result.indexOf("Error") == -1)) {
	// Toast.makeText(RegisterActivity.this, "注册失败", 5).show();
	// } else {
	// Toast.makeText(RegisterActivity.this, "注册失败", 5).show();
	// }
	// }
	//
	// }
}
