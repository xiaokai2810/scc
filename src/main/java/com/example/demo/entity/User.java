package com.example.demo.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String address;

    private String lines;

    public User(Integer id, String username, String password, String address,String lines) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.lines=lines;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getLines () {
        return lines;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}