package com.github.thierrysquirrel.core.serializer;

/**
 * ClassName: MqSerializer
 * Description: 序列化器接口定义
 * date: 2019-07-12
 *
 * @author Negi
 * @since 2.0.7
 */
public interface MqSerializer {
    /**
     * 序列化为Json
     *
     * @param object 对象
     * @return 二进制数据
     */
    byte[] serialize(Object object);

    /**
     * Json反序列化为对象
     *
     * @param bytes     序列化的二进制数据
     * @param clazz     反序列化后的对象
     * @return 对象
     */
    Object deserialize(byte[] bytes, Class<?> clazz);
}
