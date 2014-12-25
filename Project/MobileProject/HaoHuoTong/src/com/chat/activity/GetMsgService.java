package com.chat.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.google.gson.JsonObject;
import com.haohuotong.R;
import com.haohuotong.activity.FriendActivity;
import com.haohuotong.activity.SettingCenterActivity;
import com.haohuotong.global.MyApplication;
import com.way.chat.common.bean.TextMessage;
import com.way.chat.common.bean.User;
import com.way.chat.common.tran.bean.TranObject;
import com.way.chat.common.tran.bean.TranObjectType;
import com.way.chat.common.util.Constants;
import com.way.client.Client;
import com.way.client.ClientInputThread;
import com.way.client.ClientOutputThread;
import com.way.client.MessageListener;
import com.way.util.MessageDB;
import com.way.util.MyDate;
import com.way.util.SharePreferenceUtil;

/**
 * 收取消息服务
 * 
 * @author way
 * 
 */
public class GetMsgService extends Service {
	private static final int MSG = 0x001;
	public static Client client = new Client();
	public static NotificationManager mNotificationManager;
	public static boolean isStart = false;//是否与服务器连接�?
	public static int newMsgNum = 0;// 新消息条�?
	private Notification mNotification;
	private Context mContext = this;
	private SharePreferenceUtil util;
	private MessageDB messageDB;
	private MyApplication application = null;

	private BroadcastReceiver backKeyReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "QQ进入后台运行", 0).show();
