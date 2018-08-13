package com.example.shiroweb.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class StatelessAccessControlFilter extends AccessControlFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        //1.客户端生成的消息摘要
        String clientDigest = servletRequest.getParameter("digest");

        //2.客户端传入用户身份
        String username = servletRequest.getParameter("username");

        //3.客户端请求的参数列表
        Map<String , String[]> params = new HashMap<String, String[]>(servletRequest.getParameterMap());
        params.remove("digest");

        //4.生成无状态Token
        StatelessAuthenticationToken token = new StatelessAuthenticationToken(username, params, clientDigest);

        try {
            //5.交给realm登录
            super.getSubject(servletRequest, servletResponse).login(token);
        } catch (AuthenticationException e) {
            //6.登录失败
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            //返回401
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("login error");
            //e.printStackTrace();
            //登录失败
            return false;
        }
        //登陆成功
        return true;
    }
}
