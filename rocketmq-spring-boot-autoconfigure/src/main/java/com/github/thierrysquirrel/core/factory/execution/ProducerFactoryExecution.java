package com.github.thierrysquirrel.core.factory.execution;

import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.strategy.PutProducerStrategy;
import com.github.thierrysquirrel.thread.AbstractProducerThread;

import java.util.Map;

/**
 * ClassName: ProducerFactoryExecution
 * Description:
 * date: 2019/5/3 13:25
 *
 * @author Thierry
 * @since JDK 1.8
 */
public class ProducerFactoryExecution extends AbstractProducerThread {

	public ProducerFactoryExecution(Map<String, Object> producerConsumer, RocketMessage rocketMessage, Object bean, RocketProperties rocketProperties) {
		super(producerConsumer, rocketMessage, bean, rocketProperties);
	}

	/**
	 * 开始向容器装填
	 *
	 * @param producerConsumer producerConsumer
	 * @param rocketMessage    rocketMessage
	 * @param bean             bean
	 * @param rocketProperties rocketProperties
	 */
	@Override
	protected void statsPutProducer(Map<String, Object> producerConsumer, RocketMessage rocketMessage, Object bean, RocketProperties rocketProperties) {
		PutProducerStrategy.putProducer(producerConsumer,rocketMessage,bean,rocketProperties);
	}
}
