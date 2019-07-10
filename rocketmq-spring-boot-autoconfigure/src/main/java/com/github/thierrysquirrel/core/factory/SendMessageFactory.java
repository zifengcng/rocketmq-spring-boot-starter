package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.core.producer.DefaultLocalTransactionExecuter;
import com.github.thierrysquirrel.core.strategy.SendMessageStrategy;

/**
 * ClassName: SendMessageFactory
 * Description:
 * date: 2019/4/29 21:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SendMessageFactory {
	private SendMessageFactory() {
	}

	public static void sendMessage(Producer producer, CommonMessage commonMessage, byte[] bytes) {
		Message message = MessageFactory.createMessage(commonMessage, bytes);
		long startDeliverTime = commonMessage.timeUnit().toMillis(commonMessage.startDeliverTime());
		message.setStartDeliverTime(System.currentTimeMillis() + startDeliverTime);
		SendMessageStrategy.send(commonMessage, producer, message);

	}

	public static void sendMessage(OrderProducer orderProducer, OrderMessage orderMessage, byte[] bytes) {
		Message message = MessageFactory.createMessage(orderMessage, bytes);
		orderProducer.send(message, orderMessage.shardingKey());

	}

	public static void sendMessage(TransactionProducer transactionProducer, TransactionMessage transactionMessage, byte[] bytes) {
		Message message = MessageFactory.createMessage(transactionMessage, bytes);
		transactionProducer.send(message, new DefaultLocalTransactionExecuter(), null);
	}
}
