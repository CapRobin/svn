package com.way.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import com.way.chat.common.tran.bean.TranObject;

public class ClientInputThread extends Thread {
	private Socket socket;
	private TranObject msg;
	private String getMyMsg = null;
	private boolean isStart = true;
	private ObjectInputStream ois;
	private String getMsg = null;
	private MessageListener messageListener;
	private BufferedReader bufferedReader = null;

	public ClientInputThread(Socket socket) {
		this.socket = socket;
	}

	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	@Override
	public void run() {
		
		//Java后台返回数据
//		try {
//			while (isStart) {
//				msg = (TranObject) ois.readObject();
//				System.out.println("Get Msg is ---------------------->>" + msg);
//				messageListener.Message(msg);
//			}
//			ois.close();
//			if (socket != null)
//				socket.close();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		// 读取服务端数据方法1
		try {

			String string1 = null;
			String string2 = "";
			while (isStart) {

				InputStream ips = socket.getInputStream();// 定义输入流，来自于socket的输入流
				byte buffer[] = new byte[1024];
				ips.read(buffer);
				getMsg = new String(buffer, "GBK").trim();
				System.out.println("GetMsg is ---------->>" + getMsg);
				messageListener.MyMessage(getMsg);
				
//				try {
//					JSONObject jsonObject1 = new JSONObject(getMsg);
//					String msgType = jsonObject1.getString("MsgTypeValue");
////					String jsonStr1 = jsonObject1.getString("Chat");
////					JSONObject jsonObject2 = new JSONObject(jsonObject1.getString("Chat"));
//					String getMsgtype = new JSONObject(jsonObject1.getString("Chat")).getString("Msgtype");
////					JSONObject jsonObject3 = new JSONObject(jsonStr2);
////					String jsonStr3 = jsonObject3.getString("UserGUID");
//					
//					JSONObject jsonObject = new JSONObject(new JSONObject(jsonObject1.getString("Chat")).getString("MsgConnect"));
//					String getUserGUID = jsonObject.getString("UserGUID");
//					System.out.println("Get UserGUID is --------------->>" + getUserGUID);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				// System.out.println("getMsg is -------->>"+getMsg);
//				System.out.println("string1 is --------------->>" + string1);
//				System.out.println("string2 is --------------->>" + string2);
//				string1 = getMsg;
//				if (!string1.equals(string2)) {
//					messageListener.MyMessage(string1);
//					string2 = string1;
//					System.out.println("监听事件已发送--------------->>");
//				}
//				sleep(2000);
			}
			ois.close();
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		// 读取服务端数据方法2
//		try {
//			bufferedReader = new BufferedReader(new InputStreamReader(
//					socket.getInputStream()));
//			while (isStart) {
//
//				try {
//					if ((getMsg = bufferedReader.readLine()) != null) {
//						System.out
//								.println("Get Msg is ---------------------->>"
//										+ getMsg);
//						messageListener.MyMessage(getMsg);
//					}
//
//				} catch (Exception e) {
//
//					e.printStackTrace();
//
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// 读取服务端数据方法3
//		try {
//			bufferedReader = new BufferedReader(new InputStreamReader(
//					socket.getInputStream()));
//			while (isStart) {
//				DataInputStream input = new DataInputStream(
//						socket.getInputStream());
//				getMsg = input.readUTF();
//				System.out.println("服务器端返回过来的是------------>>>: " + getMsg);
//				messageListener.MyMessage(getMsg);
//
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("Get Msg is ---------------------->>" + getMsg);
	}
}
