package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by john on 2016/8/21.
 */
@WebServlet(urlPatterns = "/d2", displayName = "美女图片", name = "美女图片")
public class Demo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置编码
        resp.setContentType("text/html;");
        //通过路径得到一个输入流
        String path = this.getServletContext().getRealPath("/WEB-INF/美女.jpg");
        FileInputStream fis = new FileInputStream(path);

        //得到要下载的文件以及文件的名字
        File file = new File(path);
        String name = file.getName();
        String[] split = name.split("\\.");
        System.out.println(split[0]);
        //告知客户端要进行文件下载
        name = URLEncoder.encode(name, "UTF-8");
        resp.setHeader("content-disposition", "attachment;filename=" + name);
        resp.setHeader("content-type", "image/jpeg");

        //创建字节输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        int len = 1;
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            outputStream.write(b, 0, len);
        }

    }
}
