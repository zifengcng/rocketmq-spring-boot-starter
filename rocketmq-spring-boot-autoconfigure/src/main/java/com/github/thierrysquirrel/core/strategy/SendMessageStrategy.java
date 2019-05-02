package com.github.thierrysquirrel.core.strategy;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.github.thierrysquirrel.annotation.RockerMessage;
import com.github.thierrysquirrel.core.producer.DefaultSendCallback;
import com.github.thierrysquirrel.core.producer.MessageSendType;

/**
 * ClassName: SendMessageStrategy  
 * Description:  
 * date: 2019/4/29 23:37 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SendMessageStrategy {
	public static void send(RockerMessage rockerMessage, Producer producer, Message message){
		if(rockerMessage.messageSendType().equals(MessageSendType.SEND)){
			producer.send(message);
			return;
		}
		if(rockerMessage.messageSendType().equals(MessageSendType.SEND_ASYNC)){
			producer.sendAsync(message,new DefaultSendCallback());
			return;
		}
		if(rockerMessage.messageSendType().equals(MessageSendType.SEND_ONEWAY)){
			producer.sendOneway(message);
		}
	}
}
