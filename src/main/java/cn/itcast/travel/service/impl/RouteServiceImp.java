package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImp;
import cn.itcast.travel.dao.impl.RouteImgDaoImp;
import cn.itcast.travel.dao.impl.SellerDaoImp;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImp implements RouteService {
    RouteDao rd = new RouteDaoImp();
    RouteImgDao rout = new RouteImgDaoImp();
    SellerDao sell = new SellerDaoImp();
//    Route route = new Route();

    @Override
    public List query(int cid, int start, int pageSize, String rname) {
        return rd.query(cid, start, pageSize, rname);
    }

    @Override
    public int count(int cid, String rname) {
        return rd.count(cid, rname);
    }

    @Override
    public PageBean pb(int currentPage, int pageSize, int cid, String rname) {
        PageBean pb = new PageBean();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = rd.count(cid, rname);
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize:  totalCount / pageSize +1;
        pb.setTotalPage(totalPage);

        int start = (currentPage - 1) * pageSize;
        List list = query(cid, start, pageSize, rname);
        pb.setList(list);

        return pb;
    }

    @Override
    public Route findOne(String rid) {
        // 根据id去route表中查询route对象
        Route route = rd.findOne(Integer.parseInt(rid));

        // 根据route的id查询图片集合信息tab_route_img
        List routeImg = rout.findByRid(Integer.parseInt(rid));
        route.setRouteImgList(routeImg);

        // 根据route的sid查询卖家信息
        Seller seller = sell.findById(route.getSid());
        route.setSeller(seller);

        return route;
    }
}
