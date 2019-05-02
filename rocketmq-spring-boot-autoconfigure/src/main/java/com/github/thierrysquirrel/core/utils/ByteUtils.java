package com.github.thierrysquirrel.core.utils;

/**
 * ClassName: ByteUtils <br/>
 * Description: <br/>
 * date: 2019/4/28 22:42<br/>
 *
 * @author ThierrySquirrel<br />
 * @since JDK 1.8
 */
public class ByteUtils {
	public static byte[] objectToByte(Object object){
		return JsonUtils.toJson(object).getBytes();
	}
}
