package com.github.thierrysquirrel.annotation;

import java.lang.annotation.*;

/**
 * ClassName: OrderMessage <br/>
 * Description: <br/>
 * date: 2019/4/27 21:36<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OrderMessage {
	/**
	 * 您在控制台创建的 Group ID
	 * @return String
	 */
	String groupID() default "";
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
	 * 分区顺序消息中区分不同分区的关键字段，sharding key 于普通消息的 key 是完全不同的概念。
	 * 全局顺序消息，该字段可以设置为任意非空字符串。
	 * @return String
	 */
	String shardingKey() default "shardingKey";
}
