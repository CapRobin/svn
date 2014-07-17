package com.steellogistics.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.steellogistics.R;

public class MainActivity extends Activity implements OnClickListener{
	private Button titleLeftBut,titleRightBut;
	private RelativeLayout titleLayout = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleLeftBut = (Button) findViewById(R.id.titleLeftBut);
        titleRightBut = (Button) findViewById(R.id.titleRightBut);
        titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
        
        titleLeftBut.setOnClickListener(this);
        titleRightBut.setOnClickListener(this);
        titleLayout.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titleLeftBut:
			 Toast.makeText(this, titleLeftBut.getText(), 5).show();
			break;
		case R.id.titleRightBut:
			 Toast.makeText(this, titleRightBut.getText(), 5).show();
			break;
		case R.id.titleLayout:
			 Toast.makeText(this, "titleLayout is clink", 5).show();
			break;
		case 2:
			
			break;
		}
	}

    
}
