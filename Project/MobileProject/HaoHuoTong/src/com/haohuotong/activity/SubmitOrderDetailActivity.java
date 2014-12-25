package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.task.AbThread;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.titlebar.AbTitleBar;
import com.chat.activity.GetMsgService;
import com.haohuotong.R;
import com.haohuotong.adapter.UserAuctionAdapter;
import com.haohuotong.entity.AuctionInfo;
import com.haohuotong.entity.DriverCarInfo;
import com.haohuotong.entity.DriverUserInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.entity.UserInfo;
import com.haohuotong.global.MyApplication;
import com.haohuotong.web.PublicInfoWeb;
import com.way.chat.common.bean.TextMessage;
import com.way.chat.common.bean.User;
import com.way.chat.common.tran.bean.TranObject;
import com.way.chat.common.tran.bean.TranObjectType;
import com.way.client.Client;
import com.way.client.ClientOutputThread;
import com.way.util.Encode;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name��SubmitOrderDetailActivity.java
 * @Describe������ȷ������
 * @Author FaRong����yfr5734@gmail.com
 * @Date��2013-12-13 ����4:07:53
 * @Version v1.0
 */
public class SubmitOrderDetailActivity extends AbActivity implements OnClickListener {
	private ListView listView = null;
//	private TextView driverName,driverPhone,driverLevel,driverPJ;
//	private TextView carType,carMax,carKey,carUnit;
	private TextView cartypeText, carLengthText, carHightText, volumeText, maxLoadText, freightStateText;
	private TextView engineNumText, chejiaNumText, operationNumText, insuranceNumText, guakaodanwText;
	private TextView userNameText, personalMobileText, suicheMobileText, identityCardText, jinjiMobileText;
	private TextView driverSexText, familyAddressText, qiwangliuxiangText, jiashizhizhaoText, carNumText,biddingMoneyText;
	private TextView driverLevelText, driverPraiseText;
	private ImageView docImage = null;
	private Button consult = null,winning = null;
	private EditText remark;
	private Button qusitinInfo, actionListBut;
	private MyApplication application = null;
	private AbTaskQueue mAbTaskQueue = null;
	private AbThread mAbTaskThread = null;
	private List<Parameter> getParameterList = null;
    private int UserId = 0;
    private int AuctionId = 0;
    private double moneyValue = 0;
    private String remarkStr = null;
    private DriverCarInfo driverCarInfo = new DriverCarInfo();
    private DriverUserInfo driverUserInfo = new DriverUserInfo();
	private AlertDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.order_detail_list);
        UserId = getIntent().getIntExtra("User_id", 0);
        Log.i("tag", "------------------------>UserId = "+UserId);
        AuctionId = getIntent().getIntExtra("Add_id", 0);
        moneyValue = getIntent().getDoubleExtra("Money_value", 0);
		// ��ȡIntent���ݵ�����
		initTitleRightLayout();
		initView();
		//�������뷨
		final InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);       
		imm.hideSoftInputFromWindow(remark.getWindowToken(), 0);

	}

	/**
	 * 
	 * ��������ʼ��������
	 * 
	 * @throws
	 * @date��2013-11-13 ����11:13:10
	 * @version v1.0
	 */
	private void initTitleRightLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText("�����û�����");
		// if (getIntentType == 0) {
		// mAbTitleBar.setTitleText("������Ϣ");
		// } else {
		// mAbTitleBar.setTitleText("�Ƽ���Ϣ");
		// }

		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) abApplication;
	}
	
	/**
	 * 
	 * ��������ʼ��View
	 * 
	 * @throws
	 * @date��2013-11-13 ����10:21:24
	 * @version v1.0
	 */
	private void initView() {
		mAbTaskQueue = AbTaskQueue.getInstance();
		getDriverUserInfoData();
		getDriverCarInfoData();
		docImage = (ImageView) findViewById(R.id.docImage);

		userNameText = (TextView) findViewById(R.id.userNameText);
		personalMobileText = (TextView) findViewById(R.id.personalMobileText);
//		suicheMobileText = (TextView) findViewById(R.id.suicheMobileEdit);
		identityCardText = (TextView) findViewById(R.id.identityCardText);
//		jinjiMobileText = (TextView) findViewById(R.id.jinjiMobileEdit);
		driverSexText = (TextView) findViewById(R.id.driverSexText);
		familyAddressText = (TextView) findViewById(R.id.familyAddressText);
		qiwangliuxiangText = (TextView) findViewById(R.id.qiwangliuxiangText);//��������
		jiashizhizhaoText = (TextView) findViewById(R.id.jiashizhizhaoText);
		driverLevelText = (TextView) findViewById(R.id.driver_levelText);
		driverPraiseText = (TextView) findViewById(R.id.driver_praiseText);
		biddingMoneyText = (TextView) findViewById(R.id.biddingMoneyText);
		carNumText = (TextView) findViewById(R.id.carNumText);
		cartypeText = (TextView) findViewById(R.id.cartypeText);
		carLengthText = (TextView) findViewById(R.id.carLengthText);
		carHightText = (TextView) findViewById(R.id.carHightText);
		volumeText = (TextView) findViewById(R.id.volumeText);
		maxLoadText = (TextView) findViewById(R.id.maxLoadText);
		freightStateText = (TextView) findViewById(R.id.freightStateText);
		engineNumText = (TextView) findViewById(R.id.engineNumText);
		chejiaNumText = (TextView) findViewById(R.id.chejiaNumText);
		operationNumText = (TextView) findViewById(R.id.operationNumText);
		insuranceNumText = (TextView) findViewById(R.id.insuranceNumText);
		guakaodanwText = (TextView) findViewById(R.id.guakaodanwText);
        
		remark = (EditText) findViewById(R.id.remark);
        consult = (Button) findViewById(R.id.consult);
		winning = (Button) findViewById(R.id.winingBut);
		consult.setOnClickListener(this);
		winning.setOnClickListener(this);
	}
