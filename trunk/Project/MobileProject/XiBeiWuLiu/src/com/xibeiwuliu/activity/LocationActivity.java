package com.xibeiwuliu.activity;

import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.xibeiwuliu.adapter.MyLocationAdapter;
import com.xibeiwuliu.database.SqliteDaoArea;
import com.xibeiwuliu.entity.AreaInfo;

public class LocationActivity extends BaseActivity implements OnClickListener {
	private SqliteDaoArea daoArea = null;
	private String getMsg = "选择城市";
	private boolean isShowRightBut = false;
	private GridView gridView = null;
	private List<AreaInfo> strList = null;
	private Button sfBtn, shiBtn, qxBtn;
	private int provinceNameId = 0;
	private int cityNameId = 0;
	private int countyNameId = 0;
	public Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(LocationActivity.this, msg.getData().getString("Msg"), 5).show();
				break;
			case 1:
				setView1();
				break;
			case 2:
				setView2();
				break;
			case 3:
				setView3();
				break;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.location_view);
		// getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
		initView();
		// getData(provinceNameId);
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年5月6日 下午3:45:47
	 * @Version v1.0
	 * 
	 */
	private void initView() {
		daoArea = SqliteDaoArea.getInstance(LocationActivity.this);
		sfBtn = (Button) findViewById(R.id.sfBtn);
		shiBtn = (Button) findViewById(R.id.shiBtn);
		qxBtn = (Button) findViewById(R.id.qxBtn);
		gridView = (GridView) findViewById(R.id.gridview);

		sfBtn.setOnClickListener(this);
		shiBtn.setOnClickListener(this);
		qxBtn.setOnClickListener(this);
	}

	/**
	 * 
	 * @Describe：加载数据
	 * @Throws:
	 * @Date：2014年5月6日 下午4:02:20
	 * @Version v1.0
	 * 
	 */
	private void getData(int cityId, int butSelect) {
		final int getCityId = cityId;
		final int selectId = butSelect;
		new Thread() {
			@Override
			public void run() {
				strList = daoArea.getAreaInfo(getCityId);
				if (strList.size() > 0 && strList != null) {
					if (selectId == 1) {
						sendMyMsgUpdateUI(1, null);
					}else if (selectId == 2) {
						sendMyMsgUpdateUI(2, null);
					}else if (selectId == 3) {
						sendMyMsgUpdateUI(3, null);
					}
				}
			}
		}.start();
	}

	/**
	 * 
	 * @Describe： 加载数据页面
	 * @Throws:
	 * @Date：2014年5月6日 下午3:58:37
	 * @Version v1.0
	 * 
	 */
	private void setView1() {
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		MyLocationAdapter locationAdapter = new MyLocationAdapter(this, strList);
		gridView.setAdapter(locationAdapter);
		gridView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(LocationActivity.this, strList.get(position).getCcityName(), 5).show();
				cityNameId = strList.get(position).getCcityId();
				shiBtn.setBackgroundColor(getResources().getColor(R.color.green));
				sfBtn.setBackgroundColor(getResources().getColor(R.color.white));
				qxBtn.setBackgroundColor(getResources().getColor(R.color.white));
				getData(cityNameId, 2);
			}
		});
	}

	private void setView2() {
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		MyLocationAdapter locationAdapter = new MyLocationAdapter(this, strList);
		gridView.setAdapter(locationAdapter);
		gridView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(LocationActivity.this, strList.get(position).getCcityName(), 5).show();
				cityNameId = strList.get(position).getCcityId();
				qxBtn.setBackgroundColor(getResources().getColor(R.color.green));
				shiBtn.setBackgroundColor(getResources().getColor(R.color.white));
				sfBtn.setBackgroundColor(getResources().getColor(R.color.white));
				getData(cityNameId, 3);
			}
		});
	}

	private void setView3() {
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		MyLocationAdapter locationAdapter = new MyLocationAdapter(this, strList);
		gridView.setAdapter(locationAdapter);
		gridView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(LocationActivity.this, strList.get(position).getCcityName(), 5).show();
//				cityNameId = strList.get(position).getCcityId();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sfBtn:
			sfBtn.setBackgroundColor(getResources().getColor(R.color.green));
			shiBtn.setBackgroundColor(getResources().getColor(R.color.white));
			qxBtn.setBackgroundColor(getResources().getColor(R.color.white));
			getData(provinceNameId, 1);
			break;
		case R.id.shiBtn:
			if (cityNameId > 0) {
//				getData(cityNameId, 2);
				qxBtn.setBackgroundColor(getResources().getColor(R.color.green));
				shiBtn.setBackgroundColor(getResources().getColor(R.color.white));
				qxBtn.setBackgroundColor(getResources().getColor(R.color.white));
				getData(cityNameId, 3);
			} else {
				Toast.makeText(LocationActivity.this, "请先选择省份！", 5).show();
			}
			break;
		case R.id.qxBtn:
			if (countyNameId > 0) {
//				getData(countyNameId);
			} else {
				Toast.makeText(LocationActivity.this, "请先选择市区！", 5).show();
			}
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
	public void sendMyMsgUpdateUI(int what, String toast) {
		Message msg = mBaseHandler.obtainMessage(what);
		Bundle bundle = new Bundle();
		bundle.putString("Msg", toast);
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

}