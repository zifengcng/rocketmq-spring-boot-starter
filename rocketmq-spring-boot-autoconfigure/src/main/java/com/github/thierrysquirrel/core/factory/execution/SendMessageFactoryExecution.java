package com.github.thierrysquirrel.core.factory.execution;


import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.strategy.ProducerStrategy;
import com.github.thierrysquirrel.error.RocketException;
import com.github.thierrysquirrel.thread.AbstractSendMessageThread;

import java.util.Map;


/**
 * ClassName: SendMessageFactoryExecution
 * Description:
 * date: 2019/4/28 21:31
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SendMessageFactoryExecution extends AbstractSendMessageThread {


	public SendMessageFactoryExecution(Map<String, Object> consumerContainer, RocketMessage rocketMessage, Object message, RocketProperties rocketProperties, byte[] bytes) {
		super(consumerContainer, rocketMessage, message, rocketProperties, bytes);
	}

	/**
	 * 开始发送消息
	 *
	 * @param consumerContainer consumerContainer
	 * @param rocketMessage rocketMessage
	 * @param message           message
	 * @param rocketProperties  rocketProperties
	 * @param bytes             bytes
	 */
	@Override
	protected void statsSendMessage(Map<String, Object> consumerContainer, RocketMessage rocketMessage, Object message, RocketProperties rocketProperties, byte[] bytes) {
		try {
			ProducerStrategy.statsSendMessage(consumerContainer,rocketMessage, message, rocketProperties, bytes);
		} catch (RocketException e) {
			e.printStackTrace();
		}
	}
}

