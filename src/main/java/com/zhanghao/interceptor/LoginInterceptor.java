package com.zhanghao.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (null != request.getSession().getAttribute("user")) {
            return true;
        }
        request.getSession().setAttribute("message", "请先登录");
        response.sendRedirect("/blog/admin");
        return false;
    }

}
