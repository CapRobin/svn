package com.xibeiwuliu.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.global.MyApplication;

public class LoginActivity extends BaseActivity {
	private MyApplication application = null;
	private TextView text;
	private String getMsg;
	

	private Button loginBtn;
	private EditText userNameEdit, userPwdEdit;
	private Spinner userTypeSpin = null;
	private boolean isShowRightBut = true; // 是否显示右边按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setAbContentView(R.layout.login);
//		application = (MyApplication) abApplication;
//		getMsg = getIntent().getStringExtra("msg");
//		initTitleLayout(getMsg, false);
//		initView();
		setAbContentView(R.layout.login);
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
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
		
		loginBtn = (Button) findViewById(R.id.loginBtn);
		userNameEdit = (EditText) findViewById(R.id.userNameEdit);
		userPwdEdit = (EditText) findViewById(R.id.userPwdEdit);
		userTypeSpin = (Spinner) findViewById(R.id.userTypeSpin);
		
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(LoginActivity.this, "开始登录", 5).show();
				
			}
		});
		

		// 右侧按钮的点击事件
		if (isShowRightBut) {
			this.rightTitleBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String Msg = "注    册";
					Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
					intent.putExtra("msg", Msg);
					startActivity(intent);
				}
			});
		}

	}
}
