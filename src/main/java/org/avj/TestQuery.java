package org.avj;

import org.avj.dao.DAO;
import org.avj.dao.EmployeeDAO;
import org.avj.pojo.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TestQuery {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try{connection = getMySQLConnection();
            connection.setAutoCommit(false);
            DAO<Employee,Integer> dao = new EmployeeDAO(connection);
            List<Employee> list = dao.readAll();
            for (Employee e: list) {
                System.out.println(e);
            }
            Employee emp  = dao.readById(3);
            emp.setSalary(26_001);
            connection.commit();
            boolean res = dao.update(emp);
            System.out.println(res);
        } catch(SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static Connection getMySQLConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("URL");
        String login = resource.getString("LOGIN");
        String pass = resource.getString("PASSWORD");
        Connection con = DriverManager.getConnection(url,login,pass);
        return con;
    }
}
