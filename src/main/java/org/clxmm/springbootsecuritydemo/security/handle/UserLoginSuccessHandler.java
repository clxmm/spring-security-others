package org.clxmm.springbootsecuritydemo.security.handle;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.springbootsecuritydemo.common.respon.ResponseCode;
import org.clxmm.springbootsecuritydemo.common.respon.ResponseResult;
import org.clxmm.springbootsecuritydemo.common.util.JWTTokenUtil;
import org.clxmm.springbootsecuritydemo.security.entity.SelfUserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author clx
 * @date 2020/8/7 11:16
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SelfUserEntity selfUserEntity = (SelfUserEntity) authentication.getPrincipal();
        String token = JWTTokenUtil.sign(selfUserEntity);
        // 封装返回参数
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", selfUserEntity);
        ResponseResult.builder().status(ResponseCode.SIGN_IN_OK.code).msg("登录成功").data(map);
    }


}
