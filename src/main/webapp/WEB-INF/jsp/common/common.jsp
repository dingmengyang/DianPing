<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/24
  Time: 9:58
  存放公共资源脚本
--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    request.setAttribute("basePath", basePath);
%>