<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }

        main {
            flex: 1;
            padding: 20px;
        }

        footer {
            background-color: #f8f9fa;
            padding: 10px;
            text-align: center;
            position: sticky;
            bottom: 0;
        }
    </style>
</head>
<body>
<!-- 包含header.jsp -->
<%@ include file="header.jsp" %>
<main>
    <!-- 登录表单 -->
    <h2>用户登录</h2>
    <form action="LoginServlet" method="post">
        <label for="email">邮箱:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="登录">
    </form>
</main>
<!-- 包含footer.jsp -->
<%@ include file="footer.jsp" %>
</body>
</html>