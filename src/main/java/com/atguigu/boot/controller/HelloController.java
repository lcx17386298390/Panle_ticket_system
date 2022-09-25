package com.atguigu.boot.controller;

//import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSON;
import com.atguigu.boot.bean.Ticket;
import com.atguigu.boot.service.TicketService;
import com.atguigu.boot.service.UserService;
import com.atguigu.boot.webConfig.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//编写业务
@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

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
        WebSocket.sendMessage("ticket.toString()");
        return "main";
    }

    @GetMapping("/registered")
    public String handle04() {
        return "registered";
    }

    @PostMapping("registered")
    public String regfun(HttpSession session, @RequestParam("username") String name, @RequestParam("password") String paw) {
        userService.addUser(name, paw);
        return "redirect:/regSuccess";
    }

    @GetMapping("/regSuccess")
    public String regSuccess() {
        return "login";
    }


    @GetMapping("/search")
    public String searchFun() {
        return "search";
    }

    //对新页面返回顾客购买信息
    @ResponseBody
    @PostMapping("/search")
    public List<Ticket> searchFun2(HttpSession session) {
        //提取顾客提交的信息
        Map<String, String> ticketMap = (Map<String, String>) session.getAttribute("ticketMap");
        List<Ticket> ticket = ticketService.findTicket(ticketMap.get("originValue"),ticketMap.get("destinationValue"),ticketMap.get("departrueDate"));
        return ticket;
    }

    //提交乘客购买信息
    @ResponseBody
    @PostMapping("/main")
    public String searchFun2(
            @RequestParam("originValue") String originValue,//出发地
            @RequestParam("destinationValue") String destinationValue,//目的地
            @RequestParam("passengersType") String passengersType,//乘客类型
            @RequestParam("departrueTime") String departrueTime, //出发时间
            HttpSession session
    ) {
        Map<String, String> ticketMap = new HashMap<>();
        ticketMap.put("originValue", originValue);
        ticketMap.put("destinationValue", destinationValue);
        ticketMap.put("passengersType", passengersType);
        ticketMap.put("departrueDate", departrueTime);
        session.setAttribute("ticketMap", ticketMap);
        return "success";
    }
}
