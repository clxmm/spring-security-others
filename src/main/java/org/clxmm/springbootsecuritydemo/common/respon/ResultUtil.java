package org.clxmm.springbootsecuritydemo.common.respon;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 返回结果工具类
 *
 * @author clx
 * @date 2020/8/7 10:48
 */
@Slf4j
public class ResultUtil {


    private ResultUtil() {
    }

    /**
     * 使用response输出JSON
     *
     * @param response
     * @param resultMap
     */
    public static void responseJson(ServletResponse response, ResponseResult resultMap) {

        try (PrintWriter writer = response.getWriter()) {
//            response.setCharacterEncoding("utf-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            writer.println(resultMap);
        } catch (Exception e) {
            log.error("输出json 异常 { }:" + e.getMessage());
        }
    }











}
