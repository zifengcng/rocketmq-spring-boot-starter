package com.github.rocketmq.core.producer;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: DefaultLocalTransactionChecker <br/>
 * Description: <br/>
 * date: 2019/4/28 21:42<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
@Data
@Slf4j
public class DefaultLocalTransactionChecker implements LocalTransactionChecker {
	private TransactionStatus transactionStatus;

	public DefaultLocalTransactionChecker(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	/**
	 * 回查本地事务，Broker回调Producer，将未结束的事务发给Producer，由Producer来再次决定事务是提交还是回滚
	 *
	 * @param msg 消息
	 * @return {@link TransactionStatus} 事务状态, 包含提交事务、回滚事务、未知状态
	 */
	@Override
	public TransactionStatus check(Message msg) {
		log.info(">>>> 回查本地事务 message:{}>>>>", msg);
		return transactionStatus;
	}
}
