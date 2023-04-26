/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.payrolldesktopapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Dao {
      public static Connection getConnection() throws SQLException {
        //register driver
       

        //get connection
        String url = "jdbc:postgresql://localhost:5432/auca_db";
        String username = "postgres";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }
}
