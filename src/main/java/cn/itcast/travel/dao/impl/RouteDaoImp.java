package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImp implements RouteDao {
    JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int count(int cid, String rname) {
        ArrayList list = new ArrayList();
//        String sql = "select count(*) from tab_route where cid=?";
        String sql = "SELECT count(*) from tab_route where 1=1";

        if (cid != 0) {
            sql = sql + " and cid=?";
            list.add(cid);
        }

        if (rname != null && rname.length() > 0 &&!rname.equals("null")) {
            sql = sql + " and rname like ?";
            list.add("%" +rname+ "%");
        }
        Object[] objects = list.toArray();
        return jt.queryForObject(sql, Integer.class, objects);

    }

    @Override
    public List query(int cid, int start, int pageSize, String rname) {
//        String sql = "select * from tab_route where cid=? limit ? , ?";
        String sql = "select * from tab_route where 1=1";
        ArrayList list = new ArrayList();

        if (cid != 0) {
            sql = sql + " and cid=?";
            list.add(cid);
        }

        if (rname != null && rname.length() > 0 &&!rname.equals("null")) {
            sql = sql + " and rname like ?";
            list.add("%" +rname+ "%");
        }
        sql = sql + " limit ? , ?";
        list.add(start);
        list.add(pageSize);
        Object[] objects = list.toArray();
        return jt.query(sql, new BeanPropertyRowMapper<>(Route.class),objects);
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid=?";

        return jt.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
    }
}
