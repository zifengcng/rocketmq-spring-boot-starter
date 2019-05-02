package com.github.thierrysquirrel.core.factory.execution;

import com.github.thierrysquirrel.core.factory.MethodFactory;
import com.github.thierrysquirrel.core.utils.JsonUtils;
import com.github.thierrysquirrel.error.RocketException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * ClassName: MethodFactoryExecution <br/>
 * Description: <br/>
 * date: 2019/4/27 16:26<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
@Data
@Slf4j
public class MethodFactoryExecution {
	private Object bean;
	private Method method;

	public MethodFactoryExecution(Object bean, Method method) {
		this.bean = bean;
		this.method = method;
	}

	public void methodExecution(String messageJson) throws RocketException {
		Class<?> methodParameter = MethodFactory.getMethodParameter(method);


		Object methodParameterBean = JsonUtils.fromJson(messageJson, methodParameter);
		try {
			method.invoke(bean, methodParameterBean);
		} catch (Exception e) {
			throw new RocketException(e);
		}
	}

}
