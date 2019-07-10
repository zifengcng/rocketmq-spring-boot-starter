package com.github.thierrysquirrel.core.consumer;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.github.thierrysquirrel.core.factory.execution.MethodFactoryExecution;
import com.github.thierrysquirrel.error.RocketException;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: DefaultMessageListener
 * Description:
 * date: 2019/4/27 17:05
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class DefaultMessageListener extends AbstractRocketListener implements MessageListener {

	public DefaultMessageListener(MethodFactoryExecution methodFactoryExecution) {
		super(methodFactoryExecution);
	}

	/**
	 * 消费消息接口，由应用来实现<br>
	 * 网络抖动等不稳定的情形可能会带来消息重复，对重复消息敏感的业务可对消息做幂等处理
	 *
	 * @param message 消息
	 * @param context 消费上下文
	 * @return 消费结果，如果应用抛出异常或者返回Null等价于返回Action.ReconsumeLater
	 * @see <a href="https://help.aliyun.com/document_detail/44397.html">如何做到消费幂等</a>
	 */
	@Override
	public Action consume(Message message, ConsumeContext context) {
		log.info(">>>>message:{}>>>>", message);
		String messageJson = new String(message.getBody());
		try {
			super.getMethodFactoryExecution().methodExecution(messageJson);
		} catch (RocketException e) {
			super.printErrorLog();
			return Action.ReconsumeLater;
		}
		return Action.CommitMessage;
	}
}
