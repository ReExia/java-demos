package com.example.shirodemo1.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.Filter;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.配置 ShiroFilterFactory : ShiroFilterFactoryBean
 * 2.配置SecurityManager
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        /**
         * 1.定义 ShiroFactoryBean
         * 2.设置 SecurityManager
         * 3.配置拦截器 + 配置登录和登录成功得地址
         * 4.返回 ShiroFactoryBean
         */

        //1. 定义 ShiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //2. 设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

       /* Map<String, Filter> filters = new HashMap<String, Filter>();
        CustomFormAuthticationFilter customFormAuthticationFilter = new CustomFormAuthticationFilter();
        filters.put("authc", customFormAuthticationFilter);
        shiroFilterFactoryBean.setFilters(filters);*/

        //3. 配置拦截的url
        Map<String, String> filterChainMap = new LinkedHashMap<>();

        // 配置退出url,这个由shiro进行实现的
        filterChainMap.put("/logout", "logout");
        //设置favicon.ico:匿名访问anon
        filterChainMap.put("/favicon.ico", "anon");
        //可匿名访问到js文件
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/img/**", "anon");

        //authc : 所有url都必须通过认证才能访问
        filterChainMap.put("/**", "authc");
        //设置默认登录的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置登录成功跳转的url
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //设置未授权url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        //返回 ShiroFilterFactoryBean
        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro aop注解支持.
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //注入授权认证
        securityManager.setRealm(myShiroRealm());

        //注入缓存管理器
        securityManager.setCacheManager(ehCacheManager());

        //设置rememberMe
        securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    /**
     * 自定义认证,授权器
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 密码加密算法.
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        //HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        MyHashedCredentialsMatcher hashedCredentialsMatcher = new MyHashedCredentialsMatcher(ehCacheManager());
        //加密算法 todo 加密算法 后面可以写到配置文件里
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数 todo 加密次数 后面可以写到配置文件里
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 注入Ehcache缓存.
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        //配置缓存文件.
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * 是shiro - thymeleaf 解析.
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


    /**
     * 验证码生成器
     * @return
     */
    @Bean
    public Producer producer() {
        System.out.println("ShiroConfiguration.captchaProducer()");
        DefaultKaptcha producer = new DefaultKaptcha();
        Properties properties = new Properties();

        /*kaptcha.border  是否有边框  默认为true  我们可以自己设置yes，no
        kaptcha.border.color   边框颜色   默认为Color.BLACK
        kaptcha.border.thickness  边框粗细度  默认为1
        kaptcha.producer.impl   验证码生成器  默认为DefaultKaptcha
        kaptcha.textproducer.impl   验证码文本生成器  默认为DefaultTextCreator
        kaptcha.textproducer.char.string   验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
        kaptcha.textproducer.char.length   验证码文本字符长度  默认为5
        kaptcha.textproducer.font.names    验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        kaptcha.textproducer.font.size   验证码文本字符大小  默认为40
        kaptcha.textproducer.font.color  验证码文本字符颜色  默认为Color.BLACK
        kaptcha.textproducer.char.space  验证码文本字符间距  默认为2
        kaptcha.noise.impl    验证码噪点生成对象  默认为DefaultNoise
        kaptcha.noise.color   验证码噪点颜色   默认为Color.BLACK
        kaptcha.obscurificator.impl   验证码样式引擎  默认为WaterRipple
        kaptcha.word.impl   验证码文本字符渲染   默认为DefaultWordRenderer
        kaptcha.background.impl   验证码背景生成器   默认为DefaultBackground
        kaptcha.background.clear.from   验证码背景颜色渐进   默认为Color.LIGHT_GRAY
        kaptcha.background.clear.to   验证码背景颜色渐进   默认为Color.WHITE
        kaptcha.image.width   验证码图片宽度  默认为200
        kaptcha.image.height  验证码图片高度  默认为50*/

        // 是否有边框  默认为true  我们可以自己设置yes，no
        properties.put("kaptcha.border", "yes");

        //边框颜色   默认为Color.BLACK
        properties.put("kaptcha.border.color", "105,179,90");

        //字体颜色;
        properties.put("kaptcha.textproducer.font.color", "blue");

        //验证码样式引擎  默认为WaterRipple
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");

        // 验证码图片宽度  默认为200
        properties.put("kaptcha.image.width", "145");

        //验证码图片高度  默认为50
        properties.put("kaptcha.image.height", "45");

        //验证码文本字符大小  默认为40
        properties.put("kaptcha.textproducer.font.size", "45");

        //存放在session中的key;
        properties.put("kaptcha.session.key", "code");

        //产生字符的长度
        properties.put("kaptcha.textproducer.char.length", "4");

        //文本字符字体
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");

        Config config = new Config(properties);
        producer.setConfig(config);
        return producer;
    }


}
