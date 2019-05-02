package com.github.thierrysquirrel.core.consumer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.order.ConsumeOrderContext;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import com.aliyun.openservices.ons.api.order.OrderAction;
import com.github.thierrysquirrel.core.factory.execution.MethodFactoryExecution;
import com.github.thierrysquirrel.error.RocketException;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: DefaultMessageOrderListener <br/>
 * Description: <br/>
 * date: 2019/4/26 23:16<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */

@Slf4j
public class DefaultMessageOrderListener extends AbstractRocketListener implements MessageOrderListener {


	public DefaultMessageOrderListener(MethodFactoryExecution methodFactoryExecution) {
		super(methodFactoryExecution);
	}

	/**
	 * 消费消息接口，由应用来实现<br>
	 * 需要注意网络抖动等不稳定的情形可能会带来消息重复，对重复消息敏感的业务可对消息做幂等处理
	 *
	 * @param message 消息
	 * @param context 消费上下文
	 * @return {@link OrderAction} 消费结果，如果应用抛出异常或者返回Null等价于返回Action.ReconsumeLater
	 * @see <a href="https://help.aliyun.com/document_detail/44397.html">如何做到消费幂等</a>
	 */
	@Override
	public OrderAction consume(Message message, ConsumeOrderContext context) {
		log.info(">>>> Order message:{}>>>>", message);
		String messageBody = new String(message.getBody());
		try {
			super.getMethodFactoryExecution().methodExecution(messageBody);
		} catch (RocketException e) {
			super.printErrorLog();
		}
		return OrderAction.Success;
	}
}
