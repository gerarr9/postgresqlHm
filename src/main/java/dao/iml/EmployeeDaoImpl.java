package dao.iml;

import dao.EmployeeDao;
import dao.jdbs.ConnectionManager;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final  String FIND_ALL ="SELECT * FROM employee";
    private static final  String INSERT ="INSERT INTO employee (first_name,last_name,gender,age) VALUES(?,?,?,?)";
    private  static  final  String DELETED ="DELETE FROM employee WHERE id = (?)";
    private  static  final  String getById ="SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id AND employee.id=(?)";

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()){
                Employee employee = new Employee(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee add(Employee employee) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleted(int id) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETED)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getById(int id) {
        Employee employee = new Employee();
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getById)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
               employee.setName(resultSet.getString("first_name"));
               employee.setLastName(resultSet.getString("last_name"));
               employee.setGender(resultSet.getString("gender"));
               employee.setAge(resultSet.getInt("age"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }


}
