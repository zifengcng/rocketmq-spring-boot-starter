package com.github.thierrysquirrel.core.factory;

import java.lang.reflect.Method;

/**
 * ClassName: MethodFactory <br/>
 * Description: <br/>
 * date: 2019/4/27 16:13<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */

public class MethodFactory {

	public static Class<?> getMethodParameter(Method method) {
			return method.getParameterTypes()[0];
	}
}
