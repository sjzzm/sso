package com.ithuang.login.controller;

import com.ithuang.login.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "target",required = false,defaultValue = "http://localhost:8080/") String target, HttpSession session){
        session.setAttribute("target",target);
        return "login";
    }

    @PostMapping("/check")
    public String checkUser(User user, HttpSession session, HttpServletResponse response){
        String target = session.getAttribute("target").toString();
        String token = "";
        //模拟数据库验证
        if(user.getUsername().equals("admin") && user.getPassword().equals("123456")) {
            //模拟一个token 因为目前没用域名，所以直接把token拼接在url上面了
            token = UUID.randomUUID().toString();
//            Cookie cookie = new Cookie("token",token);
//            cookie.setDomain("baidu.com");
//            response.addCookie(cookie);
            return "redirect:" + target +"?token="+token;
        }else {
            session.setAttribute("msg","登录失败，请检查用户名和密码后重试！");
            return "login";
        }
    }

}