//			setMsgNotification();
		}
	};
	// 用来更新通知栏消息的handler
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case MSG:
//				newMsgNum++;// 每收到一次消息，自增�?��
//				TranObject<TextMessage> textObject = (TranObject<TextMessage>) msg
//						.getData().getSerializable("msg");
//				System.out.println(textObject);
//				if (textObject != null) {
//					int form = textObject.getFromUser();// 消息从哪里来
//					String content = textObject.getObject().getMessage();// 消息内容
//
//					ChatMsgEntity entity = new ChatMsgEntity("",
//							MyDate.getDateEN(), content, -1, true);// 收到的消�?
//					messageDB.saveMsg(form, entity);// 保存到数据库
//					// 更新通知栏
//					int icon = R.drawable.notify_newmessage;
//					CharSequence tickerText = form + ":" + content;
//					long when = System.currentTimeMillis();
//					mNotification = new Notification(icon, tickerText, when);
//					// RemoteViews view = mNotification.contentView;
//					// view.setTextViewText(R.id.notify_num, "("
//					// + newMsgNum + "条新消息)");
//
//					mNotification.flags = Notification.FLAG_NO_CLEAR;
//					// 设置默认声音
//					mNotification.defaults |= Notification.DEFAULT_SOUND;
//					// 设定震动(需加VIBRATE权限)
//					mNotification.defaults |= Notification.DEFAULT_VIBRATE;
//					mNotification.contentView = null;
//
//					Intent intent = new Intent(mContext,
//							FriendListActivity.class);
//					PendingIntent contentIntent = PendingIntent.getActivity(
//							mContext, 0, intent, 0);
//					mNotification.setLatestEventInfo(mContext, util.getName()
//							+ " (" + newMsgNum + "条新消息)", content,
//							contentIntent);
//				}
//				mNotificationManager.notify(Constants.NOTIFY_ID, mNotification);
//				break;
//
//			default:
//				break;
//			}
			
			switch (msg.what) {
			case 0:
//				newMsgNum++;// 每收到一次消息，自增�?��
				application.newMsgNum ++;
//				TranObject<TextMessage> textObject = (TranObject<TextMessage>) msg
//						.getData().getSerializable("msg");
//				System.out.println(textObject);
				String getMsg = msg.getData().getString("Msg");
				if (!getMsg.isEmpty()) {
//					int form = textObject.getFromUser();// 消息从哪里来
					String content = getMsg;// 消息内容


					try {
						JSONObject jsonObject1 = new JSONObject(getMsg);
						String msgType = jsonObject1.getString("MsgTypeValue");
//						String jsonStr1 = jsonObject1.getString("Chat");
//						JSONObject jsonObject2 = new JSONObject(jsonStr1);
//						String jsonStr2 = jsonObject2.getString("MsgConnect");
//						JSONObject jsonObject3 = new JSONObject(jsonStr2);
//						String jsonStr3 = jsonObject3.getString("UserGUID");
						
						String firstMsgType= new JSONObject(jsonObject1.getString("Chat")).getString("Msgtype");
						if (firstMsgType.equals("SubMitInfo")) {
							//保存连接成功返回信息到全局
							application.firstGetMsg = getMsg;
						}
						JSONObject jsonObject = new JSONObject(new JSONObject(jsonObject1.getString("Chat")).getString("MsgConnect"));
						String getUserGUID = jsonObject.getString("UserGUID");
						application.socketRegisterCode = getUserGUID;
						System.out.println("Get UserGUID is --------------->>" + getUserGUID);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//					ChatMsgEntity entity = new ChatMsgEntity("",
//							MyDate.getDateEN(), content, -1, true);// 收到的消�?
//					messageDB.saveMsg(form, entity);// 保存到数据库
					// 更新通知栏
					int icon = R.drawable.notify_newmessage;
					CharSequence tickerText = "推送消息" + ":" + content;
					long when = System.currentTimeMillis();
					mNotification = new Notification(icon, tickerText, when);
//					 RemoteViews view = mNotification.contentView;
//					 view.setTextViewText(R.id.notify_num, "("
//					 + newMsgNum + "条新消息)");

					mNotification.flags = Notification.FLAG_NO_CLEAR;
					mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
					// 设置默认声音
					mNotification.defaults |= Notification.DEFAULT_SOUND;
					// 设定震动(需加VIBRATE权限)
					mNotification.defaults |= Notification.DEFAULT_VIBRATE;
					mNotification.contentView = null;

					application.getPushMsg = content;		//保存推送消息到全局变量
					Intent intent = new Intent(mContext,
							SettingCenterActivity.class);
					PendingIntent contentIntent = PendingIntent.getActivity(
							mContext, 0, intent, 0);
					mNotification.setLatestEventInfo(mContext, util.getName()
							+ " (" + application.newMsgNum + "条新消息)", content,
							contentIntent);
				}
				mNotificationManager.notify(Constants.NOTIFY_ID, mNotification);
				break;

			default:
				break;
			}
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {// 在onCreate方法里面注册广播接收�?
		// TODO Auto-generated method stub
		super.onCreate();
		messageDB = new MessageDB(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.BACKKEY_ACTION);
		registerReceiver(backKeyReceiver, filter);
		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		application = (MyApplication) getApplication();
		util = new SharePreferenceUtil(getApplicationContext(),
				Constants.SAVE_USER);
		loadData();
	}
	
    public void loadData(){
		
		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对�?
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				MyMessage();
			}

			@Override
			public void get() {
				isStart = client.start();
				Log.i("tag","-----------GetMsgService.isStart="+isStart);
			};
		};
		// �?��执行
		mAbTaskThread.execute(item);
     }

	public void MyMessage(){
		ClientInputThread in = client.getClientInputThread();
//		in.setMessageListener(new MessageListener() {
//
//			@Override
//			public void Message(TranObject msg) {
//				// System.out.println("GetMsgService:" + msg);
//				if (util.getIsStart()) {// 如果 是在后台运行，就更新通知栏，否则就发送广播给Activity
//					if (msg.getType() == TranObjectType.MESSAGE || msg.getType() == TranObjectType.AUCTIONINFO) {// 只处理文本消息类�?
//						// System.out.println("收到新消�?);
//						// 把消息对象发送到handler去处�?
//						Message message = handler.obtainMessage();
//						message.what = MSG;
//						message.getData().putSerializable("msg", msg);
//						handler.sendMessage(message);
//					}
//				} else {
//					Intent broadCast = new Intent();
//					broadCast.setAction(Constants.ACTION);
//					broadCast.putExtra(Constants.MSGKEY, msg);
//					sendBroadcast(broadCast);// 把收到的消息已广播的形式发�?出去
//				}
//			}
//		});
		
		in.setMessageListener(new MessageListener() {

			@Override
			public void MyMessage(String msg) {
				// System.out.println("GetMsgService:" + msg);
				if (util.getIsStart()) {// 如果 是在后台运行，就更新通知栏，否则就发送广播给Activity
					System.out.println("Get Msg is --------------->>" + msg);
					sendMsgUpdateUI(0, msg);
//					if (msg.getType() == TranObjectType.MESSAGE || msg.getType() == TranObjectType.AUCTIONINFO) {// 只处理文本消息类�?
//						// System.out.println("收到新消�?);
//						// 把消息对象发送到handler去处�?
//						Message message = handler.obtainMessage();
//						message.what = MSG;
//						message.getData().putSerializable("msg", msg);
//						handler.sendMessage(message);
//					}
				} else {
					System.out.println("Get Msg is --------------->>" + msg);
					sendMsgUpdateUI(0, msg);
//					Intent broadCast = new Intent();
//					broadCast.setAction(Constants.ACTION);
//					broadCast.putExtra(Constants.MSGKEY, msg);
//					sendBroadcast(broadCast);// 把收到的消息已广播的形式发�?出去
				}
			}

		});
	}

	@Override
	// 在服务被摧毁时，做一些事�?
	public void onDestroy() {
		super.onDestroy();
		messageDB.close();
		unregisterReceiver(backKeyReceiver);
		mNotificationManager.cancel(Constants.NOTIFY_ID);
		// 给服务器发�?下线消息
		if (isStart) {
			ClientOutputThread out = client.getClientOutputThread();
			TranObject<User> o = new TranObject<User>(TranObjectType.LOGOUT);
			User u = new User();
			u.setId(Integer.parseInt(util.getId()));
			o.setObject(u);
//			out.setMsg(o);
			out.setMsg("测试数据");
			// 发�?完之后，关闭client
			out.setStart(false);
			client.getClientInputThread().setStart(false);
			
		}
		// Intent intent = new Intent(this, GetMsgService.class);
		// startService(intent);
	}

	/**
	 * 创建通知
	 */
	private void setMsgNotification() {
		int icon = R.drawable.notify;
		CharSequence tickerText = "";
		long when = System.currentTimeMillis();
		mNotification = new Notification(icon, tickerText, when);

		// 放置�?正在运行"栏目�?
		mNotification.flags = Notification.FLAG_ONGOING_EVENT;

		RemoteViews contentView = new RemoteViews(mContext.getPackageName(),
				R.layout.notify_view);
		contentView.setTextViewText(R.id.notify_name, util.getName());
		contentView.setTextViewText(R.id.notify_msg, "手机QQ正在后台运行");
		contentView.setTextViewText(R.id.notify_time, MyDate.getDateEN());
		// 指定个�?化视�?
		mNotification.contentView = contentView;

//		Intent intent = new Intent(this, FriendListActivity.class);
		Intent intent = new Intent(this, FriendActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// 指定内容意图
		mNotification.contentIntent = contentIntent;
		mNotificationManager.notify(Constants.NOTIFY_ID, mNotification);
	}
	
	/**
	 * 判断消息类型
	 * @param string
	 */
	private void getMsgType(String string) {
		if (string.equals("ChatMode")) {
			
		}else if (string.equals("ChatMode")) {
			
		}else if (string.equals("ChatMode")) {
			
		}else if (string.equals("ChatMode")) {
			
		}else if (string.equals("ChatMode")) {
			
		}else if (string.equals("Chat")) {
			
		}
		
	}
	
	/**
	 * 描述：发送消息刷新UI
	 * 
	 * @param what
	 * @param toast
	 * @throws
	 * @date：2013-7-11 下午6:01:11
	 * @version v1.0
	 */
	public void sendMsgUpdateUI(int what, String message) {
		Message msg = handler.obtainMessage(what);
		Bundle bundle = new Bundle();
		bundle.putString("Msg", message);
		msg.setData(bundle);
		handler.sendMessage(msg);
	}
}

