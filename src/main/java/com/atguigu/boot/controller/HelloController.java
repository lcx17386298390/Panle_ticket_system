package com.atguigu.boot.controller;

//import org.springframework.jdbc.core.JdbcTemplate;

import com.atguigu.boot.bean.Mine;
import com.atguigu.boot.bean.Mypassengers;
import com.atguigu.boot.bean.Ticket;
import com.atguigu.boot.service.MineService;
import com.atguigu.boot.service.MyPassengersService;
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
import java.util.*;

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
                            @RequestParam("seatNumber") Integer  seatNumber,
                            @RequestParam("seatType") String seatType,
                            @RequestParam("holdder") String holdder) {

        //先查找选购的这个航班的信息，发送到选购页面
        Ticket ticket = ticketService.findTicketByNumber(buyTicketSerialNumber);

        //记得做一个条件判断，是否已经存在或者没有这个航班号所引起的故障
        String mes = mineService.addMineBuy(buyTicketSerialNumber, passengerName, passengerId, seatNumber, seatType, holdder,ticket.getNumber(),ticket.getDepartureDate()+"   "+ticket.getDepartureTime(),ticket.getArrivalDate()+"   "+ticket.getArrivalTime());
        return mes;
    }

    //购买页面
    @GetMapping("/buy")
    public String buyFun() {
        return "buy";
    }

    //点击购买成功，修改数据库并跳转到主页面
    @ResponseBody
    @PostMapping("/buy")
    public List<String> buyFun2(
            //身份证号，航班序列号，机票类型
            @RequestParam("passengersId") String[] passengersIds,
            @RequestParam("serialNumber") String serialNumber,
            @RequestParam("ticketType") String ticketType, HttpSession session) {

        //根据身份证号查人,将人员信息全部存入
        List<Mypassengers> passengersList = new ArrayList<>();
        //根据飞机序列号查找飞机座位
        Ticket ticket = ticketService.findTicketByNumber(serialNumber);
        //信息列表
        List<String>mesList = new ArrayList<>();

        if(passengersIds.length>ticket.getEconomySeat()){
            mesList.add("您的乘客人数大于剩余座位数，无法购买");
            return mesList;
        }
        //购票显示所有人
        String holdder = (String) session.getAttribute("username");
        for (int i = 1; i <= passengersIds.length; i++) {
            passengersList.add(myPassengersService.getPassengerById(passengersIds[i - 1]));
            //获取返回的信息=>经济舱
            String mes = "";
            //经济舱
            if(ticketType.equals("economySeat")){
                mes = mineService.addMineBuy(serialNumber, passengersList.get(i - 1).getPassengerName(), passengersIds[i - 1], ticket.getEcoBeginNumber(), ticketType, holdder,ticket.getNumber(),ticket.getDepartureDate()+"   "+ticket.getDepartureTime(),ticket.getArrivalDate()+"   "+ticket.getArrivalTime());
                ticketService.setEconmyTicket(ticket.getEconomySeat()-1,ticket.getEcoBeginNumber()+1,serialNumber);
                //实现对象更新
                ticket = ticketService.findTicketByNumber(serialNumber);
            }else{  //商务舱
                mes = mineService.addMineBuy(serialNumber, passengersList.get(i - 1).getPassengerName(), passengersIds[i - 1], ticket.getFirBeginNumber(), ticketType, holdder,ticket.getNumber(),ticket.getDepartureDate()+"   "+ticket.getDepartureTime(),ticket.getArrivalDate()+"   "+ticket.getArrivalTime());
                ticketService.setfirstTicket(ticket.getFirstSeat()-1,ticket.getFirBeginNumber()+1,serialNumber);
                //对象更新
                ticket = ticketService.findTicketByNumber(serialNumber);
            }
            //已经买过票了将信息存起来，最终报错
            if(mes.equals("error")){
                mesList.add("顾客"+passengersList.get(i - 1).getPassengerName()+"已经购买过本航班，每人每航班只能购买一张票，无法进行购买");
            }else{
                mesList.add(mes);
            }
        }
        return mesList;
    }

    //返回自己库的人员列表
    @ResponseBody
    @PostMapping("passengersList")
    public List<Mypassengers> passengersList(HttpSession session) {
        return myPassengersService.getPassengers((String) session.getAttribute("username"));
    }


    @GetMapping("/mine")
    public String mineFun(){
        return "mine";
    }

    //已经购买的机票
    @GetMapping("/myBuyTicket")
    public String myBuyTicketFun1(HttpSession session){
        return "myBuyTicket";
    }

    @ResponseBody
    @PostMapping("/myBuyTicket")
    public List<Mine> myBuyTicketFun2(HttpSession session){
        //返回已经购买的飞机票信息
        List<Mine> mineList= mineService.findMineByHoldder((String)session.getAttribute("username"));
        return mineList;
    }

    @ResponseBody
    @PostMapping("/removeBuy")
    public String removeBuy(@RequestParam("buyTicketSerialNumber") String buyTicketSerialNumber,
                          @RequestParam("passengerId") String passengerId){
        mineService.removeBuy(buyTicketSerialNumber,passengerId);
        return "退票成功";
    }

    @GetMapping("/mypassengers")
    public String mypassengersGet(HttpSession session){
        return "mypassengers";
    }

    @ResponseBody
    @PostMapping("/removePassenger")
    public String removePressenger(@RequestParam("passengerId")List<String>passengerIds){
        for(int i =1;i<=passengerIds.size();i++){
            myPassengersService.deletePassenger(passengerIds.get(i-1));
        }
        return "删除成功";
    }
}
