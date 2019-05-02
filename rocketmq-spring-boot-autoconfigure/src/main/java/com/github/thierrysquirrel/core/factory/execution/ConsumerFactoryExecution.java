package com.github.thierrysquirrel.core.factory.execution;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.OrderConsumer;
import com.github.thierrysquirrel.annotation.MessageListener;
import com.github.thierrysquirrel.annotation.RocketListener;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.consumer.DefaultMessageListener;
import com.github.thierrysquirrel.core.consumer.DefaultMessageOrderListener;
import com.github.thierrysquirrel.core.factory.ConsumerFactory;
import com.github.thierrysquirrel.core.factory.ConsumerPropertiesFactory;
import com.github.thierrysquirrel.thread.AbstractConsumerThread;

import java.util.Properties;

/**
 * ClassName: ConsumerFactoryExecution  
 * Description:  
 * date: 2019/4/27 16:05 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumerFactoryExecution extends AbstractConsumerThread {


	public ConsumerFactoryExecution(RocketProperties rocketProperties, RocketListener rocketListener, MessageListener consumerListener, MethodFactoryExecution methodFactoryExecution) {
		super(rocketProperties, rocketListener, consumerListener, methodFactoryExecution);
	}

	@Override
	public void statsConsumer(RocketProperties rocketProperties,
	                   RocketListener rocketListener,
	                   MessageListener consumerListener,
	                   MethodFactoryExecution methodFactoryExecution) {

		Properties properties = ConsumerPropertiesFactory.createConsumerProperties(rocketProperties, rocketListener);
		if (consumerListener.orderConsumer()) {
			properties.put(PropertyKeyConst.SuspendTimeMillis, rocketProperties.getSuspendTimeMilli());
			OrderConsumer orderConsumer = ConsumerFactory.createOrderConsumer(properties);
			orderConsumer.subscribe(consumerListener.topic(), consumerListener.tag(), new DefaultMessageOrderListener(methodFactoryExecution));
			orderConsumer.start();
			return;
		}

		Consumer consumer = ConsumerFactory.createConsumer(properties);
		consumer.subscribe(consumerListener.topic(), consumerListener.tag(), new DefaultMessageListener(methodFactoryExecution));
		consumer.start();


	}
}
