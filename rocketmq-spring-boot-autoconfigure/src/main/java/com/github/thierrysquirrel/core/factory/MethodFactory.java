package com.github.thierrysquirrel.core.factory;

import java.lang.reflect.Method;

/**
 * ClassName: MethodFactory
 * Description:
 * date: 2019/4/27 16:13
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */

public class MethodFactory {
	private MethodFactory() {
	}

	public static Class<?> getMethodParameter(Method method) {
		return method.getParameterTypes()[0];
	}
}
