package com.haohuotong.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.haohuotong.R;
import com.haohuotong.gps.Coordinate;
import com.haohuotong.gps.GPSLocationListener;
import com.haohuotong.gps.GPSManager;
import com.haohuotong.util.MethodUtil;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * @Name��ContactsActivity.java 
 * @Describe��������ϵ��
 * @Author Administrator  yfr5734@gmail.com
 * @Date��2013-11-29 ����3:24:06
 * @Version v1.0
 */
public class ContactsActivity extends Activity {
	private GPSManager gpsManager;
	private Coordinate coordinate;
	private AlertDialog dialog;
	private String action = null;
	public int INTENTWIFI = 1;
	private int INTENTGPS = 2;
	private AudioManager audioManager = null;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(ContactsActivity.this, msg.getData().getString("Msg"), 5).show();
				break;
			case 1:
				coordinate = gpsManager.getCoordinate();
				Toast.makeText(ContactsActivity.this, "GPSλ��λ����Ϣ��\n����Ϊ��" + coordinate.Longitude + "\nά��Ϊ��" + coordinate.Latitude, 5).show();
				System.out.println("GPSλ��λ����Ϣ��\n����Ϊ��" + coordinate.Longitude + "\nά��Ϊ��" + coordinate.Latitude);
				startActivity(new Intent(ContactsActivity.this, MainActivity.class));
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lunch);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		/**
		 * �����̼߳���
		 */
		// new Thread() {
		// @Override
		// public void run() {
		// // super.run();
		//
		// try {
		// sleep(3000);
		//
		// // ���GPS�����Ƿ���
		// if (!MethodUtil.isGpsEnable(LunchActivity.this)) {
		// handler.sendEmptyMessage(2);
		// return;
		// }
		//
		// // �������
		// if (!MethodUtil.getNetworkState(LunchActivity.this)) {
		// handler.sendEmptyMessage(1);
		// return;
		// }
		// // handler.sendEmptyMessage(0);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }.start();

		// �������
		if (MethodUtil.getNetworkState(ContactsActivity.this)) {

			// ���GPS����
//			boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER);
//			if (gpsEnabled) {
//				Toast.makeText(LunchActivity.this, "GPS�Ѵ����ڹرգ�", 5).show();
//				Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, false);
//				Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, false);
//			} else {
//				Toast.makeText(LunchActivity.this, "GPS�ѹر����ڴ򿪣�", 5).show();
//				Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);
//			}
			 if (MethodUtil.isGpsEnable(ContactsActivity.this)) {
			 checkIntent();
			 } else {
			 showMyDialog(2);
			 }
		} else {
			showMyDialog(1);
		}
	}

	private void checkIntent() {
		gpsManager = GPSManager.getGPSManagerInstance();
		gpsManager.registerGPS(this, new GPSLocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				// ��ȡLocation����
			}
		});

		// ����Ƿ�Ϊ����ģʽ
		if (audioManager.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			// ������ģʽ
			audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}

		/**
		 * �����̼߳���
		 */
		new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					sleep(3000);
					sendMsgUpdateUI(1, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	/**
	 * 
	 * ��������ʾ��ʾ�Ի���
	 * 
	 * @throws
	 * @date��2013-11-19 ����10:36:52
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
		dialogTitleText1.setText("��ܰ��ʾ");

		if (connectType == 1) {
			titleInfo = "����δ�������磬���������磡";
			action = Settings.ACTION_WIRELESS_SETTINGS;
		} else if (connectType == 2) {
			titleInfo = "�������Ҫ������λ���ܣ��Ա�Ϊ���ҳ����һ���������GPS���������޷�ʹ�ñ������ף��������¡��";
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
			if (MethodUtil.isGpsEnable(ContactsActivity.this)) {
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
					// ��ȡLocation����
				}
			});
			sendMsgUpdateUI(1, null);
			break;

		default:
			break;
		}
	}

	/**
	 * ������������Ϣˢ��UI ��һ������0
	 * 
	 * @param what
	 * @param toast
	 * @throws
	 * @date��2013-11-19 ����1:28:08
	 * @version v1.0
	 */
	public void sendMsgUpdateUI(int what, String toast) {
		Message msg = handler.obtainMessage(what);
		Bundle bundle = new Bundle();
		bundle.putString("Msg", toast);
		msg.setData(bundle);
		handler.sendMessage(msg);
	}
}
