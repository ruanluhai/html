package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.Service;
import cn.itcast.travel.service.impl.ServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        Service s = new ServiceImp();
//        User user = new User();
//        ResultInfo rl = new ResultInfo();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        User login = s.login(user);
//        System.out.println(user);
//
//        if (login == null) {
//            rl.setFlag(false);
//            rl.setErrorMsg("登录失败");
//        } else {
//            request.getSession().setAttribute("user",login);
//            rl.setFlag(true);
//
//        }
//
//        ObjectMapper om = new ObjectMapper();
//        String json = om.writeValueAsString(rl);
//
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                this.doPost(request, response);
    }
}
