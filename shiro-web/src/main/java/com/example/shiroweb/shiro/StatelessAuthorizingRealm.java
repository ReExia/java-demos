package com.example.shiroweb.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class StatelessAuthorizingRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessAuthenticationToken statelessAuthenticationToken = (StatelessAuthenticationToken) authenticationToken;
        String username = statelessAuthenticationToken.getUsername();

        //这个key要和客户端一致
        //todo 最好把key存在数据库或者配置文件
        String key = "setsuna1234";

        String serverDigest = HmacSHA256Utils.digest(key, statelessAuthenticationToken.getParmas());

        System.out.println("serverDigest:"+serverDigest+",clientDigest:"+statelessAuthenticationToken.getClientDigest());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username,
                serverDigest,
                getName()
        );

        return authenticationInfo;
    }
}
