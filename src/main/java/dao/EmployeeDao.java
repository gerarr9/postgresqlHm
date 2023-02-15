package dao;

import dao.jdbs.ConnectionManager;
import model.Employee;


import java.util.List;

public interface EmployeeDao {
  List<Employee> findAll();

  Employee add(Employee employee);
  void deleted(int id);
  Employee getById(int id);
}
