<%@ page import="org.example.reservlet.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="header.jsp" %>
    <meta charset="UTF-8">
    <title>用户信息</title>
</head>
<body>
<%User u= (User) request.getAttribute("u");%>
<h2>登录成功！欢迎 <%=u.getName()%></h2>
<p>用户 ID: <%=u.getId()%></p>
<p>邮箱: <%=u.getEmail()%></p>
</body>
<%@ include file="footer.jsp" %>
</html>