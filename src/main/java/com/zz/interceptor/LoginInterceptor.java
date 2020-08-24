package com.zz.interceptor;

import com.zz.pojo.User;
import com.zz.utils.StrUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI+"这是URI");
        User user = (User)request.getSession().getAttribute(StrUtils.LOGIN_USER);
//        User user = (User) request.getServletContext().getAttribute(StrUtils.LOGIN_USER);
        System.out.println(user+"这是user的值");
//        HttpSession user = request.getSession();
        if (user ==null){
            //证明没有登录，跳转到登录页面
            String header = request.getHeader("X-Requested-With");
            if (header !=null && header.equals("XMLHttpRequest")){
                response.getWriter().write("{'code':0, 'info':'未登录'}");
            } else {
                response.sendRedirect(request.getContextPath() +"/login.html");
            }
            return false;
        } else {
            return true;
        }
    }
}
