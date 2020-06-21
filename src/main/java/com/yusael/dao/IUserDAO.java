package com.yusael.dao;

import com.yusael.entity.User;

public interface IUserDAO {
    User login(String username, String password);
}
