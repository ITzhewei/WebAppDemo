package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by john on 2016/8/20.
 */
@WebServlet("/demo1")
public class ServletDemo1 implements Servlet {
    //这是生命周期的实例化方法
    public ServletDemo1() {
        System.out.println("************ServletDemo1执行了*****");
    }



    //生命周期的服务方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("************service*****");
    }

    @Override
    public void destroy() {
        System.out.println("************destroy*****");
    }


    //生命周期的初始化方法
    @Override
    public void init(javax.servlet.ServletConfig servletConfig) throws ServletException {
        System.out.println("************init*****");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }
}
