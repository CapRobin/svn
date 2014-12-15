package com.net56888.logistics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.net56888.logistics.data.Entity;
import com.net56888.logistics.data.EntitySet;
import com.net56888.logistics.data.LocationEntity;

public class Register extends FragmentActivity implements DataSource.Callback {
	private static final String TAG = "Register";

	private Spinner mUserType;
	private EditText mUserName;
	private EditText mPassword;
	private EditText mName1;
	private EditText mPostcard;
	private Spinner mSex;
	private LocationSelector mProvinceCity;
	// private EditText mTel;
	// private EditText mSj;
	private Spinner mCarType;
	private Spinner mCarnumsP;
	private Spinner mCarnumsA;
	private EditText mCarnumsN;
	// private EditText mRoads;
	private LocationSelector mRoadsStart;
	private LocationSelector mRoadsStop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		View titleBar = this.findViewById(R.id.title_bar);

		Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
		lbtn.setText(R.string.btn_back);
		lbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

		});

		Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
		rbtn.setVisibility(View.INVISIBLE);

		TextView tv = (TextView) titleBar.findViewById(R.id.title);
		tv.setText(R.string.title_register);

		mUserType = (Spinner) findViewById(R.id.register_usertype);
		mUserName = (EditText) findViewById(R.id.register_username);
		mPassword = (EditText) findViewById(R.id.register_password);
		mName1 = (EditText) findViewById(R.id.register_name1);
		mPostcard = (EditText) findViewById(R.id.register_postcard);
		mSex = (Spinner) findViewById(R.id.register_sex);
		FragmentManager fm = this.getSupportFragmentManager();
		mProvinceCity = (LocationSelector) fm.findFragmentById(R.id.fragment_province_city);
		// mTel = (EditText) findViewById(R.id.register_tel);
		// mSj = (EditText) findViewById(R.id.register_sj);
		mCarType = (Spinner) findViewById(R.id.register_cartype);
		mCarnumsP = (Spinner) findViewById(R.id.register_carnums_p);
		mCarnumsA = (Spinner) findViewById(R.id.register_carnums_a);
		mCarnumsN = (EditText) findViewById(R.id.register_carnums_n);
		// mRoads = (EditText) findViewById(R.id.register_roads);
		mRoadsStart = (LocationSelector) fm.findFragmentById(R.id.fragment_roads_start);
		mRoadsStop = (LocationSelector) fm.findFragmentById(R.id.fragment_roads_stop);

		Button submitBtn = (Button) findViewById(R.id.register_submit_btn);
		submitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validate()) {
					submit();
				}
			}

		});

		Button resetBtn = (Button) findViewById(R.id.register_reset_btn);
		resetBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				reset();
			}

		});
	}

	private boolean validate() {
		if (false == isUserTypeValid())
			return false;
		if (false == isUserNameValid())
			return false;
		if (false == isPasswordValid())
			return false;
		if (false == isName1Valid())
			return false;
		if (false == isPostcardValid())
			return false;
		if (false == isSexValid())
			return false;
		if (false == isProvinceCityValid())
			return false;
		/*
		 * if (false == isTelValid()) return false; if (false == isSjValid())
		 * return false;
		 */
		if (false == isCarTypeValid())
			return false;
		if (false == isCarnumValid())
			return false;
		if (false == isRoadsValid())
			return false;
		return true;
	}

	private boolean isUserTypeValid() {
		return true;
	}

	private boolean isUserNameValid() {
		if (mUserName.getText().toString().length() == 11)
			return true;
		Toast.makeText(Register.this, "请使用正确的手机号码作为您的用户名", Toast.LENGTH_SHORT).show();
		return false;
	}

	private boolean isPasswordValid() {
		if (mPassword.getText().toString().length() > 5)
			return true;
		Toast.makeText(Register.this, "请使用长度至少是6位的密码", Toast.LENGTH_SHORT).show();
		return false;
	}

	private boolean isName1Valid() {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(mName1.getText().toString());
		int length = 0;
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				length++;
			}
		}
		if (mName1.getText().toString().length() == length && 1 < length && length < 5)
			return true;
		Toast.makeText(Register.this, "请使用真实姓名", Toast.LENGTH_SHORT).show();
		return false;
	}

	private boolean isPostcardValid() {
		if (IdcardUtils.validateCard(mPostcard.getText().toString()))
			return true;
		Toast.makeText(Register.this, "请使用有效的身份证号码", Toast.LENGTH_SHORT).show();
		return false;
	}

	private boolean isSexValid() {
		return true;
	}

	private boolean isProvinceCityValid() {
		if (mProvinceCity.getLocations().size() == 2)
			return true;
		Toast.makeText(Register.this, "请选择省区，城市", Toast.LENGTH_SHORT).show();
		return false;
	}

	/*
	 * private boolean isTelValid() { if (mTel.getText().toString().length() >
	 * 8) return true; Toast.makeText(Register.this, "请使用有效的电话号码",
	 * Toast.LENGTH_SHORT).show(); return false; }
	 */

	/*
	 * private boolean isSjValid() { return mSj; }
	 */

	private boolean isCarTypeValid() {
		return true;
	}

	private boolean isCarnumValid() {
		if (mCarnumsN.getText().toString().length() == 5) {
			return true;
		}
		Toast.makeText(Register.this, "车牌号必须为5位", Toast.LENGTH_SHORT).show();
		return false;
	}

	private boolean isRoadsValid() {
		if (mRoadsStart.getLocations().size() > 0 && mRoadsStop.getLocations().size() > 0)
			return true;
		Toast.makeText(Register.this, "请正确填写运输线路", Toast.LENGTH_SHORT).show();
		return false;
	}

	private void submit() {
		String province = "";
		String city = "";
		EntitySet es = mProvinceCity.getLocations();
		String macaddr = Util.getIMEI(this);
		if (es.size() > 0)
			province = ((LocationEntity) es.get(0)).getMsg_Location();
		if (es.size() > 1)
			city = ((LocationEntity) es.get(1)).getMsg_Location();
		DataSource.getInstance().registerUserAsync(this, "" + mUserType.getSelectedItemPosition(), mUserName.getText().toString(), mPassword.getText().toString(), mName1.getText().toString(),
				mPostcard.getText().toString(), mSex.getSelectedItem().toString(), province, city, mUserName.getText().toString(), mUserName.getText().toString(),
				mCarType.getSelectedItem().toString(), mCarnumsP.getSelectedItem().toString() + mCarnumsA.getSelectedItem().toString() + mCarnumsN.getText().toString(),
				mRoadsStart.getLocationString() + " → " + mRoadsStop.getLocationString(), "0", macaddr);
	}

	private void reset() {
		mUserType.setSelection(0);
		mUserName.setText("");
		mPassword.setText("");
		mName1.setText("");
		mPostcard.setText("");
		mSex.setSelection(0);
		mProvinceCity.reset();
		// mTel.setText("");
		// mSj.setText("");
		mCarType.setSelection(0);
		mCarnumsP.setSelection(0);
		mCarnumsA.setSelection(0);
		mCarnumsN.setText("");
		// mRoads.setText("");
		mRoadsStart.reset();
		mRoadsStop.reset();
	}

	@Override
	public void onLogin(Entity entity) {
		// TODONOTHING
	}

	@Override
	public void onPublish(String result) {
		// TODONOTHING
	}

	@Override
	public void onCheckConnection(String result, int statusCode) {
		// TODONOTHING
	}

	@Override
	public void onRegisterUser(String _result) {
		final String result = _result;
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (null == result) {
					Toast.makeText(Register.this, R.string.register_error, Toast.LENGTH_SHORT).show();
					return;
				}
				if (0 == result.indexOf("RegSuccess")) {
					Toast.makeText(Register.this, R.string.register_regsuccess, Toast.LENGTH_SHORT).show();
				}

				if (0 == result.indexOf("RegNoGo")) {
					Toast.makeText(Register.this, R.string.register_regnogo, Toast.LENGTH_SHORT).show();
				}

				if (0 == result.indexOf("HasRegedBefore")) {
					String[] results = result.split(":");
					String note = Register.this.getResources().getString(R.string.register_hasregedbefore);
					if (results.length > 1)
						note = note + "；注册的会员号为" + results[1];
					Toast.makeText(Register.this, note, Toast.LENGTH_SHORT).show();
				}

				if (0 == result.indexOf("HasReged")) {
					Toast.makeText(Register.this, R.string.register_hasreged, Toast.LENGTH_SHORT).show();
				}

				if (0 == result.indexOf("Error")) {
					Toast.makeText(Register.this, R.string.register_error, Toast.LENGTH_SHORT).show();
				}

				finish();

			}

		});

	}

	@Override
	public void onCheckUpdate(String result) {
		// do nothing
	}

}
