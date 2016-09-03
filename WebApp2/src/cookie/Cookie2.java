package cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by john on 2016/8/24.
 */
@WebServlet("/c2")
public class Cookie2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        resp.setContentType("text/html;charset=UTF-8");
        //得到应用目录
        String contextPath = req.getContextPath();
        //得到向客户端输出的字符流
        PrintWriter out = resp.getWriter();

        Cookie[] cookies = req.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookies[i].getName().equals("lastAccessTime")) {
                //取出想要的coolie的time
                long l = Long.parseLong(cookies[i].getValue());
                out.write(new Date(l).toLocaleString()); //yyyy--mm--dd
                out.write(cookies[i].getPath()+"111");
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
