package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by john on 2016/8/21.
 */
@WebServlet(name = "ServletContext", urlPatterns = {"/demo4"},
        initParams = {@WebInitParam(name = "encoding", value = "GBK")})
public class ServletContext extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getKeyA();
    }

    private void getKeyA() throws IOException {
        String path = this.getServletContext().getRealPath("/WEB-INF/a.properties");
        //创建一个Properties对象
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        String key = properties.getProperty("key");
        System.out.println(key);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }
}
