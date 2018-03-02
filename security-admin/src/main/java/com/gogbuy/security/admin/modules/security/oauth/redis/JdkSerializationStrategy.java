package com.gogbuy.security.admin.modules.security.oauth.redis;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-28
 * Time: 15:52
 */
public class JdkSerializationStrategy extends StandardStringSerializationStrategy {
    private static final JdkSerializationRedisSerializer OBJECT_SERIALIZER = new JdkSerializationRedisSerializer();

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
        return (T) OBJECT_SERIALIZER.deserialize(bytes);
    }

    @Override
    protected byte[] serializeInternal(Object object) {
        return OBJECT_SERIALIZER.serialize(object);
    }
}
