package com.github.rocketmq.core.factory;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.github.rocketmq.annotation.OrderMessage;
import com.github.rocketmq.annotation.RockerMessage;
import com.github.rocketmq.annotation.TransactionMessage;
import com.github.rocketmq.autoconfigure.RocketProperties;
import com.github.rocketmq.core.producer.DefaultLocalTransactionChecker;

import java.util.Properties;

/**
 * ClassName: ProducerFactory <br/>
 * Description: <br/>
 * date: 2019/4/28 21:35<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
public class ProducerFactory {
	public static Producer createProducer(RockerMessage rockerMessage, RocketProperties rocketProperties){
		Properties properties = ProducerPropertiesFactory.createProducerProperties(rockerMessage, rocketProperties);
		return ONSFactory.createProducer(properties);
	}
	public static OrderProducer createProducer(OrderMessage orderMessage, RocketProperties rocketProperties){
		Properties properties = ProducerPropertiesFactory.createProducerProperties(orderMessage,rocketProperties);
		return ONSFactory.createOrderProducer(properties);
	}
	public static TransactionProducer createProducer(TransactionMessage transactionMessage, RocketProperties rocketProperties, LocalTransactionChecker localTransactionChecker){
		Properties properties = ProducerPropertiesFactory.createProducerProperties(transactionMessage, rocketProperties);
		return ONSFactory.createTransactionProducer(properties,localTransactionChecker);
	}
}
