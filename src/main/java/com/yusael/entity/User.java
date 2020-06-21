package com.yusael.entity;
public class User {

    private String u_id;
    private String u_username;
    private String u_password;

    public User() {
    }

    public User(String u_id, String u_username, String u_password) {
        this.u_id = u_id;
        this.u_username = u_username;
        this.u_password = u_password;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_username() {
        return u_username;
    }

    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id='" + u_id + '\'' +
                ", u_username='" + u_username + '\'' +
                ", u_password='" + u_password + '\'' +
                '}';
    }
}
