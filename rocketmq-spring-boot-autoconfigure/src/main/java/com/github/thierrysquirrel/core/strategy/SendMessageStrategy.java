package com.github.thierrysquirrel.core.strategy;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.core.producer.DefaultSendCallback;
import com.github.thierrysquirrel.core.producer.MessageSendType;


/**
 * ClassName: SendMessageStrategy
 * Description:
 * date: 2019/4/29 23:37
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SendMessageStrategy {
	private SendMessageStrategy() {
	}

	public static void send(CommonMessage commonMessage, Producer producer, Message message) {
		if (commonMessage.messageSendType().equals(MessageSendType.SEND)) {
			producer.send(message);
			return;
		}
		if (commonMessage.messageSendType().equals(MessageSendType.SEND_ASYNC)) {
			producer.sendAsync(message, new DefaultSendCallback());
			return;
		}
		if (commonMessage.messageSendType().equals(MessageSendType.SEND_ONE_WAY)) {
			producer.sendOneway(message);
		}
	}
}
