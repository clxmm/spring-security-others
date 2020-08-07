package org.clxmm.springbootsecuritydemo.security.handle;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.clxmm.springbootsecuritydemo.common.respon.ResponseCode;
import org.clxmm.springbootsecuritydemo.common.respon.ResponseResult;
import org.clxmm.springbootsecuritydemo.common.respon.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录处理类
 *
 * @author clx
 * @date 2020/8/7 11:04
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {


    /**
     * 用户未登录返回结果
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult<Object> e = ResponseResult.e(ResponseCode.NOT_SING_IN);
        ResultUtil.responseJson(response, e);
    }


}
