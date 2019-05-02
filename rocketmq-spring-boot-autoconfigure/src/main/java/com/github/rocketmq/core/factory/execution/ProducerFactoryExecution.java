package com.github.rocketmq.core.factory.execution;


import com.github.rocketmq.autoconfigure.RocketProperties;
import com.github.rocketmq.core.strategy.ProducerStrategy;
import com.github.rocketmq.thread.AbstractProducerThread;


/**
 * ClassName: ProducerFactoryExecution <br/>
 * Description: <br/>
 * date: 2019/4/28 21:31<br/>
 *
 * @author ThierrySquirrel<br />
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

