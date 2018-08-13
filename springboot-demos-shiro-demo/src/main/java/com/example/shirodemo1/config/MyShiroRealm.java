package com.example.shirodemo1.config;

import com.example.shirodemo1.bean.SysPermission;
import com.example.shirodemo1.bean.SysRole;
import com.example.shirodemo1.bean.UserInfo;
import com.example.shirodemo1.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 身份认证 (登录)
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

        /**
         * 1.获取用户输入的账号
         * 2.通过 username 从数据库中进行查找,找到userInfo对象
         * 3.加密,使用 SimpleAuthenticationInfo 进行身份认证
         * 4.返回身份处理对象
         */

        //1.获取用户输入的账号
        String username = (String) token.getPrincipal();

        //2.通过username从数据库进行查找,找到userInfo对象
        UserInfo userInfo = userInfoService.findByUsername(username);
        if (userInfo == null){
            return null;
        }

        //3.加密使用SimpleAuthenticationInfo进行身份处理
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getUserNameAndSalt()),
                getName()
        );

        //4.返回身份处理对象
        return simpleAuthenticationInfo;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("MyShiroRealm.doGetAuthorizationInfo()");
        //shiro提供的授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //获取到用户的权限信息
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        List<SysRole> roles = userInfo.getRoles();
        for (SysRole role : roles) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRole());
            //添加权限
            List<SysPermission> permissions = role.getPermissions();
            //添加权限.
            for(SysPermission p:role.getPermissions()){
                simpleAuthorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }
}
