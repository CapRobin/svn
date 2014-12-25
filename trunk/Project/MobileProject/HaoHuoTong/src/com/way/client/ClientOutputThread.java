package com.way.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.way.chat.common.tran.bean.TranObject;
import com.way.chat.common.tran.bean.TranObjectType;

public class ClientOutputThread extends Thread {
	private Socket socket;
//	private ObjectOutputStream oos;
	private boolean isStart = true;
//	private TranObject msg;
	private String sendMsg = "无数据";

	public ClientOutputThread(Socket socket) {
		this.socket = socket;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

//	public void setMsg(TranObject msg) {
//		this.msg = msg;
//		synchronized (this) {
//			notify();
//		}
//	}
	public void setMsg(String msg) {
		this.sendMsg = msg;
		synchronized (this) {
			notify();
		}
	}

	@Override
	public void run() {
		try {
//			while (isStart) {
//				if (msg != null) {
//					oos.writeObject(msg);
//					oos.flush();
//					if (msg.getType() == TranObjectType.LOGOUT) {// 濡傛灉鏄彂閫佷笅绾跨殑娑堟伅锛屽氨鐩存帴璺冲嚭寰幆
//						break;
//					}
//					synchronized (this) {
//						wait();// 鍙戦�瀹屾秷鎭悗锛岀嚎绋嬭繘鍏ョ瓑寰呯姸鎬�
//					}
//				}
//			}
//			oos.close();// 寰幆缁撴潫鍚庯紝鍏抽棴杈撳嚭娴佸拰socket
			
			while (isStart) {
				if (!sendMsg.isEmpty()) {
					OutputStream ops = socket.getOutputStream();//定义一个输出流，来自于Socket输出流
					ops.write(sendMsg.getBytes("GBK"));//向输出流中写入数据
					ops.flush();//刷行输出流
					synchronized (this) {
						wait();// 鍙戦�瀹屾秷鎭悗锛岀嚎绋嬭繘鍏ョ瓑寰呯姸鎬�
					}
				}
			}
//			ops.close();// 寰幆缁撴潫鍚庯紝鍏抽棴杈撳嚭娴佸拰socket
			if (socket != null)
				socket.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
