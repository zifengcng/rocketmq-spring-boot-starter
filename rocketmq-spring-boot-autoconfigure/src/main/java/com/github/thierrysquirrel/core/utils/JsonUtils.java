/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
@Deprecated
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
	 * @param <T>       T
	 * @return 对象
	 */
	public static <T> T fromJson(String json, Class<T> classType) {
		return getGson().fromJson(json, classType);
	}


}
