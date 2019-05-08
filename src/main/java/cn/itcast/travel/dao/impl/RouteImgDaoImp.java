package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImp implements RouteImgDao {
    JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List findByRid(int rid) {
        String sql = "select * from tab_route_img where rid=?";

        List query = jt.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        return query;

    }
}
