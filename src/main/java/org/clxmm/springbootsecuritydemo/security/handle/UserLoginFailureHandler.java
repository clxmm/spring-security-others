package org.clxmm.springbootsecuritydemo.security.handle;

import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.springbootsecuritydemo.common.respon.ResponseCode;
import org.clxmm.springbootsecuritydemo.common.respon.ResponseResult;
import org.clxmm.springbootsecuritydemo.common.respon.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败返回结果
 *
 * @author clx
 * @date 2020/8/7 11:08
 */
@Component
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * 登录失败返回结果
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String msg = "未知系统异常,请联系管理员";
        if (exception instanceof UsernameNotFoundException) {
            msg = "登陆失败，用户名不存在";
            log.error(msg);
        } else if (exception instanceof LockedException) {
            msg = "登录失败,账号冻结";
        } else if (exception instanceof BadCredentialsException) {
            msg = "登陆失败，密码错误";
        }
        ResultUtil.responseJson(response, responseResult);
    }


}
