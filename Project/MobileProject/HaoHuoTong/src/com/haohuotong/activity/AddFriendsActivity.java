package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.ab.task.AbThread;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.ab.view.titlebar.AbTitleBar;
import com.chat.activity.GetMsgService;
import com.haohuotong.R;
import com.haohuotong.adapter.AuctionInfoAdapter;
import com.haohuotong.entity.AuctionInfo;
import com.haohuotong.entity.LogisticsCompanyInfo;
import com.haohuotong.entity.LogisticsUserInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.global.MyApplication;
import com.haohuotong.web.PublicInfoWeb;
import com.haohuotong.web.UserInfoWeb;
import com.way.chat.common.bean.TextMessage;
import com.way.chat.common.tran.bean.TranObject;
import com.way.chat.common.tran.bean.TranObjectType;
import com.way.client.ClientOutputThread;

public class AddFriendsActivity extends AbActivity {

	private AbPullListView listView = null;
	private Button comp = null;
	private EditText edt = null;
	private AbTaskQueue mAbTaskQueue = null;
	private AuctionInfoAdapter acAdapter = null;
	private MyApplication application = null;
	private ArrayList<Parameter> driverCarParameterList = null;
	private List<AuctionInfo> mAuctionInfoList = null;
	private List<AuctionInfo> newAuctionInfoList = null;
//	private List<AuctionInfo> auctionInfoList = null;
	private boolean isAuction = false;
	private int carInfoId = 0 ;
    private int money = 0 ;
    private int currentPage = 1;//��ҳ
    private int infoAddErID = 0;
    private int infoLPRID = 0;
    private ImageView img ,ZhiZhaoImg,OfficeImg;
    private TextView tName,comName,Jianjie,comAddr,PJ;
    private LogisticsUserInfo logisticsUserInfo = null;
    private LogisticsCompanyInfo logisticsCompanyInfo = null;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.add_friends);
		initTitleLayout();
		infoAddErID = getIntent().getIntExtra("info_AddErID", 0);
		infoLPRID = getIntent().getIntExtra("info_LPR_ID", 0);
		application = (MyApplication) abApplication;
		mAbTaskQueue = AbTaskQueue.getInstance();
		
		initView();
    }
	
	/**
	 * ��������ʼ��������
	 * 
	 * @throws
	 * @date��2013-4-25 ����10:21:18
	 * @version v1.0
	 */
	private void initTitleLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();

		// �����಼���ļ�
		mAbTitleBar.setTitleText("��Ӻ���");
		mAbTitleBar.setTitleBarGravity(Gravity.CENTER, Gravity.RIGHT);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
//		mAbTitleBar.setTitleTextMargin(50, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		
	}
	
	/*
	 * ��ʼ���ؼ�
	 */
	String str = "������������Ƽ����޹�˾λ�������о��ü����������������԰����һ�Ҵ�������������������ĸ��¿Ƽ���ҵ���������������ص������ҵ";
	private void initView(){
		img = (ImageView) findViewById(R.id.img);
		ZhiZhaoImg = (ImageView) findViewById(R.id.yingyezhizhao);
		OfficeImg = (ImageView) findViewById(R.id.office_photo);
		tName = (TextView) findViewById(R.id.true_name);
		comName = (TextView) findViewById(R.id.com_name);
		Jianjie = (TextView) findViewById(R.id.com_jianjie);
		comAddr = (TextView) findViewById(R.id.com_addr);
		PJ = (TextView) findViewById(R.id.pingjia);
		
		ZhiZhaoImg.setImageResource(R.drawable.yingye);
		OfficeImg.setImageResource(R.drawable.office);
		Jianjie.setText(str);
		tName.setText("��������Ƽ�");
		comName.setText("��������Ƽ�");
		comAddr.setText("�����Ͻ� ����IBI�������� 602");
		PJ.setText("�ޣ�����");
//		getLogisticsCompanyInfo();
//		getLogisticsUserInfo();
		
		
	}
	
	
	public void getLogisticsCompanyInfo(){
		driverCarParameterList = new ArrayList<Parameter>();
		// �����첽ִ�еĶ���
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			//@Override
			public void update() {
				
			}

			@Override
			public void get() {
				setDdriverCarParameterList("goods_info_id",  carInfoId+"");
				try {
					logisticsCompanyInfo=UserInfoWeb.getLogisticsCompanyInfo("", driverCarParameterList);
				} catch (AbAppException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		};
		
		
		// ��ʼִ��
		mAbTaskQueue.execute(item);
		
	}
	
	public void getLogisticsUserInfo(){
		driverCarParameterList = new ArrayList<Parameter>();
		AbThread mAbTaskThread = new AbThread();
		// �����첽ִ�еĶ���
		final AbTaskItem item3 = new AbTaskItem();
		item3.listener = new AbTaskListener() {
  
			@Override
			public void update() {
				if (logisticsUserInfo != null) {
//					showToastInThread("������Ϣ���ͳɹ���");
				} else {
//					showToastInThread("������Ϣ����ʧ�ܣ�");
				}
			}

			@Override
			public void get() {
				try {
					setDdriverCarParameterList("goods_info_id",  carInfoId+"");
					logisticsUserInfo=UserInfoWeb.getLogisticsUserInfo("", driverCarParameterList);

				} catch (AbAppException e) {
					e.printStackTrace();
				}
			};
		};
		// ��ʼִ��
		mAbTaskThread.execute(item3);
	}

    private void setDdriverCarParameterList(String key, String value) {
		Parameter parameter = null;
		parameter = new Parameter();
		parameter.setParameterKey(key);
		parameter.setParameterValue(value);
		driverCarParameterList.add(parameter);
    }
    
    public void addFri(View view){
    	loadSubmitData();
    	Toast.makeText(this, "������ӳɹ�", 5).show();
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
					message.setMessage("2017�������Ϊ���ѣ���鿴��");
					o.setObject(message);
					o.setFromUser(2017);
					o.setToUser(2016);
//					out.setMsg(o);
					out.setMsg("��������3");
				}
			};
		};
		// ��ʼִ��
		mAbTaskThread.execute(item);
    }
    
}