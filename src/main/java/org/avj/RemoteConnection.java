package org.avj;

import java.sql.*;

public class RemoteConnection {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11510871";
        String login = "sql11510871";
        String pass = "kccB5ECeCw";

        try(Connection con = DriverManager.getConnection(url,login,pass);
            PreparedStatement prepSt = con.prepareStatement("SELECT * FROM auto;")){
//            prepSt.setString();
            ResultSet rs = prepSt.executeQuery();
            while (rs.next()){
                String line1 = rs.getString(1);
                String line2 = rs.getString(2);
                String line3 = rs.getString(3);
                System.out.printf("| %s | %s | %s |",line1,line2,line3);
            }
        }
    }
}
