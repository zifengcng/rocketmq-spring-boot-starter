package com.github.thierrysquirrel.core.strategy;

import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.ProducerConsumerFactory;
import com.github.thierrysquirrel.core.factory.ProducerFactory;
import com.github.thierrysquirrel.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.core.producer.DefaultLocalTransactionChecker;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: PutProducerStrategy <br/>
 * Description: <br/>
 * date: 2019/5/3 18:37<br/>
 *
 * @author Thierry<br />
 * @since JDK 1.8
 */
public class PutProducerStrategy {
	public static void putProducer(Map<String, Object> producerConsumer, RocketMessage rocketMessage, Object bean, RocketProperties rocketProperties) {
		if (bean instanceof CommonMessage) {
			CommonMessage commonMessage = (CommonMessage) bean;
			String producerConsumerKey = ProducerConsumerFactory.getProducerConsumerKey(rocketMessage, commonMessage);
			Producer producer = ProducerFactory.createProducer(rocketMessage, rocketProperties);
			ThreadPoolExecutor callbackThreadPoolExecutor = ThreadPoolFactory.createCallbackThreadPoolExecutor(rocketProperties);
			producer.start();
			producer.setCallbackExecutor(callbackThreadPoolExecutor);
			producerConsumer.put(producerConsumerKey, producer);
			return;
		}
		if (bean instanceof OrderMessage) {
			OrderMessage orderMessage = (OrderMessage) bean;
			String producerConsumerKey = ProducerConsumerFactory.getProducerConsumerKey(rocketMessage, orderMessage);
			OrderProducer orderProducer = ProducerFactory.createOrderProducer(rocketMessage, rocketProperties);
			orderProducer.start();
			producerConsumer.put(producerConsumerKey, orderProducer);
			return;
		}
		if (bean instanceof TransactionMessage) {
			TransactionMessage transactionMessage = (TransactionMessage) bean;
			String producerConsumerKey = ProducerConsumerFactory.getProducerConsumerKey(rocketMessage, transactionMessage);
			DefaultLocalTransactionChecker defaultLocalTransactionChecker = new DefaultLocalTransactionChecker(transactionMessage.transactionStatus());
			TransactionProducer transactionProducer = ProducerFactory.createTransactionProducer(rocketMessage, rocketProperties, defaultLocalTransactionChecker);
			transactionProducer.start();
			producerConsumer.put(producerConsumerKey, transactionProducer);
		}
	}
}
