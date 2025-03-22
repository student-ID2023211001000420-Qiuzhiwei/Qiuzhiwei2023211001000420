package org.example.reservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            String driver = getServletContext().getInitParameter("driver");
            String url = getServletContext().getInitParameter("url");
            String username = getServletContext().getInitParameter("Username");
            String password = getServletContext().getInitParameter("Password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("数据库连接初始化失败", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理 GET 请求
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>不支持 GET 请求，请使用 POST 请求</h2></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PreparedStatement insertStatement = null;

        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String userPassword = request.getParameter("password");

            String insertQuery = "INSERT INTO usertable (id, name, email, password) VALUES (?, ?, ?, ?)";
            insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, id);
            insertStatement.setString(2, name);
            insertStatement.setString(3, email);
            insertStatement.setString(4, userPassword);
            insertStatement.executeUpdate();

            // 注册成功，重定向到登录界面
            response.sendRedirect("Login.jsp");

        } catch (SQLException e) {
            out.println("<html><body><h2>数据库操作出错: " + e.getMessage() + "</h2></body></html>");
        } finally {
            try {
                if (insertStatement != null) insertStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}