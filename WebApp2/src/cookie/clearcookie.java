package cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by john on 2016/8/24.
 */
@WebServlet("/c3")
public class clearcookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        resp.setContentType("text/html;charset=UTF-8");
        //得到应用目录
        String contextPath = req.getContextPath();
        //得到向客户端输出的字符流
        PrintWriter out = resp.getWriter();
        //创建一个cookie对象
        Cookie time = new Cookie("lastAccessTime", "");
        //为了防止删除错coolie,应该设置一下该coolie的path
        time.setPath(contextPath);
        time.setMaxAge(0);//删除该coolie

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
