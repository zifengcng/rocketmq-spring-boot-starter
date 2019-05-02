package com.github.rocketmq.autoconfigure;

import com.github.rocketmq.annotation.EnableRocketMQ;
import com.github.rocketmq.aspect.RockerAspect;
import com.github.rocketmq.container.RocketProducerContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;



/**
 * ClassName: RocketProperties <br/>
 * Description: <br/>
 * date: 2019/4/25 15:57<br/>
 *
 * @author ThierrySquirrel<br />
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
