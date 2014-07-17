package com.steellogistics.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.steellogistics.R;

public class TestActivity extends Activity {
	private Button titleLeftBut,titleRightBut;
	private RelativeLayout titleLayout = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleLeftBut = (Button) findViewById(R.id.titleLeftBut);
        titleRightBut = (Button) findViewById(R.id.titleRightBut);
        titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
        
        titleLeftBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(TestActivity.this, titleLeftBut.getText()+"_TestActivity", 5).show();
			}
		});
        
        titleRightBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(TestActivity.this, titleRightBut.getText()+"_TestActivity", 5).show();
			}
		});
    }

    
}
