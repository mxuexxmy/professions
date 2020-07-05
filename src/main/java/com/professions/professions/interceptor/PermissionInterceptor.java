package com.professions.professions.interceptor;

import com.professions.professions.commons.contant.AdminPermission;
import com.professions.professions.commons.contant.ConstantUtils;
import com.professions.professions.entity.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 */
public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 以 login 结尾的请求
        if (modelAndView != null && modelAndView.getView() != null && modelAndView.getViewName().endsWith("login")) {
            TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if (user != null) {
                if (user.getAuthority() == AdminPermission.SystemAdmin) {
                    httpServletResponse.sendRedirect("system/main");
                } else if(user.getAuthority() == AdminPermission.CollegeAdmin) {
                    httpServletResponse.sendRedirect("college/main");
                } else {
                    httpServletResponse.sendRedirect("profession/main");
                }

            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
