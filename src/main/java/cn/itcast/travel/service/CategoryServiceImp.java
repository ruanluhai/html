package cn.itcast.travel.service;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.CategoryDaoImp;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImp implements CategoryService {
    CategoryDao c = new CategoryDaoImp();
    @Override
    public List<Category> findAll() {
        List<Category> all;

        // 创建jedis客户端
        Jedis jedis = JedisUtil.getJedis();

        // 使用sorted排序查询         // 查询redis,获取集合
        Set<String> cite = jedis.zrange("cate", 0, -1);
        Set<Tuple> tuples = jedis.zrangeWithScores("cate", 0, -1);

        if (cite == null || cite.size() == 0) {
            all = c.findAll();
//            System.out.println("从数据库查询");
            for (int i = 0; i < all.size(); i++) {

                // 将数据存入redis
                jedis.zadd("cate", all.get(i).getCid(), all.get(i).getCname());
            }
        }  else {
            all = new ArrayList<Category>();
//            System.out.println("从redis查询");
            // 将数据从set存入到list
            for (Tuple tuple : tuples) {
                Category c = new Category();
                c.setCname(tuple.getElement());
                c.setCid((int)tuple.getScore());
            }


        }


        // 不为空则将数据存入redis中
        return c.findAll();
    }
}
