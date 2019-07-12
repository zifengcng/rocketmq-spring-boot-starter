/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.thierrysquirrel.core.utils;

import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.core.factory.execution.SendMessageFactoryExecution;
import com.github.thierrysquirrel.core.factory.execution.ThreadPoolExecutorExecution;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: InterceptRocket
 * Description:
 * date: 2019/4/29 22:03
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class InterceptRocket {
	private InterceptRocket() {
	}

	public static <T extends Annotation> Object intercept(ProceedingJoinPoint proceedingJoinPoint, Map<String, Object> consumerContainer, ThreadPoolExecutor threadPoolExecutor, Class<T> annotationClass, ApplicationContext applicationContext) throws Throwable {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = signature.getMethod();

		T annotation = method.getAnnotation(annotationClass);
		Object proceed = proceedingJoinPoint.proceed();
		Map<String,JsonHelper> jsonHelperMap = applicationContext.getBeansOfType(JsonHelper.class);
		JsonHelper[] jsonHelpers = jsonHelperMap.values().toArray(new JsonHelper[jsonHelperMap.size()]);
		JsonHelper jsonHelper = (jsonHelperMap==null||jsonHelperMap.size()==0)?new DefaultJsonHelper():jsonHelpers[0];
		byte[] body = jsonHelper.toJson(proceed).getBytes();
		RocketMessage rocketMessage = method.getDeclaringClass().getAnnotation(RocketMessage.class);

		ThreadPoolExecutorExecution.statsThread(threadPoolExecutor, new SendMessageFactoryExecution(consumerContainer, rocketMessage, annotation, body, applicationContext));
		return proceed;
	}
}
