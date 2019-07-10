package com.github.thierrysquirrel.container;

import com.github.thierrysquirrel.annotation.MessageListener;
import com.github.thierrysquirrel.annotation.RocketListener;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.core.factory.execution.ConsumerFactoryExecution;
import com.github.thierrysquirrel.core.factory.execution.MethodFactoryExecution;
import com.github.thierrysquirrel.core.factory.execution.ThreadPoolExecutorExecution;
import com.github.thierrysquirrel.core.utils.AnnotatedMethodsUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: RocketConsumerContainer
 * Description:
 * date: 2019/4/26 21:40
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */

public class RocketConsumerContainer implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	private RocketProperties rocketProperties;

	public RocketConsumerContainer(RocketProperties rocketProperties) {
		this.rocketProperties = rocketProperties;
	}

	@PostConstruct
	public void initialize() {


		ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.createConsumeThreadPoolExecutor(rocketProperties);

		applicationContext.getBeansWithAnnotation(RocketListener.class).forEach((beanName, bean) -> {

			RocketListener rocketListener = bean.getClass().getAnnotation(RocketListener.class);
			AnnotatedMethodsUtils.getMethodAndAnnotation(bean, MessageListener.class).
					forEach((method, consumerListener) -> {
						ConsumerFactoryExecution consumerFactoryExecution = new ConsumerFactoryExecution(rocketProperties,
								rocketListener, consumerListener, new MethodFactoryExecution(bean, method));
						ThreadPoolExecutorExecution.statsThread(threadPoolExecutor, consumerFactoryExecution);
					});
		});
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
