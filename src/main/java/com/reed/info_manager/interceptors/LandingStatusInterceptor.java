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
public class LandingStatusInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;
    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。

        //这里的User是登陆时放入session的
        User user = (User) session.getAttribute("user");
        Parent parent = (Parent) session.getAttribute("parent");
        Manager manager = (Manager) session.getAttribute("manager");

        //如果session中没有user，表示没登陆
        if(user!=null&&parent==null&&manager==null){
            response.sendRedirect("/index");
            return false;
        }else if(parent!=null){
            response.sendRedirect("/myInfoDetail/parent");
            return false;
            //放行
        }else if(manager!=null){
            response.sendRedirect("/manager/index");
            return false;
        } else
            return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
