package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.error.RocketException;

import java.util.Map;

/**
 * ClassName: ProducerConsumerFactory
 * Description:
 * date: 2019/5/3 13:45
 *
 * @author Thierry
 * @since JDK 1.8
 */
public class ProducerConsumerFactory {
	public static String getProducerConsumerKey(RocketMessage rocketMessage, CommonMessage commonMessage) {
		return rocketMessage.groupID() +
				commonMessage.topic() +
				commonMessage.tag();
	}

	public static String getProducerConsumerKey(RocketMessage rocketMessage, OrderMessage orderMessage) {
		return rocketMessage.groupID() +
				orderMessage.topic() +
				orderMessage.tag();
	}

	public static String getProducerConsumerKey(RocketMessage rocketMessage, TransactionMessage transactionMessage) {
		return rocketMessage.groupID() +
				transactionMessage.topic() +
				transactionMessage.tag();
	}

	public static Producer getProducer(Map<String, Object> consumerContainer, RocketMessage rocketMessage, CommonMessage commonMessage) throws RocketException {
		String producerConsumerKey = ProducerConsumerFactory.getProducerConsumerKey(rocketMessage, commonMessage);
		return (Producer) consumerContainer.get(producerConsumerKey);
	}

	public static OrderProducer getProducer(Map<String, Object> consumerContainer, RocketMessage rocketMessage, OrderMessage orderMessage) throws RocketException {
		String producerConsumerKey = ProducerConsumerFactory.getProducerConsumerKey(rocketMessage, orderMessage);
		return (OrderProducer) consumerContainer.get(producerConsumerKey);
	}

	public static TransactionProducer getProducer(Map<String, Object> consumerContainer, RocketMessage rocketMessage, TransactionMessage transactionMessage) throws RocketException {
		String producerConsumerKey = ProducerConsumerFactory.getProducerConsumerKey(rocketMessage, transactionMessage);
		return (TransactionProducer) consumerContainer.get(producerConsumerKey);
	}
}
