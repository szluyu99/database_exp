package com.yusael.dao.impl;

import com.yusael.dao.IFlightDAO;
import com.yusael.entity.Flight;
import com.yusael.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements IFlightDAO {
    public int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            ps = conn.prepareStatement(sql);
            // 遍历参数
            for (int i = 0; i < params.length; i++) {
                // ps.setString(1, stu.getName());
                // ps.setInt(2, stu.getAge());
                ps.setObject(i + 1, params[i]);

            }
            // 4.执行语句
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }

    public void add(Flight flight) {
        String sql = "INSERT INTO flight(f_id, f_src, f_des, f_date, f_start_time, f_end_time, " +
                "f_remain_seats, f_fares, f_discount_nums, f_discount, f_subordinate_company) VALUES " +
                "(?,?,?,?,?,?,?,?,?,?,?)";
        executeUpdate(sql, flight.getF_id(), flight.getF_src(), flight.getF_des(), flight.getF_date(),
                flight.getF_start_time(), flight.getF_end_time(), flight.getF_remain_seats(), flight.getF_fares(),
                flight.getF_discount_nums(), flight.getF_discount_nums(), flight.getF_subordinate_company());
    }

    public void delete(String id) {
        String sql = "DELETE FROM flight WHERE f_id = ?";
        executeUpdate(sql, id);
    }

    public void update(String id, Flight flight) {
        String sql = "UPDATE flight SET f_id=?, f_src=?, f_des=?, f_date=?, f_start_time=?, f_end_time=?," +
                "f_remain_seats=?, f_fares=?, f_discount_nums=?, f_discount=?, f_subordinate_company=?" +
                "WHERE f_id=?";
        executeUpdate(sql, flight.getF_id(), flight.getF_src(), flight.getF_des(), flight.getF_date(),
                flight.getF_start_time(), flight.getF_end_time(), flight.getF_remain_seats(), flight.getF_fares(),
                flight.getF_discount_nums(), flight.getF_discount_nums(), flight.getF_subordinate_company(), id);
    }

    public List<Flight> queryAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "select * from flight";
            ps = conn.prepareStatement(sql);
            // 4.执行语句
            rs = ps.executeQuery();
            List<Flight> list = new ArrayList<Flight>();
            while (rs.next()) {
                Flight flight = new Flight(rs.getString("f_id"),rs.getString("f_src"),
                        rs.getString("f_des"),rs.getString("f_date"),rs.getString("f_start_time"),
                        rs.getString("f_end_time"), rs.getString("f_remain_seats"), rs.getString("f_fares"),
                        rs.getString("f_discount_nums"), rs.getString("f_discount"), rs.getString("f_subordinate_company"));
                list.add(flight);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    public Flight queryById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "select * from flight where f_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            // 4.执行语句
            rs = ps.executeQuery();

            if (rs.next()) {
                Flight flight = new Flight(rs.getString("f_id"),rs.getString("f_src"),
                        rs.getString("f_des"),rs.getString("f_date"),rs.getString("f_start_time"),
                        rs.getString("f_end_time"), rs.getString("f_remain_seats"), rs.getString("f_fares"),
                        rs.getString("f_discount_nums"), rs.getString("f_discount"), rs.getString("f_subordinate_company"));
                return flight;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}