package com.github.thierrysquirrel.autoconfigure;

import com.github.thierrysquirrel.annotation.EnableRocketMQ;
import com.github.thierrysquirrel.aspect.RocketAspect;
import com.github.thierrysquirrel.container.RocketConsumerContainer;
import com.github.thierrysquirrel.container.RocketProducerContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


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
	@Resource
	private Map<String, Object> consumerContainer;

	@Bean
	@ConditionalOnMissingBean(RocketConsumerContainer.class)
	public RocketConsumerContainer rocketConsumerContainer() {
		return new RocketConsumerContainer(rocketProperties);
	}

	@Bean
	@ConditionalOnMissingBean(Map.class)
	public Map<String, Object> consumerContainer() {
		return new ConcurrentHashMap<>(16);
	}

	@Bean
	@ConditionalOnMissingBean(RocketProducerContainer.class)
	public RocketProducerContainer rocketProducerContainer() {
		return new RocketProducerContainer(consumerContainer, rocketProperties);
	}

	@Bean
	@ConditionalOnMissingBean(RocketAspect.class)
	public RocketAspect rockerAspect() {
		return new RocketAspect(consumerContainer, rocketProperties);
	}
}