//	/**
//	 * 
//	 * ��������ʼ��
//	 * 
//	 * @throws
//	 * @date��2013-11-13 ����11:11:52
//	 * @version v1.0
//	 */
//	private void initView() {
//		mAbTaskQueue = AbTaskQueue.getInstance();
//		mAbTaskThread = new AbThread();
//		driverName = (TextView) findViewById(R.id.driverName);
//		driverPhone = (TextView) findViewById(R.id.driverPhone);
//		driverLevel = (TextView) findViewById(R.id.driverLevel);
//		driverPJ = (TextView) findViewById(R.id.driverPJ);
//		
//		carType = (TextView) findViewById(R.id.carType);
//		carMax = (TextView) findViewById(R.id.carmax);
//		carKey = (TextView) findViewById(R.id.carKey);
//		carUnit = (TextView) findViewById(R.id.carUnit);
//		
//		remark = (EditText) findViewById(R.id.remark);
//		
//		actionListBut = (Button) findViewById(R.id.actionListBut);
//		qusitinInfo = (Button) findViewById(R.id.updateInfo);
//		
//		actionListBut.setOnClickListener(this);
//		qusitinInfo.setOnClickListener(this);
//		
//		getDriverUserInfoData();
//		getDriverCarInfoData();
//	}
//	
	public void setData(){
		userNameText.setText(driverUserInfo.getDuser_name());
		personalMobileText.setText(driverUserInfo.getDuser_mobile());
//		suicheMobileText.setText(info.getDriverUserInfo().getDuser_tle());
		identityCardText.setText(driverUserInfo.getDuser_key());
//		jinjiMobileText.setText(info.getDriverUserInfo().getDuser_tleJI());
		driverSexText.setText(driverUserInfo.getDuser_sex());
		familyAddressText.setText(driverUserInfo.getDuser_Addr());
		qiwangliuxiangText.setText(driverUserInfo.getRoutePath().substring(0, driverUserInfo.getRoutePath().indexOf("|")));
		jiashizhizhaoText.setText(driverUserInfo.getDuser_J_key());
		driverLevelText.setText(driverUserInfo.getDuser_level());
		driverPraiseText.setText(driverUserInfo.getDuser_PJ());
		biddingMoneyText.setText(this.moneyValue+"");
		carNumText.setText(driverCarInfo.getCar_key());
		cartypeText.setText(driverCarInfo.getCar_type());
		carLengthText.setText(driverCarInfo.getCar_length());
		carHightText.setText(driverCarInfo.getCar_height());
		volumeText.setText(driverCarInfo.getCar_bulk());
		maxLoadText.setText(driverCarInfo.getCar_max_dun());
		// freightStateEdit.setText(info.getDriverCarInfo().get); //��δ����ֶ�
		engineNumText.setText(driverCarInfo.getCar_Fkey());
		chejiaNumText.setText(driverCarInfo.getCar_Jkey());
		operationNumText.setText(driverCarInfo.getCar_Ykey());
		insuranceNumText.setText(driverCarInfo.getCar_Bkey());
		guakaodanwText.setText(driverCarInfo.getCar_Gunit());
	}

	/**
	 * 
	 * ��������ȡ˾����Ϣ����
	 * 
	 * @throws
	 * @date��2013-11-13 ����1:47:25
	 * @version v1.0
	 */
	public void getDriverUserInfoData() {
		getParameterList = new ArrayList<Parameter>();
		// �������ֲ�ѯ���¼�\
		
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				setData();
			}

			@Override
			public void get() {
				try {
					setParameterList("userid",String.valueOf(UserId));
					driverUserInfo = new DriverUserInfo();
					driverUserInfo = PublicInfoWeb.DriverUserInfoDetail("I_A005", getParameterList);

				} catch (Exception e) {					
				}
			};
		};

		// ��һ����������
		mAbTaskQueue.execute(item);
	}
	
	/**
	 * 
	 * ��������ȡ������Ϣ����
	 * 
	 * @throws
	 * @date��2013-11-13 ����1:47:25
	 * @version v1.0
	 */
	public void getDriverCarInfoData() {
		getParameterList = new ArrayList<Parameter>();
		// �������ֲ�ѯ���¼�\

		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				setData();
			}

			@Override
			public void get() {
				try {
					setParameterList("userid", String.valueOf(UserId));
					driverCarInfo = new DriverCarInfo();
					driverCarInfo = PublicInfoWeb.DriverCarInfoDetail("I_A004", getParameterList);

				} catch (Exception e) {					
				}
			};
		};

		// ��һ����������
		mAbTaskQueue.execute(item);
	}
	
	private void setParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		getParameterList.add(parameter);
		// 13895479167
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.consult:
			finish();
			break;
		case R.id.winingBut:
