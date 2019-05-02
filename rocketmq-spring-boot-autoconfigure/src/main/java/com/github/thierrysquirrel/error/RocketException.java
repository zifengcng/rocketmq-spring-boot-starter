package com.github.thierrysquirrel.error;

/**
 * ClassName: RocketException  
 * Description:  
 * date: 2019/4/27 16:44 
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class RocketException extends Exception {

	public RocketException(String message) {
		super(message);
	}

	public RocketException(Throwable cause) {
		super(cause);
	}

	public RocketException(String message, Throwable cause) {
		super(message, cause);
	}
}
