package com.luongpham.model;

import com.luongpham.common.AppConfig;

import java.sql.*;

public class MyConnection {

    /**
     * Các bước để connect tới db:
     * Bước 1: kiểm tra driver jdbc với hàm driverTest();
     * Bước 2: thực hiện connect với Db bằng hàm connectDB cần có url db, username, password
     * Bước 3: parepar và pareparUpdate dùng để lấy ra đối tượng dùng để thực thi các lệnh query trên db
     * Bước 4: đóng kết nối bằng hàm closeConnection();
     */

    public static Connection connection = null;

    //kiểm tra driver jdbc đã tồn tại trong project dedekeet nối
    public void driverTest() throws ClassNotFoundException {
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("JDBC Driver not found" + ex.getMessage());
        }
    }

    // hàm để kết nối database
    public Connection connectDB() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            driverTest();
            try {
                //cú pháp kết nối datanase
                connection = DriverManager.getConnection(AppConfig.URL_DATABASE, AppConfig.USERNAME, AppConfig.PASSWORD);
                if (connection != null) System.out.println("Connect DB successfully");
            } catch (Exception e) {
                throw new SQLException("Connect DB fail " + e.getMessage());
            }
        }
        return connection;
    }

    //hàm dùng để lấy ra prepareStatment để thực hiện các câu lệnh query
    public PreparedStatement prepare(String sql) {
        try {
            System.out.println(">> " + sql);
            //connection.prepareStatment trả về đối tượng PrepareStatment dùng để thực hiện query String sql
            return connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet.TYPE_SCROLL_SENSITIVE: cho phép con trỏ resultSet chạy từ bản ghi đầu đến cuối.
            //ResultSet.CONCUR_UPDATABLE: tạo ra một đuối tượng resultSet có thể được cập nhập.
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //dùng để lấy ra prepare cho các thao tác thêm sửa xóa query Update
    public PreparedStatement prepareUpdate(String sql) {
        try {
            System.out.println(">> " + sql);
            return connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            //Statement.RETURN_GENERATED_KEYS trả về id của bản ghi vừa insert thành công
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // đóng kết nối database
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection is closed");
        }
    }
}
