package com.net56888.logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.net56888.logistics.data.EntitySet;
import com.net56888.logistics.data.InfoEntity;
import com.net56888.logistics.data.LocationEntity;

public class SearchTab extends Fragment {

	private View mView;

	public static final String SEARCH_START_CITY = "com.net56888.logistics.search_start_city";
	public static final String SEARCH_STOP_CITY = "com.net56888.logistics.search_stop_city";
	public static final String SEARCH_TYPE = "com.net56888.logistics.search_type";

	LocationSelector mFrgmntStartArea;
	LocationSelector mFrgmntStopArea;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		mView = inflater.inflate(R.layout.fragment_search_tab, container, false);

		View titleBar = mView.findViewById(R.id.title_bar);
		Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
		lbtn.setVisibility(View.INVISIBLE);

		Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
		rbtn.setText(R.string.btn_clear);
		rbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				reset();
			}

		});

		TextView tv = (TextView) titleBar.findViewById(R.id.title);
		tv.setText(R.string.title_search);

		Button searchCargoBtn = (Button) mView.findViewById(R.id.search_cargo_btn);
		searchCargoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SearchTab.this.getActivity(), SearchBrowser.class);

				i.putExtra(SearchTab.this.SEARCH_START_CITY, convertLocationsToString(mFrgmntStartArea.getLocations()));
				i.putExtra(SearchTab.this.SEARCH_STOP_CITY, convertLocationsToString(mFrgmntStopArea.getLocations()));
				i.putExtra(SearchTab.this.SEARCH_TYPE, InfoEntity.Type.toInteger(InfoEntity.Type.CargoInfo));
				startActivity(i);
			}

		});

		Button searchTruckBtn = (Button) mView.findViewById(R.id.search_truck_btn);
		searchTruckBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SearchTab.this.getActivity(), SearchBrowser.class);

				i.putExtra(SearchTab.this.SEARCH_START_CITY, convertLocationsToString(mFrgmntStartArea.getLocations()));
				i.putExtra(SearchTab.this.SEARCH_STOP_CITY, convertLocationsToString(mFrgmntStopArea.getLocations()));
				i.putExtra(SearchTab.this.SEARCH_TYPE, InfoEntity.Type.toInteger(InfoEntity.Type.TruckInfo));
				startActivity(i);
			}

		});

		FragmentManager fm = this.getActivity().getSupportFragmentManager();
		mFrgmntStartArea = (LocationSelector) fm.findFragmentById(R.id.fragment_start_area);
		mFrgmntStopArea = (LocationSelector) fm.findFragmentById(R.id.fragment_stop_area);

		return mView;
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
