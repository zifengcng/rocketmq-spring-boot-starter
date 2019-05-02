package com.github.thierrysquirrel.core.strategy;

import com.github.thierrysquirrel.annotation.OrderMessage;
import com.github.thierrysquirrel.annotation.RockerMessage;
import com.github.thierrysquirrel.annotation.TransactionMessage;
import com.github.thierrysquirrel.autoconfigure.RocketProperties;
import com.github.thierrysquirrel.core.factory.SendMessageFactory;

/**
 * ClassName: ProducerStrategy <br/>
 * Description: <br/>
 * date: 2019/4/29 21:34<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
public class ProducerStrategy {
	public static void  statsSendMessage(Object message, RocketProperties rocketProperties, byte[] bytes){
		if(message instanceof RockerMessage){
			RockerMessage rockerMessage = (RockerMessage) message;
			SendMessageFactory.sendMessage(rockerMessage,rocketProperties,bytes);
			return;
		}
		if(message instanceof OrderMessage){
			OrderMessage orderMessage=(OrderMessage) message;
			SendMessageFactory.sendMessage(orderMessage,rocketProperties,bytes);
			return;
		}
		if(message instanceof TransactionMessage){
			TransactionMessage transactionMessage=(TransactionMessage) message;
			SendMessageFactory.sendMessage(transactionMessage,rocketProperties,bytes);
		}
	}
}
