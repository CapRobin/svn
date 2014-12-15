package com.net56888.logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.net56888.logistics.data.Entity;
import com.net56888.logistics.data.InfoEntity;
import com.net56888.logistics.data.UserEntity;
/**
 * 
 * Copyright (c) 2012 All rights reserved
 * @Name：InfoPub.java 
 * @Describe：发布车源信息
 * @Author Administrator  yfr5734@gmail.com
 * @Date：2013-11-20 下午2:42:10
 * @Version v1.0
 */
public class InfoPub extends FragmentActivity implements DataSource.Callback {
    private static final String TAG = "InfoPub";

    private InfoEntity.Type mType;

    private LocationSelector mLocationStartArea;
    private LocationSelector mLocationStopArea;
    private TextView mTruckPlateCaption;
    private EditText mTruckPlate;
    private TextView mCargoTypeCaption;
    private Spinner mCargoType;
    private TextView mCargoWeightCaption;
    private LinearLayout mCargoWeightLayout;
    private EditText mCargoWeight;
    private Spinner mCargoWeightUnit;
    private Spinner mTruckType;
    private EditText mTruckLength;
    private TextView mTruckCapacityCaption;
    private LinearLayout mTruckCapactiyLayout;
    private EditText mTruckCapacity;
    private EditText mMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_info_pub);

        View titleBar = this.findViewById(R.id.title_bar);

        Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
        lbtn.setText(R.string.btn_back);
        lbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                InfoPub.this.finish();
            }

        });

        Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
        rbtn.setVisibility(View.INVISIBLE);

        Intent i = this.getIntent();
        int t = i.getIntExtra(PublishTab.PUBLISH_TYPE, 0);
        mType = InfoEntity.Type.fromInt(t);

        TextView tv = (TextView) titleBar.findViewById(R.id.title);
        if (InfoEntity.Type.CargoInfo == mType)
            tv.setText(R.string.publish_info_cargo);
        if (InfoEntity.Type.TruckInfo == mType)
            tv.setText(R.string.publish_info_truck);

        UserEntity user = Config.getInstance().getUser();
        if (null != user) {
            /*
             * ((EditText) InfoPub.this.findViewById(R.id.pub_tel))
             * .setText(user.getMsg_Tel()); ((EditText)
             * InfoPub.this.findViewById(R.id.pub_net_phone))
             * .setText(user.getMsg_Net_Phone());
             */
        }

        FragmentManager fm = this.getSupportFragmentManager();
        this.mLocationStartArea = (LocationSelector) fm
                .findFragmentById(R.id.fragment_start_area);
        this.mLocationStopArea = (LocationSelector) fm
                .findFragmentById(R.id.fragment_stop_area);
        this.mTruckPlateCaption = (TextView) this
                .findViewById(R.id.pub_truck_plate_caption);
        this.mTruckPlate = (EditText) this.findViewById(R.id.pub_truck_plate);
        this.mCargoTypeCaption = (TextView) this
                .findViewById(R.id.pub_cargo_type_caption);
        this.mCargoType = (Spinner) this.findViewById(R.id.pub_cargo_type);
        this.mCargoWeightCaption = (TextView) this
                .findViewById(R.id.pub_cargo_weight_caption);
        this.mCargoWeightLayout = (LinearLayout) this
                .findViewById(R.id.pub_cargo_weight_layout);
        this.mCargoWeight = (EditText) this.findViewById(R.id.pub_cargo_weight);
        this.mCargoWeightUnit = (Spinner) this
                .findViewById(R.id.pub_cargo_weight_unit_list);
        this.mTruckType = (Spinner) this.findViewById(R.id.pub_truck_type);
        this.mTruckLength = (EditText) this.findViewById(R.id.pub_truck_length);
        this.mTruckCapacityCaption = (TextView) this
                .findViewById(R.id.pub_truck_capacity_caption);
        this.mTruckCapactiyLayout = (LinearLayout) this
                .findViewById(R.id.pub_truck_capacity_layout);
        this.mTruckCapacity = (EditText) this
                .findViewById(R.id.pub_truck_capacity);
        this.mMemo = (EditText) this.findViewById(R.id.pub_memo);

        if (InfoEntity.Type.CargoInfo == mType) {
            this.mTruckPlateCaption.setVisibility(View.GONE);
            this.mTruckPlate.setVisibility(View.GONE);
            this.mTruckCapacityCaption.setVisibility(View.GONE);
            this.mTruckCapactiyLayout.setVisibility(View.GONE);
        }
        if (InfoEntity.Type.TruckInfo == mType) {
            this.mCargoTypeCaption.setVisibility(View.GONE);
            this.mCargoType.setVisibility(View.GONE);
            this.mCargoWeightCaption.setVisibility(View.GONE);
            this.mCargoWeightLayout.setVisibility(View.GONE);
        }

        Button submitBtn = (Button) findViewById(R.id.pub_submit_btn);
        submitBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                submit();
            }

        });

        Button resetBtn = (Button) findViewById(R.id.pub_reset_btn);
        resetBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                reset();
            }

        });
    }

    /**
     * 
     * 描述：发布车源信息
     * @throws 
     * @date：2013-11-20 下午2:43:55
     * @version v1.0
     */
    private void submit() {
        UserEntity user = Config.getInstance().getUser();
        if (null == user) {
            Toast.makeText(InfoPub.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("0" == user.getMsg_Publish_Enabled()) {
            Toast.makeText(InfoPub.this, "您没有发布信息的权限", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        StringBuilder sb = new StringBuilder();

        String startArea = this.mLocationStartArea.getLocationString();
        String stopArea = this.mLocationStopArea.getLocationString();

        sb.append(startArea);
        sb.append(" → ");
        sb.append(stopArea);

        if (InfoEntity.Type.CargoInfo == mType) {

            sb.append(", 有");
            sb.append(this.mCargoType.getSelectedItem().toString());
            if (this.mCargoWeight.getText().length() > 0) {
                sb.append(this.mCargoWeight.getText());
                sb.append(this.mCargoWeightUnit.getSelectedItem().toString());
            }

            sb.append(", 求");
            if (this.mTruckLength.getText().length() > 0) {
                sb.append(this.mTruckLength.getText());
                sb.append("米");
            }
            if (this.mTruckType.getSelectedItemPosition() == 0) {
                sb.append("车型"); // make it “车型不限”
            }
            sb.append(this.mTruckType.getSelectedItem().toString());

            if (this.mMemo.getText().length() > 0) {
                sb.append(",");
                sb.append(this.mMemo.getText());
            }

        }
        if (InfoEntity.Type.TruckInfo == mType) {
            sb.append(", 有");
            if (this.mTruckLength.getText().length() > 0) {
                sb.append(this.mTruckLength.getText());
                sb.append("米");
            }
            sb.append(this.mTruckType.getSelectedItem().toString());

            if (this.mTruckCapacity.getText().length() > 0) {
                sb.append(",");
                sb.append("载重");
                sb.append(this.mTruckCapacity.getText());
                sb.append("吨");
            }

            if (this.mTruckPlate.getText().length() > 0) {
                sb.append(",");
                sb.append("车牌");
                sb.append(this.mTruckPlate.getText());
            }

            if (this.mMemo.getText().length() > 0) {
                sb.append(",");
                sb.append(this.mMemo.getText());
            }
        }

        Log.i(TAG, sb.toString());

        InfoEntity info = new InfoEntity();
        info.setMsg_Type(InfoEntity.Type.toString(this.mType));
        info.setMsg_Content(sb.toString());
        info.setMsg_StartCity(startArea);

        DataSource.getInstance().publishInfoAsync(this, info, user);
    }

    private void reset() {
        this.mLocationStartArea.reset();
        this.mLocationStopArea.reset();
        this.mTruckPlate.setText("");
        this.mCargoType.setSelection(0);
        this.mCargoWeight.setText("");
        this.mCargoWeightUnit.setSelection(0);
        this.mTruckType.setSelection(0);
        this.mTruckLength.setText("");
        this.mTruckCapacity.setText("");
        this.mMemo.setText("");
    }

    @Override
    public void onLogin(Entity entity) {
        // do nothing
    }

    @Override
    public void onPublish(String _result) {
        final String result = _result;
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(InfoPub.this, result, Toast.LENGTH_SHORT).show();
                finish();
            }

        });

    }

    @Override
    public void onCheckConnection(String result, int statusCode) {
        // do nothing
    }

    @Override
    public void onRegisterUser(String result) {
        // do nothing

    }

    @Override
    public void onCheckUpdate(String result) {
        // do nothing
        
    }

}
