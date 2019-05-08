package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.CategoryServiceImp;
import cn.itcast.travel.service.Service;
import cn.itcast.travel.service.impl.ServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            ResultInfo rl = new ResultInfo();
            rl.setFlag(false);
            rl.setErrorMsg("验证码错误");
            ObjectMapper om = new ObjectMapper();
            String result = om.writeValueAsString(rl);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(result);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();

        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Service s = new ServiceImp();
        boolean regist = s.regist(user);

        ResultInfo rl = new ResultInfo();

        if (regist) {
            rl.setFlag(true);
        } else {
            rl.setFlag(false);
            rl.setErrorMsg("注册失败");
        }

        //序列化json
        ObjectMapper om = new ObjectMapper();
        String result = om.writeValueAsString(rl);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(result);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Service s = new ServiceImp();
        User user = new User();
        ResultInfo rl = new ResultInfo();
        user.setUsername(username);
        user.setPassword(password);

        User login = s.login(user);
        System.out.println(user);

        if (login == null) {
            rl.setFlag(false);
            rl.setErrorMsg("登录失败");
        } else {
            request.getSession().setAttribute("user",login);
            rl.setFlag(true);

        }

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(rl);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        om.writeValue(response.getOutputStream(), user);
    }


    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("/travel/login.html");
    }



}
