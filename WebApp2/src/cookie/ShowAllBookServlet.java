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
import java.util.Map;

/**
 * Created by john on 2016/9/2.
 */
@WebServlet("/show")
public class ShowAllBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        resp.setContentType("text/html;charset=UTF-8");
        //得到应用目录
        String contextPath = req.getContextPath();
        //得到向客户端输出的字符流
        PrintWriter out = resp.getWriter();

        out.write("本网站有以下好书:<br/>");
        Map<String, Book> books = DBUtils.getBooks();
        for (Map.Entry<String, Book> book : books.entrySet()) {
            out.write("<a href='" + req.getContextPath() + "/showdetail?id=" + book.getKey() + "' target='_blank'>" + book.getValue().getName() + "</a>");
            out.write("<br/>");
        }

        out.write("<hr/>您最近浏览过的书有:<br/>");
        //通过coolie来拿到你浏览过的书籍
        Cookie[] cookies = req.getCookies();
        Cookie historyBookId = null;
        for (int i = 0; i < cookies.length; i++) {
            if ("historyBookId".equals(cookies[i].getName())) {
                historyBookId = cookies[i];
            }
//            out.write(cookies[i].getName()+cookies[i].getValue() + "<br/>");
        }
        if (historyBookId == null) {
            out.write("您最近没有浏览过书籍");
        } else {
            String[] split = historyBookId.getValue().split("-");
            for (String s : split) {
                out.write(DBUtils.getBookById(s).getName() + "<br/>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
