package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RockerMessage;
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
	private static Properties createProducerProperties(RocketProperties rocketProperties){
		Properties properties = PropertiesFactory.createProperties(rocketProperties);
		properties.put(PropertyKeyConst.SendMsgTimeoutMillis,rocketProperties.getSendMsgTimeoutMillis());
		return properties;
	}
	public static Properties createProducerProperties(RockerMessage rockerMessage,RocketProperties rocketProperties){
		Properties properties = createProducerProperties(rocketProperties);
		properties.put(PropertyKeyConst.GROUP_ID,rockerMessage.groupID());
		return properties;
	}
	public static Properties createProducerProperties(OrderMessage orderMessage, RocketProperties rocketProperties){
		Properties properties = createProducerProperties(rocketProperties);
		properties.put(PropertyKeyConst.GROUP_ID,orderMessage.groupID());
		return properties;
	}
	public static Properties createProducerProperties(TransactionMessage transactionMessage, RocketProperties rocketProperties){
		Properties properties = createProducerProperties(rocketProperties);
		properties.put(PropertyKeyConst.GROUP_ID,transactionMessage.groupID());
		properties.put(PropertyKeyConst.CheckImmunityTimeInSeconds,rocketProperties.getCheckImmunityTimeInSeconds());
		return properties;
	}
}
