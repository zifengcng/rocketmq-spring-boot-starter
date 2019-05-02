package com.github.thierrysquirrel.autoconfigure;

import com.github.thierrysquirrel.annotation.EnableRocketMQ;
import com.github.thierrysquirrel.aspect.RockerAspect;
import com.github.thierrysquirrel.container.RocketProducerContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;



/**
 * ClassName: RocketProperties  
 * Description:  
 * date: 2019/4/25 15:57 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Configuration
@EnableConfigurationProperties(RocketProperties.class)
@ConditionalOnBean(annotation = EnableRocketMQ.class)
public class RocketAutoConfiguration {
	@Resource
	private RocketProperties rocketProperties;


	@Bean
	@ConditionalOnMissingBean(RocketProducerContainer.class)
	public RocketProducerContainer mqMessageListenerContainer() {
		return new RocketProducerContainer(rocketProperties);
	}

	@Bean
	@ConditionalOnMissingBean(RockerAspect.class)
	public RockerAspect rockerAspect() {
		return new RockerAspect(rocketProperties);
	}

}
