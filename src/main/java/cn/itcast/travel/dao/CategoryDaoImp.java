package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImp implements CategoryDao {
    JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return jt.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}
