package com.tls.xblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tls.xblog.entity.User;
import com.tls.xblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    @Qualifier("UserService")
    UserService userService;

    /*如果不设置请求类型，则默认所有*/
    /*此接口用于拦截器转发，初始请求类型未知，故不设置请求类型，默认接收所有*/
    @RequestMapping(value = "/notlogin")
    public String notLogin(HttpServletResponse response) throws JsonProcessingException {
        System.out.println("未登录");
        response.setStatus(403);
        return "请先登录!";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public String login( HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        request.getSession().removeAttribute("uid");
        Cookie cookie1 = new Cookie("uid","");
        cookie1.setPath("/");
        cookie1.setMaxAge(0);
        Cookie cookie2 = new Cookie("username","");
        cookie2.setPath("/");
        cookie2.setMaxAge(0);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return "已退出!";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("uid") String uid, @RequestParam("passwd") String passwd, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        User user = userService.login(uid, passwd);
        System.out.println(user);
        ObjectMapper objectMapper = new ObjectMapper();
        if (user!=null){
            Map map = new HashMap();
            map.put("user",user);
            map.put("msg","登录成功!");
            map.put("code",1);
            Cookie cookie1 = new Cookie("uid",user.getUid());
            cookie1.setPath("/");
            Cookie cookie2 = new Cookie("username",user.getUsername());
            cookie2.setPath("/");
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            //如果有则返回。如果没有则创建并自动以cookie形式设置到前端
            request.getSession().setAttribute("uid",user.getUid());
            System.out.println(request.getSession().getAttribute("uid"));

            return objectMapper.writeValueAsString(map);
        }else {
            Map map = new HashMap();
            map.put("user",null);
            map.put("msg","用户名或密码错误!");
            map.put("code",0);
            return objectMapper.writeValueAsString(map);
        }

    }

}
