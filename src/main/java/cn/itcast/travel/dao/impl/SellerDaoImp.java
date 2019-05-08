package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImp implements SellerDao{
    JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findById(int id) {
        String sql = "select * from tab_seller where sid=?";

        return jt.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class),id);
    }
}