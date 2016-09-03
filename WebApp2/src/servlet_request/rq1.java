package servlet_request;

import entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by john on 2016/8/22.
 */
@WebServlet("/rq1")
public class rq1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得所有的请求消息头
       /* Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            System.out.println(element +":"+ req.getHeader(element));
        }*/
        //获得表单数据
        //根据表单中的name属性的名,来获得其对应的value的值
        puTong(req, resp);
        //利用框架技术来进行封装数据
        resp.setContentType("text;html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
//        Bean(req);
        //利用commonutils.jar
//        common(req);
        //利用inputStream
//        inputStream(req);
    }

    private void inputStream(HttpServletRequest req) throws IOException {
        ServletInputStream in = req.getInputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1) {
            String s = new String(b, 0, len);
            byte[] bytes = s.getBytes("UTF-8");
            System.out.println(new String(bytes));
        }
    }

    private void common(HttpServletRequest req) {
        try {
            User user = new User();
            System.out.println(user.toString());
            BeanUtils.populate(user, req.getParameterMap());
            System.out.println(user.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Bean(HttpServletRequest req) {
        try {
            User user = new User();
            System.out.println(user.toString());
            Map<String, String[]> map = req.getParameterMap();
            for (Map.Entry<String, String[]> m : map.entrySet()) {
                String name = m.getKey();
                String[] value = m.getValue();
                //创建属性描述器
                PropertyDescriptor pd = new PropertyDescriptor(name, User.class);
                //得到setter属性
                Method setter = pd.getWriteMethod();
                if (value.length == 1) {
                    setter.invoke(user, value[0]);
                } else {
                    setter.invoke(user, (Object) value);
                }
            }
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void puTong(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        String sex = req.getParameter("sex");
        String[] hobbies = req.getParameterValues("hobby");
        String city = req.getParameter("city");
        PrintWriter writer = resp.getWriter();
        username = new String(username.getBytes("iso-8859-1"), "UTF-8");
        System.out.println(username);
        writer.write(username);
        writer.write("<br/>");
        writer.write(pwd);
        writer.write("<br/>");
        writer.write(sex);
        writer.write("<br/>");
        for (String s : hobbies) {
            writer.write(s);
            System.out.println(s);
            writer.write("<br/>");
        }
        writer.write(city);
        writer.write("<br/>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
