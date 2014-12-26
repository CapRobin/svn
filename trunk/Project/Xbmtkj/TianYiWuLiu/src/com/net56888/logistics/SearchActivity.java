package com.net56888.logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.net56888.logistics.data.EntitySet;
import com.net56888.logistics.data.InfoEntity;
import com.net56888.logistics.data.LocationEntity;

public class SearchActivity extends FragmentActivity {

	public static final String SEARCH_START_CITY = "com.net56888.logistics.search_start_city";
	public static final String SEARCH_STOP_CITY = "com.net56888.logistics.search_stop_city";
	public static final String SEARCH_TYPE = "com.net56888.logistics.search_type";
	private LocationSelector mFrgmntStartArea;
	private LocationSelector mFrgmntStopArea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_search_tab_x);
		intView();
	}

	// 初始化View
	private void intView() {
		Button lbtn = (Button) findViewById(R.id.left_title_btn);
		Button rbtn = (Button) findViewById(R.id.right_title_btn);
		TextView tv = (TextView) findViewById(R.id.title);
		Button searchCargoBtn = (Button) findViewById(R.id.search_cargo_btn);
		Button searchTruckBtn = (Button) findViewById(R.id.search_truck_btn);

		lbtn.setVisibility(View.INVISIBLE);
		rbtn.setText(R.string.btn_clear);
		rbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				reset();
			}
		});

		tv.setText(R.string.title_search);

		searchCargoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SearchActivity.this, SearchBrowser.class);
				i.putExtra(SearchActivity.SEARCH_START_CITY, convertLocationsToString(mFrgmntStartArea.getLocations()));
				i.putExtra(SearchActivity.SEARCH_STOP_CITY, convertLocationsToString(mFrgmntStopArea.getLocations()));
				i.putExtra(SearchActivity.SEARCH_TYPE, InfoEntity.Type.toInteger(InfoEntity.Type.CargoInfo));
				startActivity(i);
			}

		});

		searchTruckBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SearchActivity.this, SearchBrowser.class);

				i.putExtra(SearchActivity.SEARCH_START_CITY, convertLocationsToString(mFrgmntStartArea.getLocations()));
				i.putExtra(SearchActivity.SEARCH_STOP_CITY, convertLocationsToString(mFrgmntStopArea.getLocations()));
				i.putExtra(SearchActivity.SEARCH_TYPE, InfoEntity.Type.toInteger(InfoEntity.Type.TruckInfo));
				startActivity(i);
			}
		});

		FragmentManager fm = SearchActivity.this.getSupportFragmentManager();
		mFrgmntStartArea = (LocationSelector) fm.findFragmentById(R.id.fragment_start_area);
		mFrgmntStopArea = (LocationSelector) fm.findFragmentById(R.id.fragment_stop_area);
	}

	private void reset() {
		mFrgmntStartArea.reset();
		mFrgmntStopArea.reset();
	}

	private String convertLocationsToString(EntitySet locations) {
		for (int i = locations.size(); i > 0; i--) {
			LocationEntity le = (LocationEntity) locations.get(i - 1);
			if (null != le)
				return le.getMsg_Location();
		}
		return "";
	}

}
