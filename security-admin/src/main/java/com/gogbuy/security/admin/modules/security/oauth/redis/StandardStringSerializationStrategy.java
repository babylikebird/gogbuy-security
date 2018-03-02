package com.gogbuy.security.admin.modules.security.oauth.redis;

import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-28
 * Time: 15:51
 */
public abstract class StandardStringSerializationStrategy extends BaseRedisTokenStoreSerializationStrategy{
    private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();

    @Override
    protected String deserializeStringInternal(byte[] bytes) {
        return STRING_SERIALIZER.deserialize(bytes);
    }

    @Override
    protected byte[] serializeInternal(String string) {
        return STRING_SERIALIZER.serialize(string);
    }
}
