package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.xibeiwuliu.view.MyView;

public class PublishCargoActivity extends BaseActivity implements OnClickListener {
	private String getMsg;
	private boolean isShowRightBut = false;
	private Button publicInfoBut, isYuYueBut;
	private MyView myView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.public_cargo_info);
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
		initView();
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年5月6日 下午3:45:47
	 * @Version v1.0
	 * 
	 */
	private void initView() {
		myView = (MyView) findViewById(R.id.myView);
		publicInfoBut = (Button) findViewById(R.id.publicInfoBut);
		isYuYueBut = (Button) findViewById(R.id.isYuYueBut);
		// //设置出发地合目的地为空
		MyView.setStartAddress();
		MyView.setEndAddress();

		publicInfoBut.setOnClickListener(this);
		isYuYueBut.setOnClickListener(this);
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年5月6日 下午3:45:47
	 * @Version v1.0
	 * 
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.publicInfoBut:
			String startAddress = MyView.getStartAddress();
			String endAddress = MyView.getEndAddress();
			if (TextUtils.isEmpty(startAddress) && TextUtils.isEmpty(endAddress)) {
				Toast.makeText(PublishCargoActivity.this, "请先选择出发地和目的地！", 5).show();
			} else if (TextUtils.isEmpty(startAddress)) {
				Toast.makeText(PublishCargoActivity.this, "请先选择出发地！", 5).show();
			} else if (TextUtils.isEmpty(endAddress)) {
				Toast.makeText(PublishCargoActivity.this, "请先选择目的地！", 5).show();
			} else {
				Toast.makeText(PublishCargoActivity.this, "出发地为：" + myView.getStartAddress() + "\n目的地为：" + myView.getEndAddress(), 5).show();
			}
			break;
		case R.id.isYuYueBut:
			Toast.makeText(PublishCargoActivity.this, "请选择预约时间", 5).show();
			break;
		}
	}

}