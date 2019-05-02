package com.github.rocketmq.core.producer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: DefaultLocalTransactionExecuter <br/>
 * Description: <br/>
 * date: 2019/4/28 22:28<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
@Slf4j
public class DefaultLocalTransactionExecuter implements LocalTransactionExecuter {
	/**
	 * 执行本地事务，由应用来重写
	 *
	 * @param msg 消息
	 * @param arg 应用自定义参数，由send方法传入并回调
	 * @return {@link TransactionStatus} 返回事务执行结果，包括提交事务、回滚事务、未知状态
	 */
	@Override
	public TransactionStatus execute(Message msg, Object arg) {
		log.info(">>>> 执行本地事务 message:{}>>>>", msg);
		return TransactionStatus.CommitTransaction;
	}
}
