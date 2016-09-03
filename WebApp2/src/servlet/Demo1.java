package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by john on 2016/8/21.
 */
@WebServlet("/demo7")
public class Demo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //设置服务器使用的编码
//        resp.setCharacterEncoding("UTF-8");
//        //告诉客户端要使用什么编码
//        resp.setHeader("content-type", "text/html;charset=UTF-8");

        //上面的两行代码封装成了这一行
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("hello这是我给你返回的信息");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
