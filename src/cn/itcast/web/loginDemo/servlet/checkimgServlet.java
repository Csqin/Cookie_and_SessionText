package cn.itcast.web.loginDemo.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkimgServlet")
public class checkimgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width=200;
        int height=100;
        //1.创建图片对象（验证码图片对象）
        //得到背景为黑色的矩形
        BufferedImage bimg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //2.美化操作
        //2.1填充背景色，获取画笔对象
        Graphics graphics = bimg.getGraphics();
        //设置背景色
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0,0,width,height);
        //设置边框
        graphics.setColor(Color.BLUE);
        graphics.drawRect(0,0,width-1,height-1);
        //生成随机验证码
        String str="qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //字符缓冲区
        StringBuilder sb=new StringBuilder();
        Random ran=new Random();
        for (int i=1;i<=4;i++)
        {
            int in = ran.nextInt(str.length());
            char c = str.charAt(in);
            graphics.drawString(c+"", width/5*i,height/2 );
            sb.append(c);
        }
        //传递验证码到session
        String checkcode_session = sb.toString();
        request.getSession().setAttribute("checkcode_session",checkcode_session);
        //   设置干扰线
        graphics.setColor(Color.PINK);
        for (int i=1;i<=8;i++)
        {
            int x1 = ran.nextInt(width);
            int x2=ran.nextInt(width);
            int y1=ran.nextInt(height);
            int y2=ran.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);
        }

        //3.输出到页面上
        ImageIO.write(bimg, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
