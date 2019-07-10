package com.github.thierrysquirrel.core.producer;

/**
 * ClassName: MessageSendType
 * Description:
 * date: 2019/4/28 21:02
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum MessageSendType {
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
	SEND_ONE_WAY

}
