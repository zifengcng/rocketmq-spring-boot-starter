package com.github.rocketmq.core.factory;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.github.rocketmq.annotation.RocketListener;
import com.github.rocketmq.autoconfigure.RocketProperties;

import java.util.Properties;

/**
 * ClassName: ConsumerPropertiesFactory <br/>
 * Description: <br/>
 * date: 2019/4/27 15:37<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
public class ConsumerPropertiesFactory {
	public static Properties createConsumerProperties(RocketProperties rocketProperties,
	                                                  RocketListener rocketListener) {

		Properties properties = PropertiesFactory.createProperties(rocketProperties);

		properties.put(PropertyKeyConst.GROUP_ID, rocketListener.groupID());
		properties.put(PropertyKeyConst.MessageModel, rocketListener.messageModel());
		properties.put(PropertyKeyConst.ConsumeThreadNums, rocketProperties.getConsumeThreadNums());
		properties.put(PropertyKeyConst.MaxReconsumeTimes, rocketProperties.getMaxReconsumeTimes());
		properties.put(PropertyKeyConst.ConsumeTimeout, rocketProperties.getConsumeTimeout());


		return properties;

	}
}
