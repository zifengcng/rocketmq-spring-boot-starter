package com.github.thierrysquirrel.thread;

import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import lombok.Data;

/**
 * ClassName: AbstractProducerThread  
 * Description:  
 * date: 2019/4/29 21:39 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractProducerThread implements Runnable {
	private Object message;
	private RocketProperties rocketProperties;
	private byte[] bytes;

	public AbstractProducerThread(Object message, RocketProperties rocketProperties, byte[] bytes) {
		this.message = message;
		this.rocketProperties = rocketProperties;
		this.bytes = bytes;
	}

	/**
	 * 开始发送消息
	 * @param message message
	 * @param rocketProperties rocketProperties
	 * @param bytes bytes
	 */
	protected abstract  void  statsSendMessage(Object message, RocketProperties rocketProperties, byte[] bytes);
	@Override
	public void run() {
		statsSendMessage(this.getMessage(),
				this.getRocketProperties(),
				this.getBytes());
	}
}
