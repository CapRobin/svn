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
	public Button rightTitleBut = null;
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
	 * @Describe：TODO
	 * @param titleMsg					标题名称
	 * @param isShowRightBar		是否显示右边的按钮
	 * @Throws:
	 * @Date：2014年4月29日 上午10:38:04
	 * @Version v1.0
	 * 
	 */
	protected void initTitleLayout(String titleMsg, boolean isShowRightBar) {
		AbTitleBar mAbTitleBar = this.getTitleBar();

		// 添加左侧布局文件
		mAbTitleBar.setTitleText(titleMsg);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		mAbTitleBar.setTitleTextSize(16);

		// 添加右侧布局文件
		if (isShowRightBar) {
			View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
			mAbTitleBar.addRightView(rightViewMore);
			rightTitleBut = (Button) rightViewMore.findViewById(R.id.moreBtn);
			rightTitleBut.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					Toast.makeText(BaseActivity.this, "点击事件", 5).show();
				}
			});
		}
	}

	/**
	 * 
	 * 描述：获取网络状态
	 * 
	 * @return
	 * @throws
	 * @date：2012-8-30 上午11:01:52
	 * @version v1.0
	 */
	public boolean getNetworkState() {
		// 检测网络
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWork = connectivity.getActiveNetworkInfo();
		if (netWork != null) {
			return netWork.isAvailable();
		}
		return false;
	}

	/**
	 * 描述：发送消息刷新UI 第一参数是0
	 * 
	 * @param what
	 * @param toast
	 * @throws
	 * @date：2013-11-19 下午1:28:08
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
