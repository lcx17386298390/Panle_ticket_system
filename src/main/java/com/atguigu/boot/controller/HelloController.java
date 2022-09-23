package com.atguigu.boot.controller;

//import org.springframework.jdbc.core.JdbcTemplate;

import com.atguigu.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

//编写业务
@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String handle01(Model model, HttpSession session) {
        model.addAttribute("loginmes", "请输入账号密码");
        if (session.getAttribute("loginmes") != null) {
            model.addAttribute("loginmes", session.getAttribute("loginmes"));
        }
        if (session.getAttribute("username") != null) {
            model.addAttribute("username", session.getAttribute("username"));
        }
        if (session.getAttribute("userPaw") != null) {
            model.addAttribute("userPaw", session.getAttribute("userPaw"));
        }
        return "login";
    }


    @PostMapping("/login")
    public String handle02(@RequestParam("username") String name, @RequestParam("userpaw") String paw, Model model, HttpSession session) {
        session.setAttribute("username", name);
        session.setAttribute("userpaw", paw);
        session.setAttribute("userName", name);
        return "redirect:/mainIndex";
    }

    @GetMapping("/mainIndex")
    public String handle03() {
        return "main";
    }

    @GetMapping("/registered")
    public String handle04() {
        return "registered";
    }

    @PostMapping("registered")
    public String regfun(HttpSession session, @RequestParam("username") String name, @RequestParam("password") String paw) {
        userService.addUser(name,paw);
        return "redirect:/regSuccess";
    }

    @GetMapping("/regSuccess")
    public String regSuccess(){
        return "login";
    }

    @GetMapping("/search")
    public String searchFun(@RequestParam("originValue")String originValue,
                            @RequestParam("destinationValue")String destinationValue ,
                            @RequestParam("passengersType")String passengersType){

    }
}
