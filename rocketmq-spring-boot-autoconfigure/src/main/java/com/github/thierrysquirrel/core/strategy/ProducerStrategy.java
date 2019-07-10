package com.github.thierrysquirrel.core.strategy;

import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.core.factory.ProducerConsumerFactory;
import com.github.thierrysquirrel.core.factory.SendMessageFactory;
import com.github.thierrysquirrel.error.RocketException;

import java.util.Map;

/**
 * ClassName: ProducerStrategy
 * Description:
 * date: 2019/4/29 21:34
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ProducerStrategy {
	private ProducerStrategy() {
	}

	public static void statsSendMessage(Map<String, Object> consumerContainer, RocketMessage rocketMessage, Object message, byte[] bytes) throws RocketException {
		if (message instanceof CommonMessage) {
			CommonMessage commonMessage = (CommonMessage) message;
			Producer producer = ProducerConsumerFactory.getProducer(consumerContainer, rocketMessage, commonMessage);
			SendMessageFactory.sendMessage(producer, commonMessage, bytes);
			return;
		}
		if (message instanceof OrderMessage) {
			OrderMessage orderMessage = (OrderMessage) message;
			OrderProducer orderProducer = ProducerConsumerFactory.getProducer(consumerContainer, rocketMessage, orderMessage);
			SendMessageFactory.sendMessage(orderProducer, orderMessage, bytes);
			return;
		}
		if (message instanceof TransactionMessage) {
			TransactionMessage transactionMessage = (TransactionMessage) message;
			TransactionProducer transactionProducer = ProducerConsumerFactory.getProducer(consumerContainer, rocketMessage, transactionMessage);
			SendMessageFactory.sendMessage(transactionProducer, transactionMessage, bytes);
		}
	}
}
