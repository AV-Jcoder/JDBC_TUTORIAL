package org.avj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.avj.pojo.*;

public class Employee_CarDAO implements DAO<Employee_Car,Integer>{

    Connection connection;

    Employee_CarDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Employee_Car e) {
        try(PreparedStatement prSt = connection.prepareStatement(SQLQuery.CREATE.QUERY)){
        prSt.setString(1,e.getReg_num());
        prSt.setString(2,e.getBrand_model());
        prSt.setInt(3,e.getEmployee_id());
        prSt.executeQuery().next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Employee_Car> readAll() {
        List<Employee_Car> list = new LinkedList<>();
        try(PreparedStatement prSt = connection.prepareStatement(SQLQuery.READ_ALL.QUERY)){
            ResultSet rs = prSt.executeQuery();
            while (rs.next()){
                Employee_Car ec = new Employee_Car();
                ec.setCar_id(rs.getInt("car_id"));
                ec.setReg_num(rs.getString("reg_num"));
                ec.setReg_num(rs.getString("brand_model"));
                ec.setCar_id(rs.getInt("employee_id"));
                list.add(ec);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Employee_Car readById(Integer key) {
        Employee_Car ec = null;
        try(PreparedStatement prSt = connection.prepareStatement(SQLQuery.READ_BY_ID.QUERY)){
            prSt.setInt(1,key);
            ResultSet rs = prSt.executeQuery();
            while (rs.next()){
                ec = new Employee_Car();
                ec.setCar_id(rs.getInt("car_id"));
                ec.setReg_num(rs.getString("reg_num"));
                ec.setReg_num(rs.getString("brand_model"));
                ec.setCar_id(rs.getInt("employee_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ec;
    }

    @Override
    public boolean update(Employee_Car e) {
        boolean result = false;
        try(PreparedStatement prSt = connection.prepareStatement(SQLQuery.UPDATE.QUERY)){
            prSt.setString(1,e.getReg_num());
            prSt.setString(2,e.getBrand_model());
            prSt.setInt(3, e.getEmployee_id());
            prSt.setInt(4, e.getCar_id());
            result = prSt.executeQuery().next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void deleteById(Integer key) {
        try(PreparedStatement prSt = connection.prepareStatement(SQLQuery.DELETE.QUERY)){
            prSt.setInt(1,key);
            ResultSet rs = prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    enum SQLQuery {
        CREATE("INSERT INTO employee_car (reg_num, brand_model, employee_id) VALUES (?,?,?) RETURNING car_id;"),
        READ_ALL("SELECT * FROM employee_cars;"),
        READ_BY_ID("SELECT * FROM employee_cars AS ec WHERE ec.car_id=(?);"),
        UPDATE("UPDATE employee_car SET reg_num=(?), brand_model=(?), employee_id=(?) WHERE car_id=(?) RETURNING car_id;"),
        DELETE("DELETE FROM employee_car WHERE car_id = (?) RETURNING car_id;");
        final String QUERY;
        SQLQuery(String query){
            this.QUERY = query;
        }
    }
}