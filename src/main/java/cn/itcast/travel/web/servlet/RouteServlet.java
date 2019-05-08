package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        // 获取rname
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");


        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);

        }  else {
            currentPage = 1;
        }

        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);

        } else {
            pageSize = 5;
        }

        RouteService rs = new RouteServiceImp();
        PageBean pb = rs.pb(currentPage, pageSize, cid, rname);

        writeValue(pb, response);
//        String json = super.writeValueAsString(pb);
////        System.out.println(json);
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().print(json);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");

        RouteService rs = new RouteServiceImp();
        Route route = rs.findOne(rid);
        System.out.println(route);

        writeValue(route, response);

    }
}
