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
@WebServlet("/c1")
public class Cookie1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.getContextPath();

        PrintWriter out = resp.getWriter();
        //首先获取cookie的最后访问时间
        Cookie[] cookies = req.getCookies();//获得客户端的所有coolie
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            //判断当前的coolie'是否是你想要的coolie
            if (cookies[i].getName().equals("lastAccessTime")) {
                //取出想要的coolie的time
                long l = Long.parseLong(cookies[i].getValue());
                out.write(new Date(l).toLocaleString()); //yyyy--mm--dd
                out.write(cookies[i].getPath() + "");
                out.println("<html><body><a hrel='/c3'>clear</a></body></html>");
            }
        }
        //创建coolie,并且将创建的coolie保存到客户端上
        Cookie time = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
        //设置coolie的有效保存时间,单位是秒
        time.setMaxAge(-1); //-1表示当前cookie跟随当前应用,0表示立即删除该coolie,剩下的表示保存多长时间
        //设置coolie的保存路径
//        time.setPath(req.getContextPath());
        resp.addCookie(time);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
