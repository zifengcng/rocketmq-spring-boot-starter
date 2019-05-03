package com.github.thierrysquirrel.aspect;

import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.core.utils.AspectUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: RocketAspect
 * Description:
 * date: 2019/4/28 21:07
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Aspect
@Slf4j
@Data
public class RocketAspect {
	private Map<String, Object> consumerContainer;
	private RocketProperties rocketProperties;
	private ThreadPoolExecutor threadPoolExecutor;

	public RocketAspect(Map<String, Object> consumerContainer, RocketProperties rocketProperties) {
		this.consumerContainer = consumerContainer;
		this.rocketProperties = rocketProperties;
		this.threadPoolExecutor = ThreadPoolFactory.createSendMessageThreadPoolExecutor(rocketProperties);
	}

	@Pointcut("@annotation(com.github.thierrysquirrel.annotation.CommonMessage)")
	public void commonMessagePointcut() {

	}

	@Pointcut("@annotation(com.github.thierrysquirrel.annotation.OrderMessage)")
	public void orderMessagePointcut() {

	}

	@Pointcut("@annotation(com.github.thierrysquirrel.annotation.TransactionMessage)")
	public void transactionMessagePointcut() {

	}

	@Around("commonMessagePointcut()")
	public Object rockerMessageSend(ProceedingJoinPoint point) throws Throwable {
		return AspectUtils.intercept(point, consumerContainer, rocketProperties, threadPoolExecutor, CommonMessage.class);
	}

	@Around("orderMessagePointcut()")
	public Object orderMessageSend(ProceedingJoinPoint point) throws Throwable {
		return AspectUtils.intercept(point, consumerContainer, rocketProperties, threadPoolExecutor, OrderMessage.class);
	}

	@Around("transactionMessagePointcut()")
	public Object transactionMessageSend(ProceedingJoinPoint point) throws Throwable {
		return AspectUtils.intercept(point, consumerContainer, rocketProperties, threadPoolExecutor, TransactionMessage.class);
	}
}
