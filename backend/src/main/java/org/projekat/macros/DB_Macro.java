package org.projekat.macros;

import java.sql.*;
import java.util.Objects;
import java.util.function.Function;

public class DB_Macro {
    private static Connection conn = null;
    static {
        String url = "jdbc:postgresql://localhost:8080/baza";
        String user = "root";
        String pass = "password";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
        }catch (ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
