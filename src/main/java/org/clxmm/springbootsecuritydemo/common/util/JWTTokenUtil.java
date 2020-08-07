package org.clxmm.springbootsecuritydemo.common.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.springbootsecuritydemo.common.config.JWTConfig;
import org.clxmm.springbootsecuritydemo.security.entity.SelfUserEntity;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;

/**
 * @author clx
 * @date 2020/8/6 10:39
 */
@Slf4j
public class JWTTokenUtil {


    private JWTTokenUtil() {
    }


    /**
     * 验证 token 是否正确
     *
     * @param token
     * @param username
     * @param secret
     * @return
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = getAlgorithm(JWTConfig.getSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(JWTConfig.getTokenPrefix(), username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("验证token 失败; 错误信息 {} ", e.getMessage());
        }
        return false;
    }


    /**
     * 生成token
     *
     * @param uid
     * @param username
     * @param secret
     * @return
     */
    public static String sign(String uid, String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + JWTConfig.getExpiration());
            Algorithm algorithm = getAlgorithm(JWTConfig.getSecret());
            String sign = JWT.create()
                    .withClaim(JWTConfig.getTokenPrefix(), username)
                    .withClaim("uid", uid)
                    .withExpiresAt(date)
                    .sign(algorithm);
            return sign;
        } catch (Exception e) {
            log.error("生成token 失败: {} ", e.getMessage());
        }
        return null;
    }


    public static String sign(SelfUserEntity selfUserEntity) {
        try {
            Date date = new Date(System.currentTimeMillis() + JWTConfig.getExpiration());
            Algorithm algorithm = getAlgorithm(JWTConfig.getSecret());
            String sign = JWT.create()
                    .withClaim(JWTConfig.getTokenPrefix(), selfUserEntity.getUsername())
                    .withClaim("uid", selfUserEntity.getUserId())
                    .withClaim("authorities", JSON.toJSONString(selfUserEntity.getAuthorities()))
                    .withExpiresAt(date)
                    .sign(algorithm);
            return sign;
        } catch (Exception e) {
            log.error("生成token 失败: {} ", e.getMessage());
        }
        return null;
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim(JWTConfig.getTokenHeader()).asString();
        } catch (Exception e) {
            log.error("获取token中的信息失败: {}", e.getMessage());
        }
        return null;
    }


    /**
     * 获得token中的指定KEY值信息
     *
     * @param token
     * @return
     */
    public static String get(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            log.error("获取token中指定的key失败！ {}", e.getMessage());
        }
        return null;
    }

    private static Algorithm getAlgorithm(String secret) {
        return Algorithm.HMAC256(secret);
    }

}
