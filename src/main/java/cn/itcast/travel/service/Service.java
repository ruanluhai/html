package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface Service {
    boolean regist(User user);

    User login(User user);
}
