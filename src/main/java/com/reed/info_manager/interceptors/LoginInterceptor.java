package com.reed.info_manager.interceptors;

import com.reed.info_manager.entity.Manager;
import com.reed.info_manager.entity.Parent;
import com.reed.info_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) session.getAttribute("user");
        Parent parent = (Parent) session.getAttribute("parent");
        Manager manager = (Manager) session.getAttribute("manager");
        if(user==null&&parent==null&&manager==null){
            response.sendRedirect("/login");
            return false;
        }else{
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
