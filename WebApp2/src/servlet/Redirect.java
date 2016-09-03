package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by john on 2016/8/22.
 */
@WebServlet("/d6")
public class Redirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //告诉客户端,返回码是302需要重定向,重新访问另一个页面来
        System.out.println("我借钱");
        /*resp.setStatus(302);
        resp.setHeader("location", "/WebApp2/d4");*/
        //上面的两句话可以包装成为
        //请求重定向
        resp.sendRedirect("/d4");
        System.out.println("我回来了");
        String contextPath1 = req.getContextPath();
        System.out.println("contextPath"+contextPath1);

    }
}
