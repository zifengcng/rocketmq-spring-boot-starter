package com.github.thierrysquirrel.core.producer;

/**
 * ClassName: MessageSendType <br/>
 * Description: <br/>
 * date: 2019/4/28 21:02<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
public enum  MessageSendType {
	/**
	 * 同步发送
	 */
	SEND,
	/**
	 * 异步发送
	 */
	SEND_ASYNC,
	/**
	 * 单向发送
	 */
	SEND_ONEWAY

}
