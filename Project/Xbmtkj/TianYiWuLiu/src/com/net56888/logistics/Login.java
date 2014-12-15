package com.net56888.logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.net56888.logistics.data.Entity;
import com.net56888.logistics.data.UserEntity;

public class Login extends FragmentActivity implements DataSource.Callback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_login);

		View titleBar = this.findViewById(R.id.title_bar);

		Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
		lbtn.setText(R.string.btn_back);
		lbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Login.this.finish();
			}

		});

		Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
		rbtn.setText(R.string.btn_register);
		rbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Login.this, Register.class);
				startActivity(i);
			}

		});

		TextView tv = (TextView) titleBar.findViewById(R.id.title);
		tv.setText(R.string.title_login);

		Button login_btn = (Button) findViewById(R.id.login_btn);
		login_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				EditText et_account = (EditText) Login.this.findViewById(R.id.login_account);
				EditText et_password = (EditText) Login.this.findViewById(R.id.login_password);
				DataSource.getInstance().getLoginInfoAsync(Login.this, et_account.getText().toString(), et_password.getText().toString(), "0", Util.getIMEI(Login.this));
			}

		});
	}

	@Override
	public void onLogin(Entity entity) {
		final UserEntity ue = (UserEntity) entity;
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (ue == null) {
					Toast.makeText(Login.this, R.string.login_fail_alert, Toast.LENGTH_SHORT).show();
				} else if (ue.getError().length() > 0) {
					Toast.makeText(Login.this, ue.getError(), Toast.LENGTH_SHORT).show();
				} else {
					Config.getInstance().setUser(ue);
					ue.insert(App.getLogisticsDB().getUserTable());
					Intent i = new Intent(Login.this, Profile.class);
					startActivity(i);
					finish();
				}

			}

		});

	}

	@Override
	public void onPublish(String result) {
		// Do nothing
	}

	@Override
	public void onCheckConnection(String result, int statusCode) {
		// Do nothing
	}

	@Override
	public void onRegisterUser(String result) {
		// Do nothing
	}

	@Override
	public void onCheckUpdate(String result) {
		// Do nothing

	}

}
