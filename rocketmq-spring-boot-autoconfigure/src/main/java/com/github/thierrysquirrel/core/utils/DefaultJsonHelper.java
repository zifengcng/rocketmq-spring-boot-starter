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
 * ClassName: DefaultJsonHelper
 * Description: json解析器默认实现
 * date: 2019-07-12
 *
 * @author Negi
 * @since 2.0.7
 */
public class DefaultJsonHelper<T> implements JsonHelper<T> {
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * 序列化为Json
	 *
	 * @param obj 对象
	 * @return json
	 */
	public String toJson(T obj) {
		return gson.toJson(obj);
	}

	/**
	 * Json反序列化为对象
	 *
	 * @param json      json
	 * @param classType 对象
	 * @return 对象
	 */
	public T fromJson(String json, Class<T> classType) {
		return gson.fromJson(json, classType);
	}
}
