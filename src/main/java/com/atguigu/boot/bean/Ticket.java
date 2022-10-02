package com.atguigu.boot.bean;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

//将机票封装成一个类
public class Ticket {
    //航班编号
    private String number;
    //出发地
    private String origin;
    //目的地
    private String destination;
    //出发日期
    private Date departureDate;
    //出发时间
    private Time departureTime;
    //到达日期
    private Date arrivalDate;
    //到达时间
    private Time arrivalTime;
    //经济舱剩余座位数
    private Integer economySeat;
    //经济舱价格
    private Integer economyPrice;
    //经济舱剩余座位数
    private Integer firstSeat;
    //经济舱价格
    private Integer firstPrice;



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getEconomySeat() {
        return economySeat;
    }

    public void setEconomySeat(Integer economySeat) {
        this.economySeat = economySeat;
    }

    public Integer getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(Integer economyPrice) {
        this.economyPrice = economyPrice;
    }

    public Integer getFirstSeat() {
        return firstSeat;
    }

    public void setFirstSeat(Integer firstSeat) {
        this.firstSeat = firstSeat;
    }

    public Integer getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Integer firstPrice) {
        this.firstPrice = firstPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number='" + number + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", economySeat=" + economySeat +
                ", economyPrice=" + economyPrice +
                ", firstSeat=" + firstSeat +
                ", firstPrice=" + firstPrice +
                '}';
    }
}
