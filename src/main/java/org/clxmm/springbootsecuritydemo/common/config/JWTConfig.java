package org.clxmm.springbootsecuritydemo.common.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT配置类
 * @author clx
 * @date 2020/8/6 10:14
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {
    /**
     * 密钥KEY
     */
    public static String secret;
    /**
     * TokenKey
     */
    public static String tokenHeader;
    /**
     * Token前缀字符
     */
    public static String tokenPrefix;
    /**
     * 过期时间
     */
    public static Integer expiration;
    /**
     * 不需要认证的接口
     */
    public static String antMatchers;


    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        JWTConfig.secret = secret;
    }

    public static String getTokenHeader() {
        return tokenHeader;
    }

    public static void setTokenHeader(String tokenHeader) {
        JWTConfig.tokenHeader = tokenHeader;
    }

    public static String getTokenPrefix() {
        return tokenPrefix;
    }

    public static void setTokenPrefix(String tokenPrefix) {
        JWTConfig.tokenPrefix = tokenPrefix;
    }

    public static Integer getExpiration() {
        return expiration;
    }

    public static void setExpiration(Integer expiration) {
        JWTConfig.expiration = expiration;
    }

    public static String getAntMatchers() {
        return antMatchers;
    }

    public static void setAntMatchers(String antMatchers) {
        JWTConfig.antMatchers = antMatchers;
    }
}
