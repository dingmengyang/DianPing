package com.imooc.controller.interceptors;

import com.imooc.constant.SessionKeyConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截登录之外的请求，检查session里是否有登录信息
 */
public class SessionInterceptor implements HandlerInterceptor{

    /*在执行Handler处理方法之前执行
     *@return true:执行下一个拦截器，执行完所有拦截器后，再执行被拦截的Controller
     *        false：从当前拦截器往回执行之前的所有拦截器的afterCompletion方法，再退出拦截器链
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest.getSession().getAttribute(SessionKeyConstant.USER_INFO)!=null){
            return true;
        }
        httpServletRequest.getRequestDispatcher("/login/sessionTimeout").forward(httpServletRequest,httpServletResponse);
        return false;
    }

    //在进行Handler处理方法之后，返回ModelAndView之前
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //在Handler方法执行完后执行
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
