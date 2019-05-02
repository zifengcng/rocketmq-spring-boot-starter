package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RockerMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;

import java.util.Properties;

/**
 * ClassName: ProducerFactory  
 * Description:  
 * date: 2019/4/28 21:35 
 *
 * @author ThierrySquirrel
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
