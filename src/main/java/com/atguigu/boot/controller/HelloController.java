package com.atguigu.boot.controller;

//import org.springframework.jdbc.core.JdbcTemplate;

import com.atguigu.boot.bean.Ticket;
import com.atguigu.boot.bean.mypassengers;
import com.atguigu.boot.service.MineService;
import com.atguigu.boot.service.MyPassengersService;
import com.atguigu.boot.service.TicketService;
import com.atguigu.boot.service.UserService;
import com.atguigu.boot.webConfig.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Arrays;
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

    @Autowired
    MineService mineService;

    @Autowired
    MyPassengersService myPassengersService;

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
    public String regfun(HttpSession session,
                         @RequestParam("username") String name,
                         @RequestParam("password") String paw,
                         @RequestParam("personName") String personName,
                         @RequestParam("personId") String personId,
                         @RequestParam("sex") String sex,
                         @RequestParam("email") String email,
                         @RequestParam("phone") String phone) {
        userService.addUser(name, paw, personName, personId, sex, phone, email);
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
        List<Ticket> ticket = ticketService.findTicket(ticketMap.get("originValue"), ticketMap.get("destinationValue"), ticketMap.get("departrueDate"));
        System.out.println(ticket.toString());
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

    @ResponseBody
    @PostMapping("/ticketBuyButton")
    public String ticketBuy(@RequestParam("buyTicketSerialNumber") String buyTicketSerialNumber,
                            @RequestParam("passengerName") String passengerName,
                            @RequestParam("passengerId") String passengerId,
                            @RequestParam("seatNumber") String seatNumber,
                            @RequestParam("seatType") String seatType,
                            @RequestParam("holdder") String holdder){

        //先查找选购的这个航班的信息，发送到选购页面
        Ticket ticket = ticketService.findTicketByNumber(buyTicketSerialNumber);


        //记得做一个条件判断，是否已经存在或者没有这个航班号所引起的故障
        String mes = mineService.addMineBuy(buyTicketSerialNumber, passengerName, passengerId, seatNumber, seatType, holdder);
        return mes;
    }

    //购买页面
    @GetMapping("/buy")
    public String buyFun(){
        return "buy";
    }

    //点击购买成功，修改数据库并跳转到主页面
    @ResponseBody
    @PostMapping("/buy")
    public String buyFun2(@RequestParam("passengersId")String[] passengersIds,@RequestParam("serialNumber")String serialNumber,@RequestParam("ticketType")String ticketType){
        System.out.println(Arrays.toString(passengersIds)+"+"+serialNumber+"+"+ticketType);
        return "ok";
    }

    //返回自己库的人员列表
    @ResponseBody
    @PostMapping("passengersList")
    public List<mypassengers> passengersList(){
        return myPassengersService.getPassengers();
    }
}
