package com.github.thierrysquirrel.core.utils;

import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * ClassName: AnnotatedMethodsUtils
 * Description:
 * date: 2019/5/3 11:44
 *
 * @author Thierry
 * @since JDK 1.8
 */
public class AnnotatedMethodsUtils {
	private AnnotatedMethodsUtils() {
	}

	public static <T extends Annotation> Map<Method, T> getMethodAndAnnotation(Object bean, Class<T> annotation) {
		return MethodIntrospector.selectMethods(bean.getClass(),
				(MethodIntrospector.MetadataLookup<T>) method -> AnnotatedElementUtils
						.findMergedAnnotation(method, annotation));
	}
}
