package com.github.thierrysquirrel.core.factory.execution;


import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.strategy.ProducerStrategy;
import com.github.thierrysquirrel.thread.AbstractProducerThread;


/**
 * ClassName: ProducerFactoryExecution  
 * Description:  
 * date: 2019/4/28 21:31 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ProducerFactoryExecution extends AbstractProducerThread {


	public ProducerFactoryExecution(Object message, RocketProperties rocketProperties, byte[] bytes) {
		super(message, rocketProperties, bytes);
	}

	/**
	 * 开始发送消息
	 *
	 * @param message          message
	 * @param rocketProperties rocketProperties
	 * @param bytes            bytes
	 */
	@Override
	protected void statsSendMessage(Object message, RocketProperties rocketProperties, byte[] bytes) {
		ProducerStrategy.statsSendMessage(message, rocketProperties, bytes);
	}
}

