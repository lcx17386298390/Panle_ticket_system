package com.atguigu.boot.bean;

public class Mypassengers {
    private String passengerName;
    private String passengerId;
    private String hodler;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getHodler() {
        return hodler;
    }

    public void setHodler(String hodler) {
        this.hodler = hodler;
    }

    @Override
    public String toString() {
        return "Mypassengers{" +
                "passengerName='" + passengerName + '\'' +
                ", passengerId='" + passengerId + '\'' +
                ", hodler='" + hodler + '\'' +
                '}';
    }
}
