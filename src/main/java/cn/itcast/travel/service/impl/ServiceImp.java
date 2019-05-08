package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.Dao;
import cn.itcast.travel.dao.impl.DaoImp;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.Service;
import cn.itcast.travel.util.UuidUtil;

import java.util.UUID;

public class ServiceImp implements Service {
    Dao d = new DaoImp();
    @Override
    public boolean regist(User user) {
        if (d.regist(user)) {
            return false;
        };
        d.save(user);
        return true;
    }

    @Override
    public User login(User user) {
        return d.login(user);
    }

}
