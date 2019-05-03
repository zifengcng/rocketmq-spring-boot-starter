package com.github.thierrysquirrel.core.factory;

import com.aliyun.openservices.ons.api.Message;
import com.github.thierrysquirrel.annotation.CommonMessage;
import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RocketMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;

/**
 * ClassName: MessageFactory  
 * Description:  
 * date: 2019/4/28 21:55 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class MessageFactory {
	public static Message createMessage(CommonMessage commonMessage, byte[] body){
		return new Message(commonMessage.topic(),
				commonMessage.tag(),
				body);
	}
	public static Message createMessage(OrderMessage orderMessage, byte[] body){
		return new Message(orderMessage.topic(),
				orderMessage.tag(),
				body);
	}
	public static Message createMessage(TransactionMessage transactionMessage, byte[] body){
		return new Message(transactionMessage.topic(),
				transactionMessage.tag(),
				body);
	}
}
