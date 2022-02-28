package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
   
    public static Connection getConnection(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/sad_db", "root","");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return con;
    }
}