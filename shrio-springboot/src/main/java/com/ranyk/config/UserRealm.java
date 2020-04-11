package com.ranyk.config;

import com.ranyk.pojo.User;
import com.ranyk.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName:UserRealm
 * Description:自定义的Realm对象
 *
 * @author ranyi
 * @date 2020-04-10 17:30
 * Version: V1.0
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 用户授权方法
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");

        //拿到当前的用户对象
        Subject subject = SecurityUtils.getSubject();
        //获得当前的User对象
        User principal = (User) subject.getPrincipal();
        //设置用户的权限
        info.addStringPermission(principal.getPerms());


        return info;
    }

    /**
     * 用户认证方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //模拟数据
        /*String name = "ranyk";
        String password = "123456";*/

        UsernamePasswordToken userTok = (UsernamePasswordToken) token;
        //真实数据
        User user = userService.queryUserByName(userTok.getUsername());
        if (user == null){
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginuser",user);


        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
