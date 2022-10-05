package com.atguigu.boot.bean;

public class mypassengers {
    private String passengerName;
    private String passengerId;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengersId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public String toString() {
        return "mypassengers{" +
                "passengerName='" + passengerName + '\'' +
                ", passengerId='" + passengerId + '\'' +
                '}';
    }
}
