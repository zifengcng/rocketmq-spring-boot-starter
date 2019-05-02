package com.github.thierrysquirrel.container;

import com.github.thierrysquirrel.annotation.MessageListener;
import com.github.thierrysquirrel.annotation.RocketListener;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.core.factory.execution.ConsumerFactoryExecution;
import com.github.thierrysquirrel.core.factory.execution.MethodFactoryExecution;
import com.github.thierrysquirrel.core.factory.execution.ThreadPoolExecutorExecution;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: RocketProducerContainer <br/>
 * Description: <br/>
 * date: 2019/4/26 21:40<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */

public class RocketProducerContainer implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	private RocketProperties rocketProperties;

	public RocketProducerContainer(RocketProperties rocketProperties) {
		this.rocketProperties = rocketProperties;
	}

	@PostConstruct
	public void initialize() {


		ThreadPoolExecutor threadPoolExecutor = ThreadPoolFactory.createConsumeThreadPoolExecutor(rocketProperties);

		applicationContext.getBeansWithAnnotation(RocketListener.class).forEach((beanName, bean) -> {


			Map<Method, MessageListener> annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
					(MethodIntrospector.MetadataLookup<MessageListener>) method -> AnnotatedElementUtils
							.findMergedAnnotation(method, MessageListener.class));
			RocketListener rocketListener = bean.getClass().getAnnotation(RocketListener.class);
			annotatedMethods.forEach((method, consumerListener) -> {

				ConsumerFactoryExecution consumerFactoryExecution = new ConsumerFactoryExecution(rocketProperties,
						rocketListener, consumerListener, new MethodFactoryExecution(bean, method));
				ThreadPoolExecutorExecution.statsThread(threadPoolExecutor, consumerFactoryExecution);
			});
		});
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
