package com.gogbuy.security.admin.modules.security.oauth.redis;

import com.gogbuy.security.admin.modules.security.oauth.token.AccessToken;
import com.gogbuy.security.admin.modules.security.oauth.token.OauthToken;
import com.gogbuy.security.admin.modules.security.oauth.token.RefreshToken;
import com.gogbuy.security.admin.modules.security.userdetails.GogUserDetails;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-02-28
 * Time: 15:44
 */
public class RedisTokenStore {
    private static final String ACCESS = "access:";
    private static final String AUTH_TO_ACCESS = "auth_to_access:";
    private static final String AUTH = "auth:";
    private static final String REFRESH_AUTH = "refresh_auth:";
    private static final String ACCESS_TO_REFRESH = "access_to_refresh:";
    private static final String REFRESH = "refresh:";
    private static final String REFRESH_TO_ACCESS = "refresh_to_access:";
    private static final String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
    private static final String UNAME_TO_ACCESS = "uname_to_access:";

    private final RedisConnectionFactory connectionFactory;

    private RedisTokenStoreSerializationStrategy serializationStrategy = new JdkSerializationStrategy();

    private String prefix = "";

    public RedisTokenStore(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSerializationStrategy(RedisTokenStoreSerializationStrategy serializationStrategy) {
        this.serializationStrategy = serializationStrategy;
    }
    private RedisConnection getConnection() {
        return connectionFactory.getConnection();
    }

    private byte[] serialize(Object object) {
        return serializationStrategy.serialize(object);
    }

    private byte[] serializeKey(String object) {
        return serialize(object);
    }
    private OauthToken deserializeAccessToken(byte[] bytes) {
        return serializationStrategy.deserialize(bytes, OauthToken.class);
    }
    private RefreshToken deserializeRefreshToken(byte[] bytes){
        return serializationStrategy.deserialize(bytes,RefreshToken.class);
    }
    private Authentication deserializeAuthentication(byte[] bytes) {
        return serializationStrategy.deserialize(bytes, Authentication.class);
    }

    public void storeOauthToken(OauthToken oauthToken, Authentication authentication) {
        AccessToken accessToken = oauthToken.getAccessToken();
        //access
        byte[] accessSerializeKey = serializeKey(ACCESS + accessToken.getAccessToken());

        byte[] authSerializeKey = serializeKey(AUTH + accessToken.getAccessToken());

        byte[] authValue =  serialize(authentication);

        GogUserDetails userDetails = (GogUserDetails) authentication.getPrincipal();
        //auth
        byte[] authToAccessKey = serializeKey(AUTH_TO_ACCESS + generateKey(userDetails.getUsername()));
        RedisConnection conn = getConnection();
        try {
            conn.openPipeline();
            conn.set(accessSerializeKey,serialize(oauthToken));
            conn.set(authSerializeKey,authValue);
            conn.set(authToAccessKey,accessSerializeKey);
            if (accessToken.getExpiration() != null){
                conn.expire(accessSerializeKey,accessToken.getExpiresIn());
                conn.expire(authSerializeKey,accessToken.getExpiresIn());
                conn.expire(authToAccessKey,accessToken.getExpiresIn());
            }
            RefreshToken refreshToken = oauthToken.getRefreshToken();
            byte[] refreshSerializeKey = serializeKey(REFRESH + refreshToken.getRefreshToken());
            conn.set(refreshSerializeKey,serialize(refreshToken));
            if (refreshToken.getExpiration() != null){
                conn.expire(refreshSerializeKey,refreshToken.getExpiresIn());
            }
            conn.closePipeline();
        } finally {
            conn.close();
        }
    }
    public RefreshToken readRefreshToken(String refreshToken){
        byte[] refreshSerializeKey = serializeKey(REFRESH + refreshToken);
        byte[] bytes = null;
        RedisConnection conn = getConnection();
        try {
            bytes =  conn.get(refreshSerializeKey);
        }finally {
            conn.close();
        }
        return deserializeRefreshToken(bytes);
    }
    public OauthToken getOauthToken(String token){
        byte[] accessSerializeKey = serializeKey(ACCESS + token);
        byte[] bytes = null;
        RedisConnection conn = getConnection();
        try {
            bytes =  conn.get(accessSerializeKey);
        }finally {
            conn.close();
        }
        return deserializeAccessToken(bytes);
    }
    public OauthToken getOauthToken(Authentication authentication){
        GogUserDetails userDetails = (GogUserDetails) authentication.getPrincipal();
        byte[] authToAccessKey = serializeKey(AUTH_TO_ACCESS + generateKey(userDetails.getUsername()));
        RedisConnection conn = getConnection();
        byte[] bytes = null;
        try {
            byte[] accessSerializeKey  =  conn.get(authToAccessKey);
            if (accessSerializeKey != null){
                bytes = conn.get(accessSerializeKey);
            }
        }finally {
            conn.close();
        }
        return deserializeAccessToken(bytes);
    }
    public Authentication readAuthenticationByAccessToken(String token){
        byte[] accessSerializeKey = serializeKey(AUTH + token);
        byte[] bytes = null;
        RedisConnection conn = getConnection();
        try {
            bytes =  conn.get(accessSerializeKey);
        }finally {
            conn.close();
        }
        return deserializeAuthentication(bytes);
    }
    protected String generateKey(String values) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(values.getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", nsae);
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).", uee);
        }
    }
}
