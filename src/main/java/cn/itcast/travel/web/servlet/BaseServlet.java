package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /*
        获取路径
         */
        String requestURI = req.getRequestURI();
//        System.out.println(requestURI);

        /*
        切割路径获取方法名
         */
        String substring = requestURI.substring(requestURI.lastIndexOf("/") + 1);
//        System.out.println(substring);

        // this是UserServlet
        try {
            Method method = this.getClass().getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,res);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public void writeValue(Object obj, HttpServletResponse res) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        res.setContentType("application/json;charset=utf-8");
        mapper.writeValue(res.getWriter(), obj);
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(obj);
    }
}
