package sample.com.baidu.yun.channel.sample;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：AndroidPushBroadcastMessageSample.java
 * @Describe：推送消息
 * @Author: yfr5734@gmail.com
 * @Date：2014年8月5日 下午2:13:43
 * @Version v1.0
 */
public class AndroidPushBroadcastMessageSample {

	public static void main(String[] args) {

		/*
		 * @brief 推送广播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
		 */

		// 1. 设置developer平台的ApiKey/SecretKey
		String apiKey = "bepquH1kBYkblD4v3ooC7bgw";
		String secretKey = "pe8eoH3ZGRXBeA9G4nnr2na6jzK0mIvQ";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

		// 2. 创建BaiduChannelClient对象实例
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);

		// 3. 若要了解交互细节，请注册YunLogHandler类
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {

			// 4. 创建请求类对象
			PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
			request.setDeviceType(3); // device_type => 1: web 2: pc 3:android
										// 4:ios 5:wp

			request.setMessage("百度推送测试 ----->> AndroidPushBroadcastMessageSample_01");
			// 若要通知，
			 request.setMessageType(1);
			 request.setMessage("{\"title\":\"Notify_标题\",\"description\":\"Notify_内容描述\"}");

			// 5. 调用pushMessage接口
			PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);

			// 6. 认证推送成功
			System.out.println("push amount : " + response.getSuccessAmount());

		} catch (ChannelClientException e) {
			// 处理客户端错误异常
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 处理服务端错误异常
			System.out.println(String.format("request_id: %d, error_code: %d, error_message: %s", e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
		}

	}

}
