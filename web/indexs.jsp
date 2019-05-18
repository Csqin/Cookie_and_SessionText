<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: ShmilyCSQ
  Date: 2019/4/24
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>home</title>
  </head>
  <body>
<%
                    Cookie[] ck = request.getCookies();
                    boolean flag=false;//没有cookie为lastTime
                    //当Cookie数组中的值不为空并且长度大于0时进行遍历
                    if ( ck != null || ck.length>0)
                    {
                      for (Cookie cookie : ck) {
                        String name = cookie.getName();

                        if ("lastTime".equals(name))
                        {
                          //有：不是第一次访问
                          flag=true;//有lastTime的cookie
                          //存在lastTime的Cookie，获取其值输出到页面上
                          String value = cookie.getValue();
                          //输出前将value进行URL解码
                          String values = URLDecoder.decode(value, "utf-8");
%>


          <h1>欢迎回来，您上次访问时间为:<%=values %></h1>
<%


                          // //获取当前时间的字符串，重新设置Cookie的值，重新发送cookie

                          //获取到美国形式的日期
                          Date date=new Date();
                          //设置为中国形式的日期形式
                          SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                          String dates = sdf.format(date);
                          //特殊字符需要进行URL转码
                          String str_value = URLEncoder.encode(dates,"utf-8");
                          //重新赋值
                          cookie.setValue(str_value);
                          //设置Cookie的存活时间,1个月
                          cookie.setMaxAge(60*60*24*30);
                          //重新发送cookie
                          response.addCookie(cookie);
                          //找到目标cookie，下面的cookie就不用遍历啦
                          break;
                        }
                      }

                    }

                    if (ck==null || ck.length==0 || flag==false)
                    {
                      /**
                       * 2. 没有：是第一次访问
                       1. 响应数据：您好，欢迎您首次访问
                       2. 写回Cookie：lastTime=2018年6月10日11:50:01
                       */
                      //获取到美国形式的日期
                      Date date=new Date();
                      //设置为中国形式的日期形式
                      SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                      String dates = sdf.format(date);
                      //特殊字符需要进行URL转码
                      String str_value = URLEncoder.encode(dates,"utf-8");
                      Cookie cookie=new Cookie("lastTime",str_value);
                      //设置Cookie的存活时间,1个月
                      cookie.setMaxAge(60*60*24*30);
                      //发送cookie
                      response.addCookie(cookie);
%>

                      <h1>您好，欢迎您首次访问</h1>
<%

                    }
%>
  </body>
</html>
