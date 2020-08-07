package org.clxmm.springbootsecuritydemo.security.jwt;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.digester.ArrayStack;
import org.clxmm.springbootsecuritydemo.common.config.JWTConfig;
import org.clxmm.springbootsecuritydemo.common.util.JWTTokenUtil;
import org.clxmm.springbootsecuritydemo.security.entity.SelfUserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 *
 * @author clx
 * @date 2020/8/6 10:59
 */
@Slf4j
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {


    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(JWTConfig.getTokenHeader());
        if (StringUtils.isNotBlank(tokenHeader)) {
            try {
                // 获取用户名
                String username = JWTTokenUtil.getUsername(tokenHeader);
                if (StringUtils.isNotBlank(username)) {
                    // 获取角色
                    List<GrantedAuthority> authorities = new ArrayStack<>();
                    String authority  = JWTTokenUtil.get(tokenHeader, "authorities");
                    if (StringUtils.isNotBlank(authority)) {
                        List<Map<String,String>> authorityMap = JSONObject.parseObject(authority, List.class);
                        for (Map<String, String> role: authorityMap) {
                            if (!org.springframework.util.StringUtils.isEmpty(role)) {
                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                            }
                        }
                    }
                    // 组装参数
                    SelfUserEntity selfUserEntity = new SelfUserEntity();
                    selfUserEntity.setUsername(username);
                    long uid = Long.parseLong(JWTTokenUtil.get(tokenHeader, "uid"));
                    selfUserEntity.setUserId(uid);
                    selfUserEntity.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(selfUserEntity, uid, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (TokenExpiredException e) {
                log.error("token 过期 {} :" + e.getMessage());
            } catch (Exception e) {
                log.error("无效token {} :" + e.getMessage());
            }
        }
    }
}
