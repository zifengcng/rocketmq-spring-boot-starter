package com.github.rocketmq.core.factory;

import com.aliyun.openservices.ons.api.Message;
import com.github.rocketmq.annotation.OrderMessage;
import com.github.rocketmq.annotation.RockerMessage;
import com.github.rocketmq.annotation.TransactionMessage;

/**
 * ClassName: MessageFactory <br/>
 * Description: <br/>
 * date: 2019/4/28 21:55<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
public class MessageFactory {
	public static Message createMessage(RockerMessage rockerMessage,byte[] body){
		return new Message(rockerMessage.topic(),
				rockerMessage.tag(),
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
