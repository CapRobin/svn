package com.net56888.logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.net56888.logistics.data.InfoEntity;

public class PublishTab extends Fragment {

	public final static String PUBLISH_TYPE = "com.net56888.logistics.PublishTab.Publish_Type";
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		mView = inflater.inflate(R.layout.fragment_publish_tab, container, false);

		View titleBar = mView.findViewById(R.id.title_bar);
		Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
		lbtn.setVisibility(View.INVISIBLE);

		Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
		rbtn.setVisibility(View.GONE);
		/*
		 * rbtn.setText(R.string.btn_published); rbtn.setOnClickListener(new
		 * OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * }
		 * 
		 * });
		 */

		TextView tv = (TextView) titleBar.findViewById(R.id.title);
		tv.setText(R.string.title_publish);

		Button cargoBtn = (Button) mView.findViewById(R.id.publish_info_cargo_btn);
		cargoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(PublishTab.this.getActivity(), InfoPub.class);
				i.putExtra(PUBLISH_TYPE, InfoEntity.Type.toInteger(InfoEntity.Type.CargoInfo));
				startActivity(i);
			}

		});

		Button truckBtn = (Button) mView.findViewById(R.id.publish_info_truck_btn);
		truckBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//原始代码
//				Intent i = new Intent(PublishTab.this.getActivity(), InfoPub.class);
//				i.putExtra(PUBLISH_TYPE, InfoEntity.Type.toInteger(InfoEntity.Type.TruckInfo));
//				startActivity(i);
				
				//修改代码
				startActivity(new Intent(PublishTab.this.getActivity(), MainActivity.class));
				
			}

		});

		return mView;
	}
}
