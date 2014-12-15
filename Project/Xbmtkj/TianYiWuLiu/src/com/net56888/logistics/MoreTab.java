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

public class MoreTab extends Fragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        mView = inflater.inflate(R.layout.fragment_more_tab, container, false);

        View titleBar = mView.findViewById(R.id.title_bar);
        Button lbtn = (Button) titleBar.findViewById(R.id.left_title_btn);
        lbtn.setVisibility(View.INVISIBLE);

        Button rbtn = (Button) titleBar.findViewById(R.id.right_title_btn);
        rbtn.setVisibility(View.INVISIBLE);

        TextView tv = (TextView) titleBar.findViewById(R.id.title);
        tv.setText(R.string.title_more);
        
        ViewGroup vg = (ViewGroup)mView.findViewById(R.id.icons);
        View iconView = new IconView(this.getActivity().getApplicationContext(), R.drawable.account_icon, "账户");
        iconView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Class<?> target;
                if (null == Config.getInstance().getUser()) {
                    target = Login.class;
                } else {
                    target = Profile.class;
                }
                Intent i = new Intent(MoreTab.this.getActivity(), target);
                startActivity(i);
            }
            
        });
        vg.addView(iconView);
        
        return mView;
    }
}
