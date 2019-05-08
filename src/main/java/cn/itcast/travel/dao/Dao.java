package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface Dao {

    boolean regist(User user);
    void save(User user);

    User login(User user);
}

