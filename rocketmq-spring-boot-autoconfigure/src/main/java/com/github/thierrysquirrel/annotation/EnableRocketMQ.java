package com.github.thierrysquirrel.annotation;

import com.github.thierrysquirrel.autoconfigure.RocketAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * ClassName: MessageListener <br/>
 * Description: <br/>
 * date: 2019/4/26 16:26<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import ({RocketAutoConfiguration.class})
public @interface EnableRocketMQ {

}
