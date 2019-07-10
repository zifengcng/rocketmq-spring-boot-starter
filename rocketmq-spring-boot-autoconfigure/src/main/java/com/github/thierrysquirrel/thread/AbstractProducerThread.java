package com.github.thierrysquirrel.thread;

import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import lombok.Data;

import java.util.Map;

/**
 * ClassName: AbstractProducerThread
 * Description:
 * date: 2019/5/3 14:02
 *
 * @author Thierry
 * @since JDK 1.8
 */
@Data
public abstract class AbstractProducerThread implements Runnable {
	private Map<String, Object> producerConsumer;
	private RocketMessage rocketMessage;
	private Object bean;
	private RocketProperties rocketProperties;

	public AbstractProducerThread(Map<String, Object> producerConsumer, RocketMessage rocketMessage, Object bean, RocketProperties rocketProperties) {
		this.producerConsumer = producerConsumer;
		this.rocketMessage = rocketMessage;
		this.bean = bean;
		this.rocketProperties = rocketProperties;
	}

	/**
	 * 开始向容器装填
	 *
	 * @param producerConsumer producerConsumer
	 * @param rocketMessage    rocketMessage
	 * @param bean             bean
	 * @param rocketProperties rocketProperties
	 */
	protected abstract void statsPutProducer(Map<String, Object> producerConsumer, RocketMessage rocketMessage, Object bean, RocketProperties rocketProperties);

	@Override
	public void run() {
		statsPutProducer(producerConsumer, rocketMessage, bean, rocketProperties);
	}
}
