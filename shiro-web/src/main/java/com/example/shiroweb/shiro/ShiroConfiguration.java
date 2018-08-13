package com.example.shiroweb.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //配置
        shiroFilterFactoryBean.getFilters().put("statelessAuthc", statelessAccessControlFilter());

        //拦截请求
        Map<String,String> filterChainDefinitionMap =new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/**","statelessAuthc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置subjectFactory
        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(statelessAuthorizingRealm());

        //禁用session作为存储策略
        ((DefaultSessionStorageEvaluator)((DefaultSubjectDAO)securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);
        return securityManager;
    }

    /**
     * subject工厂
     * @return
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory(){
        StatelessDefaultSubjectFactory statelessDefaultSubjectFactory = new StatelessDefaultSubjectFactory();
        return statelessDefaultSubjectFactory;
    }

    /**
     * session 管理器
     * 禁用会话管理
     * @return
     */
    @Bean
    public DefaultSessionManager sessionManager(){
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    /**
     * 注入realm
     * @return
     */
    @Bean
    public StatelessAuthorizingRealm statelessAuthorizingRealm(){
        StatelessAuthorizingRealm statelessAuthorizingRealm = new StatelessAuthorizingRealm();
        return statelessAuthorizingRealm;
    }

    /**
     * 注入过滤器
     * @return
     */
    @Bean
    public StatelessAccessControlFilter statelessAccessControlFilter(){
        StatelessAccessControlFilter statelessAccessControlFilter = new StatelessAccessControlFilter();
        return statelessAccessControlFilter;
    }

}
