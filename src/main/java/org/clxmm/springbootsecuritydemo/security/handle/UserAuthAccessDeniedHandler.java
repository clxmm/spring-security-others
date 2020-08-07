package org.clxmm.springbootsecuritydemo.security.handle;

import org.clxmm.springbootsecuritydemo.common.respon.ResponseCode;
import org.clxmm.springbootsecuritydemo.common.respon.ResponseResult;
import org.clxmm.springbootsecuritydemo.common.respon.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 暂无权限处理类
 *
 * @author clx
 * @date 2020/8/7 10:48
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseResult<Object> build = ResponseResult.builder().status(ResponseCode.NOT_SING_IN.code).data("权限缺失").msg("权限缺失").build();
        ResultUtil.responseJson(response,build);
    }




}
