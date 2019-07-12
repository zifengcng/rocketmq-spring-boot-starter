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

package com.github.thierrysquirrel.core.serializer;

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
public class GsonSerializer implements MqSerializer {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public byte[] serialize(Object obj) {
        return gson.toJson(obj).getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes, Class<?> clazz) {
        return gson.fromJson(new String(bytes), clazz);
    }
}
