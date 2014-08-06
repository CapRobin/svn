package sample.com.baidu.yun.channel.sample;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class AndroidPushMessageSample {

	public static void main(String[] args) {

		/*
		 * @brief 推送单播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
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
			// 手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3); // device_type => 1: web 2: pc 3:android
										// // 4:ios 5:wp
			// request.setChannelId(311299362L);
			// request.setUserId("311299362");

			// 可使用指定的账户为指定的用户推送消息(此处使用百度不同的百度账号测试，实际操作中可使用客户端自己的UserId进行绑定)

			// 发荣账号
			request.setChannelId(4158243755153922484L);
			request.setUserId("577121623249540842");
			// 明明账号
			// request.setChannelId(4055509595846740063L);
			// request.setUserId("656796655012285164");

			request.setMessage("百度推送测试 ----->> AndroidPushMessageSample_02");
			// 若要通知，
			 request.setMessageType(1);
			 request.setMessage("{\"title\":\"标题\",\"description\":\"百度推送测试\"}");

			// 5. 调用pushMessage接口
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

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
