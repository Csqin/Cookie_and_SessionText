package cn.itcast.web.loginDemo.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //判断验证码是否输入正确
        String checkcode_session = request.getSession().getAttribute("checkcode_session").toString();
        String checkcode = request.getParameter("checkcode").toString();
        String ck = checkcode.toUpperCase();
        String ck_session = checkcode_session.toUpperCase();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(ck);
        System.out.println(ck_session);
        if (ck_session.equals(ck))
        {

              if ("Csqin".equals(username) && "123".equals(password))
                {
                    request.getSession().setAttribute("username",username);
                    request.getRequestDispatcher("/succeessServlet").forward(request,response);
                }
              else
              {
                  request.getSession().setAttribute("cc_error","用户名或者密码错误");
                  request.getRequestDispatcher("/index.jsp").forward(request,response);
              }
        }
        else
        {
            request.getSession().setAttribute("cc_error","验证码错误");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
