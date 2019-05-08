package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.Dao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DaoImp implements Dao {
    JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public boolean regist(User user) {
        String sql = "select * from tab_user where username=?";

        try {
            User user1 = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername());

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";
        int update = jt.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail());
    }

    @Override
    public User login(User user) {
        String sql = "select * from tab_user where username=? and password=?";
        User user1;

        try {

            user1 = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword());

        } catch (Exception e) {
            return null;
        }
        return user1;
    }
}
