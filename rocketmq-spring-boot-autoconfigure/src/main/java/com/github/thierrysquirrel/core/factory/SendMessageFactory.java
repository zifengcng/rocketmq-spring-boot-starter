package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RockerMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.producer.DefaultLocalTransactionChecker;
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
	public static void sendMessage(RockerMessage rockerMessage, RocketProperties rocketProperties, byte[] bytes) {
		Producer producer = ProducerFactory.createProducer(rockerMessage, rocketProperties);
		producer.start();
		System.out.println("安全");
		Message message = MessageFactory.createMessage(rockerMessage, bytes);
		long startDeliverTime = rockerMessage.timeUnit().toMillis(rockerMessage.startDeliverTime());
		message.setStartDeliverTime(System.currentTimeMillis() + startDeliverTime);
		SendMessageStrategy.send(rockerMessage, producer, message);
		producer.shutdown();
	}

	public static void sendMessage(OrderMessage orderMessage, RocketProperties rocketProperties, byte[] bytes) {
		OrderProducer orderProducer = ProducerFactory.createProducer(orderMessage, rocketProperties);
		orderProducer.start();
		Message message = MessageFactory.createMessage(orderMessage, bytes);
		orderProducer.send(message, orderMessage.shardingKey());
		orderProducer.shutdown();
	}

	public static void sendMessage(TransactionMessage transactionMessage, RocketProperties rocketProperties, byte[] bytes) {
		DefaultLocalTransactionChecker defaultLocalTransactionChecker = new DefaultLocalTransactionChecker(transactionMessage.transactionStatus());
		TransactionProducer transactionProducer = ProducerFactory.createProducer(transactionMessage, rocketProperties, defaultLocalTransactionChecker);
		transactionProducer.start();

		Message message = MessageFactory.createMessage(transactionMessage, bytes);

		transactionProducer.send(message, new DefaultLocalTransactionExecuter(), null);

		transactionProducer.shutdown();
	}
}
