package com.chat.activity;

import java.util.List;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.haohuotong.R;
import com.way.chat.common.bean.User;
import com.way.chat.common.tran.bean.TranObject;
import com.way.chat.common.tran.bean.TranObjectType;
import com.way.chat.common.util.Constants;
import com.way.client.Client;
import com.way.client.ClientOutputThread;
import com.way.util.DialogFactory;
import com.way.util.Encode;
import com.way.util.SharePreferenceUtil;
import com.way.util.UserDB;

/**
 * 好友列表的Activity
 * 
 * @author way
 * 
 * 1、ActivityGroup的核心就是继承了该类，能够通过getLocalActivityManager()得到一个LocalActivityManager
 * 2、LocalActivityManager通过startActivity(String id, Intent intent),
*    可以与指定的Actiivty绑定，并且返回一个Window。LocalActivityManager可以同时管理多个Activity
*    Window window1 = am.startActivity("Module1", newIntent(TestView.this, ModuleView1.class));
* 3、Window可以通过getDecorView()方法，返回一个View,然后通过与指定容器的addView(View)方法,实现不同的效果
* 4、Intent.FLAG_ACTIVITY_CLEAR_TOP:如果在当前Task中，有要启动的Activity，
*    那么把该Acitivity之前的所有Activity都关掉，并把此Activity置前以避免创建Activity的实例

 */
public class FriendListActivity extends ActivityGroup implements
		OnClickListener {

	private LinearLayout layout_body_activity;
	private ImageView img_recent_chat;
	private ImageView img_friend_list;
	private ImageView img_group_friend;

	private ImageView myHeadImage;
	private TextView myName;

	private ImageView bg_recent_chat;
	private ImageView bg_friend_list;
	private ImageView bg_group_friend;

	private int current = 1; // 默认选中第2个，可以动态的改变此参数值
	private TranObject msg;
	private List<User> list;
	private MenuInflater mi;// 菜单
	private int[] imgs = { R.drawable.icon, R.drawable.f1, R.drawable.f2,
			R.drawable.f3, R.drawable.f4, R.drawable.f5, R.drawable.f6,
			R.drawable.f7, R.drawable.f8, R.drawable.f9 };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.friend_list);
//		mi = new MenuInflater(this);

//		UserDB userDB = new UserDB(FriendListActivity.this);
//
//		msg = (TranObject) getIntent().getSerializableExtra(Constants.MSGKEY);
//		if (msg == null) {// 如果为空，说明是从后台切换过来的，需要从数据库中读取好友列表信息
//			msg = new TranObject<List<User>>(null);
//			list = userDB.getUser();
//			msg.setObject(list);
//		} else {// 把好友列表信息保存到数据库
//			list = (List<User>) msg.getObject();
//			userDB.updateUser(list);
//		}
		initUI();
//		showView(current);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (!GetMsgService.isStart) {
			loadData();
		}
		new SharePreferenceUtil(this, Constants.SAVE_USER).setIsStart(false);
		NotificationManager manager = GetMsgService.mNotificationManager;
		if (manager != null) {
			manager.cancel(Constants.NOTIFY_ID);
			GetMsgService.newMsgNum = 0;
		}
		super.onResume();
	}
	
	 public void loadData(){
			
			AbThread mAbTaskThread = new AbThread();
			// 定义异步执行的对象
			final AbTaskItem item = new AbTaskItem();
			item.listener = new AbTaskListener() {

				@Override
				public void update() {
				}

				@Override
				public void get() {
					Intent service = new Intent(FriendListActivity.this, GetMsgService.class);
					startService(service);
				};
			};
			// 开始执行
			mAbTaskThread.execute(item);
	}

	private void initUI() {
		layout_body_activity = (LinearLayout) findViewById(R.id.bodylayout);

		img_recent_chat = (ImageView) findViewById(R.id.tab1);
		img_recent_chat.setOnClickListener(this);
		img_friend_list = (ImageView) findViewById(R.id.tab2);
		img_friend_list.setOnClickListener(this);
		img_group_friend = (ImageView) findViewById(R.id.tab3);
		img_group_friend.setOnClickListener(this);

		myHeadImage = (ImageView) findViewById(R.id.friend_list_myImg);
		myName = (TextView) findViewById(R.id.friend_list_myName);

		bg_recent_chat = (ImageView) findViewById(R.id.tab1_bg);
		bg_friend_list = (ImageView) findViewById(R.id.tab2_bg);
		bg_group_friend = (ImageView) findViewById(R.id.tab3_bg);

//		myHeadImage.setImageResource(imgs[list.get(0).getImg()]);
//		myName.setText(list.get(0).getName());
//		myHeadImage.setImageResource(imgs[0]);
//		myName.setText("test");
		layout_body_activity.setFocusable(true);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tab1:
			current = 0;
			showView(current);
			break;
		case R.id.tab2:
			current = 1;
			showView(current);
			break;
		case R.id.tab3:
			current = 2;
			showView(current);
			break;
		default:
			break;
		}
	}

	// 在主界面中显示其他界面
	public void showView(int current) {
		switch (current) {
		case 0:
			layout_body_activity.removeAllViews();
			View v = getLocalActivityManager().startActivity("one",
					new Intent(FriendListActivity.this, Tab1.class)
			        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
					.getDecorView();

			bg_recent_chat.setVisibility(View.VISIBLE);
			bg_friend_list.setVisibility(View.INVISIBLE);
			bg_group_friend.setVisibility(View.INVISIBLE);

			layout_body_activity.addView(v);
			break;
		case 1:
			
			layout_body_activity.removeAllViews();
			Intent i = new Intent(FriendListActivity.this, Tab2.class);
			i.putExtra(Constants.MSGKEY, msg);
			layout_body_activity.addView(getLocalActivityManager().startActivity("two", 
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
					.getDecorView());
			bg_recent_chat.setVisibility(View.INVISIBLE);
			bg_friend_list.setVisibility(View.VISIBLE);
			bg_group_friend.setVisibility(View.INVISIBLE);
			break;
		case 2:
			layout_body_activity.removeAllViews();
			layout_body_activity.addView(getLocalActivityManager()
					.startActivity("three",new Intent(FriendListActivity.this, Tab3.class) 
					.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
					.getDecorView());
			bg_recent_chat.setVisibility(View.INVISIBLE);
			bg_friend_list.setVisibility(View.INVISIBLE);
			bg_group_friend.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mi.inflate(R.menu.friend_list, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	// 菜单选项添加事件处理
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.friend_menu_add:
//			Toast.makeText(getApplicationContext(), "添加好友", 0).show();
//			Intent intent = new Intent(this,AddFriendActivity.class);
//			startActivity(intent);
			break;
		case R.id.friend_menu_exit:
//			exitDialog(FriendListActivity.this, "QQ提示", "您真的要退出吗？");
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
//		exitDialog(this,"退出", "确定要退出吗？");
	}

	// 完全退出提示窗
	private void exitDialog(Context context, String title, String msg) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 关闭服务
						Intent service = new Intent(FriendListActivity.this,
								GetMsgService.class);
						stopService(service);

						// 发送广播关闭所有activity
						Intent i = new Intent();
						i.setAction(Constants.ACTION);
						sendBroadcast(i);
						Log.i("tag", "==================="+GetMsgService.isStart);
						finish();// 关闭自己，因为自己没有继承MyActivity
						
					}
				}).setNegativeButton("取消", null).create().show();
	}
	
	
}
