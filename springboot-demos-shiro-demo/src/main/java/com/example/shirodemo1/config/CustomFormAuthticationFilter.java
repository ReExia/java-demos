package com.example.shirodemo1.config;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CustomFormAuthticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        //是登录请求提交
        if (isLoginSubmission(request, response)){
            //获取前端输入的参数
            String received = request.getParameter("kaptchaValidate");
            HttpServletRequest request2 = (HttpServletRequest)request;

            String captcha = (String)request2.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            boolean b = received.equalsIgnoreCase(captcha);
            if(!b){
                request.setAttribute("shiroLoginFailure","kaptchaValidateFailed");
                return true;
            }
        }

        return super.onAccessDenied(request, response);
    }
}
