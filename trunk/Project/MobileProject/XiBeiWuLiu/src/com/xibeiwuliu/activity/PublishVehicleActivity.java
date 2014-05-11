package com.xibeiwuliu.activity;

import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.xibeiwuliu.adapter.MyLocationAdapter;
import com.xibeiwuliu.database.SqliteDaoArea;
import com.xibeiwuliu.entity.AreaInfo;
import com.xibeiwuliu.view.NoScrollGridView;

public class PublishVehicleActivity extends BaseActivity implements OnClickListener {
	private SqliteDaoArea daoArea = null;
	private boolean isShowRightBut = false;
	private NoScrollGridView gridView, gridView2;
	private List<AreaInfo> strList, strList2 = null;
	private Button sfBtn, csBtn, qxBtn, sfBtn2, csBtn2, qxBtn2;
	private LinearLayout gridViewlayout, gridViewlayout2, textlayout, textlayout2;
	private TextView addressText, addressText2;
	private String sfNameStr, csNameStr, qxNameStr, sfNameStr2, csNameStr2, qxNameStr2 = null;
	private int sfNameId, csNameId, qxNameId, sfNameId2, csNameId2, selectId, qxNameId2, selectId2 = 0;
	private String getMsg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.vehicle_location_view);
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
		initView();
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
		daoArea = SqliteDaoArea.getInstance(PublishVehicleActivity.this);
		gridViewlayout = (LinearLayout) findViewById(R.id.gridViewlayout);
		textlayout = (LinearLayout) findViewById(R.id.textlayout);
		addressText = (TextView) findViewById(R.id.addressText);
		sfBtn = (Button) findViewById(R.id.sfBtn);
		csBtn = (Button) findViewById(R.id.shiBtn);
		qxBtn = (Button) findViewById(R.id.qxBtn);
		gridView = (NoScrollGridView) findViewById(R.id.gridview);

		gridViewlayout2 = (LinearLayout) findViewById(R.id.gridViewlayout2);
		textlayout2 = (LinearLayout) findViewById(R.id.textlayout2);
		addressText2 = (TextView) findViewById(R.id.addressText2);
		sfBtn2 = (Button) findViewById(R.id.sfBtn2);
		csBtn2 = (Button) findViewById(R.id.shiBtn2);
		qxBtn2 = (Button) findViewById(R.id.qxBtn2);
		gridView2 = (NoScrollGridView) findViewById(R.id.gridview2);

		sfBtn.setOnClickListener(this);
		csBtn.setOnClickListener(this);
		qxBtn.setOnClickListener(this);
		textlayout.setOnClickListener(this);

		sfBtn2.setOnClickListener(this);
		csBtn2.setOnClickListener(this);
		qxBtn2.setOnClickListener(this);
		textlayout2.setOnClickListener(this);

		// EditText cfdEdit = (EditText) findViewById(R.id.cfdEdit);
		// cfdEdit.setText("从这里出发");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sfBtn:
			gridView.setVisibility(View.VISIBLE);
			sfBtn.setBackgroundColor(getResources().getColor(R.color.green));
			csBtn.setBackgroundColor(getResources().getColor(R.color.white));
			qxBtn.setBackgroundColor(getResources().getColor(R.color.white));
			csBtn.setText("城市");
			qxBtn.setText("区县");
			csNameId = 0;
			qxNameId = 0;
			selectId = 1;
			getData(sfNameId, true);
			break;
		case R.id.shiBtn:
			if (csNameId > 0) {
				sfBtn.setBackgroundColor(getResources().getColor(R.color.white));
				csBtn.setBackgroundColor(getResources().getColor(R.color.green));
				qxBtn.setBackgroundColor(getResources().getColor(R.color.white));
				qxBtn.setText("区县");
				qxNameId = 0;
				selectId = 2;
				getData(csNameId, true);
			} else {
				Toast.makeText(PublishVehicleActivity.this, "请先选择省份！", 5).show();
			}
			break;
		case R.id.qxBtn:
			if (qxNameId > 0) {
				selectId = 3;
				getData(qxNameId, true);
			} else {
				Toast.makeText(PublishVehicleActivity.this, "请先选择城市！", 5).show();
			}
			break;
		case R.id.textlayout:
			textlayout.setVisibility(View.GONE);
			gridViewlayout.setVisibility(View.VISIBLE);
			sfBtn.setBackgroundColor(getResources().getColor(R.color.green));
			csBtn.setBackgroundColor(getResources().getColor(R.color.white));
			qxBtn.setBackgroundColor(getResources().getColor(R.color.white));
			sfBtn.setText("省份");
			csBtn.setText("城市");
			qxBtn.setText("区县");
			csNameId = 0;
			qxNameId = 0;
			selectId = 1;
			getData(sfNameId, true);
			break;
		case R.id.sfBtn2:
			gridView2.setVisibility(View.VISIBLE);
			sfBtn2.setBackgroundColor(getResources().getColor(R.color.green));
			csBtn2.setBackgroundColor(getResources().getColor(R.color.white));
			qxBtn2.setBackgroundColor(getResources().getColor(R.color.white));
			csBtn2.setText("城市");
			qxBtn2.setText("区县");
			csNameId2 = 0;
			qxNameId2 = 0;
			selectId2 = 1;
			getData(sfNameId2, false);
			break;
		case R.id.shiBtn2:
			if (csNameId2 > 0) {
				sfBtn2.setBackgroundColor(getResources().getColor(R.color.white));
				csBtn2.setBackgroundColor(getResources().getColor(R.color.green));
				qxBtn2.setBackgroundColor(getResources().getColor(R.color.white));
				qxBtn2.setText("区县");
				qxNameId2 = 0;
				selectId2 = 2;
				getData(csNameId2, false);
			} else {
				Toast.makeText(PublishVehicleActivity.this, "请先选择省份！", 5).show();
			}
			break;
		case R.id.qxBtn2:
			if (qxNameId2 > 0) {
				selectId2 = 3;
				getData(qxNameId2, false);
			} else {
				Toast.makeText(PublishVehicleActivity.this, "请先选择城市！", 5).show();
			}
			break;
		case R.id.textlayout2:
			textlayout2.setVisibility(View.GONE);
			gridViewlayout2.setVisibility(View.VISIBLE);
			sfBtn2.setBackgroundColor(getResources().getColor(R.color.green));
			csBtn2.setBackgroundColor(getResources().getColor(R.color.white));
			qxBtn2.setBackgroundColor(getResources().getColor(R.color.white));
			sfBtn2.setText("省份");
			csBtn2.setText("城市");
			qxBtn2.setText("区县");
			csNameId2 = 0;
			qxNameId2 = 0;
			selectId2 = 1;
			getData(sfNameId2, false);
			break;
		}
	}

	/**
	 * 
	 * @Describe：加载地址数据
	 * @param cityId
	 *            查询省市或城市ID
	 * @param selectBtn
	 *            省市县查询ButtonId
	 * @Throws:
	 * @Date：2014年5月7日 下午1:20:16
	 * @Version v1.0
	 * 
	 */
	private void getData(int cityId, boolean cfdOrMdd) {

		final int getCityId = cityId;
		final boolean isCfdOrMdd = cfdOrMdd;
		showProgressDialog();
		AbThread mAbTaskThread = new AbThread();
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeProgressDialog();
				if (isCfdOrMdd) {

					if (strList.size() > 0 && strList != null) {
						showView();
					}
				} else {
					if (strList2.size() > 0 && strList2 != null) {
						showView2();
					}
				}
			}

			@Override
			public void get() {
				try {
					if (isCfdOrMdd) {
						strList = daoArea.getAreaInfo(getCityId);
					} else {
						strList2 = daoArea.getAreaInfo(getCityId);
					}

				} catch (Exception e) {
					e.printStackTrace();
					showToastInThread(e.getMessage());
				}
			};
		};
		mAbTaskThread.execute(item);
	}

	/**
	 * 
	 * @Describe： 显示页面数据
	 * @Throws:
	 * @Date：2014年5月6日 下午3:58:37
	 * @Version v1.0
	 * 
	 */
	public void showView() {
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

				switch (selectId) {
				case 1:
					sfNameStr = strList.get(position).getCcityName();
					csNameId = strList.get(position).getCcityId();
					csBtn.setBackgroundColor(getResources().getColor(R.color.green));
					sfBtn.setBackgroundColor(getResources().getColor(R.color.white));
					qxBtn.setBackgroundColor(getResources().getColor(R.color.white));
					sfBtn.setText(sfNameStr);
					selectId = 2;
					getData(csNameId, true);
					break;
				case 2:
					csNameStr = strList.get(position).getCcityName();
					qxNameId = strList.get(position).getCcityId();
					qxBtn.setBackgroundColor(getResources().getColor(R.color.green));
					csBtn.setBackgroundColor(getResources().getColor(R.color.white));
					sfBtn.setBackgroundColor(getResources().getColor(R.color.white));
					csBtn.setText(csNameStr);
					selectId = 3;
					getData(qxNameId, true);
					break;
				case 3:
					qxNameStr = strList.get(position).getCcityName();
					gridViewlayout.setVisibility(View.GONE);
					textlayout.setVisibility(View.VISIBLE);
					qxBtn.setText(qxNameStr);
					addressText.setText(sfNameStr + csNameStr + qxNameStr);
					strList = null;
					break;
				}
			}
		});
	}

	public void showView2() {
		gridView2.setSelector(new ColorDrawable(Color.TRANSPARENT));
		MyLocationAdapter locationAdapter = new MyLocationAdapter(this, strList2);
		gridView2.setAdapter(locationAdapter);
		gridView2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		gridView2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				switch (selectId2) {
				case 1:
					sfNameStr2 = strList2.get(position).getCcityName();
					csNameId2 = strList2.get(position).getCcityId();
					csBtn2.setBackgroundColor(getResources().getColor(R.color.green));
					sfBtn2.setBackgroundColor(getResources().getColor(R.color.white));
					qxBtn2.setBackgroundColor(getResources().getColor(R.color.white));
					sfBtn2.setText(sfNameStr2);
					selectId2 = 2;
					getData(csNameId2, false);
					break;
				case 2:
					csNameStr2 = strList2.get(position).getCcityName();
					qxNameId2 = strList2.get(position).getCcityId();
					qxBtn2.setBackgroundColor(getResources().getColor(R.color.green));
					csBtn2.setBackgroundColor(getResources().getColor(R.color.white));
					sfBtn2.setBackgroundColor(getResources().getColor(R.color.white));
					csBtn2.setText(csNameStr2);
					selectId2 = 3;
					getData(qxNameId2, false);
					break;
				case 3:
					qxNameStr2 = strList2.get(position).getCcityName();
					gridViewlayout2.setVisibility(View.GONE);
					textlayout2.setVisibility(View.VISIBLE);
					qxBtn2.setText(qxNameStr);
					addressText2.setText(sfNameStr2 + csNameStr2 + qxNameStr2);
					strList = null;
					break;
				}
			}
		});
	}
}