package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
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
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.util.MethodUtil;
import com.xibeiwuliu.web.UserInfoWeb;

public class RegisterActivity extends BaseActivity {
	private MyApplication application = null;
	private String getMsg;
	private String register = null;

	private Button registerBtn;
	private EditText realNameEdit, userNameEdit, pwdEdit, affirmPwdEdit;
	private String realNameEditStr, userNameEditStr, pwdEditStr,
			affirmPwdEditStr;
	private Spinner userTypeSpin = null;
	private boolean isShowRightBut = true; // 是否显示右边按钮
	
	private int userType = 0;		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.register);
		application = (MyApplication) abApplication;
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, false);
		initView();

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

		realNameEdit = (EditText) findViewById(R.id.realNameEdit);
		userNameEdit = (EditText) findViewById(R.id.userNameEdit);
		pwdEdit = (EditText) findViewById(R.id.pwdEdit);
		affirmPwdEdit = (EditText) findViewById(R.id.affirmPwdEdit);
		userTypeSpin = (Spinner) findViewById(R.id.userTypeSpin);
		registerBtn = (Button) findViewById(R.id.registerBtn);
		

		userTypeSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				String getUserTypeStr = userTypeSpin.getSelectedItem().toString().toString();
				if ("司机".equals(getUserTypeStr)) {
					userType = 0;
				} else {
					userType = 1;
				}
				Toast.makeText(RegisterActivity.this, getUserTypeStr, 5).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		

		registerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startRegister();

			}
		});

	}

	private void startRegister() {
		final String Imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
				.getDeviceId();
		realNameEditStr = realNameEdit.getText().toString().trim();
		userNameEditStr = userNameEdit.getText().toString().trim();
		pwdEditStr = pwdEdit.getText().toString().trim();
		affirmPwdEditStr = affirmPwdEdit.getText().toString().trim();

		// 输入姓名验证
		if (TextUtils.isEmpty(realNameEditStr)) {
			realNameEdit.setError(getResources().getString(R.string.realName));
			realNameEdit.setFocusable(true);
			realNameEdit.setFocusableInTouchMode(true);
			realNameEdit.requestFocus();
			return;
		}
		if (MethodUtil.chineseLength(realNameEditStr) < 3	|| MethodUtil.chineseLength(realNameEditStr) > 16) {
			realNameEdit.setError(getResources().getText(R.string.really_name_on));
			realNameEdit.setFocusable(true);
			realNameEdit.setFocusableInTouchMode(true);
			realNameEdit.requestFocus();
			return;
		}

		// 输入电话验证
		if (TextUtils.isEmpty(userNameEditStr)) {
			userNameEdit.setError(getResources().getString(R.string.phoneNumError));
			userNameEdit.setFocusable(true);
			userNameEdit.setFocusableInTouchMode(true);
			userNameEdit.requestFocus();
			return;
		}
		if (!MethodUtil.isMobileNo(userNameEditStr)) {
			userNameEdit.setError(getResources().getText(R.string.error_phone));
			userNameEdit.setFocusable(true);
			userNameEdit.setFocusableInTouchMode(true);
			userNameEdit.requestFocus();
			return;
		}

		// 输入密码验证
		if (TextUtils.isEmpty(pwdEditStr)) {
			pwdEdit.setError(getResources().getString(R.string.setPwdError));
			pwdEdit.setFocusable(true);
			pwdEdit.setFocusableInTouchMode(true);
			pwdEdit.requestFocus();
			return;
		}

		// if (!MethodUtil.isMobileNo(setPwdStr)) {
		// setPwd.setError(getResources().getText(R.string.pwdNotPhoneError));
		// setPwd.setFocusable(true);
		// setPwd.setFocusableInTouchMode(true);
		// setPwd.requestFocus();
		// return;
		// }

		// 确认密码验证
		if (!pwdEditStr.equals(affirmPwdEditStr)) {
			pwdEdit.setError(getResources().getString(R.string.affirmPwdError));
			affirmPwdEdit.setFocusable(true);
			affirmPwdEdit.setFocusableInTouchMode(true);
			affirmPwdEdit.requestFocus();
			return;
		} else {
			pwdEditStr = affirmPwdEditStr;
		}

		// 显示进度框
		showProgressDialog();
		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对象
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeProgressDialog();
				if (!TextUtils.isEmpty(register)) {
					Toast.makeText(RegisterActivity.this, register, 5).show();
				} else {
					Toast.makeText(RegisterActivity.this, "获取数据失败", 5).show();
				}
			}

			@Override
			public void get() {
				try {
					

//					realNameEditStr = realNameEdit.getText().toString().trim();
//					userNameEditStr = userNameEdit.getText().toString().trim();
//					pwdEditStr = pwdEdit.getText().toString().trim();
//					affirmPwdEditStr = affirmPwdEdit.getText().toString().trim();
					
					register = UserInfoWeb.userRegister(realNameEditStr, userNameEditStr, pwdEditStr, userType, Imei, "1542519456");
				} catch (AbAppException e) {
					e.printStackTrace();
					showToastInThread(e.getMessage());
				}
			};
		};
		// 开始执行
		mAbTaskThread.execute(item);
	}

}
