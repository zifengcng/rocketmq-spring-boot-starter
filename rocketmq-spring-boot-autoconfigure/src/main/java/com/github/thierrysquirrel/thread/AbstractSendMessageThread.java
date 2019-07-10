package com.github.thierrysquirrel.thread;

import com.github.thierrysquirrel.annotation.RocketMessage;
import lombok.Data;

import java.util.Map;

/**
 * ClassName: AbstractSendMessageThread
 * Description:
 * date: 2019/4/29 21:39
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractSendMessageThread implements Runnable {
	private Map<String, Object> consumerContainer;
	private RocketMessage rocketMessage;
	private Object message;
	private byte[] bytes;

	public AbstractSendMessageThread(Map<String, Object> consumerContainer, RocketMessage rocketMessage, Object message, byte[] bytes) {
		this.consumerContainer = consumerContainer;
		this.rocketMessage = rocketMessage;
		this.message = message;
		this.bytes = bytes;
	}

	/**
	 * 开始发送消息
	 *
	 * @param consumerContainer consumerContainer
	 * @param rocketMessage     rocketMessage
	 * @param message           message
	 * @param bytes             bytes
	 */
	protected abstract void statsSendMessage(Map<String, Object> consumerContainer, RocketMessage rocketMessage, Object message, byte[] bytes);

	@Override
	public void run() {
		statsSendMessage(consumerContainer,
				rocketMessage,
				message,
				bytes);
	}
}
