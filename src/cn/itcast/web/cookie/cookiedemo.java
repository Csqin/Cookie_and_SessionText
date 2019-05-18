package cn.itcast.web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试服务器下不同项目的Cookie共享
 */
@SuppressWarnings("all")
@WebServlet("/cookiedemo")
public class cookiedemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("---------------------------------------");
        System.out.println("CookieText.........");
        Cookie[] cks = request.getCookies();
        if (cks!=null)
        {
            for (Cookie ck : cks) {
                String name = ck.getName();
                String value = ck.getValue();
                System.out.println(name+":"+value);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
