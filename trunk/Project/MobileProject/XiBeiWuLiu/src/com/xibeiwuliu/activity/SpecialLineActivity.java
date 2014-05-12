package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xibeiwuliu.database.SqliteDaoArea;
import com.xibeiwuliu.entity.AreaInfo;
import com.xibeiwuliu.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SpecialLineActivity.java
 * @Describe：专线查询
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午4:45:22
 * @Version v1.0 *
 * 
 */
public class SpecialLineActivity extends BaseActivity {
	private MyApplication application = null;
	private TextView text;
	private String getMsg;
	private SqliteDaoArea daoArea = null;
	private List<AreaInfo> strList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setAbContentView(R.layout.special_line);
		application = (MyApplication) abApplication;
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, false);
		initView();

	}

	/**
	 * 
	 * 描述：初始化View
	 * 
	 * @throws
	 * @date：2013-11-13 上午10:21:24
	 * @version v1.0
	 */
	private void initView() {
		daoArea = SqliteDaoArea.getInstance(SpecialLineActivity.this);
		strList = daoArea.getAreaInfo(0);
		
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = null;
		for (int i = 0; i < strList.size(); i++) {
			item = new HashMap<String, Object>();
			item.put("cityName", strList.get(i).getCcityName());
			data.add(item);
		}
		ListView cityList = (ListView) findViewById(R.id.cityList);
		SimpleAdapter simpleAdapter = new SimpleAdapter(SpecialLineActivity.this, data, R.layout.city_name, new String[] { "cityName" },
				new int[] { R.id.cityName });

		cityList.setAdapter(simpleAdapter);
		cityList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String getCcityName = strList.get(arg2).getCcityName();
				Toast.makeText(SpecialLineActivity.this, getCcityName + "专线", 5).show();
				Intent intent = new Intent(SpecialLineActivity.this, CargoListActivity.class);
				intent.putExtra("msg", "货运专线");
				startActivity(intent);
			}
		});

	}
}
