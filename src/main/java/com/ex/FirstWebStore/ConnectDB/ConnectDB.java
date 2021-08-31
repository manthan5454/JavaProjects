package com.ex.FirstWebStore.ConnectDB;

import java.sql.*;

public class ConnectDB {
    private String jdbcURL = "jdbc:mysql://localhost:3306/storedb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mysql";

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        return connection;
    }

    public void getDisconnect() throws SQLException {
//        Connection connection = null;
//        if (connection != null && !connection.isClosed()) {
//            connection.close();
//        }
    }
}




