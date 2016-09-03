package cookie;

import entity.Book;
import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by john on 2016/9/2.
 */
@WebServlet("/showdetail")
public class ShowBookDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        resp.setContentType("text/html;charset=UTF-8");
        //得到应用目录
        String contextPath = req.getContextPath();
        //得到向客户端输出的字符流
        PrintWriter out = resp.getWriter();
        /**以上是头部**/

        //显示图书的详细信息
        //获得id
        String id = req.getParameter("id");

        Book book = DBUtils.getBookById(id);

        out.write(book.getName() + "<br/>");
        out.write(book.getAuthor() + "<br/>");
        out.write(book.getPrice() + "<br/>");

        //将当前浏览过的书的id写回到客户端上
        String historyBookId = organizedId(id, req);
        Cookie ck = new Cookie("historyBookId", historyBookId);
        ck.setPath("/");
        ck.setMaxAge(Integer.MAX_VALUE);
        resp.addCookie(ck);

    }

    /**
     * 包装需要返回的书的id
     *
     * @param id
     * @param req
     * @return
     */
    private String organizedId(String id, HttpServletRequest req) {
        //首先查找当前页面有没有cookie
        Cookie[] cookies = req.getCookies();//得到当前页面的所有cookie
        if (cookies == null) {
            return id;
        }
        //当有cookie的时候,判断是否有名字叫historycoolie的cookie
        Cookie historyBookId = null;
        for (int i = 0; i < cookies.length; i++) {
            if ("historyBookId".equals(cookies[i].getName())) {
                historyBookId = cookies[i];
            }
        }
        //如果没有historyBookId的Cookie
        if (historyBookId == null) {
            return id;
        }
        //如果存在名字是historyBookId的cookie那就进行解析
        String value = historyBookId.getValue();  //2-1-3
        String[] split = value.split("-");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(split));
        //如果当前的list的size小于3也就是cookie里边有1-2本书
        if (list.size() < 3) {// 1 / 2
            if (list.contains(id)) {
                list.remove(id);
            }
        } else {
            if (list.contains(id)) {
                list.remove(id);
            } else {
                list.removeLast();
            }
        }
        //不管什么情况都要在最前边加上最新id
        list.addFirst(id);
        //将这个list再重新包装成为新的string
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("-");
            }
            sb.append(list.get(i));
        }
        return sb.toString();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
