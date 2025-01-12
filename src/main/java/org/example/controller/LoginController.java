package org.example.controller;

import org.example.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    Connection connection;
    LoginController() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();;
    }
    public boolean login(String ID, String Pwd) throws SQLException {
        String SQL = "Select * From ACCOUNTS";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            String id = rst.getString("ID");
            String pwd = rst.getString("Pwd");
            if(id.equals(ID)&&pwd.equals(Pwd)){
                return true;
            }

        }
        return false;

    }
}
