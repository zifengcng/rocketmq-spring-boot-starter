package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;

import com.github.thierrysquirrel.annotation.RocketMessage;
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

	public static Producer createProducer(RocketMessage rocketMessage, RocketProperties rocketProperties) {
		Properties properties = ProducerPropertiesFactory.createProducerProperties(rocketMessage, rocketProperties);
		return ONSFactory.createProducer(properties);
	}

	public static OrderProducer createOrderProducer(RocketMessage rocketMessage, RocketProperties rocketProperties) {
		Properties properties = ProducerPropertiesFactory.createProducerProperties(rocketMessage, rocketProperties);
		return ONSFactory.createOrderProducer(properties);
	}

	public static TransactionProducer createTransactionProducer(RocketMessage rocketMessage, RocketProperties rocketProperties, LocalTransactionChecker localTransactionChecker) {
		Properties properties = ProducerPropertiesFactory.createProducerProperties(rocketMessage, rocketProperties);
		properties.put(PropertyKeyConst.CheckImmunityTimeInSeconds, rocketProperties.getCheckImmunityTimeInSeconds());
		return ONSFactory.createTransactionProducer(properties, localTransactionChecker);
	}
}
