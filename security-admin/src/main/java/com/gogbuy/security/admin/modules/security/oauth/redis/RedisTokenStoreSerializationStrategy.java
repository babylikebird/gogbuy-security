package com.gogbuy.security.admin.modules.security.oauth.redis;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-28
 * Time: 15:49
 */
public interface RedisTokenStoreSerializationStrategy {
    <T> T deserialize(byte[] bytes, Class<T> clazz);

    String deserializeString(byte[] bytes);

    byte[] serialize(Object object);

    byte[] serialize(String data);
}
