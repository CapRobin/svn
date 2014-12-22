package com.net56888.logistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MoreActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_more_tab_x);
		intView();
	}

	// 初始化View
	private void intView() {
		Button lbtn = (Button) findViewById(R.id.left_title_btn);
		Button rbtn = (Button) findViewById(R.id.right_title_btn);
		TextView tv = (TextView) findViewById(R.id.title);
		ViewGroup vg = (ViewGroup) findViewById(R.id.icons);

		lbtn.setVisibility(View.INVISIBLE);
		rbtn.setVisibility(View.INVISIBLE);

		tv.setText(R.string.title_more);

		View iconView = new IconView(MoreActivity.this.getApplicationContext(), R.drawable.account_icon, "账户");
		iconView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Class<?> target;
				if (null == Config.getInstance().getUser()) {
					target = Login.class;
				} else {
					target = Profile.class;
				}
				Intent i = new Intent(MoreActivity.this, target);
				startActivity(i);
			}

		});
		vg.addView(iconView);

	}

}
