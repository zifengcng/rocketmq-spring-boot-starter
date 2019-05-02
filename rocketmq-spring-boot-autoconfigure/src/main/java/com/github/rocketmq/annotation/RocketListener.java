package com.github.rocketmq.annotation;

import com.aliyun.openservices.ons.api.PropertyValueConst;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * ClassName: RocketListener <br/>
 * Description: <br/>
 * date: 2019/4/26 21:35<br/>
 *
 * @author ThierrySquirrel<br />
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
	 * @return String
	 */
	String groupID() default "";

	/**
	 * 消费模式，默认集群消费
	 * @return
	 */
	String messageModel() default PropertyValueConst.CLUSTERING;


}
