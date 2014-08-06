package com.steellogistics.activity;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.location.Location;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.steellogistics.R;
import com.steellogistics.global.Constant;
import com.steellogistics.global.MyApplication;
import com.steellogistics.gps.Coordinate;
import com.steellogistics.gps.GPSLocationListener;
import com.steellogistics.gps.GPSManager;
import com.steellogistics.util.MethodUtil;

/**
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：LunchActivity.java
 * @Describe：程序启动页面
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午2:47:29
 * @Version v1.0
 */
public class LunchActivity extends Activity {
	private GPSManager gpsManager;
	private Coordinate coordinate;
	private AlertDialog dialog;
	private MyApplication application = null;
	private String action = null;
	public int INTENTWIFI = 1;
	private int INTENTGPS = 2;
	private Dialog mDialog;
	private AudioManager audioManager = null;
	private Dialog mProgressDialog;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(LunchActivity.this, msg.getData().getString("Msg"), 5).show();
				break;
			case 1:
				coordinate = gpsManager.getCoordinate();

				Toast.makeText(LunchActivity.this, "GPS位置位置信息：\n经度为：" + "" + "" + "" + "" + coordinate.Longitude + "\n维度为：" + coordinate.Latitude, 5).show();
				System.out.println("GPS位置位置信息：\n经度为：" + coordinate.Longitude + "\n维度为：" + coordinate.Latitude);

				// startActivity(new Intent(LunchActivity.this,
				// MainActivity.class));

				Intent intent = new Intent(LunchActivity.this, MainActivity.class);
				intent.putExtra("longitude", coordinate.Longitude);
				intent.putExtra("latitude", coordinate.Latitude);
				startActivity(intent);
				finish();
				break;
			case 2:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉任务栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.lunch);
		// showRoundProcessDialog(this, R.layout.loading_process_dialog_anim);
		mProgressDialog = new Dialog(LunchActivity.this, R.style.theme_dialog_alert);
		mProgressDialog.setContentView(R.layout.window_layout);
		mProgressDialog.setCancelable(true);
		mProgressDialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface dialog) {
				LunchActivity.this.finish();
			}
		});
		mProgressDialog.show();

		application = (MyApplication) getApplication();
		application.isBoot = true;
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		// 检测网络
		if (MethodUtil.getNetworkState(LunchActivity.this)) {

			// 暂时屏蔽GPS检测
			// if (MethodUtil.isGpsEnable(LunchActivity.this)) {
			// checkIntent();
			// } else {
			// showMyDialog(2);
			// }

			checkIntent();
		} else {
			showMyDialog(1);
		}
	}

	public void showRoundProcessDialog(Context mContext, int layout) {
		OnKeyListener keyListener = new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_SEARCH) {
					return true;
				}
				return false;
			}
		};

		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.setOnKeyListener(keyListener);
		mDialog.show();
		// 注意此处要放在show之后 否则会报异常
		mDialog.setContentView(layout);
	}

	public boolean isExist() {
		boolean ret = false;
		// 检测数据库是否存在
		File f = new File(Constant.dbPath);
		if (!f.exists()) {
			ret = MethodUtil.importDatabase(LunchActivity.this, f);
			if (ret) {
				// sendMsgUpdateUI(1,"数据库加载成功...");
			} else {
				sendMsgUpdateUI(0, "数据库加载失败!");
				finish();
			}
		} else {
			// sendMsgUpdateUI(1,"数据库加载成功...");
		}
		return ret;
	}

	private void checkIntent() {
		gpsManager = GPSManager.getGPSManagerInstance();
		gpsManager.registerGPS(this, new GPSLocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				// 获取Location监听
			}
		});

		// 检测是否为静音模式
		if (audioManager.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			// 打开声音模式
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}

		/**
		 * 启动线程加载
		 */
		new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					sleep(3000);

					// 检测数据库是否存在
					File f = new File(Constant.dbPath);
					if (!f.exists()) {
						boolean ret = MethodUtil.importDatabase(LunchActivity.this, f);
						if (ret) {
							// sendMsgUpdateUI(1,"数据库加载成功...");
						} else {
							sendMsgUpdateUI(0, "数据库加载失败!");
							finish();
						}
					} else {
						// sendMsgUpdateUI(1,"数据库加载成功...");
					}
					// 检测数据库是否存在
					// boolean b = isExist();
					// System.out.println(b);

					sendMsgUpdateUI(1, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * 
	 * 描述：显示提示对话框
	 * 
	 * @throws
	 * @date：2013-11-19 上午10:36:52
	 * @version v1.0
	 */
	private void showMyDialog(int unConnectType) {
		final int connectType = unConnectType;
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

		if (connectType == 1) {
			titleInfo = "您还未开启网络，请设置网络！";
			action = Settings.ACTION_WIRELESS_SETTINGS;
		} else if (connectType == 2) {
			titleInfo = "本软件需要开启定位功能，以便为您找车或找货，不开启GPS功能您将无法使用本软件，祝您生意兴隆！";
			action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
		}
		setMessage.setText(titleInfo);
		acceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				try {
					intent.setAction(action);
					startActivityForResult(intent, connectType);
				} catch (ActivityNotFoundException ex) {
					intent.setAction(Settings.ACTION_SETTINGS);
					try {
						startActivity(intent);
					} catch (Exception e) {
					}
				}
			}
		});

		unAcceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				finish();
			}
		});
		dialog.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (MethodUtil.isGpsEnable(LunchActivity.this)) {
				checkIntent();
			} else {
				showMyDialog(2);
			}
			break;
		case 2:
			gpsManager = GPSManager.getGPSManagerInstance();
			gpsManager.registerGPS(this, new GPSLocationListener() {

				@Override
				public void onLocationChanged(Location location) {
					// 获取Location监听
				}
			});
			sendMsgUpdateUI(1, null);
			break;

		default:
			break;
		}
	}

	/**
	 * 描述：发送消息刷新UI 第一参数是0
	 * 
	 * @param what
	 * @param toast
	 * @throws
	 * @date：2013-11-19 下午1:28:08
	 * @version v1.0
	 */
	public void sendMsgUpdateUI(int what, String toast) {
		Message msg = handler.obtainMessage(what);
		Bundle bundle = new Bundle();
		bundle.putString("Msg", toast);
		msg.setData(bundle);
		handler.sendMessage(msg);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mProgressDialog.dismiss();
	}
}
