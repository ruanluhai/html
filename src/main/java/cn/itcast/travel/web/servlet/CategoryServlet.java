package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.CategoryServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cate/*")
public class CategoryServlet extends BaseServlet {
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService c = new CategoryServiceImp();

        List<Category> all = c.findAll();
        writeValue(all, response);
//        System.out.println(all);


    }



}
