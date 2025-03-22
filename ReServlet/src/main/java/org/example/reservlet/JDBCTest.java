package org.example.reservlet;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] arts)  {
        //用户信息和url

        String url = "jdbc:mysql://localhost:3306/TestJDBC";
        String username = "root";//数据库用户名
        String password = "qzw014036";//数据库密码
        try {
            //获取数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);
            //操作数据库
            Statement statement = connection.createStatement();//获取操作数据库的对象
            String sql="select * from Infos";
            ResultSet resultSet = statement.executeQuery(sql);//执行sql，获取结果集

            while(resultSet.next()){ //遍历结果集，取出数据
                String name=resultSet.getString("Name");
                String sex=resultSet.getString("Gender");
                String ID=resultSet.getString("Student_ID");
                //输出数据
                System.out.print("姓名："+name);
                System.out.print("   性别："+sex);
                System.out.print("   年龄"+ID);

                System.out.println();
            }
            //关闭结果集、数据库操作对象、数据库连接
            resultSet.close();
            statement.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}


