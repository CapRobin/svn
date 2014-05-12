package zxing.standopen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class TwoDimensionCodeActivity extends Activity {
	private EditText input = null;
	private Button ok = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.two_dimension_code_view);
		input = (EditText) findViewById(R.id.input);
		ok = (Button) findViewById(R.id.make);
//		scanner = (Button) findViewById(R.id.btn_scanner);
		ok.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("content", input.getText().toString());
				intent.setClass(TwoDimensionCodeActivity.this, TwoDimensionCodeShowActivity.class);
				startActivity(intent);
			}
		});
	}

}
