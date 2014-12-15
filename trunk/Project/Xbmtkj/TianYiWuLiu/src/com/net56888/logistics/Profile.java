package com.net56888.logistics;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.net56888.logistics.data.UserEntity;

public class Profile extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        View titleBar = this.findViewById(R.id.title_bar);

        Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
        lbtn.setText(R.string.btn_back);
        lbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Profile.this.finish();
            }

        });

        Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
        rbtn.setVisibility(View.INVISIBLE);

        TextView tv = (TextView) titleBar.findViewById(R.id.title);
        tv.setText(R.string.title_login);

        Button logout_btn = (Button) findViewById(R.id.logout_btn);
        TextView userView = (TextView) findViewById(R.id.profile_username);
        
        logout_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Config.getInstance().setUser(null);
                App.getLogisticsDB().getUserTable().clear();
                Profile.this.finish();
            }

        });
        
        UserEntity user = Config.getInstance().getUser();
        
        if (null != user) {
            userView.setText(user.getMsg_Name());
        } else {
            userView.setText("");
        }
    }

}
