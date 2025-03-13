import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // 1. 获取 ServletConfig 对象
        ServletConfig config = getServletConfig();

        // 2. 从 web.xml 中读取初始化参数
        String name = config.getInitParameter("name");
        String studentId = config.getInitParameter("studentId");

        // 3. 输出参数到浏览器
        out.println("<html><body>");
        out.println("<h1>Task 1-Get init parameters from web.xml：</h1>");
        out.println("<p>姓名: " + name + "</p>");
        out.println("<p>学号: " + studentId + "</p>");
        out.println("</body></html>");
       

    }
}