//			remarkStr = remark.getText().toString().trim();
//			remark.setText("");
			showMyDialog();
//			startActivity(new Intent(SubmitOrderDetailActivity.this, PublicVehicleInfoActivity.class));
			break;
		}
	}
	
	/**
	 * 
	 * ��������ʾ��ʾ�Ի���
	 * 
	 * @throws
	 * @date��2013-11-19 ����10:36:52
	 * @version v1.0
	 */
	private void showMyDialog() {
		remarkStr = remark.getText().toString().trim();
		Builder builder = new AlertDialog.Builder(this);
		dialog = builder.create();
		View retieve = LayoutInflater.from(this).inflate(R.layout.dialog_show_order, null);
		dialog.setView(retieve, 0, 0, 0, 0);
		Button acceptBtn = (Button) retieve.findViewById(R.id.acceptBtn);
		Button unAcceptBtn = (Button) retieve.findViewById(R.id.unAcceptBtn);
		TextView dialogTitleText1 = (TextView) retieve.findViewById(R.id.dialogTitleText1);
		TextView winId = (TextView) retieve.findViewById(R.id.WinId);
		TextView winName = (TextView) retieve.findViewById(R.id.WinName);
		TextView winIDCard = (TextView) retieve.findViewById(R.id.WinCard);
		TextView winJiaShiZhiZhao = (TextView) retieve.findViewById(R.id.Win_J_id);
		TextView winCarCard = (TextView) retieve.findViewById(R.id.Win_car_Card);
		TextView money = (TextView) retieve.findViewById(R.id.moeny);
		TextView remarkTxt = (TextView) retieve.findViewById(R.id.remark_view);
		dialogTitleText1.setText("�����������б���Ϣ");
		winId.setText("�б�ţ�"+this.AuctionId);
		winName.setText("�б��ˣ�"+driverUserInfo.getDuser_name());
		winIDCard.setText("���֤�ţ�"+driverUserInfo.getDuser_key());
		winJiaShiZhiZhao.setText("��ʻִ�գ�"+driverUserInfo.getDuser_J_key());
		winCarCard.setText("���ƺţ�"+driverCarInfo.getCar_key());
		money.setText("���ۣ�"+this.moneyValue);
		remarkTxt.setText("��ע��Ϣ��"+this.remarkStr);
		acceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				remark.setText("");
				loadSubmitData();
				Toast.makeText(SubmitOrderDetailActivity.this,"�б�", Toast.LENGTH_LONG).show();
				dialog.dismiss();
			}
		});

		unAcceptBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});
		dialog.show();
	}
	
	/**
	 * �ύ�˺�������Ϣ��������
	 */
	private void submit() {
		
	}

    public void loadSubmitData(){
		
		AbThread mAbTaskThread = new AbThread();
		// �����첽ִ�еĶ���
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
			}

			@Override
			public void get() {
				ClientOutputThread out = GetMsgService.client.getClientOutputThread();
				if (out != null) {
					TranObject<TextMessage> o = new TranObject<TextMessage>(
							TranObjectType.AUCTIONINFO);
					TextMessage message = new TextMessage();
					message.setMessage("�����б���Ϣ����鿴��");
					o.setObject(message);
					o.setFromUser(2016);
					o.setToUser(2017);
//					out.setMsg(o);
					out.setMsg("��������2");
				}
			};
		};
		// ��ʼִ��
		mAbTaskThread.execute(item);
    }
}
