<%--
  Created by IntelliJ IDEA.
  User: ShmilyCSQ
  Date: 2019/4/24
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <script>
        window.onload=function () {
            var img = document.getElementById("img");
                    img.onclick=function () {
                        var time = new Date().getTime();
                        img.src="/Csqin2/checkimgServlet?"+time;
                    }
        }
    </script>
</head>
<body>
<form action="/Csqin2/loginServlet" method="post">

用户名<input type="text"  name="username"><br>
密码<input type="text"  name="password"><br>
验证码<input type="text"  name="checkcode"  ><br>
<img id="img" src="/Csqin2/checkimgServlet" width="100px" height="40px"><br>
<button type="submit">提交</button><br>
<div><%=request.getSession().getAttribute("cc_error")%></div>
</form>
</body>
</html>
