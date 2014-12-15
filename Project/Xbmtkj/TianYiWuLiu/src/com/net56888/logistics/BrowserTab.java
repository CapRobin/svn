package com.net56888.logistics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.net56888.logistics.data.InfoEntity;
import com.net56888.logistics.data.UserEntity;

public class BrowserTab extends ListFragment {

    public final String BrowserTabParams = "com.net56888.logistics.InfoEntity";
    
	private final String TAG = "BrowserTab";
	private View mView = null;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView");
		if (container == null) {
			return null;
		}
		
		mView = (LinearLayout)inflater.inflate(R.layout.fragment_browser_tab, container, false);
		View titleBar = mView.findViewById(R.id.title_bar);
		Button lbtn = (Button)titleBar.findViewById(R.id.left_title_btn);
		lbtn.setText(R.string.btn_refresh);
		lbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                BrowserTab.this.refreshData();
            }
		    
		});
		
		Button rbtn = (Button)titleBar.findViewById(R.id.right_title_btn);
		rbtn.setText(R.string.btn_cat);
		rbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final String Cargo = BrowserTab.this.getString(R.string.info_filter_opt_cargo);
                final String Truck = BrowserTab.this.getString(R.string.info_filter_opt_truck);
                final CharSequence[] items = {Cargo, Truck};

                AlertDialog.Builder builder = new AlertDialog.Builder(BrowserTab.this.getActivity());
                builder.setTitle(R.string.info_filter_title);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(BrowserTab.this.getActivity(), items[item], Toast.LENGTH_SHORT).show();
                        
                        if (item == 0) Config.getInstance().setType(InfoEntity.Type.CargoInfo);
                        if (item == 1) Config.getInstance().setType(InfoEntity.Type.TruckInfo);
                        BrowserTab.this.refreshData();
                        
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
		    
		});
		
		TextView tv = (TextView)titleBar.findViewById(R.id.title);
		tv.setText(R.string.title_browser);
		
		refreshData();
		
		return mView;
	}
	
	
	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
		this.getListView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                    long id) {
                InfoEntity ie = (InfoEntity)adapter.getItemAtPosition(position);
                
                Intent i = new Intent(BrowserTab.this.getActivity(), Detail.class);
                i.putExtra("DATA", ie);
                startActivity(i);
            }
		    
		});
        super.onViewCreated(view, savedInstanceState);
    }


	public void refreshData() {
	    UserEntity user = Config.getInstance().getUser();
	    String city = Config.getInstance().getCity();
	    if (null != user) city = user.getMsg_City();
		loadData(city,  Config.getInstance().getType());
	}
	
	public void loadData(String city, InfoEntity.Type type) {
		setListAdapter(new BrowserAdapter(getActivity(), city, "", type));
	}
}
