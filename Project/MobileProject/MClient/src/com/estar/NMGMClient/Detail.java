package com.estar.NMGMClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.estar.comm.MsgInfos;
import com.estar.comm.MySuperActivity;
import com.estar.comm.MySuperApplication;
import com.estar.comm.TextPage;
import com.estar.NMGMClient.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.ArrowKeyMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class Detail extends MySuperActivity {

	TextView mTypeCode, mAddTime, mTel, mCompany;
	TextView mContent;
	Button mButton, mButton2;
	private int selectedTelIndex = 0;
	private String[] arrayTel;
	private String mStopCity;
	private MySuperApplication app;
	private boolean isShowRightBut = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.detail);
		setAbContentView(R.layout.detail);
		// getMsg = getIntent().getStringExtra("msg");
		initTitleLayout("详情", isShowRightBut);

		// 设置全局变量相关
		app = (MySuperApplication) getApplication(); // 获得我们的应用程序MySuperApplication
		// 取得查询关键字
		mStopCity = app.GetConfigValue("stopcity");

		mTypeCode = (TextView) findViewById(R.id.typecode);
		mAddTime = (TextView) findViewById(R.id.addtime);
		mContent = (TextView) findViewById(R.id.content);
		mCompany = (TextView) findViewById(R.id.company);
		mTel = (TextView) findViewById(R.id.tel);
		// --------设置信息内容可以复制----------------
		mContent.setFocusableInTouchMode(true);
		mContent.setFocusableInTouchMode(true);
		mContent.setFocusable(true);
		mContent.setClickable(true);
		mContent.setLongClickable(true);
		mContent.setMovementMethod(ArrowKeyMovementMethod.getInstance());
		mContent.setText(mContent.getText(), BufferType.SPANNABLE);
		// --------------------------------------------
		// Button
		mButton = (Button) findViewById(R.id.button1);
		mButton2 = (Button) findViewById(R.id.button2);

		// 取得接收参数
		// 接收参数（通过Bundle）
		/*
		 * Bundle bunde=this.getIntent().getExtras(); String
		 * ItemId=bunde.getString("ID"); String
		 * msg_type=bunde.getString("msg_type"); String
		 * msg_content=bunde.getString("msg_content"); String
		 * msg_tel=bunde.getString("msg_tel");
		 */
		// 接收参数(通过putExtra）
		Intent intent = getIntent();
		MsgInfos item = (MsgInfos) intent.getParcelableExtra("MSGINFODATA");
		//
		String msg_code = item.getMsg_Code();
		String msg_type = item.getMsg_Type();
		String msg_content = item.getMsg_Content();
		String msg_tel = item.getMsg_Tel();
		String msg_addtime = item.getMsg_AddTime();
		String msg_company = "";
		String tableType = app.m_strTableType;

		// 分离出公司名称与电话
		String[] msg_tels = msg_tel.split(" ");
		String str1 = "";
		for (int i = 0; i < msg_tels.length; i++) {
			if (isChinese(msg_tels[i])) {
				msg_company += msg_tels[i] + " ";
			} else {
				str1 += msg_tels[i] + "\n";
			}
		}
		// ===================
		if (msg_type.equals("0")) {
			if (tableType.equals("钢材信息")) {
				msg_type = "[" + getString(R.string.typeInfo1Txt) + "]";// 钢材信息
			} else {
				msg_type = "[" + getString(R.string.typeBtn1Txt) + "]";// 货源信息
			}

		} else {
			if (tableType.equals("煤炭信息")) {
				msg_type = "[" + getString(R.string.typeInfo2Txt) + "]";// 煤炭信息
			} else {
				msg_type = "[" + getString(R.string.typeBtn2Txt) + "]";// 车源信息
			}

		}
		String typeCode = msg_type + msg_code;
		// 显示详情
		mTypeCode.setText(typeCode);
		mAddTime.setText(msg_addtime);
		// ----------------------------------------
		// 提取信息内容中的电话
		Pattern pTel = Pattern.compile("((1[0-9]{10,})|(\\d{3,4}-\\d{7,8})|(\\d{7,}))");/*
																						 * 把1开头11位及以上的数字
																						 * ，
																						 * 及联系电话都匹配出来
																						 */
		Matcher matchr = pTel.matcher(msg_content);
		while (matchr.find()) {
			String telnums = matchr.group();
			str1 += telnums + "\n";
			msg_content = msg_content.replace(telnums, "");
		}
		// ----------------------------------------
		if (mStopCity.equals("")) {
			mContent.setText(msg_content);
		} else {
			// ---------------------------------------------------------
			String strKeyword;
			// 判断搜索条件是否为多个
			int result = mStopCity.indexOf(",");
			if (result == -1) {// 单个关键字
				strKeyword = mStopCity;
			} else {// 多个关键字
				String[] strings = mStopCity.split(",");
				int i = 0, k = 0;
				for (; i < strings.length; i++) {
					k = i;
					int result2 = msg_content.indexOf(strings[k]);
					if (result2 >= 0)
						break;
				}
				if (k >= strings.length)
					k = strings.length - 1;
				strKeyword = strings[k];
			}
			// -----------------------------------------------------------
			// ===========使关键字变色=========================
			SpannableString s = new SpannableString(msg_content);
			Pattern p = Pattern.compile(strKeyword);
			Matcher m = p.matcher(s);
			while (m.find()) {
				int start = m.start();
				int end = m.end();
				s.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			mContent.setText(s);
			// ================================================
		}
		mTel.setText(str1);
		mCompany.setText(msg_company);

		// 取得电话号码
		String str2 = mTel.getText().toString();
		arrayTel = str2.split("\\n");

		// 点击电话号码拨打电话
		mTel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ShowTelMenuDlg();
			}
		});
		// 点击按钮拨打电话
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示拨号对话框
				ShowTelMenuDlg();
			}
		});
		// 返回
		mButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 返回
				Detail.this.finish();
			}
		});
	}

	//
	protected void ShowTelMenuDlg() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(getString(R.string.xuanzhe)).setSingleChoiceItems(arrayTel, 0, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				selectedTelIndex = which;
			}
		}).setPositiveButton(getString(R.string.DialTxt), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				//
				String strTel = get_StringNum(arrayTel[selectedTelIndex]);
				// Toast.makeText(Detail.this, strTel,
				// Toast.LENGTH_SHORT).show();
				// 验证电话
				if (PhoneNumberUtils.isGlobalPhoneNumber(strTel)) {
					// 显示拨号界面
					// Intent i=new
					// Intent(Intent.ACTION_DIAL,Uri.parse("tel://"+strTel));
					// 直接拨号(需要注意打电话，必须在清单文件AndroidManifest.xml文件中配置声明应用程序需要打电话的许可。)
					Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + strTel));
					Detail.this.startActivity(i);
				} else {
					Toast.makeText(Detail.this, getString(R.string.DialErrorTxt), 5000).show();
				}

			}
		}).setNeutralButton(getString(R.string.CancelTxt), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 取消

			}
		}).create();

		alertDialog.show();
	}

	private boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}

		return false;

	}

	private boolean isChinese(String s) {
		Pattern pa = Pattern.compile("^[\u4e00-\u9fa5]*$");
		Matcher matcher = pa.matcher(s);
		return matcher.find(); // true为全部是汉字，否则是false
	}

	private String get_StringNum(String s) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);

		return m.replaceAll("").trim();

	}
}
