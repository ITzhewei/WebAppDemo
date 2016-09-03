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
@WebServlet("/d5")
public class SetTimeRefresh extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setIntHeader("refresh", 2);//设置两秒刷新一次
        Random random = new Random();
        resp.getWriter().write(random.nextInt(100) + "");*/

        //注册成功后设置3秒后跳转到某个页面
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("refresh", "3;url=/d4");
        resp.getWriter().write("3秒后跳转到d4页面");
    }
}
