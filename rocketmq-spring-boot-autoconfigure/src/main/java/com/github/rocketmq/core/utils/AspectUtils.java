package com.github.rocketmq.core.utils;

import com.github.rocketmq.autoconfigure.RocketProperties;
import com.github.rocketmq.core.factory.execution.ProducerFactoryExecution;
import com.github.rocketmq.core.factory.execution.ThreadPoolExecutorExecution;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: AspectUtils <br/>
 * Description: <br/>
 * date: 2019/4/29 22:03<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
public class AspectUtils {
	public static <T extends Annotation> Object intercept(ProceedingJoinPoint proceedingJoinPoint, RocketProperties rocketProperties, ThreadPoolExecutor threadPoolExecutor, Class<T> annotationClass) throws Throwable {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = signature.getMethod();
		T annotation = method.getAnnotation(annotationClass);
		Object proceed = proceedingJoinPoint.proceed();

		byte[] body = ByteUtils.objectToByte(proceed);
		ThreadPoolExecutorExecution.statsThread(threadPoolExecutor, new ProducerFactoryExecution(annotation, rocketProperties, body));
		return proceed;
	}
}
