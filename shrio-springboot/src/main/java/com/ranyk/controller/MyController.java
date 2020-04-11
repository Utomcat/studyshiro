package com.ranyk.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:MyController
 * Description:控制类
 *
 * @author ranyi
 * @date 2020-04-10 16:51
 * Version: V1.0
 */
@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("msg","hellp shrio");
        return "index";
    }


    @RequestMapping("/user/add")
    public String add(Model model){
        return "user/add";
    }


    @RequestMapping("/user/update")
    public String update(Model model){
        return "user/update";
    }


    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        return "login";
    }


    @RequestMapping("/login")
    public String login(Model model,String user, String pwd){

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装登录用户对象
        UsernamePasswordToken token = new UsernamePasswordToken(user,pwd);

        try {
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }


    @RequestMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(Model model,String user, String pwd){
        return "未经授权，无法访问此页面！";
    }

}
