package com.beifeng.interceptor;

import com.beifeng.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/1 23:37
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        System.out.println("进入拦截器-------------------");
        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            // 用户未登录
            response.sendRedirect(request.getContextPath()+"/admin");
            return false;
        }

        return true;
    }
}
