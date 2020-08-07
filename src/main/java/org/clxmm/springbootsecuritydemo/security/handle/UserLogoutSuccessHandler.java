package org.clxmm.springbootsecuritydemo.security.handle;

import org.clxmm.springbootsecuritydemo.common.respon.ResponseCode;
import org.clxmm.springbootsecuritydemo.common.respon.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author clx
 * @date 2020/8/7 13:32
 */
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        ResponseResult.builder().status(ResponseCode.LOGOUT_OK.code).msg("登录成功").data(null);
    }


}
