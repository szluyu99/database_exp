package com.yusael.test;

import com.yusael.dao.impl.UserDAOImpl;
import com.yusael.entity.User;

public class MyTest {
    public static void main(String[] args) {
        /*List<Flight> flights = new FlightDAOImpl().queryAll();
        for (Flight flight : flights) {
            System.out.println(flight);*/
        User hanlei = new UserDAOImpl().login("hanlei", "123456");
        System.out.println(hanlei);
    }
}
