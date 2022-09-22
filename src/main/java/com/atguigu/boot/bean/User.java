package com.atguigu.boot.bean;

public class User {
    private String name;
    private String paw;

    public User(String name, String paw) {
        this.name = name;
        this.paw = paw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaw() {
        return paw;
    }

    public void setPaw(String paw) {
        this.paw = paw;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", paw='" + paw + '\'' +
                '}';
    }
}
