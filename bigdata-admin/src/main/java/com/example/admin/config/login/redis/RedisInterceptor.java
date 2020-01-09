package com.example.admin.config.login.redis;

import com.alibaba.fastjson.JSON;
import com.example.admin.dto.response.OutputResult;
import com.example.core.constants.AnonymousAccessUrl;
import com.example.core.constants.ResponseCode;
import com.example.core.utils.RedisUtil;
import com.example.core.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 实现服务端自定义token拦截器识别token
 * @author daniel
 * @date 2019-12-23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RedisInterceptor implements HandlerInterceptor {

    private final RedisUtil redisUtil;

    /**
     * 在请求被处理之前拦截请求，查看请求头是否携带token，进行处理
     * @author daniel
     * @date 2019-12-23
     * @param request 请求实体
     * @param response 响应实体
     * @param handler 处理器
     * @return 返回token是否通过验证的结果，布尔类型返回值
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("进入token拦截器的preHandle提前处理模块");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            log.info("handler处理器没有映射到方法，给予通过！");
            return true;
        }

        //此处为不需要登录的接口地址放行
        String visitedUrl = request.getRequestURI();
        if(visitedUrl.contains(AnonymousAccessUrl.LOGIN) || visitedUrl.contains(AnonymousAccessUrl.REGISTRATION)) {
            //表示用户为登录或者注册，给予通过
            log.info("表示用户为登录或者注册，给予通过");
            return true;
        }

        //接下来判断请求实体头部中是否含有token数据
        log.info("判断请求实体头部中是否含有token数据");
        String tokenVal = request.getHeader(null);
        if(StringUtils.isEmpty(tokenVal)) {
            log.info("请求实体头部中token数据为空，表示用户还没登录或者token过期，无法通过！");
            //返回响应实体,ResponseCode对应为token数据为空
            makeResponse(response, ResponseCode.ERROR);
            return false;
        }
        redisUtil.exists(tokenVal);
        log.info("请求实体头部存在token参数, 将在JWT中进行校验！");
        Boolean validationResult = TokenUtil.verify(tokenVal);
        if(validationResult) {
            log.info("token参数在JWT中校验通过，给予通过！");
            return true;
        }
        log.info("token参数在JWT中校验失败，判断token参数为非法数据，请仔细检查！");
        //非法参数的错误
        makeResponse(response, ResponseCode.ILLEGAL);
        return false;
    }

    private void makeResponse(HttpServletResponse response, ResponseCode responseCode) {
        //生成响应数据
        OutputResult<Void> outputResult = new OutputResult<>(responseCode);
        //将数据转换为json格式
        String jsonVal = JSON.toJSONString(outputResult);
        //设置response属性
        response.reset();
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("UTF-8");
        //返回响应
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(jsonVal);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
