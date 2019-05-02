package com.github.rocketmq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * ClassName: MessageListener <br/>
 * Description: <br/>
 * date: 2019/4/26 22:37<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Component
public @interface MessageListener {
	/**
	 * Message 所属的 Topic
	 * @return String
	 */
	String topic() default "";
	/**
	 * 订阅指定 Topic 下的 Tags：
	 * 1. * 表示订阅所有消息
	 * 2. TagA || TagB || TagC 表示订阅 TagA 或 TagB 或 TagC 的消息
	 * @return String
	 */
	String tag() default "*";

	/**
	 * 是否为顺序消息
	 * @return Boolean
	 */

	boolean orderConsumer()default false;
}
