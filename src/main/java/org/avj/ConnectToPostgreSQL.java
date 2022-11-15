package org.avj;

import java.sql.*;

public class ConnectToPostgreSQL {
    public static void main(String[] args) throws SQLException {
        final String user = "alex";
        final String pass = "qwerty";
        final String url = "jdbc:postgresql://localhost:5432/shop";

        try(Connection connection = DriverManager.getConnection(url,user,pass);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = (?);")){

            statement.setInt(1,9);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String name = resultSet.getString("login");
                String password = resultSet.getString(3);
                final int role = resultSet.getInt("role");
                final int id = resultSet.getInt("id");
                System.out.printf("id=%d, name=%s, pass=%s, role=%d\n",id,name,password,role);
            }
        }
    }
}
