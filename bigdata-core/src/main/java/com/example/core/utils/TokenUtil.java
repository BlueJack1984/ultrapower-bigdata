package com.example.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 * @author daniel
 * @date 2019-12-23
 */
public class TokenUtil {

    //定义常量
    /**
     * 定义token参数的过期时间，初始设置为15分钟，可调整
     */
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    /**
     * 定义token参数的加密秘钥
     */
    private static final String TOKEN_SECRET = "the_first_token_123";

    /**
     * 实现签名方法： 这里不应该使用密码进行加密，
     * 不安全，但是是自己的小demo就这样写了。
     */
    /**
     * 功能是生成签名，有效期15分钟
     * @param account 表示用户账号
     * @param password 表示用户密码
     * @return
     */
    public static String sign(String account, String password) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("account", account)
                    .withClaim("password", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 实现token的检验方法
     * 检验token是否正确
     * @param token 请求头部携带的token数据
     * @return 返回token验证结果，true或者false
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从token中获取用户账号account信息
     * @param token 请求头部携带的token数据
     * @return 返回解密后的account账号
     */
    public static String getUserName(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("account").asString();
        } catch (JWTDecodeException e){
            e.printStackTrace();
            return null;
        }
    }
}