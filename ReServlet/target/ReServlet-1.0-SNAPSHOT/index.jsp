<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
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
    <h1><%= "Hello World!" %></h1>
    <br/>
    <a href="hello-servlet">Hello Servlet</a>
    <br/>
    <a href="Login.jsp">登录</a>
    <br/>
    <a href="register.jsp">注册</a>
</main>
<!-- 包含footer.jsp -->
<%@ include file="footer.jsp" %>
</body>
</html>