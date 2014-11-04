package com.gohome.activity;

import java.util.ArrayList;
import java.util.List;

import com.gohome.R;
import com.gohome.view.MyImgScroll;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name��MainActivity.java
 * @Describe��������ҳ
 * @Author: yfr5734@gmail.com
 * @Date��2014��7��22�� ����10:09:15
 * @Version v1.0
 */
public class MainActivity extends BaseActivity implements OnClickListener {
	private Button ghBut, qgBut, fbBut, ssBut, zxBut, gdBut;
	private MyImgScroll myPager; // ͼƬ����
	private LinearLayout ovalLayout; // Բ������
	private List<View> listViews; // ͼƬ��
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private AlertDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.main);
		titleBarInitView();
		initView();

//		// �����û�������Ϣ
//		if (application.userInfo == null) {
//			makeData();
//		}
	}

	/**
	 * 
	 * @Describe����ʼ��������
	 * @Throws:
	 * @Date��2014��7��24�� ����9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo("��    ҳ", isShowLeftBut, "�˳�", isShowRightBut, "����");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					showMyDialog();
					showToast("�˳�");
				}
			});
		}

		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					showToast("����");
//					if (application.isLogin) {
//						startActivity(new Intent(MainActivity.this, PersonalCenterActivity.class));
//					} else {
//						startActivity(new Intent(MainActivity.this, LoginActivity.class));
//					}
				}
			});
		}
	}

	/**
	 * 
	 * @Describe����ʼ��View
	 * @Throws:
	 * @Date��2014��4��28�� ����11:07:32
	 * @Version v1.0
	 */
	private void initView() {

		ghBut = (Button) findViewById(R.id.ghBut);
		qgBut = (Button) findViewById(R.id.qgBut);
		fbBut = (Button) findViewById(R.id.fbBut);
		ssBut = (Button) findViewById(R.id.ssBut);
		zxBut = (Button) findViewById(R.id.zxBut);
		gdBut = (Button) findViewById(R.id.gdBut);

		titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
		myPager = (MyImgScroll) findViewById(R.id.myvp);
		ovalLayout = (LinearLayout) findViewById(R.id.vb);

		ghBut.setOnClickListener(this);
		qgBut.setOnClickListener(this);
		fbBut.setOnClickListener(this);
		ssBut.setOnClickListener(this);
		zxBut.setOnClickListener(this);
		gdBut.setOnClickListener(this);

		// ��ʼ��ͼƬ
		InitViewPager();
		// ��ʼ����
		myPager.start(this, listViews, 4000, ovalLayout, R.layout.ad_bottom_item, R.id.ad_item_v, R.drawable.dot_focused, R.drawable.dot_normal);

	}

	/**
	 * @Describe����ʼ�����ͼƬ
	 * @Throws:
	 * @Date��2014��4��28�� ����3:29:10
	 * @Version v1.0
	 */
	private void InitViewPager() {
		listViews = new ArrayList<View>();
		int[] imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
		for (int i = 0; i < imageResId.length; i++) {
			final int imageItem = i;
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			// ����ͼƬ����¼�
//			imageView.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					Intent intent = new Intent();
//					switch (imageItem) {
//					case 0:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						// intent.putExtra("url", "http://dl.5671.cc/");
//						intent.putExtra("url", "http://www.nxgtjt.com");
//						startActivity(intent);
//						break;
//					case 1:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						intent.putExtra("url", "http://www.baosteel.com/group/index.htm");
//						startActivity(intent);
//						break;
//					case 2:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						intent.putExtra("url", "http://www.gtxh.com");
//						startActivity(intent);
//						break;
//					case 3:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						intent.putExtra("url", "http://www.custeel.com");
//						startActivity(intent);
//						break;
//					case 4:
//						intent.setClass(MainActivity.this, MyWebView.class);
//						intent.putExtra("url", "http://www.csteelnews.com");
//						startActivity(intent);
//						break;
//					}
//					Toast.makeText(MainActivity.this, "������˵�" + (myPager.getCurIndex() + 1) + "�����λ", Toast.LENGTH_SHORT).show();
//				}
//			});

			imageView.setScaleType(ScaleType.CENTER_CROP);
			listViews.add(imageView);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ghBut:
			startActivity(new Intent(MainActivity.this, LoginActivity.class));
			showToast("��תҳ��_01");
			break;
		case R.id.qgBut:
			startActivity(new Intent(MainActivity.this, RegisterActivity.class));
			showToast("��תҳ��_02");
			break;
		case R.id.fbBut:
//			startActivity(new Intent(MainActivity.this, PublishSupplyActivity.class));
			showToast("��תҳ��_03");
			break;
		case R.id.ssBut:
//			startActivity(new Intent(MainActivity.this, SearchActivity.class));
			showToast("��תҳ��_04");
			break;
		case R.id.zxBut:
//			startActivity(new Intent(MainActivity.this, RelevantInfoActivity.class));
			showToast("��תҳ��_05");
			break;
		case R.id.gdBut:
//			startActivity(new Intent(MainActivity.this, MoreActivity.class));
			showToast("��תҳ��_06");
			break;
		}
	}

	/**
	 * 
	 * @Describe����������
	 * @Throws:
	 * @Date��2014��8��1�� ����1:33:36
	 * @Version v1.0
	 */
//	private void makeData() {
//		UserInfo userInfo = new UserInfo();
//
//		userInfo.setUserName("yfr5734");
//		userInfo.setGrade("��ͨ��Ա");
//		userInfo.setLasttime("2014.08.30");
//
//		userInfo.setRealName("������");
//		userInfo.setIdentityCard("640321198603153879");
//		userInfo.setSex("��");
//		userInfo.setAge("26");
//		userInfo.setMobile("13800000001");
//		userInfo.setTelephone("0951-5321564");
//		userInfo.setEmail("1542956428@qq.com");
//		userInfo.setIndustry("���Ƹ�");
//		userInfo.setAddress("��������");
//		userInfo.setCompanyInfo("��˾������1998�꣬רע������ҵ���꣬��һ������ҵ���࣡");
//
//		application.userInfo = userInfo;
//	}

}
