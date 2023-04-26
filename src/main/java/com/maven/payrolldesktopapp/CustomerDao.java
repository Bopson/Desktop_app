/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.payrolldesktopapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.JdbcBlackHole;
import rw.nub.customer.model.Customer;

/**
 *
 * @author hnjej
 */
public class CustomerDao {

    String url = "jdbc:postgresql://localhost:5433/NUbank";
    String uname = "postgres";
    String pwd = "root";
    Connection con ;

    public Integer registerCustomer(Customer customer) {
        String sql = "INSERT INTO customer2 VALUES(?,?,?,?,?)";

        try {
            con = DriverManager.getConnection(url, uname, pwd);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, customer.getCustomerId());
            pst.setString(2, customer.getFirstName());
            pst.setString(3, customer.getLastName());
            pst.setString(4, customer.getEmail());
            pst.setString(5, customer.getPhoneNumber());

            return pst.executeUpdate();
        } catch (Exception ex) {
            return 0;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void registerCustomer(List<Customer> customers) {
        try {
            String sql = "INSERT INTO customer2 VALUES(?,?,?,?,?)";
            Integer batchSize = 500;
            Integer counter = 0;
            
            con = DriverManager.getConnection(url, uname, pwd);
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement(sql);
            for (Customer customer : customers) {
                pst.setString(1, customer.getCustomerId());
                pst.setString(2, customer.getFirstName());
                pst.setString(3, customer.getLastName());
                pst.setString(4, customer.getEmail());
                pst.setString(5, customer.getPhoneNumber());
                
                pst.addBatch();
                counter++;
                
                if(counter % batchSize==0){
                    pst.executeBatch();
     
                }
            }
            pst.executeBatch();
            con.commit();
        } catch (Exception ex) {
            
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Integer updateCustomer(Customer customer) {

        return 0;
    }

    public Integer deleteCustomer(Customer customer) {
        try {

        } catch (Exception e) {
        }
        return 0;
    }

    public List<Customer> findCustomers() {
        try {

        } catch (Exception e) {
        }
        return new ArrayList<>();
    }
}
