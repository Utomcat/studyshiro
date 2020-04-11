package com.ranyk.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * ClassName:ShiroConfig
 * Description:Shiro配置类
 *
 * @author ranyi
 * @date 2020-04-10 17:28
 * Version: V1.0
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean对象(ShiroFilter工厂)
     * @param securityManager SecurityManager对象
     * @return 返回创建的工厂对象
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFatoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        //添加用户的权限认证过滤器
        /*
         * anon: 无需认证就可以访问
         * authc： 必须认证了才能访问
         * user: 必须拥有 保存用户信息至cookie中的功能 才能访问
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色才能进行访问
         */
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        //授权
        //设置请求需要那个权限才能访问
        map.put("/user/add","perms[user:add]");
        map.put("/user/update","perms[user:update]");


        //map.put("/user/add","authc");
        //map.put("/user/update","authc");
        map.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(map);



        //设置登录的请求
        bean.setLoginUrl("/toLogin");

        //设置未授权的请求
        bean.setUnauthorizedUrl("/unauthorized");
        return bean;
    }



    /**
     * 创建 DafaultWebSecurityManager 对象
     * @param userRealm 传入参数 UserRealm对象
     * @return 返回创建的DefaultWebSecurityManager对象
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /**
     * 创建realm对象
     * @return 返回realm对象
     */
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }


    /**
     * 配置ShiroDialect
     * @return ShiroDialect 实例对象
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
