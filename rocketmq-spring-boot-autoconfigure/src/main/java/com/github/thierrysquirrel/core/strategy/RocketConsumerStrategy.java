package com.github.thierrysquirrel.core.strategy;

import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.execution.ProducerFactoryExecution;
import com.github.thierrysquirrel.core.factory.execution.ThreadPoolExecutorExecution;
import com.github.thierrysquirrel.core.utils.AnnotatedMethodsUtils;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: RocketConsumerStrategy
 * Description:
 * date: 2019/5/3 11:36
 *
 * @author Thierry
 * @since JDK 1.8
 */

public class RocketConsumerStrategy {
	private RocketConsumerStrategy() {
	}

	public static void putProducer(ThreadPoolExecutor threadPoolExecutor, Map<String, Object> producerConsumer, Object bean, RocketProperties rocketProperties) {
		RocketMessage rocketMessage = bean.getClass().getAnnotation(RocketMessage.class);
		AnnotatedMethodsUtils.getMethodAndAnnotation(bean, CommonMessage.class).
				forEach((method, commonMessage) -> {
					ProducerFactoryExecution producerFactoryExecution = new ProducerFactoryExecution(producerConsumer, rocketMessage, commonMessage, rocketProperties);
					ThreadPoolExecutorExecution.statsThread(threadPoolExecutor, producerFactoryExecution);
				});
		AnnotatedMethodsUtils.getMethodAndAnnotation(bean, OrderMessage.class).
				forEach((method, orderMessage) -> {
					ProducerFactoryExecution producerFactoryExecution = new ProducerFactoryExecution(producerConsumer, rocketMessage, orderMessage, rocketProperties);
					ThreadPoolExecutorExecution.statsThread(threadPoolExecutor, producerFactoryExecution);
				});
		AnnotatedMethodsUtils.getMethodAndAnnotation(bean, TransactionMessage.class).
				forEach((method, transactionMessage) -> {
					ProducerFactoryExecution producerFactoryExecution = new ProducerFactoryExecution(producerConsumer, rocketMessage, transactionMessage, rocketProperties);
					ThreadPoolExecutorExecution.statsThread(threadPoolExecutor, producerFactoryExecution);
				});
	}
}
