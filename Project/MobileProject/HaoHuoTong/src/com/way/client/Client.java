package com.way.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.util.Log;

import com.way.chat.common.util.Constants;

public class Client {

	private Socket client;
	private ClientThread clientThread;

	public Client() {
	}

	public boolean start() {
		Log.i("tag","-------------------------Client");
		try {
			//Socket���ʷ�����
			client = new Socket();
			client.connect(new InetSocketAddress(Constants.SERVER_IP,
					Constants.SERVER_PORT), 3000);
			if (client.isConnected()) {
				 System.out.println("Connected..");
				clientThread = new ClientThread(client);
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		Log.i("tag","-------------------------Client true");
		return true;
	}

	// ֱ��ͨ��client�õ����߳�
	public ClientInputThread getClientInputThread() {
		return clientThread.getIn();
	}

	// ֱ��ͨ��client�õ�д�߳�
	public ClientOutputThread getClientOutputThread() {
		return clientThread.getOut();
	}

	// ֱ��ͨ��clientֹͣ��д��Ϣ
	public void setIsStart(boolean isStart) {
		clientThread.getIn().setStart(isStart);
		clientThread.getOut().setStart(isStart);
	}

	public class ClientThread extends Thread {

		private ClientInputThread in;
		private ClientOutputThread out;

		public ClientThread(Socket socket) {
			in = new ClientInputThread(socket);
			out = new ClientOutputThread(socket);
		}

		public void run() {
			in.setStart(true);
			out.setStart(true);
			in.start();
			out.start();
		}

		// �õ�����Ϣ�߳�
		public ClientInputThread getIn() {
			return in;
		}

		// �õ�д��Ϣ�߳�
		public ClientOutputThread getOut() {
			return out;
		}
	}
}