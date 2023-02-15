import dao.EmployeeDao;
import dao.iml.EmployeeDaoImpl;
import model.Employee;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        //  employeeDao.add(new Employee("Oba","Boba","W",14));
        employeeDao.findAll().forEach(s -> System.out.println(s));
        System.out.println("///////////////////////////////////////////////////////////////////");
        employeeDao.deleted(8);
        employeeDao.getById(1);

    }
}
