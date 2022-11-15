package org.avj;

import java.sql.*;

public class ConnectToMySQL {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/alexdb";
        String login = "alex";
        String pass = "qwerty";

        // This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'.
        // The driver is automatically registered via the SPI and manual loading of
        // the driver class is generally unnecessary.
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        try(Connection con = DriverManager.getConnection(url,login,pass);
            PreparedStatement prepSt = con.prepareStatement("SELECT * FROM employee;")){
//            prepSt.setString();
            ResultSet rs = prepSt.executeQuery();
            while (rs.next()){
                String line1 = rs.getString("name");
//                String line2 = rs.getString(2);
//                String line3 = rs.getString(3);
//                String line4 = rs.getString(4);
            }

        }



    }
}
