package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;

import java.util.Properties;

/**
 * ClassName: ProducerPropertiesFactory  
 * Description:  
 * date: 2019/4/28 21:21 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ProducerPropertiesFactory {

	public static Properties createProducerProperties(RocketMessage rockerMessage, RocketProperties rocketProperties){
		Properties properties = PropertiesFactory.createProperties(rocketProperties);
		properties.put(PropertyKeyConst.SendMsgTimeoutMillis,rocketProperties.getSendMsgTimeoutMillis());
		properties.put(PropertyKeyConst.GROUP_ID,rockerMessage.groupID());
		return properties;
	}

}
