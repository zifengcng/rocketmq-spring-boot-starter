package com.github.thierrysquirrel.thread;

import com.github.thierrysquirrel.annotation.MessageListener;
import com.github.thierrysquirrel.annotation.RocketListener;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.execution.MethodFactoryExecution;
import lombok.Data;

/**
 * ClassName: AbstractConsumerThread
 * Description:
 * date: 2019/4/27 20:03
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractConsumerThread implements Runnable {
	private RocketProperties rocketProperties;
	private RocketListener rocketListener;
	private MessageListener consumerListener;
	private MethodFactoryExecution methodFactoryExecution;

	public AbstractConsumerThread(RocketProperties rocketProperties, RocketListener rocketListener, MessageListener consumerListener, MethodFactoryExecution methodFactoryExecution) {
		this.rocketProperties = rocketProperties;
		this.rocketListener = rocketListener;
		this.consumerListener = consumerListener;
		this.methodFactoryExecution = methodFactoryExecution;
	}

	/**
	 * 消费者开始监听
	 *
	 * @param rocketProperties       rocketProperties
	 * @param rocketListener         rocketListener
	 * @param consumerListener       consumerListener
	 * @param methodFactoryExecution methodFactoryExecution
	 */
	protected abstract void statsConsumer(RocketProperties rocketProperties,
	                                      RocketListener rocketListener,
	                                      MessageListener consumerListener,
	                                      MethodFactoryExecution methodFactoryExecution);

	@Override
	public void run() {
		statsConsumer(this.getRocketProperties(),
				this.getRocketListener(),
				this.getConsumerListener(),
				this.getMethodFactoryExecution());
	}
}
