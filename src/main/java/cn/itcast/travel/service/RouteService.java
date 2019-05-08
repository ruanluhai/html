package cn.itcast.travel.service;

import cn.itcast.travel.dao.impl.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImp;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;


import java.util.List;

public interface RouteService {
    List query(int cid, int start, int pageSize, String rname);

    int count(int cid, String rname);

    PageBean pb(int currentPage, int pageSize, int cid, String  rname);

    Route findOne(String rid);
}
