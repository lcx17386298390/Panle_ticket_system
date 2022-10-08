package com.atguigu.boot.bean;

//我的购买内容
public class Mine {

    //购买的飞机航班序列
    private String buyTicketSerialNumber;
    //乘客姓名
    private String passengerName;
    //身份证号
    private String passengerId;
    //座位号
    private String seatNumber;
    //座位类型
    private String seatType;
    //拥有者（暂时省略）
    private String holder;
    //出发时间
    private String departureTime;

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    //到达时间
    private String arrivalTime;


    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    //飞机航次
    private String ticketNumber;

    public String getBuyTicketSerialNumber() {
        return buyTicketSerialNumber;
    }

    public void setBuyTicketSerialNumber(String buyTicketSerialNumber) {
        this.buyTicketSerialNumber = buyTicketSerialNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }


    @Override
    public String toString() {
        return "Mine{" +
                "buyTicketSerialNumber='" + buyTicketSerialNumber + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", passengerId='" + passengerId + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatType='" + seatType + '\'' +
                ", holder='" + holder + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", ticketNumber='" + ticketNumber + '\'' +
                '}';
    }
}
