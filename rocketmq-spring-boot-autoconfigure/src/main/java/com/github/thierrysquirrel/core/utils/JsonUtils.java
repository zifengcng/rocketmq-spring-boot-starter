package com.github.thierrysquirrel.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ClassName: JsonUtils
 * Description:
 * date: 2019/4/27 16:16
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class JsonUtils {
	private static Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.create();

	private JsonUtils() {
	}

	/**
	 * 得到Gson
	 *
	 * @return gson
	 */
	private static Gson getGson() {
		return gson;
	}

	/**
	 * 序列化为Json
	 *
	 * @param obj 对象
	 * @return json
	 */
	public static String toJson(Object obj) {
		return getGson().toJson(obj);
	}

	/**
	 * Json反序列化为对象
	 *
	 * @param json      json
	 * @param classType 对象
	 * @return 对象
	 */
	public static <T> T fromJson(String json, Class<T> classType) {
		return getGson().fromJson(json, classType);
	}


}
