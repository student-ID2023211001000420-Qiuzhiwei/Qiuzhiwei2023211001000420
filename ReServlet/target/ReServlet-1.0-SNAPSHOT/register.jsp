<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册界面</title>
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
<%@ include file="header.jsp" %>
<main>
    <h2>用户注册</h2>
    <form action="register" method="post">
        <label for="name">姓名:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="email">邮箱:</label>
        <input type="text" id="email" name="email" required><br><br>

        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="注册">
    </form>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>