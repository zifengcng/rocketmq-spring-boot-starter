package com.github.thierrysquirrel.annotation;

import com.aliyun.openservices.ons.api.PropertyValueConst;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * ClassName: RocketListener
 * Description:
 * date: 2019/4/26 21:35
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Component
public @interface RocketListener {
	/**
	 * 您在控制台创建的 Group ID
	 *
	 * @return String
	 */
	String groupID() default "";

	/**
	 * 消费模式，默认集群消费
	 *
	 * @return String
	 */
	String messageModel() default PropertyValueConst.CLUSTERING;


}
