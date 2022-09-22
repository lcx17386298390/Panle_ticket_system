package com.atguigu.boot.interceptor;

import com.atguigu.boot.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String paw = (String) session.getAttribute("userpaw");
        System.out.println("拦截器" + name + "+" + paw);
        if (userService.findUser(name, paw) != 200) {
            if(!(name==null)){
                session.setAttribute("loginmes", "账号或密码错误");
            }
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
