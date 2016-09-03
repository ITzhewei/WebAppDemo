package servlet;

import cn.dsna.util.images.ValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by john on 2016/8/21.
 */
@WebServlet("/d4")
public class Demo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        suijiYanzheng(resp);
        ValidateCode vc = new ValidateCode(110, 25, 4, 8);
        vc.write(resp.getOutputStream());
        System.out.println("来找我借钱啊");
    }

    private void suijiYanzheng(HttpServletResponse resp) throws IOException {
        int width = 110;
        int height = 30;
        //在内存中创建一个图像对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        //创建一个画笔
        Graphics graphics = img.getGraphics();
        //给图片添加背景颜色
        graphics.setColor(Color.PINK);
        graphics.fillRect(1, 1, width - 2, height - 2);
        //给边框一个颜色
        graphics.setColor(Color.blue);
        graphics.drawRect(1, 1, width - 1, height - 1);
        //设置文本样式
        graphics.setColor(Color.black);
        graphics.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 17));
        //给图片添加文本
        Random random = new Random();
        graphics.drawString(random.nextInt(10) + "", 20, 20);
        graphics.drawString(random.nextInt(10) + "", 40, 20);
        graphics.drawString(random.nextInt(10) + "", 60, 20);
        graphics.drawString(random.nextInt(10) + "", 80, 20);

        //给图片添加随机线来迷糊数字
        for (int i = 0; i < 8; i++) {
            graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
        //将图片以流的方式输出到客户端
        ImageIO.write(img, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
