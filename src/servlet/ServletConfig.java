package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by john on 2016/8/21.
 */
@WebServlet(name = "ServletConfig", urlPatterns ={"/demo3"},
        initParams = {@WebInitParam(name ="encoding",value = "GBK")})
public class ServletConfig extends HttpServlet {
    private javax.servlet.ServletConfig config;
//    @Override
//    public void init(javax.servlet.ServletConfig config) throws ServletException {
//        this.config=config;
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String encoding = config.getInitParameter("encoding");
        String encoding1 = this.getServletConfig().getInitParameter("encoding");
        String encoding2 = this.getInitParameter("encoding");
    }
}
