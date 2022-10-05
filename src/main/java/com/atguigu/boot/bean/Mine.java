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
    private String holdder;

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

    public String getHoldder() {
        return holdder;
    }

    public void setHoldder(String holdder) {
        this.holdder = holdder;
    }

    @Override
    public String toString() {
        return "Mine{" +
                "buyTicketSerialNumber='" + buyTicketSerialNumber + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", passengerId='" + passengerId + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatType='" + seatType + '\'' +
                ", holdder='" + holdder + '\'' +
                '}';
    }
}
