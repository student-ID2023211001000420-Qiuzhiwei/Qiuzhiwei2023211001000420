

import jakarta.servlet.ServletConfig;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet("/register")
public class Register extends HttpServlet {
    private Connection connection;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String driver = config.getServletContext().getInitParameter("driver");
        String url = config.getServletContext().getInitParameter("url");
        String username = config.getServletContext().getInitParameter("username");
        String password = config.getServletContext().getInitParameter("password");

        System.out.println("Driver: " + driver);
        System.out.println("URL: " + url);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("数据库连接初始化失败", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");

        // 验证 birthdate 是否为空
        if (birthdate == null || birthdate.isEmpty()) {
            out.println("<html><body><h2>Birthdate is required</h2></body></html>");
            return;
        }

        // 验证日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(birthdate, formatter);
        } catch (DateTimeParseException e) {
            out.println("<html><body><h2>Invalid birthdate format. Please use yyyy-MM-dd.</h2></body></html>");
            return;
        }

        try (PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO TestJDBC (username, password, email, gender, birthdate) VALUES (?,?,?,?,?)")) {

            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.setString(3, email);
            insertStatement.setString(4, gender);
            // 使用 LocalDate 转换为 java.sql.Date
            insertStatement.setDate(5, Date.valueOf(localDate));
            insertStatement.executeUpdate();

            // 查询并展示结果
            try (PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM register")) {
                ResultSet resultSet = selectStatement.executeQuery();
                out.println("<html><body>");
                out.println("<h2>用户列表</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>用户名</th><th>密码</th><th>邮箱</th><th>性别</th><th>出生日期</th></tr>");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String user = resultSet.getString("username");
                    String pass = resultSet.getString("password");
                    String emailValue = resultSet.getString("email");
                    String genderValue = resultSet.getString("gender");
                    String birthdateValue = resultSet.getString("birthdate");
                    out.println("<tr><td>" + id + "</td><td>" + user + "</td><td>" + pass + "</td><td>" + emailValue + "</td><td>" + genderValue + "</td><td>" + birthdateValue + "</td></tr>");
                }
                out.println("</table>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h2>数据库操作出错: " + e.getMessage() + "</h2></body></html>");
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