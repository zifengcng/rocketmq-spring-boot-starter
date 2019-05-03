package com.github.thierrysquirrel.annotation;

import com.github.thierrysquirrel.core.producer.MessageSendType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: RocketMessage
 * Description:  
 * date: 2019/4/26 21:36 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
@Documented
public @interface RocketMessage {
	/**
	 * 您在控制台创建的 Group ID
	 *
	 * @return String
	 */
	String groupID() default "";

}
