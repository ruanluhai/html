package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    // 统计信息条数
    int count(int cid, String rname);

    // 查询消息
    List query(int cid, int start, int pageSize, String rname);

    public Route findOne(int rid);
}
