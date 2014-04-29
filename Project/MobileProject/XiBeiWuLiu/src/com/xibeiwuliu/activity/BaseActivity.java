package com.xibeiwuliu.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;

public class BaseActivity extends AbActivity {

	public Handler mBaseHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(BaseActivity.this, msg.getData().getString("Msg"), 5).show();
				break;
			case 1:
				showDialog(0);
				break;
			case 2:
				removeDialog(0);
				break;
			case 3:

				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.base);
	}

	/**
	 * 
	 * @Describe��TODO
	 * @param titleMsg					��������
	 * @param isShowRightBar		�Ƿ���ʾ�ұߵİ�ť
	 * @Throws:
	 * @Date��2014��4��29�� ����10:38:04
	 * @Version v1.0
	 * 
	 */
	protected void initTitleLayout(String titleMsg, boolean isShowRightBar) {
		AbTitleBar mAbTitleBar = this.getTitleBar();

		// �����಼���ļ�
		mAbTitleBar.setTitleText(titleMsg);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// ����Ҳ಼���ļ�
		if (isShowRightBar) {
			View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
			mAbTitleBar.addRightView(rightViewMore);
			Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
			about.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					Toast.makeText(BaseActivity.this, "����¼�", 5).show();
				}
			});
		}
	}

	/**
	 * 
	 * ��������ȡ����״̬
	 * 
	 * @return
	 * @throws
	 * @date��2012-8-30 ����11:01:52
	 * @version v1.0
	 */
	public boolean getNetworkState() {
		// �������
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWork = connectivity.getActiveNetworkInfo();
		if (netWork != null) {
			return netWork.isAvailable();
		}
		return false;
	}

	/**
	 * ������������Ϣˢ��UI ��һ������0
	 * 
	 * @param what
	 * @param toast
	 * @throws
	 * @date��2013-11-19 ����1:28:08
	 * @version v1.0
	 */
	public void sendMsgUpdateUI(int what, String toast) {
		Message msg = mBaseHandler.obtainMessage(what);
		Bundle bundle = new Bundle();
		bundle.putString("Msg", toast);
		msg.setData(bundle);
		mBaseHandler.sendMessage(msg);
	}
}
