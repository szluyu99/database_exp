package com.yusael.entity;

public class Flight {
    private String f_id; // 航班号
    private String f_src; // 起点
    private String f_des; // 终点
    private String f_date; // 日期
    private String f_start_time; // 起飞时刻
    private String f_end_time; // 到达时刻
    private String f_remain_seats; // 剩余座位
    private String f_fares; // 票价
    private String f_discount_nums; // 折扣票数
    private String f_discount; // 折扣率
    private String f_subordinate_company; // 航班所属航空公司

    @Override
    public String toString() {
        return "Flight{" +
                "f_id='" + f_id + '\'' +
                ", f_src='" + f_src + '\'' +
                ", f_des='" + f_des + '\'' +
                ", f_date='" + f_date + '\'' +
                ", f_start_time='" + f_start_time + '\'' +
                ", f_end_time='" + f_end_time + '\'' +
                ", f_remain_seats='" + f_remain_seats + '\'' +
                ", f_fares='" + f_fares + '\'' +
                ", f_discount_nums='" + f_discount_nums + '\'' +
                ", f_discount='" + f_discount + '\'' +
                ", f_subordinate_company='" + f_subordinate_company + '\'' +
                '}';
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_src() {
        return f_src;
    }

    public void setF_src(String f_src) {
        this.f_src = f_src;
    }

    public String getF_des() {
        return f_des;
    }

    public void setF_des(String f_des) {
        this.f_des = f_des;
    }

    public String getF_date() {
        return f_date;
    }

    public void setF_date(String f_date) {
        this.f_date = f_date;
    }

    public String getF_start_time() {
        return f_start_time;
    }

    public void setF_start_time(String f_start_time) {
        this.f_start_time = f_start_time;
    }

    public String getF_end_time() {
        return f_end_time;
    }

    public void setF_end_time(String f_end_time) {
        this.f_end_time = f_end_time;
    }

    public String getF_remain_seats() {
        return f_remain_seats;
    }

    public void setF_remain_seats(String f_remain_seats) {
        this.f_remain_seats = f_remain_seats;
    }

    public String getF_fares() {
        return f_fares;
    }

    public void setF_fares(String f_fares) {
        this.f_fares = f_fares;
    }

    public String getF_discount_nums() {
        return f_discount_nums;
    }

    public void setF_discount_nums(String f_discount_nums) {
        this.f_discount_nums = f_discount_nums;
    }

    public String getF_discount() {
        return f_discount;
    }

    public void setF_discount(String f_discount) {
        this.f_discount = f_discount;
    }

    public String getF_subordinate_company() {
        return f_subordinate_company;
    }

    public void setF_subordinate_company(String f_subordinate_company) {
        this.f_subordinate_company = f_subordinate_company;
    }

    public Flight(String f_id, String f_src, String f_des, String f_date, String f_start_time, String f_end_time, String f_remain_seats, String f_fares, String f_discount_nums, String f_discount, String f_subordinate_company) {
        this.f_id = f_id;
        this.f_src = f_src;
        this.f_des = f_des;
        this.f_date = f_date;
        this.f_start_time = f_start_time;
        this.f_end_time = f_end_time;
        this.f_remain_seats = f_remain_seats;
        this.f_fares = f_fares;
        this.f_discount_nums = f_discount_nums;
        this.f_discount = f_discount;
        this.f_subordinate_company = f_subordinate_company;
    }
}
