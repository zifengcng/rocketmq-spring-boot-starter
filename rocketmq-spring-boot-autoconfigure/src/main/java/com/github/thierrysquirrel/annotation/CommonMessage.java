package com.github.thierrysquirrel.annotation;

import com.github.thierrysquirrel.core.producer.MessageSendType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: CommonMessage <br/>
 * Description: <br/>
 * date: 2019/5/3 11:16<br/>
 *
 * @author Thierry<br />
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CommonMessage {
	/**
	 * Message 所属的 Topic
	 *
	 * @return String
	 */
	String topic() default "";

	/**
	 * 订阅指定 Topic 下的 Tags：
	 * 1. * 表示订阅所有消息
	 * 2. TagA || TagB || TagC 表示订阅 TagA 或 TagB 或 TagC 的消息
	 *
	 * @return String
	 */
	String tag() default "*";

	/**
	 * 重点：配合时间单位投递
	 * 定时消息，单位毫秒（ms），在指定时间戳（当前时间之后）进行投递，例如 2016-03-07 16:21:00 投递。如果被设置成当前时间戳之前的某个时刻，消息将立刻投递给消费者。
	 * 延时消息，单位毫秒（ms），在指定延迟时间（当前时间之后）进行投递，例如消息在 3 秒后投递
	 *
	 * @return long
	 */
	long startDeliverTime() default 0;

	/**
	 * 时间单位  默认秒
	 *
	 * @return TimeUnit
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;

	/**
	 * 消息发送类型 默认异步
	 *
	 * @return MessageSendType
	 */
	MessageSendType messageSendType() default MessageSendType.SEND_ASYNC;
}
