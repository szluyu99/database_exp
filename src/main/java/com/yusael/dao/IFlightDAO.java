package com.yusael.dao;

import com.yusael.entity.Flight;
import java.util.List;

public interface IFlightDAO {

    // 添加航班信息
    void add(Flight flight);

    // 删除航班信息
    void delete(String id);

    // 更新航班信息
    void update(String id, Flight flight);

    // 查询所有航班信息
    List<Flight> queryAll();

    // 根据Id查询航班
    Flight queryById(String id);
}
