package com.github.thierrysquirrel.error;

/**
 * ClassName: RocketException <br/>
 * Description: <br/>
 * date: 2019/4/27 16:44<br/>
 *
 * @author ThierrySquirrel<br />
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
