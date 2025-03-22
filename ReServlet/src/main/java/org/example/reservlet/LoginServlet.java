package org.example.reservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
        // 处理 GET 请求，跳转到登录页面
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        try {
            String email = request.getParameter("email");
            String userPassword = request.getParameter("password");

            String selectQuery = "SELECT * FROM usertable WHERE email = ? AND password = ?";
            selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, email);
            selectStatement.setString(2, userPassword);
            resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // 将用户信息存储在 request 中
                User u= new User(resultSet.getString("id"), resultSet.getString("name"), email);
                request.setAttribute("u",u);
                // 跳转到 userinfo.jsp
                request.getRequestDispatcher("userinfo.jsp").forward(request, response);
            } else {
                out.println("<html><body><h2>登录失败，请检查邮箱和密码！</h2></body></html>");
            }
        } catch (SQLException e) {
            out.println("<html><body><h2>数据库操作出错: " + e.getMessage() + "</h2></body></html>");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (selectStatement != null) selectStatement.close();
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

    // 定义 User 类用于存储用户信息

}