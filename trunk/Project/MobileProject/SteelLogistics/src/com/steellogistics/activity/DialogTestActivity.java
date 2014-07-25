package com.steellogistics.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.steellogistics.R;

public class DialogTestActivity extends Activity {
	private Button titleLeftBut, titleRightBut;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		titleLeftBut = (Button) findViewById(R.id.titleLeftBut);
		titleRightBut = (Button) findViewById(R.id.titleRightBut);
	}

}
